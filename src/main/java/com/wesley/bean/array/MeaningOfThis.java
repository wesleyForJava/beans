package com.wesley.bean.array;

import java.util.function.DoubleUnaryOperator;

public class MeaningOfThis {
	public final int value = 4;
	public void doIt()
	{
		int value = 6;
		Runnable r = new Runnable(){
			public final int value = 5;
			public void run(){
				int value = 10;
				System.out.println(this.value);
			}
		};
		r.run(); 
	}
	public static void main(String...args)
	{       
		MeaningOfThis m = new MeaningOfThis();
		m.doIt(); // ???   
		double converter = converter(1, 2, 3);
		System.out.println(converter);
		DoubleUnaryOperator curriedConverter = curriedConverter(2, 3);
		double applyAsDouble = curriedConverter.applyAsDouble(1);
		System.out.println(applyAsDouble);
		
	}
	static double converter(double x, double f, double b) {
		return x * f + b;
	}
	
	static DoubleUnaryOperator curriedConverter(double f, double b) {
		return (double x) -> x * f + b;
	}
	
	 static class TrainJourney {
		public int price;
		public TrainJourney onward;

		public TrainJourney(int p, TrainJourney t) {
			price = p;
			onward = t;
		}
	}

	public static TrainJourney append(TrainJourney a, TrainJourney b) {
		return a == null ? b : new TrainJourney(a.price, append(a.onward, b));
	}
	
	static class Tree {
		private String key;
		private int val;
		private Tree left, right;

		public Tree(String k, int v, Tree l, Tree r) {
			key = k;
			val = v;
			left = l;
			right = r;
		}
	}

 static	class TreeProcessor {
		public static int lookup(String k, int defaultval, Tree t) {
			if (t == null)
				return defaultval;
			if (k.equals(t.key))
				return t.val;
			return lookup(k, defaultval, k.compareTo(t.key) < 0 ? t.left : t.right);
		}
		// 处理Tree的其他方法
	}
 
		 public static void update(String k, int newval, Tree t) {
			 if (t == null) { /* 应增加一个新的节点 */ }
			 else if (k.equals(t.key)) t.val = newval;
			 else update(k, newval, k.compareTo(t.key) < 0 ? t.left : t.right);
			 }
		 
				 
			public static Tree updates(String k, int newval, Tree t) {
				if (t == null)
					t = new Tree(k, newval, null, null);
				else if (k.equals(t.key))
					t.val = newval;
				else if (k.compareTo(t.key) < 0)
					t.left = updates(k, newval, t.left);
				else
					t.right = updates(k, newval, t.right);
				return t;
			}
			
			public static Tree fupdate(String k, int newval, Tree t) {
				return (t == null) ? new Tree(k, newval, null, null) : k.equals(t.key) ? new Tree(k, newval, t.left, t.right) :k.compareTo(t.key) < 0 ?
				new Tree(t.key, t.val, fupdate(k,newval, t.left), t.right) :new Tree(t.key, t.val, t.left, fupdate(k,newval, t.right));
				}
}
