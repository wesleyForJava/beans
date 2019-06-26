package com.wesley.bean.pattern;

import java.util.function.Consumer;
//让我们从一个例子着手，看看这个模式是如何工作的。
//假设你需要编写一个简单的在线银行应用。
//通常，用户需要输入一个用户账户，之后应用才能从银行的数据库中得到用户的详细信息，
//最终完成一些让用户满意的操作。
// 不同分行的在线银行应用让客户满意的方式可能还略有不同，
//比如给客户的账户发放红利，或者仅仅是少发送一些推广文件。你可能通过下面的抽象类方式来
//实现在线银行应用：
//processCustomer 方法搭建了在线银行算法的框架：获取客户提供的ID，然后提供服务让
//用户满意。不同的支行可以通过继承 OnlineBanking 类，对该方法提供差异化的实现。
//使用Lambda表达式
//使用你偏爱的Lambda表达式同样也可以解决这些问题（创建算法框架，让具体的实现插入
//某些部分）。你想要插入的不同算法组件可以通过Lambda表达式或者方法引用的方式实现。
//这里我们向 processCustomer 方法引入了第二个参数，它是一个 Consumer<Customer> 类
//型的参数，与前文定义的 makeCustomerHappy 的特征保持一致：
//现在，你可以很方便地通过传递Lambda表达式，直接插入不同的行为，不再需要继承
//OnlineBanking 类了：
//new OnlineBankingLambda().processCustomer(1337, (Customer c) ->
//System.out.println("Hello " + c.getName());
//这是又一个例子，佐证了Lamba表达式能帮助你解决设计模式与生俱来的设计僵化问题。
/**
 * 验证模板方法
 * 在线银行: 基础操作 + 让客户满意的优化
 * 不同的支行可以继承OnlineBanking对优化提供差异实现
 */
abstract class OnlineBanking {
		public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
			Customer c = Database.getCustomerWithId(id);
			makeCustomerHappy.accept(c);
		}

    public void processCustomer(int id){
        Customer c = Database.getCustomerWithId(id);
        c.setName("jack");
        makeCustomerHappy(c);
    }
    abstract void makeCustomerHappy(Customer c);


    // dummy Customer class
    static private class Customer {
    	private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
    	
    }
    // dummy Datbase class
    static private class Database{
        static Customer getCustomerWithId(int id){ return new Customer();}
    }
    
    public static void main(String[] args) {
    	//new OnlineBankingLambda().processCustomer(1337, (Customer c) -> System.out.println("Hello " + c.getName());
    //	new OnlineBanking().processCustomer(id);
    	new good().processCustomer(12);
    }
    
   static class good extends OnlineBanking{
		@Override
		void makeCustomerHappy(Customer c) {
			System.out.println("hello!,"+c.getName());
		}
    	
    }
    
}