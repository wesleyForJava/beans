package com.wesley.bean.array;

import java.util.HashMap;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

import com.google.gson.JsonObject;
import com.wesley.bean.pojo.User;

public class Person {

		private Optional<Car> car;

		public int age;
		public Optional<Car> getCar() { return car; }
 
		
		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}


		public static class Car {
		private Optional<Insurance> insurance;
		public Optional<Insurance> getInsurance() { return insurance; }
		}
		public static class Insurance {
		private String name;
		public String getName() { return name; }
		}
	public static String getCarInsuranceName(Person person) {
		Optional<Car> optCar = Optional.empty();
		System.out.println(optCar);
//		Car car=new Car;
//		Optional<Car> optCar1= Optional.of(car);
		//System.out.println(optCar1);
		return person.getCar().get().getInsurance().get().getName();
	}
//	public static String getCarInsuranceName(Person person) {
//		if (person != null) {
//			Car car = person.getCar();
//			if (car != null) {
//				Insurance insurance = car.getInsurance();
//				if (insurance != null) {
//					return insurance.getName();
//				}
//			}
//		}
//		return "Unknown";
//	}
//	public static String getCarInsuranceName(Person person) {
//		if (person == null) {
//			return "Unknown";
//		}
//		Car car = person.getCar();
//		if (car == null) {
//			return "Unknown";
//		}
//		Insurance insurance = car.getInsurance();
//		if (insurance == null) {
//			return "Unknown";
//		}
//		return insurance.getName();
//	}
	
	public static void main(String[] args) {
	//	def carInsuranceName = person?.car?.insurance?.name
//		String carInsuranceName = getCarInsuranceName(new Person());
//		System.out.println(carInsuranceName);
//		Insurance insurance=new Insurance();
//		Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
//		Optional<String> name = optInsurance.map(Insurance::getName);
//		System.out.println(name.get());
		//Person person=new Person();
//		Optional<Person> optPerson = Optional.of(person);
//		Optional<Person> optPerson=Optional.empty();
//		Optional<String> map = optPerson.flatMap(Person::getCar).flatMap(Car::getInsurance).map(Insurance::getName);
//		 map.ifPresent((Consumer<? super String>) getConsumer());
//		//System.out.println(present);
//		String orElse = map.orElse("null Value");	
		//System.out.println(orElse);
//		HashMap<String, String> map=new HashMap<String, String>();
//		map.put("2", "2");
//		Optional<Object> value = Optional.ofNullable(map.get("key"));
//		System.out.println(value.get());
		Properties props = new Properties();
		props.setProperty("a", "5");
		props.setProperty("b", "true");
		props.setProperty("c", "-3");
		Object object = props.get("a");
		System.out.println(object.toString());
		
//		ExecutorService executor = Executors.newCachedThreadPool();
//		Future<Double> future = executor.submit(new Callable<Double>() {
//		public Double call() {
//		return doSomeLongComputation();
//		}});
//		doSomethingElse();
//		try {
//		Double result = future.get(1, TimeUnit.SECONDS);
//		} catch (ExecutionException ee) {
//		// 计算抛出一个异常
//		} catch (InterruptedException ie) {
//		// 当前线程在等待过程中被中断
//		} catch (TimeoutException te) {
//		// 在Future对象完成之前超过已过期
//		}
		
//		  首先，你会学到如何为你的客户提供异步API（如果你拥有一间在线商店的话，这是非常
//		  有帮助的）。
//		  其次，你会掌握如何让你使用了同步API的代码变为非阻塞代码。你会了解如何使用流水
//		  线将两个接续的异步操作合并为一个异步计算操作。这种情况肯定会出现，比如，在线
//		  商店返回了你想要购买商品的原始价格，并附带着一个折扣代码——最终，要计算出该
//		  商品的实际价格，你不得不访问第二个远程折扣服务，查询该折扣代码对应的折扣比率。
//		  你还会学到如何以响应式的方式处理异步操作的完成事件，以及随着各个商店返回它的
//		  商品价格，最佳价格查询器如何持续地更新每种商品的最佳推荐，而不是等待所有的商
//		  店都返回他们各自的价格（这种方式存在着一定的风险，一旦某家商店的服务中断，用
//		  户可能遭遇白屏）。
		
		
	}
	
	public static Consumer<? super User> getConsumer(){
		return User::getEmail;
	}

	public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
		if (person.isPresent() && car.isPresent()) {
			return Optional.of(findCheapestInsurance(person.get(), car.get()));
		} else {
			return Optional.empty();
		}
	}
	public Insurance findCheapestInsurance(Person person, Car car) {
		// 不同的保险公司提供的查询服务
		// 对比所有数据
		return null;
		}
	
	public Optional<Insurance> nullSafeFindCheapestInsurances(Optional<Person> person, Optional<Car> car) {
	//	return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
//		return person.flatMap(e -> e.getCar()).flatMap(car->car.getInsurance())
		return person.flatMap(e->car.map(c->findCheapestInsurance(e, c)));
		
//		这段代码中，你对第一个 Optional 对象调用 flatMap 方法，如果它是个空值，传递给它
//		的Lambda表达式不会执行，这次调用会直接返回一个空的 Optional 对象。反之，如果 person
//		对象存在，这次调用就会将其作为函数 Function 的输入，并按照与 flatMap 方法的约定返回
//		一个 Optional<Insurance> 对象。这个函数的函数体会对第二个 Optional 对象执行 map 操
//		作，如果第二个对象不包含 car ，函数 Function 就返回一个空的 Optional 对象，整个
//		nullSafeFindCheapestInsuranc 方法的返回值也是一个空的 Optional 对象。最后，如果
//		person 和 car 对象都存在，作为参数传递给 map 方法的Lambda表达式能够使用这两个值安全
//		地调用原始的 findCheapestInsurance 方法，完成期望的操作。
	}
	
//	Insurance insurance = ...;
//	if(insurance != null && "CambridgeInsurance".equals(insurance.getName())){
//	System.out.println("ok");
//	}
//	Optional<Insurance> optInsurance = ...;
//	optInsurance.filter(insurance ->
//	"CambridgeInsurance".equals(insurance.getName()))
//	.ifPresent(x -> System.out.println("ok"));
//	找出年龄大于或者等于 minAge 参数的 Person 所对应的保险公司列表。
	public String getCarInsuranceName(Optional<Person> person, int minAge) {
		return person.filter(p->p.getAge()>=minAge).flatMap(p->p.getCar()).flatMap(c->c.getInsurance()).map(Insurance::getName).orElse("null");
   }
	
	public static Optional<Integer> stringToInt(String s) {
		try {
			return Optional.of(Integer.parseInt(s));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}
	

	
	public int readDuration(Properties props, String name) {
		String value = props.getProperty(name);
		if (value != null) {
			try {
				int i = Integer.parseInt(value);
				if (i > 0) {
					return i;
				}
			} catch (NumberFormatException nfe) {
			}
		}
		return 0;
	}
	
	public int readDurations(Properties props, String name) {
        Optional<String> ofNullable = Optional.ofNullable(props.getProperty(name));
        Optional<Integer> flatMap = ofNullable.flatMap(Person::stringToInt);
        Optional<Integer> filter = flatMap.filter(i -> i > 0);
		Integer orElse = filter.orElse(0);
		return orElse;
		}
	
}
