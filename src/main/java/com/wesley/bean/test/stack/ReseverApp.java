package com.wesley.bean.test.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReseverApp {
    public static void main(String []args) throws IOException{      //函数内含有IO输入流，不处理异常就要向上抛异常
       String input,output;
        while(true){            //输入多组数据
            System.out.println("Enter a String ");
            System.out.flush();         //清空缓存区
            input=getString();          //调用输入流方法↓
            if(input.equals("")) break;
            Reverse rv=new Reverse(input);
            output=rv.doRev();
            System.out.println(output);
        }
/*        StringBuilder sb=new StringBuilder("中华人民共和国");
        System.out.println(sb.reverse());*/
    }
    public static String getString ()throws IOException{        //输入流方法
        InputStreamReader isr=new InputStreamReader(System.in);     //用InputStreamReader声明接收器，从System.in（即控制台)输入
        BufferedReader br=new BufferedReader(isr);      //用BufferedReader包装InputStreamReader
        String s=br.readLine();         //调用BufferedReader的readLine()方法进行读取字符串
        return s;
    }
}
