package com.wesley.bean.pattern;

import java.util.function.Consumer;


public class OnlineBankingLambda {

    public static void main(String[] args) {
        new OnlineBankingLambda().processCustomer(1337, (Customer c) -> System.out.println("Hello!"+c.getName()));
    }

    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }

    // dummy Customer class
    static private class Customer {
    	private String name;

		public String getName() {
			return name;
		}
		public Customer(String name) {
			this.name=name;
		}
    	
    }
    // dummy Database class
    static private class Database{
         static	String name="jack";
        static Customer getCustomerWithId(int id){ return new Customer(name);}
    }
}