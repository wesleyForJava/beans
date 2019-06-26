package com.wesley.bean.asynchronous;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Shops {
	


	
	
    private final String name;
    private final Random random;
   public static final List<Shops> shops= Arrays.asList(new Shops("BestPrice"),
			new Shops("LetsSaveBig"),
			new Shops("MyFavoriteShop"),
			new Shops("BuyItAll"));

    public Shops(String name) {
        this.name = name;
        random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return name + ":" + price + ":" + code;
    }

    public  double calculatePrice(String product) {
        Util.delay();
        return Util.format(random.nextDouble() * product.charAt(0) + product.charAt(1));
    }
//    为了实现这个目标，你首先需要将 getPrice 转换为 getPriceAsync 方法，并修改它的返
//    回值：
//    public Future<Double> getPriceAsync(String product) { ... }
//    我们在本章开头已经提到，Java 5引入了 java.util.concurrent.Future 接口表示一个异
//    步计算（即调用线程可以继续运行，不会因为调用方法而阻塞）的结果。这意味着 Future 是一
//    个暂时还不可知值的处理器，这个值在计算完成后，可以通过调用它的 get 方法取得。因为这样
//    的设计， getPriceAsync 方法才能立刻返回，给调用线程一个机会，能在同一时间去执行其他
//    有价值的计算任务。新的 CompletableFuture 类提供了大量的方法，让我们有机会以多种可能
//    的方式轻松地实现这个方法，比如下面就是这样一段实现代码。
//    226 第 11 章  CompletableFuture ：组合式异步编程
//    代码清单11-4  getPriceAsync 方法的实现
//	   public Future<Double> getPriceAsync(String product) {
//	    CompletableFuture<Double> futurePrice = new CompletableFuture<>();
//	    new Thread( () -> {
//	    double price = calculatePrice(product);
//	    futurePrice.complete(price);
//	    }).start();
//	    return futurePrice;
//	    }
//    在这段代码中，你创建了一个代表异步计算的 CompletableFuture 对象实例，它在计算完
//    成时会包含计算的结果。接着，你调用 fork 创建了另一个线程去执行实际的价格计算工作，不
//    等该耗时计算任务结束，直接返回一个 Future 实例。当请求的产品价格最终计算得出时，你可
//    以使用它的 complete 方法，结束 completableFuture 对象的运行，并设置变量的值。很显然，
//    这个新版 Future 的名称也解释了它所具有的特性。使用这个API的客户端，可以通过下面的这段
//    代码对其进行调用。

//	public Future<Double> getPriceAsync(String product) {
//		CompletableFuture<Double> futurePrice =  new CompletableFuture<>();
//		
//		new Thread(() -> {
//			try {
//				double price = calculatePrice(product);
//				futurePrice.complete(price);
//			} catch (Exception ex) {
//				futurePrice.completeExceptionally(ex);
//			}
//		}).start();
//		return futurePrice;
//	}
    public Future<Double> getPriceAsync(String product) {
//    	ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
    	return CompletableFuture.supplyAsync(() -> calculatePrice(product),new ThreadPoolExecutor(1, 2, 2, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(2)));
    	}
//    目前为止，你已经知道对集合进行并行计算有两种方式：要么将其转化为并行流，利用 map
//    这样的操作开展工作，要么枚举出集合中的每一个元素，创建新的线程，在 Completable-
//    Future 内对其进行操作。后者提供了更多的灵活性，你可以调整线程池的大小，而这能帮助
//    你确保整体的计算不会因为线程都在等待I/O而发生阻塞。
//    我们对使用这些API的建议如下。
//    ❑如果你进行的是计算密集型的操作，并且没有I/O，那么推荐使用 Stream 接口，因为实
//    现简单，同时效率也可能是最高的（如果所有的线程都是计算密集型的，那就没有必要
//    创建比处理器核数更多的线程）。
//    ❑反之，如果你并行的工作单元还涉及等待I/O的操作（包括网络连接等待），那么使用
//    CompletableFuture 灵活性更好，你可以像前文讨论的那样，依据等待/计算，或者
//    W/C的比率设定需要使用的线程数。这种情况不使用并行流的另一个原因是，处理流的
//    流水线中如果发生I/O等待，流的延迟特性会让我们很难判断到底什么时候触发了等待。
	public String getName() {
        return name;
    }
	private final Executor executor =
			Executors.newFixedThreadPool(Math.min(shops.size(), 100),
					new ThreadFactory() {
				public Thread newThread(Runnable r) {
					Thread t = new Thread(r);
					t.setDaemon(true);
					return t;
				}
			});
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Shops shop = new Shops("BestShop");
		long start = System.nanoTime();
		Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
		long invocationTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println("Invocation returned after " + invocationTime + " msecs");
		// 执行更多任务，比如查询其他商店
		// doSomethingElse();
		// 在计算商品价格的同时
		try {
			double price = futurePrice.get(2,TimeUnit.SECONDS);
			System.out.printf("Price is %.2f%n", price);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println("Price returned after " + retrievalTime + " msecs");
		long start1 = System.nanoTime();
		String price = shop.getPrice("my favorite product");
		System.out.println(price);
		long invocationTimes = ((System.nanoTime() - start1) / 1_000_000);
		System.out.println(invocationTimes);
		//List<CompletableFuture<String>> getprCompletableFutures = getprCompletableFutures("my favorite product");
		List<String> findPricess = findPricess("my favorite product");
		System.out.println(findPricess);
//		CompletableFuture.supplyAsync(() -> shop.getName() + " price is " +
//				shop.getPrice(product), executor);
		
		
		
	}
	
	public static List<CompletableFuture<String>> getprCompletableFutures(String product){
		return shops.stream().map(shop -> CompletableFuture.supplyAsync(() ->String.format("%s price is %.2f",shop.getName(),shop.getPrice(product)))).collect(Collectors.toList());
		
	}
	
	public static List<String> findPricess(String product) {
		List<CompletableFuture<String>> priceFutures =
		shops.stream()
		.map(shop -> CompletableFuture.supplyAsync(
		() -> shop.getName() + " price is " +
		shop.getPrice(product)))
		.collect(Collectors.toList());
		return priceFutures.stream()
		.map(CompletableFuture::join)
		.collect(Collectors.toList());
		}
	
	public static List<String> findPrices(String product) {
		return shops.stream()
		.map(shop -> String.format("%s price is %.1f%n",shop.getName(), shop.getPrice(product)))
		.collect(Collectors.toList());
		}
	
	
}
