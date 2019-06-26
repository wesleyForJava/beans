package com.wesley.bean.array.eleven1;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.wesley.bean.array.eleven.Shop;
import com.wesley.bean.asynchronous.Quote;

public class BestPriceFinder {

    private final static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                                                   new Shop("LetsSaveBig"),
                                                   new Shop("MyFavoriteShop"),
                                                   new Shop("BuyItAll"),
                                                   new Shop("ShopEasy"));

    private final Executor executor = Executors.newFixedThreadPool(shops.size(), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });

    public List<String> findPricesSequential(String product) {
        return shops.stream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    public List<String> findPricesParallel(String product) {
        return shops.parallelStream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    public List<String> findPricesFuture(String product) {
        List<CompletableFuture<String>> priceFutures = findPricesStream(product)
                .collect(Collectors.<CompletableFuture<String>>toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public  Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));

    
    
    }

 
    
    @SuppressWarnings("rawtypes")
	public static void printPricesStream(String product) {
        long start = System.nanoTime();
        CompletableFuture[] futures = new  BestPriceFinder().findPricesStream(product)
                .map(f -> f.thenAccept(s -> System.out.println(s + " (done in " + ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
        
//		CompletableFuture[] futures = findPricesStream("myPhone")
//				.map(f -> f.thenAccept(System.out::println))
//				.toArray(size -> new CompletableFuture[size]);
//				CompletableFuture.allOf(futures).join();
        System.out.println("All shops have now responded in " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
    }

    @SuppressWarnings("rawtypes")
	public static void printPriceesStream() {
//		CompletableFuture[] futures = new  BestPriceFinder().findPricesStream("myPhone")
//				.map(f -> f.thenAccept(System.out::println))
//				.toArray(size -> new CompletableFuture[size]);
//				CompletableFuture.allOf(futures).join();
//				System.out.println(futures.toString());
			CompletableFuture[] futures = new BestPriceFinder().findPricesStream("GOODPHONE")
			.map(f->f.thenAccept(System.out::println))
			.toArray(size->new CompletableFuture[size]);
			CompletableFuture.anyOf(futures).join();
    }
    
    @FunctionalInterface
    public interface TemporalAdjuster {
    Temporal adjustInto(Temporal temporal);
    }
    
//    测验12.2 实现一个定制的 TemporalAdjuster
//    请设计一个 NextWorkingDay 类，该类实现了 TemporalAdjuster 接口，能够计算明天
//    的日期，同时过滤掉周六和周日这些节假日。格式如下所示：
//    date = date.with(new NextWorkingDay());
//    如果当天的星期介于周一至周五之间，日期向后移动一天；如果当天是周六或者周日，则
//    返回下一个周一。
//    答案：下面是参考的 NextWorkingDay 类的实现。
	public class NextWorkingDay implements TemporalAdjuster {
		@Override
		public Temporal adjustInto(Temporal temporal) {
			DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
			int dayToAdd = 1;
			if (dow == DayOfWeek.FRIDAY)
				dayToAdd = 3;
			else if (dow == DayOfWeek.SATURDAY)
				dayToAdd = 2;
			return temporal.plus(dayToAdd, ChronoUnit.DAYS);
		}
	}
//    该 TemporalAdjuster 通常情况下将日期往后顺延一天，如果当天是周六或者周日，则
//    依据情况分别将日期顺延3天或者2天。注意，由于 TemporalAdjuster 是一个函数式接口，
//    你只能以Lambda表达式的方式向该 adjuster 接口传递行为：
//  date.with(temporal -> {
//    DayOfWeek dow =
//    DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
//    int dayToAdd = 1;
//    if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
//    else if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
//    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
//    });
//    你大概会希望在你代码的多个地方使用同样的方式去操作日期，为了达到这一目的，我们
//    建议你像我们的示例那样将它的逻辑封装到一个类中。对于你经常使用的操作，都应该采用类
//    似的方式，进行封装。最终，你会创建自己的类库，让你和你的团队能轻松地实现代码复用。
//    如果你想要使用Lambda表达式定义 TemporalAdjuster 对象，推荐使用 Temporal-
//    Adjusters 类的静态工厂方法 ofDateAdjuster ，它接受一个 UnaryOperator<LocalDate>
//    正常情况，
//    增加1天
//    读取当前
//    日期
//    增加恰当的天数后，
//    返回修改的日期
//    如果当天是周
//    六，增加2天
//    如果当天是周
//    五，增加3天
//    12.2 操纵、解析和格式化日期 255
//    类型的参数，代码如下：


	@SuppressWarnings({ "rawtypes", "unused" })
	public static void main(String[] args) {
		
	    TemporalAdjuster nextWorkingDay = (TemporalAdjuster) TemporalAdjusters.ofDateAdjuster(
	    	    temporal -> {
	    	    DayOfWeek dow = 	    DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
	    	    int dayToAdd = 1;
	    	    if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
	    	    if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
	    	    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
	    	    });
	    	 //   date = date.with(nextWorkingDay);
//    	printPricesStream("MyIhpone");
//    	printPriceesStream();
		long start = System.nanoTime();
		CompletableFuture[] futures = new BestPriceFinder().findPricesStream("myPhone27S")
				.map(f -> f.thenAccept(s -> System.out
						.println(s + " (done in " + ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
				.toArray(size -> new CompletableFuture[size]);
//    	CompletableFuture.allOf(futures).join();
		CompletableFuture.anyOf(futures).join();
		System.out.println("All shops have now responded in " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
	}
}
