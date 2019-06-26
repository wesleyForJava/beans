package com.wesley.bean.array.eleven1;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.wesley.bean.array.eleven.Shop;
import com.wesley.bean.asynchronous.ExchangeService;
import com.wesley.bean.asynchronous.ExchangeService.Money;
import com.wesley.bean.asynchronous.Quote;
import com.wesley.bean.asynchronous.Util;

public class Discount {
	 public enum Code {
	        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

	        private final int percentage;

	        Code(int percentage) {
	            this.percentage = percentage;
	        }
	    }

	    private static final 		List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
				new Shop("LetsSaveBig"),
				new Shop("MyFavoriteShop"),
				new Shop("BuyItAll"));
	    public static String applyDiscount(Quote quote) {
	        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
	    }
	    private static double apply(double price, com.wesley.bean.asynchronous.Discount.Code code) {
	        Util.delay();
	        return  Util.format(price * (100 - code.percentage) / 100);
	    }
	    
//	    代码清单11-16中，你对一个 CompletableFuture 对象调用了 thenCompose 方法，并向其
//	    传递了第二个 CompletableFuture ，而第二个 CompletableFuture 又需要使用第一个
//	    CompletableFuture 的执行结果作为输入。但是，另一种比较常见的情况是，你需要将两个完
//	    全不相干的 CompletableFuture 对象的结果整合起来，而且你也不希望等到第一个任务完全结
//	    束才开始第二项任务。
//	    这种情况，你应该使用 thenCombine 方法，它接收名为 BiFunction 的第二参数，这个参数
//	    定义了当两个 CompletableFuture 对象完成计算后，结果如何合并。同 thenCompose 方法一样，
//	    thenCombine 方法也提供有一个 Async 的版本。这里，如果使用 thenCombineAsync 会导致
//	    BiFunction 中定义的合并操作被提交到线程池中，由另一个任务以异步的方式执行。
//	    回到我们正在运行的这个例子，你知道，有一家商店提供的价格是以欧元（EUR）计价的，
//	    但是你希望以美元的方式提供给你的客户。你可以用异步的方式向商店查询指定商品的价格，同
//	    时从远程的汇率服务那里查到欧元和美元之间的汇率。当二者都结束时，再将这两个结果结合起
//	    来，用返回的商品价格乘以当时的汇率，得到以美元计价的商品价格。用这种方式，你需要使用
//	    第 三 个 CompletableFuture 对 象 ， 当 前 两 个 CompletableFuture 计 算 出 结 果 ， 并 由
//	    BiFunction 方法完成合并后，由它来最终结束这一任务，代码清单如下所示。
		public static List<String> findPricesInUSD(String product) {
			List<CompletableFuture<Double>> fpFutures=new ArrayList<CompletableFuture<Double>>();
			for (Shop shop : shops) {
				CompletableFuture<Double> thenCombine = CompletableFuture.supplyAsync(()->Double.parseDouble(shop.getPrice(product)))
				.thenCombine(CompletableFuture.supplyAsync(()->ExchangeService.getRate(Money.EUR, Money.USD)), (price,rate)->price*rate);
				fpFutures.add(thenCombine);
			}
			return fpFutures.stream().map(CompletableFuture::join).map(price->"this price is"+price).collect(Collectors.toList());
		} 
	    public static String getPrice(String product) {
	    	double price = calculatePrice(product);
	    	Discount.Code code = Discount.Code.values()[
	    	new Random().nextInt(Discount.Code.values().length)];
	    	String name="GOLD";
	    	return String.format("%s:%.2f:%s", name, price, code);
	    	}
	    	private static double calculatePrice(String product) {
	    	Util.delay();
	    	return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
	    	}
	    	
	    	public static void main(String[] args) {
	    		
	    		
	    		LocalDate date = LocalDate.of(2014, 3, 18);
	    		date = date.with(ChronoField.MONTH_OF_YEAR, 9);
	    		date = date.plusYears(2).minusDays(10);
	    		date.withYear(2011);
	    		System.out.println(date);
	    		
	    		
	    		
	    		LocalDate date1 = LocalDate.of(2014, 3, 18);
	    		LocalDate date2 = date1.with(nextOrSame(DayOfWeek.SUNDAY));
	    		LocalDate date3 = date2.with(lastDayOfMonth());
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
//	    		List<String> price = findPricesInUSDJava7S("IPHONX");
//	    		randomDelay();
//	    		System.out.println(price.toString());
	    		findPricesStream("myPhone").map(f -> f.thenAccept(System.out::println));
//	    		与我们刚才介绍的 get 和 with 方法类似，代码清单12-7中最后一行使用的 plus 方法也是通用
//	    		方法，它和 minus 方法都声明于 Temporal 接口中。通过这些方法，对 TemporalUnit 对象加上
//	    		或者减去一个数字，我们能非常方便地将 Temporal 对象前溯或者回滚至某个时间段，通过
//	    		ChronoUnit 枚举我们可以非常方便地实现 TemporalUnit 接口。
//	    		大概你已经猜到，像 LocalDate 、 LocalTime 、 LocalDateTime 以及 Instant 这样表示时
//	    		间点的日期时间类提供了大量通用的方法，表12-2对这些通用的方法进行了总结。
//	    		表12-2 表示时间点的日期 时间类的通用方法
//	    		方 法 名  是否是静态方法 描 述
//	    		from  是  依据传入的 Temporal 对象创建对象实例
//	    		now  是  依据系统时钟创建 Temporal 对象
//	    		of  是  由 Temporal 对象的某个部分创建该对象的实例
//	    		parse  是  由字符串创建 Temporal 对象的实例
//	    		atOffset  否  将 Temporal 对象和某个时区偏移相结合
//	    		atZone  否  将 Temporal 对象和某个时区相结合
//	    		format  否  使用某个指定的格式器将 Temporal 对象转换为字符串（ Instant 类不提供该方法）
//	    		get  否  读取 Temporal 对象的某一部分的值
//	    		minus  否
//	    		创建 Temporal 对象的一个副本，通过将当前 Temporal 对象的值减去一定的时长
//	    		创建该副本
//	    		plus  否
//	    		创建 Temporal 对象的一个副本，通过将当前 Temporal 对象的值加上一定的时长
//	    		创建该副本
//	    		with  否  以该 Temporal 对象为模板，对某些状态进行修改创建该对象的副本
			}
	    	
	        private static TemporalAdjuster nextOrSame(DayOfWeek sunday) {
				// TODO Auto-generated method stub
				return null;
			}
			private static TemporalAdjuster lastDayOfMonth() {
				// TODO Auto-generated method stub
				return null;
			}
			public static  Stream<CompletableFuture<String>> findPricesStream(String product) {
	            return shops.stream()
	                    .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
	                    .map(future -> future.thenApply(Quote::parse))
	                    .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
	        }

//	    	public List<String> findPrices(String product) {
//	    		List<CompletableFuture<String>> priceFutures =
//	    		shops.stream().map(shop -> CompletableFuture.supplyAsync(
//	    		() -> shop.getPrice(product), executor))
//	    		.map(future -> future.thenApply(Quote::parse))
//	    		.map(future -> future.thenCompose(quote ->
//	    		CompletableFuture.supplyAsync(
//	    		() -> Discount.applyDiscount(quote), executor)))
//	    		.collect(Collectors.toList());
//	    		return priceFutures.stream()
//	    		.map(CompletableFuture::join)
//	    		.collect(Collectors.toList());
//	    		}
//	    	public List<String> findPrices(String product) {
//	    		return shops.stream()
//	    		.map(shop -> shop.getPrice(product))
//	    		.map(Quote::parse)
//	    		.map(Discount::applyDiscount)
//	    		.collect(Collectors.toList());
//	    	}
//	    	目前为止，你实现的 findPrices 方法只有在取得所有商店的返回值时才显示商品的价格。
//	    	而你希望的效果是，只要有商店返回商品价格就在第一时间显示返回值，不再等待那些还未返回
//	    	的商店（有些甚至会发生超时）。你如何实现这种更进一步的改进要求呢？
//	    	11.5.1 对最佳价格查询器应用的优化
//	    	你要避免的首要问题是，等待创建一个包含了所有价格的 List 创建完成。你应该做的是直
//	    	接处理 CompletableFuture 流，这样每个 CompletableFuture 都在为某个商店执行必要的操
//	    	作。为了实现这一目标，在下面的代码清单中，你会对代码清单11-12中代码实现的第一部分进
//	    	行重构，实现 findPricesStream 方法来生成一个由 CompletableFuture 构成的流。
	    	
	    	
	    	private final static Executor executor = Executors.newFixedThreadPool(shops.size(), new ThreadFactory() {
	    		@Override
	    		public Thread newThread(Runnable r) {
	    			Thread t = new Thread(r);
	    			t.setDaemon(true);
	    			return t;
	    		}
	    	});
	    	
	    	
	    	
          public static Stream<CompletableFuture<String>> findPriceStreams(String product){
			Stream<CompletableFuture<String>> map = shops.stream().map(shop->CompletableFuture.supplyAsync(()->Shop.getPrice(product),executor))
					.map(future->future.thenApply(Quote::parse)) 
					.map(future->future
							.thenCompose(quote->CompletableFuture.supplyAsync(
									()->Discount.applyDiscount(quote),executor)));
			return map;
        	  
          }
	    	
	    	
	        
	        
		public static List<String> findPricesInUSDJava7S(String product) {
			ExecutorService executor = Executors.newCachedThreadPool();
			List<Future<Double>> priceFutures = new ArrayList<>();
			for (Shop shop : shops) {
				Future<Double> ratesFuture = executor.submit(new Callable<Double>() {
					@Override
					public Double call() throws Exception {
						return ExchangeService.getRate(Money.EUR, Money.USD);
					}
				});
	    			Future<Double> totals = executor.submit(new Callable<Double>() {
						@SuppressWarnings("static-access")
						@Override
						public Double call() throws Exception {
                             double price = Double.parseDouble(shop.getPrice(product));							
							return price* ratesFuture.get();
						}
					});
	    			
	    			priceFutures.add(totals);
	    			
	    		}
//	    		 List<String> prices = new ArrayList<>();
//	    		 List<String> collect = priceFutures.stream().map((e)->{
//					try {
//						 String p = String.valueOf(e.get());
//					} catch (InterruptedException | ExecutionException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					return product;
//				}).collect(Collectors.toList());
	    		
	    		List<String> collect = priceFutures.stream().map(e->{
					try {
						return " price is " +String.valueOf(e.get());
					} catch (InterruptedException | ExecutionException e1) {
						e1.printStackTrace();
					}
					return null;
				}).collect(Collectors.toList());
				return collect;
	    		
	    	}
		
			private static final Random random = new Random();
		
			public static void randomDelay() {
				int delay = 500 + random.nextInt(2000);
				System.out.println(delay+"s");
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
	    	
	    	public static List<String> findPricesInUSDJava7(String product) {
	            ExecutorService executor = Executors.newCachedThreadPool();
	            List<Future<Double>> priceFutures = new ArrayList<>();
	            for (Shop shop : shops) {
	                final Future<Double> futureRate = executor.submit(new Callable<Double>() { 
	                    public Double call() {
	                        return ExchangeService.getRate(Money.EUR, Money.USD);
	                    }
	                });
	                Future<Double> futurePriceInUSD = executor.submit(new Callable<Double>() { 
	                    public Double call() {
	                        try {
	                            double priceInEUR = Double.parseDouble(shop.getPrice(product));
	                            return priceInEUR * futureRate.get();
	                        } catch (InterruptedException | ExecutionException e) {
	                            throw new RuntimeException(e.getMessage(), e);
	                        }
	                    }
	                });
	                priceFutures.add(futurePriceInUSD);
	            }
	            List<String> prices = new ArrayList<>();
	            for (Future<Double> priceFuture : priceFutures) {
	                try {
	                    prices.add(/*shop.getName() +*/ " price is " + priceFuture.get());
	                }
	                catch (ExecutionException | InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	            return prices;
	    	}
	    	
}
