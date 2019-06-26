package com.wesley.bean.emun;

import com.rabbitmq.client.AMQP.Basic.Get;

public  enum Four {
    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);  
    // 成员变量  
    private String name;  
    private int index;  
    // 构造方法  
    private Four(String name, int index) {  
        this.name = name;  
        this.index = index;  
    }  
    //覆盖方法  
    @Override  
    public String toString() {  
        return this.index+"_"+this.name;  
   } 
    public static void main(String[] args) {
		System.out.println(Four.RED.index);
		 String string = get("2");
		 System.out.println(string);
	}
    
    public static String get(String index) {
    	Four[] values = values();
    	Four four = values[Integer.valueOf(index)-1];
		return four.name;
    }
    
}
