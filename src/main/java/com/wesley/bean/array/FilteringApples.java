package com.wesley.bean.array;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.Set;
import java.util.concurrent.RecursiveTask;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector.Characteristics;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import com.wesley.bean.array.Dish.Type;
import com.wesley.bean.pojo.User;


public class FilteringApples {
	public static <A, T, R> void main(String ... args){

        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                                              new Apple(155, "green"),
                                              new Apple(120, "red"));	

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples = filterApples(inventory, FilteringApples::isGreenApple);
        System.out.println(greenApples);
        
        // [Apple{color='green', weight=155}]
        List<Apple> heavyApples = filterApples(inventory, FilteringApples::isHeavyApple);
        System.out.println(heavyApples);
        
        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples2 = filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
        System.out.println(greenApples2);
        
        // [Apple{color='green', weight=155}]
        List<Apple> heavyApples2 = filterApples(inventory, (Apple a) -> a.getWeight() > 150);
        System.out.println(heavyApples2);
        
        // []
        List<Apple> weirdApples = filterApples(inventory, (Apple a) -> a.getWeight() < 80 || 
                                                                       "brown".equals(a.getColor()));
        System.out.println(weirdApples);
        
        
        List<Apple> heavyAppless =
        		inventory.stream().filter((Apple a) -> a.getWeight() > 150).sorted()
        		.collect(toList());
        inventory.parallelStream().filter((Apple a)->a.getColor()=="red").collect(toList());
        
        
//        def simplifyExpression(expr: Expr): Expr = expr match {
//        case BinOp("+", e, Number(0)) => e
//        case BinOp("*", e, Number(1)) => e
//        case BinOp("/", e, Number(1)) => e
//        case _ => expr
//        }
        
        
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<String> wordLengths = words.stream().map(String::toString).collect(toList());
       
        System.out.println(wordLengths);
        
        List<String[]> collect = words.stream().map(word -> word.split("")).distinct().collect(toList());
        
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
        System.out.println(streamOfwords);
        List<Stream<String>> collect2 = words.stream().map(word -> word.split("")).map(Arrays::stream).distinct().collect(toList());
        System.out.println(collect2);
        List<String> uniqueCharacters =	words.stream().map(w -> w.split("")).flatMap(Arrays::stream)
        		.distinct()
        		.collect(toList());
        System.out.println(uniqueCharacters);
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect3 = numbers.parallelStream().map(n->n*n).collect(toList());
        System.out.println(collect3);
        
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i, j})).collect(toList());
        List<Integer> numbers3 = Arrays.asList(1, 2, 3);
        List<Integer> numbers4 = Arrays.asList(3, 4);
        List<int[]> pairss = numbers3.stream().flatMap(i ->numbers4.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j})).collect(toList()) ;
        if(Dish.menu.stream().anyMatch(Dish::isVegetarian)){
        	System.out.println("The menu is (somewhat) vegetarian friendly!!");
        	}
        boolean isHealthy = Dish.menu.stream().allMatch(d -> d.getCalories() < 1000);
        System.out.println(isHealthy);
       boolean isHealthys =  Dish.menu.stream()
        		.noneMatch(d -> d.getCalories() >= 1000);
       System.out.println(isHealthys);
       Optional<Dish> dish =Dish.menu.stream().filter(Dish::isVegetarian).findAny();
       boolean present = dish.isPresent();
       System.out.println("isPresent:"+present);
       Dish orElse = dish.orElse(null);
       System.out.println("orElse:"+orElse);
       Dish dish2 = dish.get();
       System.out.println(dish2.toString());
       
       Dish.menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(d -> System.out.println(d.getName()));
       
    
       List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
       Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream().map(x -> x * x).filter(x -> x % 3 == 0).findFirst(); // 9;
       System.out.println(firstSquareDivisibleByThree.get().toString());
	   Optional<Integer> findFirst = someNumbers.stream().map(x->x*x).filter(x->x%4==0).findFirst();
	   System.out.println(findFirst.get());
	   
	   int sum = 0;
	   for (int x : numbers) {
	   sum += x;
	   }
	   System.out.println(sum);
	   
	   long start = System.currentTimeMillis();
	   Integer reduce = numbers.parallelStream().reduce(0,(a,b)->a+b);
	   long end=System.currentTimeMillis();
	   System.out.println("parallelStream:"+start+"\t"+end);
	   long start1 = System.currentTimeMillis();
	   Integer reduce1 = numbers.stream().reduce(0,(a,b)->a+b);
	   long end1=System.currentTimeMillis();
	   System.out.println("stream:"+start1+"\t"+end1);
	   System.out.println(reduce);
	   System.out.println(reduce1);
	   
	   Optional<Integer> reduce2 = numbers.stream().reduce(Integer::sum);
	   System.out.println(reduce2.get());
	   Optional<Integer> reduce3 = numbers.stream().reduce(Integer::max);
	   System.out.println(reduce3.get());
	   Optional<Integer> reduce4 = numbers.stream().reduce(Integer::min);
	   System.out.println(reduce4.get());
	   int count = Dish.menu.stream()
			   .map(d -> 1)
			   .reduce(0, (a, b) -> a + b);
	   System.out.println(count);
	   
	   Optional<Integer> reduce5 = Dish.menu.stream().map(d->1).reduce(Integer::sum);
	   System.out.println(reduce5.get());
	   long count4 = Dish.menu.stream().count();
	long count3 = count4;
	long count2 = count3;
	   System.out.println(count2);
	   int sum1 = numbers.parallelStream().reduce(0, Integer::sum);
	   System.out.println(sum1);
	   Optional<Integer> sum2 = numbers.parallelStream().reduce(Integer::sum);
	   System.out.println(sum2.get());
	   
	   int sum3 = IntStream.rangeClosed(1, 100).sum();
	   int sum4 = IntStream.rangeClosed(1, 100).filter((e)->e%2==1).sum();
	   System.out.println("总和:"+sum3+"sum4="+sum4);
//	   StreamSupport.intStream(new Streams.RangeIntSpliterator(1, 100, true), false);
	   
	 //  filter(b -> Math.sqrt(a*a + b*b) % 1 == 0);
//	   stream.filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
//	   .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});
	   
//	  long count3 = IntStream.rangeClosed(1, 100)
//	   .filter(b -> Math.sqrt(count*count + b*b) % 1 == 0)
//	   .boxed().map(b -> new int[]{count, b, (int) Math.sqrt(count * count + b * b)}).count();
//	  System.out.println(count3);
//	   IntStream.rangeClosed(1, 100)
//	   .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
//	   .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});
	   
//	   Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
//			   .flatMap(a -> IntStream.rangeClosed(a, 100)
//			   .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
//			   .mapToObj(b ->
//			   new int[]{a, b, (int)Math.sqrt(a * a + b * b)})
//			   );
//			   
//			   pythagoreanTriples.limit(28)
//			   .forEach(t ->
//			   System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
//			   
//			   Stream<double[]> pythagoreanTriples2 =
//					   IntStream.rangeClosed(1, 100).boxed()
//					   .flatMap(a ->
//					   IntStream.rangeClosed(a, 100)
//					   .mapToObj(
//					   b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
//					   .filter(t -> t[2] % 1 == 0));
//			   pythagoreanTriples2.limit(2).forEach(t->{
//				   System.out.println(t[0]+","+t[1]+","+t[2]);
//			   });
//			   Stream.of("java8","num","in","action").collect(toList()).forEach(System.out::print);
//			   List<String> collect4 = Stream.of("1","2").collect(toList());
//			   System.out.println();
//			   System.out.println(collect4.toString());
//			   int[] numberss = {2, 3, 5, 7, 11, 13};
//			   int sums = Arrays.stream(numberss).sum();
//			   System.out.println(sums);
//			   long uniqueWords = 0;
//			   try(Stream<String> lines =
//			   Files.lines(Paths.get("D:\\Wesley\\Git\\beans\\src\\main\\java\\com\\wesley\\bean\\array\\data.txt"), Charset.defaultCharset())){
//			   uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
//			   .distinct()
//			   .count();
//			   }
//			   catch(IOException e){
//				   e.printStackTrace();
//			   }
//			   System.out.println(uniqueWords);
//			   
//			   Stream.iterate(0, n -> n + 2)
//			   .limit(10)
//			   .forEach(System.out::println);
//			   
//			   Stream.iterate(1, n->n*3).limit(10).forEach(System.out::println);
//			   Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0]+t[1]})
//			   .limit(20)
//			   .forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));
			   
//			   你可以使用 Files.lines 得到一个流，其中的每个元素都是给定文件中的一行。然后，你
//			   可以对 line 调用 split 方法将行拆分成单词。应该注意的是，你该如何使用 flatMap 产生一个扁
//			   平的单词流，而不是给每一行生成一个单词流。最后，把 distinct 和 count 方法链接起来，数
//			   数流中有多少各不相同的单词。
			   
			   //斐波那契
			   Stream.iterate(new int[] {0,1},t->new int[] {t[1],t[0]+t[1]}).limit(10).map(t->t[0])
			   .forEach(System.out::println);
//	   
			   
			   Stream.iterate(new int[] {0,1},t->new int[] {t[1],t[0]+t[1]}).map(t->t[0])
			   .forEach(System.out::println);
			   
//			   Stream.generate(Math::random)
//			   .limit(5)
//			   .forEach(System.out::println);
//			   
//			   IntStream ones = IntStream.generate(() -> 1).limit(10);
//			  ones.forEach(System.out::println);
//			  IntStream twos = IntStream.generate(new IntSupplier(){
//				  public int getAsInt(){
//				  return 2;
//				  }
//				  }).limit(10);
//			  
//			  twos.forEach(System.out::println);
			  Stream.iterate(new Integer[] {0,1},t->new Integer[] {t[1],t[0]+t[1]}).limit(10).forEach(System.out::println);
//			  IntSupplier fib = new IntSupplier(){
//				  private int previous = 0;
//				  private int current = 1;
//				  public int getAsInt(){
//				  int oldPrevious = this.previous;
//				  int nextValue = this.previous + this.current;
//				  this.previous = this.current;
//				  this.current = nextValue;
//				  return oldPrevious;
//				  }
//				  };
//				  IntStream.generate(fib).limit(10).forEach(System.out::println);
		    	
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
//				  Stream.iterate(new int[] {0,1},t->new int[] {t[1],t[0]+t[1]}).map(t->t[0]).limit(10).forEach(System.out::println);
//				  Map<Currency, List<Transaction>> transactionsByCurrencies =	  new HashMap<>();
//						  for (Transaction transaction : transactions) {
//						  Currency currency = transaction.getCurrency();
//						  List<Transaction> transactionsForCurrency =
//						  transactionsByCurrencies.get(currency);
//						  if (transactionsForCurrency == null) {
//						  transactionsForCurrency = new ArrayList<>();
//						  transactionsByCurrencies
//						  .put(currency, transactionsForCurrency);
//						  }
//						  transactionsForCurrency.add(transaction);
//						  }	
//						  Map<Currency, List<Transaction>> transactionsByCurrencies =
//								  transactions.stream().collect(groupingBy(Transaction::getCurrency));
	
		        long howManyDishes = Dish.menu.stream().collect(Collectors.counting());
		        System.out.println(howManyDishes);
		        long count5= Dish.menu.stream().count();
		        System.out.println(count5==howManyDishes);
		        Comparator<Dish> dishCaloriesComparator =Comparator.comparingInt(Dish::getCalories);
		        Optional<Dish> mostCalorieDish =Dish.menu.stream().collect(maxBy(dishCaloriesComparator));
		        System.out.println(mostCalorieDish.get());
		        int totalCalories = 	Dish.menu.stream().collect(summingInt(Dish::getCalories));
		       Optional<Dish> maxs = Dish.menu.stream().max(dishCaloriesComparator);
		       System.out.println(maxs.get());
		   Double collect4 = Dish.menu.stream().collect(averagingInt(Dish::getCalories));
		   System.out.println(collect4);
		        
		        
		   String shortMenu = Dish.menu.stream().map(Dish::getName).collect(joining());
		   System.out.println(shortMenu);
		   String shortMenuDelimiter = Dish.menu.stream().map(Dish::getName).collect(joining(","));
		   System.out.println(shortMenuDelimiter);
		   
		   
		   int totalCalorty = Dish.menu.stream().collect(reducing(
				   0, Dish::getCalories, (i, j) -> i + j));
		        System.out.println(totalCalorty);
		        
		        Optional<Dish> mostCalorieDish1 =Dish.
						menu.stream().collect(reducing(
						(d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
		        System.out.println(mostCalorieDish1.get());
		        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
		        List<Integer> numbers11 = stream.reduce(
		        new ArrayList<Integer>(),
		        (List<Integer> l, Integer e) -> {
		        l.add(e);
		        return l; },
		        (List<Integer> l1, List<Integer> l2) -> {
		        l1.addAll(l2);
		        return l1; });
		        System.out.println(numbers11.toString());
//		        这个解决方案有两个问题：一个语义问题和一个实际问题。语义问题在于， reduce 方法
//		        旨在把两个值结合起来生成一个新值，它是一个不可变的归约。与此相反， collect 方法的设
//		        计就是要改变容器，从而累积要输出的结果。这意味着，上面的代码片段是在滥用 reduce 方
//		        法，因为它在原地改变了作为累加器的 List 。你在下一章中会更详细地看到，以错误的语义
//		        使用 reduce 方法还会造成一个实际问题：这个归约过程不能并行工作，因为由多个线程并发
//		        修改同一个数据结构可能会破坏 List 本身。在这种情况下，如果你想要线程安全，就需要每
//		        次分配一个新的 List ，而对象分配又会影响性能。这就是 collect 方法特别适合表达可变容
//		        器上的归约的原因，更关键的是它适合并行操作，本章后面会谈到这一点。
		        int totalCalories1 = Dish.menu.stream().collect(reducing(0,
		        		Dish::getCalories,
		        		Integer::sum));
		        int max = Dish.menu.parallelStream().mapToInt(Dish::getCalories).sum();
		        System.out.println(totalCalories1);
		        System.out.println(max);
		        
		        int totalCalories11 =
		        		Dish.menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
		        System.out.println(totalCalories11==max);
		        
		        int totalCalories2 = Dish.menu.stream().mapToInt(Dish::getCalories).sum();
		        System.out.println(totalCalories2);
		        
//		        以下哪一种 reducing 收集器的用法能够合法地替代 joining 收集器（如6.2.3节用法）？
		        String shortMenu4 = Dish.menu.stream().map(Dish::getName).collect(joining());
//		        (1)  
		        String shortMenu1 = Dish.menu.stream().map(Dish::getName)
		        .collect( reducing ( (s1, s2) -> s1 + s2 ) ).get();
//		        (2) 
		   //  String shortMenu5 = Dish.menu.stream().collect(reducing((s1, d2) ->s1.getName() + d2.getName())).get();
//		        (3) 
		        String shortMenu3 = Dish.menu.stream()
		        .collect( reducing( "",Dish::getName, (s1, s2) -> s1 + s2 ) );
		        System.out.println(shortMenu4);
		        System.out.println(shortMenu1);
		        System.out.println(shortMenu3);
		        
//		        语句1和语句3是有效的，语句2无法编译。
//		        (1) 这会将每道菜转换为菜名，就像原先使用 joining 收集器的语句一样。然后用一个
//		        String 作为累加器归约得到的字符串流，并将菜名逐个连接在它后面。
//		        (2) 这无法编译，因为 reducing 接受的参数是一个 BinaryOperator<t> ，也就是一个
//		        BiFunction<T,T,T> 。这就意味着它需要的函数必须能接受两个参数，然后返回一个相同类
//		        型的值，但这里用的Lambda表达式接受的参数是两个菜，返回的却是一个字符串。
//		        (3) 这会把一个空字符串作为累加器来进行归约，在遍历菜肴流时，它会把每道菜转换成
//		        菜名，并追加到累加器上。请注意，我们前面讲过， reducing 要返回一个 Optional 并不需
//		        要三个参数，因为如果是空流的话，它的返回值更有意义——也就是作为累加器初始值的空字
//		        符串。
//		        请注意，虽然语句1和语句3都能够合法地替代 joining 收集器，它们在这里是用来展示我
//		        们为何可以（至少在概念上）把 reducing 看作本章中讨论的所有其他收集器的概括。然而就
//		        实际应用而言，不管是从可读性还是性能方面考虑，我们始终建议使用 joining 收集器。
		        
		        
		        
		Map<Dish.Type, List<Dish>> dishesByType = Dish.menu.stream().collect(groupingBy(Dish::getType));
		System.out.println(dishesByType.toString());
		Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = Dish.menu.stream()
				.collect(groupingBy(Dish::getType, groupingBy(e -> {
					if (e.getCalories() <= 400)
						return CaloricLevel.DIET;
					else if (e.getCalories() <= 700)
						return CaloricLevel.NORMAL;
					else
						return CaloricLevel.FAT;
				})));
		System.out.println(dishesByTypeCaloricLevel);
		
		Map<Dish.Type, Long> typesCount = Dish.menu.stream().collect(
				groupingBy(Dish::getType, counting()));
		System.out.println(typesCount);
		
//		再举一个例子，你可以把前面用于查找菜单中热量最高的菜肴的收集器改一改，按照菜的类
//		型分类：
		Map<Dish.Type, Optional<Dish>> mostCaloricByType =
		Dish.menu.stream()
		.collect(groupingBy(Dish::getType,
		maxBy(comparingInt(Dish::getCalories))));
		System.out.println(mostCaloricByType);
//		这个分组的结果显然是一个 map ，以 Dish 的类型作为键，以包装了该类型中热量最高的 Dish
//		的 Optional<Dish> 作为值：
//		{FISH=Optional[salmon], OTHER=Optional[pizza], MEAT=Optional[pork]}
		
		Map<Type, Optional<Dish>> collect5 = Dish.menu.stream().collect(groupingBy(Dish::getType,minBy(comparingInt(Dish::getCalories))));
	System.out.println(collect5);
//	1. 把收集器的结果转换为另一种类型
//	因为分组操作的 Map 结果中的每个值上包装的 Optional 没什么用，所以你可能想要把它们
//	去掉。要做到这一点，或者更一般地来说，把收集器返回的结果转换为另一种类型，你可以使用
//	Collectors.collectingAndThen 工厂方法返回的收集器，如下所示。
//	代码清单6-3 查找每个子组中热量最高的 Dish
		Map<Dish.Type, Dish> mostCaloricByTypes = Dish.menu.stream().collect(
				groupingBy(Dish::getType, collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
	Dish.menu.stream().collect(groupingBy(Dish::getType,collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
		System.out.println(mostCaloricByTypes);
	Dish dish3 = mostCaloricByTypes.get(Dish.Type.OTHER);
	System.out.println(dish3);
//	这个工厂方法接受两个参数——要转换的收集器以及转换函数，并返回另一个收集器。这个
//	收集器相当于旧收集器的一个包装， collect 操作的最后一步就是将返回值用转换函数做一个映
//	射。在这里，被包起来的收集器就是用 maxBy 建立的那个，而转换函数 Optional::get 则把返
//	回的 Optional 中的值提取出来。前面已经说过，这个操作放在这里是安全的，因为 reducing
//	收集器永远都不会返回 Optional.empty() 。其结果是下面的 Map ：
//	{FISH=salmon, OTHER=pizza, MEAT=pork}
//	把好几个收集器嵌套起来很常见，它们之间到底发生了什么可能不那么明显。图6-6可以直
//	观地展示它们是怎么工作的。从最外层开始逐层向里，注意以下几点。
//	  收集器用虚线表示，因此 groupingBy 是最外层，根据菜肴的类型把菜单流分组，得到三
//	个子流。
//	  groupingBy 收集器包裹着 collectingAndThen 收集器，因此分组操作得到的每个子流
//	都用这第二个收集器做进一步归约。
//	  collectingAndThen 收集器又包裹着第三个收集器 maxBy 。
//	  随后由归约收集器进行子流的归约操作，然后包含它的 collectingAndThen 收集器会对
//	其结果应用 Optional:get 转换函数。
//	  对三个子流分别执行这一过程并转换而得到的三个值，也就是各个类型中热量最高的
//	Dish ，将成为 groupingBy 收集器返回的 Map 中与各个分类键（ Dish 的类型）相关联的值。
//	2. 与 groupingBy 联合使用的其他收集器的例子
//	一般来说，通过 groupingBy 工厂方法的第二个参数传递的收集器将会对分到同一组中的所
//	有流元素执行进一步归约操作。例如，你还重用求出所有菜肴热量总和的收集器，不过这次是对
//	每一组 Dish 求和：
	Map<Dish.Type, Integer> totalCaloriesByType =
	Dish.menu.stream().collect(groupingBy(Dish::getType,
	summingInt(Dish::getCalories)));
	System.out.println(totalCaloriesByType);
	Integer integer = totalCaloriesByType.get(Dish.Type.FISH);
	System.out.println(integer);
	
//	然而常常和 groupingBy 联合使用的另一个收集器是 mapping 方法生成的。这个方法接受两
//	个参数：一个函数对流中的元素做变换，另一个则将变换的结果对象收集起来。其目的是在累加
//	之前对每个输入元素应用一个映射函数，这样就可以让接受特定类型元素的收集器适应不同类型
//	的对象。我们来看一个使用这个收集器的实际例子。比方说你想要知道，对于每种类型的 Dish ，
//	菜单中都有哪些 CaloricLevel 。我们可以把 groupingBy 和 mapping 收集器结合起来，如下所示：
		Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = Dish.menu.stream()
				.collect(groupingBy(Dish::getType, mapping(dishs -> {
					if (dishs.getCalories() <= 400)
						return CaloricLevel.DIET;
					else if (dishs.getCalories() <= 700)
						return CaloricLevel.NORMAL;
					else
						return CaloricLevel.FAT;
				}, toSet())));
		Map<Type, List<CaloricLevel>> collect6 = Dish.menu.stream().collect(groupingBy(Dish::getType,mapping(dishse->{
			if(dishse.getCalories() <= 400) {
				return CaloricLevel.DIET;
			}else{
				return null;
			}
		}, toList())));
		System.out.println("rnmgbhahahhahah"+collect6);
		System.out.println(caloricLevelsByType);
		Set<CaloricLevel> set = caloricLevelsByType.get(Dish.Type.MEAT);
		System.out.println(set);
		
		Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType1 =
				Dish.menu.stream().collect(
				groupingBy(Dish::getType, mapping(
				e -> { if (e.getCalories() <= 400) return CaloricLevel.DIET;
				else if (e.getCalories() <= 700) return CaloricLevel.NORMAL;
				else return CaloricLevel.FAT; },
				toCollection(LinkedHashSet::new) )));
		System.out.println(caloricLevelsByType1);
		
		Map<Boolean, List<Dish>> partitionedMenu =Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian));
	System.out.println(partitionedMenu);
	List<Dish> list = partitionedMenu.get(false);
	System.out.println(list);
	List<Dish> vegetarianDishes =
			Dish.menu.stream().filter(Dish::isVegetarian).collect(toList());
	System.out.println(vegetarianDishes);
	//在区分素食分类的情况下还可以在往下分组
	Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType =
			Dish.menu.stream().collect(
			partitioningBy(Dish::isVegetarian,
			groupingBy(Dish::getType)));
	Map<Boolean, Map<Integer, List<Dish>>> collect7 = Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian,groupingBy(Dish::getCalories)));
	System.out.println(collect7);
	System.out.println(vegetarianDishesByType);
	
//	这里，对于分区产生的素食和非素食子流，分别按类型对菜肴分组，得到了一个二级 Map ，
//	和6.3.1节的二级分组得到的结果类似。再举一个例子，你可以重用前面的代码来找到素食和非素
//	食中热量最高的菜：
	Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
			Dish.menu.stream().collect(
		   //区分素食与荤食
			partitioningBy(Dish::isVegetarian,
			collectingAndThen(
	       //在素食中比较热量最大的  在荤食中比较热量最大的 所以只会产生两组值
			maxBy(comparingInt(Dish::getCalories)),
			//取出Optional
			Optional::get)));
	
	System.out.println("素食与荤食中最大热量是食物是:"+mostCaloricPartitionedByVegetarian);
	Map<Boolean, Dish> collect8 = Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian,collectingAndThen(minBy(comparingInt(Dish::getCalories)),Optional::get)));
	
	
	System.out.println(collect8);
	
	
//	测验6.2：使用 partitioningBy
//	我们已经看到，和 groupingBy 收集器类似， partitioningBy 收集器也可以结合其他收
//	集器使用。尤其是它可以与第二个 partitioningBy 收集器一起使用来实现多级分区。以下多
//	级分区的结果会是什么呢？
//	(1)  
	Map<Boolean, Map<Boolean, List<Dish>>> collect9 = Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian,partitioningBy (d -> d.getCalories() > 500)));
       System.out.println(collect9);
	//	(2) 
	//Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian,partitioningBy (Dish::getType)));
//	(3)  
	Map<Boolean, Long> collect10 = Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian,counting()));
	System.out.println(collect10);
//	答案如下。
//	(1) 这是一个有效的多级分区，产生以下二级 Map ：
//	{ false={false=[chicken, prawns, salmon], true=[pork, beef]},
//	true={false=[rice, season fruit], true=[french fries, pizza]}}
//	(2) 这无法编译，因为 partitioningBy 需要一个谓词，也就是返回一个布尔值的函数。
//	方法引用 Dish::getType 不能用作谓词。
//	(3) 它会计算每个分区中项目的数目，得到以下 Map ：
//	{false=5, true=4}
	Map<Boolean, List<Integer>> collect11 = IntStream.rangeClosed(2, 10).boxed()
	.collect(
	partitioningBy(candidate -> isPrimet(candidate)));
	System.out.println(collect11);
       
	
//	表6-1  Collectors 类的静态工厂方法
//	工厂方法  返回类型  用 于
//	toList  List<T>  把流中所有项目收集到一个 List
//	使用示例：List<Dish> dishes = menuStream.collect(toList());
//	toSet  Set<T>  把流中所有项目收集到一个 Set ，删除重复项
//	使用示例： Set<Dish> dishes = menuStream.collect(toSet());
//	toCollection  Collection<T>  把流中所有项目收集到给定的供应源创建的集合
//	使用示例： Collection<Dish> dishes = menuStream.collect(toCollection(),
//	ArrayList::new); 
//	counting  Long  计算流中元素的个数
//	使用示例： long howManyDishes = menuStream.collect(counting());
//	summingInt  Integer  对流中项目的一个整数属性求和
//	如果待测数字不能被流中任
//	何数字整除则返回 true
//	产生一个自然数
//	范围，从2开始，
//	直至但不包括待
//	测数
//	6.5 收集器接口 129
//	（续）
//	工厂方法  返回类型  用 于
//	使用示例： int totalCalories =
//	menuStream.collect(summingInt(Dish::getCalories));
//	averagingInt  Double  计算流中项目 Integer 属性的平均值
//	使用示例： double avgCalories =
//	menuStream.collect(averagingInt(Dish::getCalories));
//	summarizingInt  IntSummaryStatistics
//	收集关于流中项目 Integer 属性的统计值，例如最大、最小、
//	总和与平均值
//	使用示例： IntSummaryStatistics menuStatistics =
//	menuStream.collect(summarizingInt(Dish::getCalories));
//	joining`  String  连接对流中每个项目调用 toString 方法所生成的字符串
//	使用示例： String shortMenu =
//	menuStream.map(Dish::getName).collect(joining(", "));
//	maxBy  Optional<T>
//	一个包裹了流中按照给定比较器选出的最大元素的 Optional ，
//	或如果流为空则为 Optional.empty()
//	使用示例： Optional<Dish> fattest =
//	menuStream.collect(maxBy(comparingInt(Dish::getCalories)));
//	minBy  Optional<T>
//	一个包裹了流中按照给定比较器选出的最小元素的 Optional ，
//	或如果流为空则为 Optional.empty()
//	使用示例： Optional<Dish> lightest =
//	menuStream.collect(minBy(comparingInt(Dish::getCalories)));
//	reducing  归约操作产生的类型
//	从一个作为累加器的初始值开始，利用 BinaryOperator 与流
//	中的元素逐个结合，从而将流归约为单个值
//	使用示例： int totalCalories =
//	menuStream.collect(reducing(0, Dish::getCalories, Integer::sum));
//	collectingAndThen 转换函数返回的类型  包裹另一个收集器，对其结果应用转换函数
//	使用示例： int howManyDishes =
//	menuStream.collect(collectingAndThen(toList(), List::size));
//	groupingBy  Map<K, List<T>>
//	根据项目的一个属性的值对流中的项目作问组，并将属性值作
//	为结果 Map 的键
//	使用示例： Map<Dish.Type,List<Dish>> dishesByType =
//	menuStream.collect(groupingBy(Dish::getType));
//	partitioningBy  Map<Boolean,List<T>> 根据对流中每个项目应用谓词的结果来对项目进行分区
//	使用示例： Map<Boolean,List<Dish>> vegetarianDishes =
//	menuStream.collect(partitioningBy(Dish::isVegetarian));
//	本章开头提到过，所有这些收集器都是对 Collector 接口的实现，因此我们会在本章剩余部
//	分中详细讨论这个接口。我们会看看这个接口中的方法，然后探讨如何实现你自己的收集器。
	
//	1. 建立新的结果容器： supplier 方法
//	supplier 方法必须返回一个结果为空的 Supplier ，也就是一个无参数函数，在调用时它会
//	创建一个空的累加器实例，供数据收集过程使用。很明显，对于将累加器本身作为结果返回的收
//	集器，比如我们的 ToListCollector ，在对空流执行操作的时候，这个空的累加器也代表了收
//	6.5 收集器接口 131
//	集过程的结果。在我们的 ToListCollector 中， supplier 返回一个空的 List ，如下所示：
//	public Supplier<List<T>> supplier() {
//	return () -> new ArrayList<T>();
//	}
//	请注意你也可以只传递一个构造函数引用：
	
//	. 将元素添加到结果容器： accumulator 方法
//	accumulator 方法会返回执行归约操作的函数。当遍历到流中第n个元素时，这个函数执行
//	时会有两个参数：保存归约结果的累加器（已收集了流中的前 n1 个项目），还有第n个元素本身。
//	该函数将返回 void ，因为累加器是原位更新，即函数的执行改变了它的内部状态以体现遍历的
//	元素的效果。对于 ToListCollector ，这个函数仅仅会把当前项目添加至已经遍历过的项目的
//	列表：
//	public BiConsumer<List<T>, T> accumulator() {
//	return (list, item) -> list.add(item);
//	}
//	你也可以使用方法引用，这会更为简洁：
//	public BiConsumer<List<T>, T> accumulator() {
//	return List::add;
//	}
//	3. 对结果容器应用最终转换： finisher 方法
//	在遍历完流后， finisher 方法必须返回在累积过程的最后要调用的一个函数，以便将累加
//	器对象转换为整个集合操作的最终结果。通常，就像 ToListCollector 的情况一样，累加器对
//	象恰好符合预期的最终结果，因此无需进行转换。所以 finisher 方法只需返回 identity 函数：
//	public Function<List<T>, List<T>> finisher() {
//	return Function.identity();
//	}
//	这三个方法已经足以对流进行顺序归约，至少从逻辑上看可以按图6-7进行。实践中的实现
//	细节可能还要复杂一点，一方面是因为流的延迟性质，可能在 collect 操作之前还需要完成其他
//	中间操作的流水线，另一方面则是理论上可能要进行并行归约。
	
	Collector<A,T,R> collectorImpl =new Collector<A, T, R>() {

		@Override
		public Supplier<T> supplier() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public BiConsumer<T, A> accumulator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Function<T, R> finisher() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public BinaryOperator<T> combiner() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Set<Characteristics> characteristics() {
			return null;
		}
	};
	
//	5.  characteristics 方法
//	最后一个方法—— characteristics 会返回一个不可变的 Characteristics 集合，它定义
//	了收集器的行为——尤其是关于流是否可以并行归约，以及可以使用哪些优化的提示。
//	Characteristics 是一个包含三个项目的枚举。
//	  UNORDERED ——归约结果不受流中项目的遍历和累积顺序的影响。
//	  CONCURRENT —— accumulator 函数可以从多个线程同时调用，且该收集器可以并行归
//	约流。如果收集器没有标为 UNORDERED ，那它仅在用于无序数据源时才可以并行归约。
//	  IDENTITY_FINISH ——这表明完成器方法返回的函数是一个恒等函数，可以跳过。这种
//	情况下，累加器对象将会直接用作归约过程的最终结果。这也意味着，将累加器 A 不加检
//	查地转换为结果 R 是安全的。
//	我们迄今开发的 ToListCollector 是 IDENTITY_FINISH 的，因为用来累积流中元素的
//	134 第 6章 用流收集数据
//	List 已经是我们要的最终结果，用不着进一步转换了，但它并不是 UNORDERED ，因为用在有序
//	流上的时候，我们还是希望顺序能够保留在得到的 List 中。最后，它是 CONCURRENT 的，但我们
//	刚才说过了，仅仅在背后的数据源无序时才会并行处理。
	
	List<Dish> dishes = Dish.menu.stream().collect(
			ArrayList::new,
			List::add,
			List::addAll);
	System.out.println(dishes);
	
	Supplier<User> user=User::new;
	System.out.println(user);

	long start12 = System.nanoTime();
	long rangedSum = rangedSum(10);
	System.out.println(System.nanoTime()-start12);
	
	long start13 = System.nanoTime();
	long parallelRangedSum = parallelRangedSum(10);
	System.out.println(System.nanoTime()-start13);
	System.out.println(rangedSum);
	
	long start14 = System.nanoTime();
	long reduce60 = LongStream.rangeClosed(1, 10).parallel().reduce(5L,Long::sum);
	System.out.println(System.nanoTime()-start14);
	
	OptionalLong reduce6 = LongStream.rangeClosed(1, 10).reduce((t1,t2)->t1*t2);
	System.out.println(reduce6.getAsLong());
	
	long ten = sideEffectParallelSum(10);
	System.out.println(ten);
	
	RecursiveTask<Integer> recursiveTask=new RecursiveTask<Integer>() {
		
		@Override
		protected Integer compute() {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	
	}
	
	public static long sideEffectSum(long n) {
		Accumulator accumulator = new Accumulator();
		LongStream.rangeClosed(1, n).forEach(accumulator::add);
		return accumulator.total;
	}
	public static long sideEffectParallelSum(long n) {
		Accumulator accumulator = new Accumulator();
		LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
		return accumulator.total;
		}
	public static class Accumulator {
		public long total = 0;

		public void add(long value) {
			total += value;
		}
	}
	
	public static long rangedSum(long n) {
		return LongStream.rangeClosed(1, n)
				.reduce(0L, Long::sum);
	}
	public static long parallelRangedSum(long n) {
		return LongStream.rangeClosed(1, n)
		.parallel()
		.reduce(0L, Long::sum);
		}
	//代码清单6-6 将前n个自然数按质数和非质数分区
	public Map<Boolean, List<Integer>> partitionPrimes1(int n) {
	return IntStream.rangeClosed(2, n).boxed().collect(partitioningBy(candidate -> isPrime1(candidate)));
	}
	//当时，通过限制除数不超过被测试数的平方根，我们对最初的 isPrime 方法做了一些改进：
	public boolean isPrime1(int candidate) {
	int candidateRoot = (int) Math.sqrt((double) candidate);
	return IntStream.rangeClosed(2, candidateRoot)
	.noneMatch(i -> candidate % i == 0);
	}
	
	public <T> Supplier<List<T>> supplier() {
		return ArrayList::new;
	}
	
	public <T> BiConsumer<List<T>, T> accumulator(){
		return List::add;
	}
	public<T> Function<List<T>, List<T>> finisher() {
	return Function.identity();
	}
	
	public<T> BiConsumer<List<T>, T> accumulators() {
	return (list, item) -> list.add(item);
	}
	
	
//	public Supplier<List<T>> supplier(){
//		return ArrayList::new;
//	}
	public<T> BinaryOperator<List<T>> combiner() {
		return (list1, list2) -> {
		list1.addAll(list2);
		return list1; 
		};
	}
	
	public interface Collector<T, A, R> {
		Supplier<A> supplier();
		BiConsumer<A, T> accumulator();
		Function<A, R> finisher();
		BinaryOperator<A> combiner();
		Set<Characteristics> characteristics();
		}
//		本列表适用以下定义。
//		  T 是流中要收集的项目的泛型。
//		  A 是累加器的类型，累加器是在收集过程中用于累积部分结果的对象。
//		  R 是收集操作得到的对象（通常但并不一定是集合）的类型。
//		例如，你可以实现一个 ToListCollector<T> 类，将 Stream<T> 中的所有元素收集到一个
//		List<T> 里，它的签名如下：
	//	public class ToListCollector<T> implements Collector<T, List<T>, List<T>>
//		我们很快就会澄清，这里用于累积的对象也将是收集过程的最终结果。
	
//	假设你要写一个方法，它接受参数 int n，并将前n个自然数分为质数和非质数。但首先，找
//	出能够测试某一个待测数字是否是质数的谓词会很有帮助：
	public static boolean isPrimet(int candidate) {
	return IntStream.range(2, candidate)
	.noneMatch(i -> candidate % i == 0);
	}
//	一个简单的优化是仅测试小于等于待测数平方根的因子：
	public boolean isPrimed(int candidate) {
	int candidateRoot = (int) Math.sqrt((double) candidate);
	return IntStream.rangeClosed(2, candidateRoot)
	.noneMatch(i -> candidate % i == 0);
	}
//	现在最主要的一部分工作已经做好了。为了把前n个数字分为质数和非质数，只要创建一
//	个包含这n个数的流，用刚刚写的 isPrime 方法作为谓词，再给 partitioningBy 收集器归约
//	就好了：
	public Map<Boolean, List<Integer>> partitionPrimes(int n) {
	return IntStream.rangeClosed(2, n).boxed()
	.collect(
	partitioningBy(candidate -> isPrimet(candidate)));
	}
//	现在我们已经讨论过了 Collectors 类的静态工厂方法能够创建的所有收集器，并介绍了使
//	用它们的实际例子。表6-1将它们汇总到一起，给出了它们应用到 Stream<T> 上返回的类型，以
//	及它们用于一个叫作 menuStream 的 Stream<Dish> 上的实际例子。
	public boolean isPrime(int candidate) {
		return IntStream.range(2, candidate)
		.noneMatch(i -> candidate % i == 0);
		}
	
	
	public enum CaloricLevel {
		DIET, NORMAL, FAT
	}
    Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = Dish.menu.stream().collect(groupingBy(dish -> {
    		if (dish.getCalories() <= 400)
    			return CaloricLevel.DIET;
    		else if (dish.getCalories() <= 700)
    			return CaloricLevel.NORMAL;
    		else
    			return CaloricLevel.FAT;
    	}));
	
	
	/*
	 * public class Enum_Set_Demo {
	 * 
	 * public static void main(String[] args) {
	 * 
	 * // Creating an EnumSet EnumSet<GFG> e_set;
	 * 
	 * // Adding elements e_set = EnumSet.of(GFG.The);
	 * 
	 * // Displaying the updated set System.out.println("The enum set is: " +
	 * e_set);
	 * 
	 * // Adding elements e_set = EnumSet.of(GFG.Geeks);
	 * 
	 * // Displaying the updated set System.out.println("The enum set is: " +
	 * e_set);
	 * 
	 * // Adding elements e_set = EnumSet.of(GFG.Welcome);
	 * 
	 * // Displaying the updated set System.out.println("The enum set is: " +
	 * e_set); } }
	 */
	public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }
	
//    public static <T> Collector<T, ?, Long> counting() {
//        return reducing(0L, e -> 1L, Long::sum);
//        }
	
	public final class Good{
		public String get(String name) {
			return name.toString();
		}
	}

    public static List<Apple> filterHeavyApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor()); 
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }       

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
    public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
    	@Override
    	public Supplier<List<T>> supplier() {
    	return ArrayList::new;
    	}
    	@Override
    	public BiConsumer<List<T>, T> accumulator() {
    	return List::add;
    	}
    	@Override
    	public Function<List<T>, List<T>> finisher() {
    	return Function.identity();
    	}
    	@Override
    	public BinaryOperator<List<T>> combiner() {
    	return (list1, list2) -> {
    	list1.addAll(list2);
    	return list1;
    	};
    	}
    	@Override
    	public Set<Characteristics> characteristics() {
			return null;
			/*
			 * return Collections.unmodifiableSet(EnumSet.of( IDENTITY_FINISH, CONCURRENT));
			 */
    	}
    	}
}
