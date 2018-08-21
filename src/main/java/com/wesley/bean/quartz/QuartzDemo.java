package com.wesley.bean.quartz;

public class QuartzDemo {
/**	核心类 
	（1）核心类 
	QuartzSchedulerThread ：负责执行向QuartzScheduler注册的触发Trigger的工作的线程。 
	ThreadPool：Scheduler使用一个线程池作为任务运行的基础设施，任务通过共享线程池中的线程提供运行效率。 
	QuartzSchedulerResources：包含创建QuartzScheduler实例所需的所有资源（JobStore，ThreadPool等）。 
	SchedulerFactory ：提供用于获取调度程序实例的客户端可用句柄的机制。 
	JobStore： 通过类实现的接口，这些类要为org.quartz.core.QuartzScheduler的使用提供一个org.quartz.Job和org.quartz.Trigger存储机制。作业和触发器的存储应该以其名称和组的组合为唯一性。 
	QuartzScheduler ：这是Quartz的核心，它是org.quartz.Scheduler接口的间接实现，包含调度org.quartz.Jobs，注册org.quartz.JobListener实例等的方法。 
	Scheduler ：这是Quartz Scheduler的主要接口，代表一个独立运行容器。调度程序维护JobDetails和触发器的注册表。 一旦注册，调度程序负责执行作业，当他们的相关联的触发器触发（当他们的预定时间到达时）。 
	Trigger ：具有所有触发器通用属性的基本接口，描述了job执行的时间出发规则。 - 使用TriggerBuilder实例化实际触发器。 
	JobDetail ：传递给定作业实例的详细信息属性。 JobDetails将使用JobBuilder创建/定义。 
	Job：要由表示要执行的“作业”的类实现的接口。只有一个方法 void execute(jobExecutionContext context) 
	(jobExecutionContext 提供调度上下文各种信息，运行时数据保存在jobDataMap中) 
	Job有个子接口StatefulJob ,代表有状态任务。 
	有状态任务不可并发，前次任务没有执行完，后面任务处于阻塞等到。
	了解Quartz体系结构
	Quartz对任务调度的领域问题进行了高度的抽象，提出了调度器、任务和触发器这3个核心的概念，并在org.quartz通过接口和类对重要的这些核心概念进行描述：
	●Job：是一个接口，只有一个方法void execute(JobExecutionContext context)，开发者实现该接口定义运行任务，JobExecutionContext类提供了调度上下文的各种信息。Job运行时的信息保存在JobDataMap实例中；
	●JobDetail：Quartz在每次执行Job时，都重新创建一个Job实例，所以它不直接接受一个Job的实例，相反它接收一个Job实现类，以便运行时通过newInstance()的反射机制实例化Job。因此需要通过一个类来描述Job的实现类及其它相关的静态信息，如Job名字、描述、关联监听器等信息，JobDetail承担了这一角色。
	通过该类的构造函数可以更具体地了解它的功用：JobDetail(java.lang.String name, java.lang.String group, java.lang.Class jobClass)，该构造函数要求指定Job的实现类，以及任务在Scheduler中的组名和Job名称；
	●Trigger：是一个类，描述触发Job执行的时间触发规则。主要有SimpleTrigger和CronTrigger这两个子类。当仅需触发一次或者以固定时间间隔周期执行，SimpleTrigger是最适合的选择；而CronTrigger则可以通过Cron表达式定义出各种复杂时间规则的调度方案：如每早晨9:00执行，周一、周三、周五下午5:00执行等；
	●Calendar：org.quartz.Calendar和java.util.Calendar不同，它是一些日历特定时间点的集合（可以简单地将org.quartz.Calendar看作java.util.Calendar的集合——java.util.Calendar代表一个日历时间点，无特殊说明后面的Calendar即指org.quartz.Calendar）。一个Trigger可以和多个Calendar关联，以便排除或包含某些时间点。
	假设，我们安排每周星期一早上10:00执行任务，但是如果碰到法定的节日，任务则不执行，这时就需要在Trigger触发机制的基础上使用Calendar进行定点排除。针对不同时间段类型，Quartz在org.quartz.impl.calendar包下提供了若干个Calendar的实现类，如AnnualCalendar、MonthlyCalendar、WeeklyCalendar分别针对每年、每月和每周进行定义；
	●Scheduler：代表一个Quartz的独立运行容器，Trigger和JobDetail可以注册到Scheduler中，两者在Scheduler中拥有各自的组及名称，组及名称是Scheduler查找定位容器中某一对象的依据，Trigger的组及名称必须唯一，JobDetail的组和名称也必须唯一（但可以和Trigger的组和名称相同，因为它们是不同类型的）。Scheduler定义了多个接口方法，允许外部通过组及名称访问和控制容器中Trigger和JobDetail。
	Scheduler可以将Trigger绑定到某一JobDetail中，这样当Trigger触发时，对应的Job就被执行。一个Job可以对应多个Trigger，但一个Trigger只能对应一个Job。可以通过SchedulerFactory创建一个Scheduler实例。Scheduler拥有一个SchedulerContext，它类似于ServletContext，保存着Scheduler上下文信息，Job和Trigger都可以访问SchedulerContext内的信息。SchedulerContext内部通过一个Map，以键值对的方式维护这些上下文数据，SchedulerContext为保存和获取数据提供了多个put()和getXxx()的方法。可以通过Scheduler# getContext()获取对应的SchedulerContext实例；
	●ThreadPool：Scheduler使用一个线程池作为任务运行的基础设施，任务通过共享线程池中的线程提高运行效率。
	Job有一个StatefulJob子接口，代表有状态的任务，该接口是一个没有方法的标签接口，其目的是让Quartz知道任务的类型，以便采用不同的执行方案。无状态任务在执行时拥有自己的JobDataMap拷贝，对JobDataMap的更改不会影响下次的执行。而有状态任务共享共享同一个JobDataMap实例，每次任务执行对JobDataMap所做的更改会保存下来，后面的执行可以看到这个更改，也即每次执行任务后都会对后面的执行发生影响。
	正因为这个原因，无状态的Job可以并发执行，而有状态的StatefulJob不能并发执行，这意味着如果前次的StatefulJob还没有执行完毕，下一次的任务将阻塞等待，直到前次任务执行完毕。有状态任务比无状态任务需要考虑更多的因素，程序往往拥有更高的复杂度，因此除非必要，应该尽量使用无状态的Job。
	如果Quartz使用了数据库持久化任务调度信息，无状态的JobDataMap仅会在Scheduler注册任务时保持一次，而有状态任务对应的JobDataMap在每次执行任务后都会进行保存。
	Trigger自身也可以拥有一个JobDataMap，其关联的Job可以通过JobExecutionContext#getTrigger().getJobDataMap()获取Trigger中的JobDataMap。不管是有状态还是无状态的任务，在任务执行期间对Trigger的JobDataMap所做的更改都不会进行持久，也即不会对下次的执行产生影响。
	Quartz拥有完善的事件和监听体系，大部分组件都拥有事件，如任务执行前事件、任务执行后事件、触发器触发前事件、触发后事件、调度器开始事件、关闭事件等等，可以注册相应的监听器处理感兴趣的事件。
	图1描述了Scheduler的内部组件结构，SchedulerContext提供Scheduler全局可见的上下文信息，每一个任务都对应一个JobDataMap，虚线表达的JobDataMap表示对应有状态的任务：
	图1 Scheduler结构图
	一个Scheduler可以拥有多个Triger组和多个JobDetail组，注册Trigger和JobDetail时，如果不显式指定所属的组，Scheduler将放入到默认组中，默认组的组名为Scheduler.DEFAULT_GROUP。组名和名称组成了对象的全名，同一类型对象的全名不能相同。
	Scheduler本身就是一个容器，它维护着Quartz的各种组件并实施调度的规则。Scheduler还拥有一个线程池，线程池为任务提供执行线程——这比执行任务时简单地创建一个新线程要拥有更高的效率，同时通过共享节约资源的占用。通过线程池组件的支持，对于繁忙度高、压力大的任务调度，Quartz将可以提供良好的伸缩性。
	提示： Quartz完整下载包examples目录下拥有10多个实例，它们是快速掌握Quartz应用很好的实例。
	使用SimpleTrigger
	SimpleTrigger拥有多个重载的构造函数，用以在不同场合下构造出对应的实例：
	●SimpleTrigger(String name, String group)：通过该构造函数指定Trigger所属组和名称；
	●SimpleTrigger(String name, String group, Date startTime)：除指定Trigger所属组和名称外，还可以指定触发的开发时间；
	●SimpleTrigger(String name, String group, Date startTime, Date endTime, int repeatCount, long repeatInterval)：除指定以上信息外，还可以指定结束时间、重复执行次数、时间间隔等参数；
	●SimpleTrigger(String name, String group, String jobName, String jobGroup, Date startTime, Date endTime, int repeatCount, long repeatInterval)：这是最复杂的一个构造函数，在指定触发参数的同时，还通过jobGroup和jobName，让该Trigger和Scheduler中的某个任务关联起来。
	通过实现 org.quartz..Job 接口，可以使 Java 类化身为可调度的任务。代码清单1提供了 Quartz 任务的一个示例：
	代码清单1 SimpleJob：简单的Job实现类
	*
	*
	*/
}
