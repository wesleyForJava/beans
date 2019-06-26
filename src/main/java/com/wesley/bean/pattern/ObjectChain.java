package com.wesley.bean.pattern;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public abstract class ObjectChain<T> {

   private ObjectChain<T> success;

	public void setSuccess(ObjectChain<T> success) {
		this.success = success;
	}
   
	public T hanlde(T input) {
		T t = hanldeWork(input);
		if(success !=null) {
			t= success.hanlde(t);
		}
		return t;
	}
	
	abstract T hanldeWork(T input);
	
	public static void main(String[] args) {
			UnaryOperator<String> f3=(String input)->input+"我叫WESLEY,我来自中国";
			UnaryOperator<String> f4=(String input)->input.replaceAll("我", "你");
			UnaryOperator<String> f5=(String input)->input+="你好!";
			Function<String, String> pipleline = f5.andThen(f3.andThen(f4));
			String apply = pipleline.apply("1111");
			System.out.println(apply);
	}
	
	
	
	
	
	
   
	
	
}
