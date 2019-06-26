package com.wesley.bean.array;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Supplier;
import java.util.function.ToIntBiFunction;
import java.util.stream.Stream;

import com.alibaba.druid.sql.visitor.functions.Length;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.groupingBy;

public class FilteringAppless {
	public static void main(String ... args) throws Exception{

		List<Apple> inventory = Arrays.asList(new Apple(80,"green"), new Apple(155, "green"), new Apple(120, "red"));	

		// [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
		List<Apple> greenApples = filterApplesByColor(inventory, "green");
		System.out.println(greenApples);

		// [Apple{color='red', weight=120}]
		List<Apple> redApples = filterApplesByColor(inventory, "red");
		System.out.println(redApples);

		// [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
		List<Apple> greenApples2 = filter(inventory, new AppleColorPredicate());
		System.out.println(greenApples2);

		// [Apple{color='green', weight=155}]
		List<Apple> heavyApples = filter(inventory, new AppleWeightPredicate());
		System.out.println(heavyApples);

		// []
		List<Apple> redAndHeavyApples = filter(inventory, new AppleRedAndHeavyPredicate());
		System.out.println(redAndHeavyApples);

		// [Apple{color='red', weight=120}]
		List<Apple> redApples2 = filter(inventory, new ApplePredicate() {
			public boolean test(Apple a){
				return a.getColor().equals("red"); 
			}
		});
		System.out.println(redApples2);
		
		List<Apple> filter = filter(inventory, (Apple p)->p.getColor()=="red");
		System.out.println(filter);
		List<Apple> filter2 = filter(inventory, (Apple p)->p.getColor()=="green");
		System.out.println(filter2);
		
		List<Integer> numbers=Arrays.asList(1,2,3,4);
		List<Integer> filter3 = filter(numbers,(Integer i)->i%2==0);
		System.out.println(filter3);
		
		inventory.sort((Apple a1,Apple a2)->a1.getWeight().compareTo(a2.getWeight()));
		
//		(List<String> list) -> list.isEmpty();
////		创建对象  
//		() -> new Apple(10);
////		消费一个对象
//		(Apple a) -> {
//		System.out.println(a.getWeight());
//		};
////		从一个对象中选择/抽取
//		(String s) -> s.length();
////		组合两个值  
//		(int a, int b) -> a * b;
////		比较两个对象
//		(Apple a1, Apple a2) ->	a1.getWeight().compareTo(a2.getWeight());	
		process(() -> System.out.println("This is awesome!!"));
//		try {
//			String result = processFile((BufferedReader br) ->
//			br.readLine() + br.readLine());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		forEach(
				Arrays.asList(1,2,3,4,5),
				(Integer i) -> System.out.println(i)
				);
//		List<Integer> l = map(
//				Arrays.asList("lambdas","in","action"),
//				(String s) -> s.length()
//				);
//		System.out.println(l);
		IntPredicate numberIntPredicate= (int i)->i%2==0;
		boolean test = numberIntPredicate.test(1001);
		System.out.println(test);
		//getlist((list) -> list.isEmpty());  
		Callable<Integer> c = () -> 42;
		System.out.println(c.call().intValue());
		PrivilegedAction<Integer> p = () -> 42;
		System.out.println(p.toString());
		
		
	//	Comparator<Apple> c1 =	(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
	//	ToIntBiFunction<Apple, Apple> c2 =	(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
	//	BiFunction<Apple, Apple, Integer> c3 =			(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
		// Predicate返回了一个boolean
	//	List<String> list=new ArrayList<String>();
		//Predicate<String> p1 = s -> list.add(s);
		// Consumer返回了一个void
	//	Consumer<String> b = s -> list.add(s);
//		闭包
//		你可能已经听说过闭包（closure，不要和Clojure编程语言混淆）这个词，你可能会想
//		Lambda是否满足闭包的定义。用科学的说法来说，闭包就是一个函数的实例，且它可以无限
//		制地访问那个函数的非本地变量。例如，闭包可以作为参数传递给另一个函数。它也可以访
//		问和修改其作用域之外的变量。现在，Java 8的Lambda和匿名类可以做类似于闭包的事情：
//		它们可以作为参数传递给方法，并且可以访问其作用域之外的变量。但有一个限制：它们不
//		能修改定义Lambda的方法的局部变量的内容。这些变量必须是隐式最终的。可以认为Lambda
//		是对值封闭，而不是对变量封闭。如前所述，这种限制存在的原因在于局部变量保存在栈上，
//		并且隐式表示它们仅限于其所在线程。如果允许捕获可改变的局部变量，就会引发造成线程
//		不安全的新的可能性，而这是我们不想看到的（实例变量可以，因为它们保存在堆中，而堆
//		是在线程之间共享的）。
//		错误：Lambda表达式引用的局部变量必须是最终的（ final ）
//		或事实上最终的
//		现在，我们来介绍你会在Java 8代码中看到的另一个功能：方法引用。可以把它们视为某些
//		Lambda的快捷写法。
		 int portNumber = 1337;
		Runnable r = () -> System.out.println(portNumber);
		r.run();
		
		inventory.sort(comparing(Apple::getWeight));
//		(Apple a) -> a.getWeight()  Apple::getWeight
//		() -> Thread.currentThread().dumpStack()  Thread.currentThread()::dumpStack
//		(str, i) -> str.substring(i)  String::substring
//		(String s) -> System.out.println(s)  System.out::println
		
	//	String labamdString="2222";
		//(labamdString l)->{Integer.parseInt(l)};
		List<String> str = Arrays.asList("a","b","A","B");
		str.sort((a1,b1)->a1.compareToIgnoreCase(b1));
		str.sort(String::compareToIgnoreCase);		
		
		Function<String, Integer> stringToInteger = Integer::parseInt;
		Integer apply = stringToInteger.apply("200");
		System.out.println(apply);
		
		
		BiPredicate<List<String>, String> contains = List::contains;
		boolean test2 = contains.test(str, "green");
		System.out.println(test2);
		//Supplier<Apple> dd = Apple::new;
		//Supplier<Apple> ee =()->new Apple(1,"2");
		//Apple a1 = dd.get();
		List<Integer> weights = Arrays.asList(7, 3, 4, 10);
		//List<Apple> apples = maps(weights, Apple::new);
		List<Apple> maps=map(weights, (a)->new Apple());
		System.out.println(maps.toString());
		
		/*
		 * BiFunction<String, Integer, Apple> c3 = Apple::new; Apple c3 =
		 * c3.apply("green", 110);
		 */
		
		BiFunction<String, Integer, Apple> xx =	(color, weight) -> new Apple(weight, color);
				Apple xxxx = xx.apply("green",110);
				System.out.println(xxxx.toString());
				
				
//				为了检验你对方法和构造函数引用的理解程度，试试测验3.7吧！
//				测验3.7：构造函数引用
//				你已经看到了如何将有零个、一个、两个参数的构造函数转变为构造函数引用。那要怎么
//				样才能对具有三个参数的构造函数，比如 Color(int, int, int)， 使用构造函数引用呢？
//				答案：你看，构造函数引用的语法是 ClassName::new ，那么在这个例子里面就是
//				Color::new 。但是你需要与构造函数引用的签名匹配的函数式接口。但是语言本身并没有提
//				供这样的函数式接口，你可以自己创建一个：
//				public interface TriFunction<T, U, V, R>{
//				R apply(T t, U u, V v);
//				}
//				现在你可以像下面这样使用构造函数引用了：
//				TriFunction<Integer, Integer, Integer, Color> colorFactory = Color::new;
//				我们讲了好多新内容：Lambda、函数式接口和方法引用。我们会在下一节把这一切付诸实践！
//				3.7 Lambda 和方法引用实战
//				为了给这一章还有我们讨论的所有关于Lambda的内容收个尾，我们需要继续研究开始的那
//				个问题——用不同的排序策略给一个 Apple 列表排序，并需要展示如何把一个原始粗暴的解决方
//				用要求的颜色和重
//				量 创 建 一个 Apple
//				的Lambda表达式
//				调用该 BiFunction 函数的 apply
//				方法，并给出要求的颜色和重量，
//				将产生一个新的 Apple 对象
//				你用 map 得到了一个
//				Function<Integer,
//				Fruit>
//				用 Integer 类型的 weight 参数调用 Function
//				的 apply() 方法将提供所要求的 Frui
				inventory.sort(comparing(Apple::getWeight));
				
				
				//逆序
				inventory.sort(comparing(Apple::getWeight).reversed());
				
				
//				2. 比较器链
//				上面说得都很好，但如果发现有两个苹果一样重怎么办？哪个苹果应该排在前面呢？你可能
//				需要再提供一个 Comparator 来进一步定义这个比较。比如，在按重量比较两个苹果之后，你可
//				能想要按原产国排序。 thenComparing 方法就是做这个用的。它接受一个函数作为参数（就像
//				comparing 方法一样），如果两个对象用第一个 Comparator 比较之后是一样的，就提供第二个
//				Comparator 。你又可以优雅地解决这个问题了：
				inventory.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));
//				3.8.2 谓词复合
//				谓词接口包括三个方法： negate 、 and 和 or ，让你可以重用已有的 Predicate 来创建更复
//				杂的谓词。比如，你可以使用 negate 方法来返回一个 Predicate 的非，比如苹果不是红的：
//				Predicate<Apple> notRedApple = redApple.negate();
//				你可能想要把两个Lambda用 and 方法组合起来，比如一个苹果既是红色又比较重：
//				Predicate<Apple> redAndHeavyApple =
//				redApple.and(a -> a.getWeight() > 150);
//				你可以进一步组合谓词，表达要么是重（150克以上）的红苹果，要么是绿苹果：
//				Predicate<Apple> redAndHeavyAppleOrGreen =
//				redApple.and(a -> a.getWeight() > 150)
//				.or(a -> "green".equals(a.getColor()));
//				这一点为什么很好呢？从简单Lambda表达式出发，你可以构建更复杂的表达式，但读起来
//				仍然和问题的陈述差不多！请注意， and 和 or 方法是按照在表达式链中的位置，从左向右确定优
//				先级的。因此， a.or(b).and(c) 可以看作 (a || b) && c 。	
				
				
//				3.8.3 函数复合
//				最后，你还可以把 Function 接口所代表的Lambda表达式复合起来。 Function 接口为此配
//				了 andThen 和 compose 两个默认方法，它们都会返回 Function 的一个实例。
//				andThen 方法会返回一个函数，它先对输入应用一个给定函数，再对输出应用另一个函数。
//				比如，假设有一个函数 f 给数字加1  (x -> x + 1) ，另一个函数 g 给数字乘2，你可以将它们组
//				合成一个函数 h ，先给数字加1，再给结果乘2：
//				Function<Integer, Integer> f = x -> x + 1;
//				Function<Integer, Integer> g = x -> x * 2;
//				Function<Integer, Integer> h = f.andThen(g);
//				int result = h.apply(1);
//				你也可以类似地使用 compose 方法，先把给定的函数用作 compose 的参数里面给的那个函
//				数，然后再把函数本身用于结果。比如在上一个例子里用 compose 的话，它将意味着 f(g(x)) ，
//				而 andThen 则意味着 g(f(x)) ：
//				Function<Integer, Integer> f = x -> x + 1;
//				Function<Integer, Integer> g = x -> x * 2;
//				Function<Integer, Integer> h = f.compose(g);
//				int result = h.apply(1);
//				图3-6说明了 andThen 和 compose 之间的区别。
				
//				现在你可以通过复合这些工具方法来创建各种转型流水线了，比如创建一个流水线：先加上
//				抬头，然后进行拼写检查，最后加上一个落款，如图3-7所示。
				Function<String, String> addHeader = Letter::addHeader;
				Function<String, String> transformationPipeline	= addHeader.andThen(Letter::checkSpelling).andThen(Letter::addFooter);
//				图3-7 使用 andThen 的转换流水线
//				第二个流水线可能只加抬头、落款，而不做拼写检查：
				Function<String, String> addHeaders = Letter::addHeader;
				Function<String, String> transformationPipelines	= addHeaders.andThen(Letter::addFooter);
				List<String> lowCaloricDishesName =	Dish.menu.stream()
				.filter(d -> d.getCalories() < 400)
				.sorted(comparing(Dish::getCalories))
				.map(Dish::getName)
				.collect(toList());		
				
				Map<Dish.Type, List<Dish>> dishesByType =
						Dish.menu.stream().collect(groupingBy(Dish::getType));
				
				
				List<String> threeHighCaloricDishNames =
				Dish.menu.stream().filter(d -> d.getCalories() > 300)
//				从 menu 获得流
//				（菜肴列表）
//				建立操作流水线：
//				首先选出高热量的
//				菜肴
				.map(Dish::getName)
				.limit(3)
				.collect(toList());
				System.out.println(threeHighCaloricDishNames);
				
				
				//Java现有的集合概念和新的流概念都提供了接口，来配合代表元素型有序值的数据接口。所
//				谓有序，就是说我们一般是按顺序取用值，而不是随机取用的。那这两者有什么区别呢？
//				我们先来打个直观的比方吧。比如说存在DVD里的电影，这就是一个集合（也许是字节，也
//				许是帧，这个无所谓），因为它包含了整个数据结构。现在再来想想在互联网上通过视频流看同
//				样的电影。现在这是一个流（字节流或帧流）。流媒体视频播放器只要提前下载用户观看位置的
//				那几帧就可以了，这样不用等到流中大部分值计算出来，你就可以显示流的开始部分了（想想观
//				看直播足球赛）。特别要注意，视频播放器可能没有将整个流作为集合，保存所需要的内存缓冲
//				区——而且要是非得等到最后一帧出现才能开始看，那等待的时间就太长了。出于实现的考虑，
//				你也可以让视频播放器把流的一部分缓存在集合里，但和概念上的差异不是一回事。
//				粗略地说，集合与流之间的差异就在于什么时候进行计算。集合是一个内存中的数据结构，
//				它包含数据结构中目前所有的值——集合中的每个元素都得先算出来才能添加到集合中。（你可
//				以往集合里加东西或者删东西，但是不管什么时候，集合中的每个元素都是放在内存里的，元素
//				都得先算出来才能成为集合的一部分。）
//				相比之下，流则是在概念上固定的数据结构（你不能添加或删除元素），其元素则是按需计
//				算的。 这对编程有很大的好处。在第6章中，我们将展示构建一个质数流（2, 3, 5, 7, 11, …）有
//				多简单，尽管质数有无穷多个。这个思想就是用户仅仅从流中提取需要的值，而这些值——在用
//				户看不见的地方——只会按需生成。这是一种生产者－消费者的关系。从另一个角度来说，流就
//				像是一个延迟创建的集合：只有在消费者要求的时候才会计算值（用管理学的话说这就是需求驱
//				动，甚至是实时制造)。
//				与此相反，集合则是急切创建的（供应商驱动：先把仓库装满，再开始卖，就像那些昙花一
//				4.3 流与集合 75
//				现的圣诞新玩意儿一样）。以质数为例，要是想创建一个包含所有质数的集合，那这个程序算起
//				来就没完没了了，因为总有新的质数要算，然后把它加到集合里面。当然这个集合是永远也创建
//				不完的，消费者这辈子都见不着了。
//				图4-3用DVD对比在线流媒体的例子展示了流和集合之间的差异。
//				另一个例子是用浏览器进行互联网搜索。假设你搜索的短语在Google或是网店里面有很多匹
//				配项。你用不着等到所有结果和照片的集合下载完，而是得到一个流，里面有最好的10个或20
//				个匹配项，还有一个按钮来查看下面10个或20个。当你作为消费者点击“下面10个”的时候，供
//				应商就按需计算这些结果，然后再送回你的浏览器上显示。
				
				
				
//				List<String> title = Arrays.asList("Java8", "In", "Action");
//				Stream<String> s = title.stream();
//				s.forEach(System.out::println);
//				s.forEach(System.out::println);
				
				List<Integer> numbersd = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
				numbersd.stream()
				.filter(i -> i % 2 == 0)
				.distinct()
				.forEach(System.out::println);
				
				
	}
	

	public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
		List<Apple> result = new ArrayList<>();
		for (Integer e : list) {
			result.add(f.apply(e));
		}
		return result;
	}
	
	public static class Letter{
		public static String addHeader(String text){
		return "From Raoul, Mario and Alan: " + text;
		}
		public static String addFooter(String text){
		return text + " Kind regards";
		}
		public static String checkSpelling(String text){
		return text.replaceAll("labda", "lambda");
		}
		}
	

//	static Map<String, Function<Integer, Fruit>> maped = new HashMap<>();
//	static {
//		maped.put("apple", Apple::new);
//		maped.put("orange", Orange::new);
//	// etc...
//	}
//	public static Fruit giveMeFruit(String fruit, Integer weight){
//	return maped.get(fruit.toLowerCase()).apply(weight);
//	}
	
	
	@FunctionalInterface
	public interface BufferedReaderProcessor {
	String process(BufferedReader b) throws IOException;
	}
	
	public static void getlist(Predicate<List<String>> good) {
		
	}


	
	
	public static String processFile(BufferedReaderProcessor p) throws
	IOException {
		try (BufferedReader br =
				new BufferedReader(new FileReader("data.txt"))) {
				return p.process(br);
				}
	}
	
//	@FunctionalInterface
//	public interface Function<T, R>{
//	R apply(T t);
//	}
//	public static <T, R> List<R> map(List<T> list,Function<T, R> f) {
//	List<R> result = new ArrayList<>();
//	for(T s: list){
//	result.add(f.apply(s));
//	}
//	return result;
//	}

//	@FunctionalInterface
//	public interface Consumer<T>{
//	void accept(T t);
//	}
	public static <T> void forEach(List<T> list, Consumer<T> c){
	  for(T i: list){
			c.accept(i);
			}
	}

	
	private static void process(Runnable runnable) {
		runnable.run();
	}
	

	public interface Predicate<T> {
		boolean test(T t);
	}

	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> result = new ArrayList<>();
		for (T e : list) {
			if (p.test(e)) {
				result.add(e);
			}
		}
		return result;
	}
	
	public static List<Apple> filterGreenApples(List<Apple> inventory){
		List<Apple> result = new ArrayList<>();
		for(Apple apple: inventory){
			if("green".equals(apple.getColor())){
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByColor(List<Apple> inventory, String color){
		List<Apple> result = new ArrayList<>();
		for(Apple apple: inventory){
			if(apple.getColor().equals(color)){
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight){
		List<Apple> result = new ArrayList<>();
		for(Apple apple: inventory){
			if(apple.getWeight() > weight){
				result.add(apple);
			}
		}
		return result;
	}

	
	public static List<Apple> filter(List<Apple> inventory, ApplePredicate p){
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

		
		public Apple() {}
		public Apple(int weight, String color){
			this.weight = weight;
			this.color = color;
		}
		public Apple(Integer weight, String color){
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

	interface ApplePredicate{
		public boolean test(Apple a);
	}

	static class AppleWeightPredicate implements ApplePredicate{
		public boolean test(Apple apple){
			return apple.getWeight() > 150; 
		}
	}
	static class AppleColorPredicate implements ApplePredicate{
		public boolean test(Apple apple){
			return "green".equals(apple.getColor());
		}
	}

	static class AppleRedAndHeavyPredicate implements ApplePredicate{
		public boolean test(Apple apple){
			return "red".equals(apple.getColor()) 
					&& apple.getWeight() > 150; 
		}
	}
	
}
