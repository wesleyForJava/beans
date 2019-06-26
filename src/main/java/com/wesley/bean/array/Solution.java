package com.wesley.bean.array;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {
	/**
     * 1.查找2011年所有的交易，按交易额排序(低到高)
     */
    public static List<Transaction> sortTransactionIn2011(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
    }


    /**
     * 2.交易员都在哪些不同的城市工作过
     */
    public static List<String> getAllCitysOfTrader(List<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList());
    }

    /**
     * 3.查找来自剑桥的交易员，按姓名排序
     */
    public static List<Trader> sortAllTradersInCambridge(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
    }

    /**
     * 4.返回所有交易员的姓名字符串，按字母顺序排序
     */
    public static String getAllTraderNameString(List<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining(","));
    }

    /**
     * 5.有没有交易员在米兰
     */
    public static boolean haveTraderInMilan(List<Transaction> transactions) {
        return transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
    }

    /**
     * 6.打印生活在剑桥的交易员的所有交易额
     */
    public static void printValueOfTraderInCambridege(List<Transaction> transactions) {
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

    }

    /**
     * 7.所有交易中，最高交易额
     */
    public static Optional<Integer> getMaxValue(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
    }

    /**
     * 8.找到交易额最小的交易
     */
    public static Optional<Transaction> getMinTransaction(List<Transaction> transactions) {
        return transactions.stream().min(comparing(Transaction::getValue));
    }


    public <T> void output(T collection) {
        if (collection instanceof Collection) {
            ((Collection) collection).stream().forEach(System.out::println);
        } else {
            System.out.println(collection);
        }

        System.out.println();

    }

    public static void main(String[] args) {
    	
        /**
         * 交易员
         */
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        /**
         * 交易
         */
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    	List<Transaction> tr2011 =
    			transactions.stream()
    			.filter(transaction -> transaction.getYear() == 2011)
    			.sorted(comparing(Transaction::getValue))
    			.collect(toList());
    	System.out.println(tr2011);
    	
    	List<String> collect = transactions.parallelStream().map(e->e.getTrader().getCity()).distinct().collect(toList());
        System.out.println(collect);
        Set<String> collects = transactions.parallelStream().map(e->e.getTrader().getCity()).collect(toSet());
        System.out.println(collects);
        
        List<Trader> collect2 = transactions.parallelStream().map(Transaction::getTrader).filter((e)->e.getCity().equals("Cambridge")).sorted(comparing(Trader::getName)).collect(toList());
    	
        System.out.println(collect2);
        
        //返回所有交易员的姓名字符串，按字母顺序排序
        String collect3 = transactions.parallelStream().map(e->e.getTrader().getName()).sorted().distinct().reduce("",(a,b)->a+b+"");
        
        System.out.println(collect3);
        String traderStr =
        		transactions.stream()
        		.map(transaction -> transaction.getTrader().getName())
        		.distinct()
        		.sorted()
        		.collect(joining());
        System.out.println(traderStr);
        
        List<Boolean> collect4 = transactions.parallelStream().map(e->e.getTrader().getCity().equals("Milan")).collect(toList());
        
        System.out.println(collect4);
		boolean anyMatch = transactions.parallelStream().anyMatch(e->e.getTrader().getCity().equals("columbia"));
        System.out.println(anyMatch);
        
        //打印生活在剑桥的交易员的所有交易额
        //Cambridge
//      transactions.parallelStream().filter(e->e.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).forEach(System.out::println);
        
//        System.out.println(collect5);
        //所有交易中，最高的交易额是多少
        Optional<Integer> reduce = transactions.parallelStream().map(e->e.getValue()).reduce(Integer::max);
      System.out.println(reduce.get());
//      Optional<Integer> highestValue =
//    		  transactions.stream()
//    		  .map(Transaction::getValue)
//    		  .reduce(Integer::max);
      //找到最小交易额的交易
//      Optional<Transaction> smallestTransaction =
//    		  transactions.stream()
//    		  .reduce((t1, t2) ->
//    		  t1.getValue() < t2.getValue() ? t1 : t2);
//      System.out.println(smallestTransaction.get());
//      Optional<Transaction> reduce2 = transactions.stream().reduce((t1,t2)->t1.getValue()<t2.getValue()?t1:t2);
//      System.out.println(reduce2.get());
//      Optional<Transaction> smallestTransactions =
//    		  transactions.stream()
//    		  .min(comparing(Transaction::getValue));
//      System.out.println(smallestTransactions.get());
      Optional<Transaction> min = transactions.parallelStream().min(comparing(Transaction::getValue));
      System.out.println(min.get());
      
      
      int calories = Dish.menu.stream()
    		  .map(Dish::getCalories)
    		  .reduce(0, Integer::sum);
      System.out.println(calories);
      List<String> map = Dish.menu.parallelStream().map(Dish::getName).collect(toList());
      System.out.println(map);
      int sum = Dish.menu.stream().mapToInt(Dish::getCalories).sum();
      System.out.println(sum);
      
      IntStream intStream = Dish.menu.stream().mapToInt(Dish::getCalories);
      Stream<Integer> stream = intStream.boxed();
      
      OptionalInt maxCalories = Dish.menu.stream()
    		  .mapToInt(Dish::getCalories)
    		  .max();
//    		  现在，如果没有最大值的话，你就可以显式处理 OptionalInt 去定义一个默认值了：
    		  int max = maxCalories.orElse(1);
    		  System.out.println(max);
    		  System.out.println(maxCalories.getAsInt());
    		  IntStream evenNumbers = IntStream.rangeClosed(1, 100)
    				  .filter(n -> n % 2 == 0);
    				  System.out.println(evenNumbers.count());
    				 OptionalInt count = IntStream.rangeClosed(1, 100).reduce((a,b)->a+b);
    				 System.out.println(count.getAsInt());
        /*
		 * Set<String> cities = transactions.stream() .map(transaction ->
		 * transaction.getTrader().getCity()) .collect(toSet());
		 */
        
//        Solution solution = new Solution();
//
//        solution.output(sortTransactionIn2011(transactions));

//        solution.output(getAllCitysOfTrader(transactions));
//
//        solution.output(sortAllTradersInCambridge(transactions));
//s
//        solution.output(getAllTraderNameString(transactions));
//
//        solution.output("haveTraderInMilan:" + haveTraderInMilan(transactions));
//
//        printValueOfTraderInCambridege(transactions);
//        System.out.println();
//
//        solution.output(getMaxValue(transactions));
//
//        solution.output(getMinTransaction(transactions));
    }

}
