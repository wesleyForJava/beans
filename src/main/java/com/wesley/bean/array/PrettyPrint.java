package com.wesley.bean.array;

import java.util.Arrays;
import java.util.List;

import com.wesley.bean.array.FilteringApples.Apple;

public class PrettyPrint{
	
//	测验2.1：编写灵活的 prettyPrintApple 方法
//	编写一个 prettyPrintApple 方法，它接受一个 Apple 的 List ，并可以对它参数化，以
//	多种方式根据苹果生成一个 String 输出（有点儿像多个可定制的 toString 方法）。例如，你
//	可 以 告 诉 prettyPrintApple 方 法 ， 只 打 印 每 个 苹 果 的 重 量 。 此 外 ， 你 可 以 让
//	prettyPrintApple 方法分别打印每个苹果，然后说明它是重的还是轻的。解决方案和我们
//	前面讨论的筛选的例子类似。为了帮你上手，我们提供了 prettyPrintApple 方法的一个粗
//	略的框架：
	public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter){
	for(Apple apple: inventory) {
	String output = formatter.accept(apple);
	System.out.println(output);
	}
	}
//	答案如下。
//	首先，你需要一种表示接受 Apple 并返回一个格式 String 值的方法。前面我们在编写
//	ApplePredicate 接口的时候，写过类似的东西：
	public interface AppleFormatter{
	  String accept(Apple a);
	}
//	现在你就可以通过实现 AppleFormatter 方法，来表示多种格式行为了：
	public static class AppleFancyFormatter implements AppleFormatter{
	public String accept(Apple apple){
	String characteristic = apple.getWeight() > 150 ? "heavy" :
	"light";
	return "A " + characteristic +
	" " + apple.getColor() +" apple";
	}
	}
	public static class AppleSimpleFormatter implements AppleFormatter{
	public String accept(Apple apple){
	return "An apple of " + apple.getWeight() + "g";
	}
	}
//	最后，你需要告诉 prettyPrintApple 方法接受 AppleFormatter 对象，并在内部使用
//	它们。你可以给 prettyPrintApple 加上一个参数：
//	public static void prettyPrintApple(List<Apple> inventory,
//	AppleFormatter formatter){
//	for(Apple apple: inventory){
//	String output = formatter.accept(apple);
//	System.out.println(output);
//	}
//	}
//	搞定啦！现在你就可以给 prettyPrintApple 方法传递多种行为了。为此，你首先要实
//	例化 AppleFormatter 的实现，然后把它们作为参数传给 prettyPrintApple ：
//	prettyPrintApple(inventory, new AppleFancyFormatter());
//	这将产生一个类似于下面的输出：
//	A light green apple
//	A heavy red apple
//	…
//	或者试试这个：
//	prettyPrintApple(inventory, new AppleSimpleFormatter());
//	这将产生一个类似于下面的输出：
//	An apple of 80g
//	An apple of 155g
//	…
//	你已经看到，可以把行为抽象出来，让你的代码适应需求的变化，但这个过程很啰嗦，因为
//	你需要声明很多只要实例化一次的类。让我们来看看可以怎样改进。
	
	public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                   "color='" + color + '\'' +
                   ", weight=" + weight +
                   '}';
        }
    }
	public static void main(String[] args) {
		List<Apple> inventory = Arrays.asList(new Apple(80,"green"), new Apple(155, "green"), new Apple(120, "red"));	
		prettyPrintApple(inventory, new AppleFormatter() {
			
			@Override
			public String accept(Apple a) {
				return a.getColor()+"颜色";
			}
		});
	}
}
