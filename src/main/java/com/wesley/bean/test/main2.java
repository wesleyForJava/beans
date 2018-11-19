package com.wesley.bean.test;

public class main2 {
	public static void main(String[] args) {
		
		System.out.println(3 << 5);
		System.out.println(4 << 30);
		long start=System.currentTimeMillis();
		System.out.println(start);
            String type="2";
            if(type.equals("1")) {
            	System.out.println("1");
            }else if(type.equals("2")) {
            	System.out.println("2");
            }else {
            	System.out.print("3");
            }
            System.out.println(System.currentTimeMillis());
            long total=System.currentTimeMillis()-start;
            System.out.println(total);
     /*       switch (type) {
			case "1":
				System.out.print("1");
				break;
			case "2":
				System.out.print("2");
				break;
			default:
				break;
			}*/
	}
}
