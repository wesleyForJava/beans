package com.wesley.bean.array;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Futrue {
	public static void main(String[] args) {
	
	ExecutorService executor = Executors.newCachedThreadPool();
	Future<Double> future = executor.submit(new Callable<Double>() {
	public Double call() {
	return doSomeLongComputation();
	}

	private Double doSomeLongComputation() {
		System.out.println("做一些事情。。。");
		return 2.0;
	}});
	doSomethingElse();
	try {
//		if(!future.isDone()) {
			Double result = future.get(1, TimeUnit.SECONDS);
			System.out.println(result);
//		}
	} catch (ExecutionException ee) {
	// 计算抛出一个异常
	} catch (InterruptedException ie) {
	// 当前线程在等待过程中被中断
	} catch (TimeoutException te) {
	// 在Future对象完成之前超过已过期
	}
//	首先，你会学到如何为你的客户提供异步API（如果你拥有一间在线商店的话，这是非常
//	有帮助的）。
//	  其次，你会掌握如何让你使用了同步API的代码变为非阻塞代码。你会了解如何使用流水
//	线将两个接续的异步操作合并为一个异步计算操作。这种情况肯定会出现，比如，在线
//	商店返回了你想要购买商品的原始价格，并附带着一个折扣代码——最终，要计算出该
//	商品的实际价格，你不得不访问第二个远程折扣服务，查询该折扣代码对应的折扣比率。
//	  你还会学到如何以响应式的方式处理异步操作的完成事件，以及随着各个商店返回它的
//	商品价格，最佳价格查询器如何持续地更新每种商品的最佳推荐，而不是等待所有的商
//	店都返回他们各自的价格（这种方式存在着一定的风险，一旦某家商店的服务中断，用
//	户可能遭遇白屏）。
	
	
	}

	private static void doSomethingElse() {
		// TODO Auto-generated method stub
		System.out.println("做一些事情。。。");
	}
	
	
	
}
