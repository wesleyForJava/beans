package com.wesley.bean.array.eleven;


import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

import com.wesley.bean.array.eleven1.Discount;
import com.wesley.bean.asynchronous.Util;

public class Shop {

    private  static String name;
    private static Random random;
    private static final 		List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
			new Shop("LetsSaveBig"),
			new Shop("MyFavoriteShop"),
			new Shop("BuyItAll"));
	private static Executor executor =
			Executors.newFixedThreadPool(Math.min(shops.size(), 100),
			new ThreadFactory() {
			public Thread newThread(Runnable r) {
			Thread t = new Thread(r);
			t.setDaemon(true);
			return t;
			}
			});

    public Shop(String name) {
        this.name = name;
        random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public static String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return name + ":" + price + ":" + code;
    }

    public static Double calculatePrice(String product) {
        Util.delay();
        return  Util.format(random.nextDouble() * product.charAt(0) + product.charAt(1));
    }

//    public String getName() {
//        return name;
//    }

	public static void delay() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
//	public static String getPrice(String product) {
//		return calculatePrice(product);
//		}
//		private static double calculatePrice(String product) {
//		delay();
//		return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
//	}
		public static void main(String[] args) throws InterruptedException, ExecutionException {
//			double price = getPrice("meiguo");
//			System.out.println(price);
//			Future<Double> priceAsync = getPriceAsync("meigou");
//			System.out.println(priceAsync.get());
//			Shop shop = new Shop("BestShop");
//			long start = System.nanoTime();
//			Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
//			long invocationTime = ((System.nanoTime() - start) / 1_000_000);
//			System.out.println("Invocation returned after " + invocationTime + " msecs");
//			// 执行更多任务，比如查询其他商店
//			doSomethingElse();
//			// 在计算商品价格的同时
//			try {
//			double price = futurePrice.get();
//			System.out.printf("Price is %.2f%n", price);
//			} catch (Exception e) {
//			throw new RuntimeException(e);
//			}
//			long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
//			System.out.println("Price returned after " + retrievalTime + " msecs");
//			long start = System.nanoTime();
//			System.out.println(findPrices("iphone 7"));
//			long duration = (System.nanoTime() - start) / 1_000_000;
//			System.out.println("Done in " + duration + " msecs");
//			String product=null;
//			long start = System.nanoTime();
//			List<CompletableFuture<String>> pricesss = getPricesss("iphone 7");
//			String string = pricesss.get(0).get();
//			System.out.println( pricesss.get(0).get()+ pricesss.get(1).get()+ pricesss.get(2).get());
//			//System.out.println(findPrices("iphone 7"));
//			long duration = (System.nanoTime() - start) / 1_000_000;
//			System.out.println("Done in " + duration + " msecs");
			long start = System.nanoTime();
			CompletableFuture<String> findPricess = findPricess("iphone 7");
			long duration = (System.nanoTime() - start) / 1_000_000;
			System.out.println("Done in " + duration + " msecs");
			
		}

		private static List<CompletableFuture<String>> getPricesss(String product) {
			List<CompletableFuture<String>> priceFutures =
					shops.stream()
					.map(shop -> CompletableFuture.supplyAsync(
					() -> String.format("%s price is %.2f",
					shop.getName(), shop.getPrice(product))))
					.collect(toList());
			return priceFutures;
		}
		
		private static void doSomethingElse() {
			// TODO Auto-generated method stub
			System.out.println("执行更多任务");
		}
		
		public static List<String> findPrices(String product) {
			List<CompletableFuture<String>> priceFutures =
			shops.stream()
			.map(shop -> CompletableFuture.supplyAsync(
			() -> shop.getName() + " price is " +
			shop.getPrice(product)))
			.collect(Collectors.toList());
			return priceFutures.stream()
			.map(CompletableFuture::join)
			.collect(toList());
			}
		

		public static CompletableFuture<String>  findPricess(String product) {
			CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(()-> new Shop("1").getName() + " price is " + new Shop("2").getPrice(product), executor);
			return supplyAsync;
			}
//		public static List<String> findPrices(String product) {
//			Stream<Shop> stream = shops.stream();
//			//System.out.println(shop);
//			Stream<String> map = stream.map(shop -> String.format("%s price is %f",shop.getName(), shop.getPrice(product)));
//		     List<String> collect = map.collect(Collectors.toList());
//			return collect;
//			}
//		@SuppressWarnings("static-access")
//		public static List<String> findPrices(String product) {
//			return shops.parallelStream()
//			.map(shop -> String.format("%s price is %.2f",
//			shop.getName(), shop.getPrice(product)))
//			.collect(toList());
//			}
		
//		public static Future<Double> getPriceAsync(String product) {
//		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
//		new Thread(() -> {
//			double price = calculatePrice(product);
//			futurePrice.complete(price);
//		}).start();
//		return futurePrice;
//	}
		
//	public Future<Double> getPriceAsync(String product) {
//		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
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
		
//	public CompletableFuture<String> getPriceAsync(String product) {
//		return CompletableFuture.supplyAsync(() -> calculatePrice(product));
//	}

	public static Random getRandom() {
		return random;
	}

	public static void setRandom(Random random) {
		Shop.random = random;
	}

	public String getName() {
		return name;
	}
		
}
