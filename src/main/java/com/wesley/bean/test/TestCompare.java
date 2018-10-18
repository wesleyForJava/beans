package com.wesley.bean.test;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Supplier;


public class TestCompare {
	private static Logger logger = LoggerFactory.getLogger(TestCompare.class);

	@FunctionalInterface
	interface Converter<F, T> {
		T convert(F from);
	}

	public static void main(String[] args) {
		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

		// TODO jdk1.7这样写
		/*
		 * Collections.sort(names, new Comparator<String>() {
		 * 
		 * @Override public int compare(String a, String b) { return a.compareTo(b); }
		 * });
		 */
		// System.out.println(names);
		// lambada
		// Collections.sort(names,(String a,String b)->a.compareTo(b));
		Collections.sort(names, (a, b) -> a.compareTo(b));
		System.out.println(names);

		// 函数式接口
		/*
		 * Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
		 * Integer convert = converter.convert("123"); System.out.println(convert);
		 */

		// Java 8 允许你使用 :: 关键字来传递方法或者构造函数引用
		/*
		 * Converter<String, Integer> converter = Integer::valueOf; Integer converted =
		 * converter.convert("123"); System.out.println(converted); // 123
		 */
		/*
		 * converter = Person::startsWith; String converted = converter.convert("Java");
		 * System.out.println(converted); // "J"
		 */

		// PersonFactory<Person> person=Person::new;
		// Person create = person.create("黎明", "活");
		// System.out.println(create);

		/*
		 * final int num = 1; Converter<Integer, String> stringConverter = (from) ->
		 * String.valueOf(from + num);
		 * 
		 * String convert = stringConverter.convert(2); // 3
		 * System.out.println(convert);
		 */

		// Function
		Function<String, Integer> toInteger = Integer::valueOf;
		Function<String, String> backToString = toInteger.andThen(String::valueOf);
		String apply = backToString.apply("123"); // "123"
		System.out.println(apply);

		// Supplier
		Supplier<Person> person = Person::new;
		System.out.println(person.get().getClass().getName());

		// Comsumer接口
		Consumer<Person> greeter = (p) -> System.out.println("Hello!" + p.firstName);
		greeter.accept(new Person("Lily", "mimi"));

		// Comparator 接口
		Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);

		Person p1 = new Person("John", "Doe");
		Person p2 = new Person("Alice", "Wonderland");

		int compare = comparator.compare(p1, p2); // > 0
		System.out.println(compare);
		int compare2 = comparator.reversed().compare(p1, p2); // < 0
		System.out.println(compare2);

		// Optional 防止报空指针异常
		Optional<String> optional = Optional.of("bam");

		logger.info("{}", optional.isPresent()); // true
		logger.info("{}", optional.get()); // "bam"
		logger.info("{}", optional.orElse("fallback")); // "bam"

		optional.ifPresent((s) -> System.out.println(s.charAt(0))); // "b"
		// Stream 接口
		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");

		// 过滤包含a的集合 filter sort
		stringCollection.stream().filter((s) -> s.contains("a")).forEach(System.out::print);
		System.out.println();
		stringCollection.stream().sorted().filter(s -> s.contains("a")).forEach(System.out::print);

		System.out.println(stringCollection);

		// Map映射
		stringCollection.stream().map(String::toUpperCase).sorted((a, b) -> a.compareTo(b))
				.forEach(System.out::println);

		// Match匹配
		boolean anyMatch = stringCollection.stream().anyMatch((s) -> s.contains("a"));
		boolean allMatch = stringCollection.stream().allMatch((s) -> s.contains("a"));
		boolean noneMatch = stringCollection.stream().noneMatch((s) -> s.contains("z"));
		System.out.println(anyMatch);
		System.out.println(allMatch);
		System.out.println(noneMatch);

		// Count
		long startsWithB = stringCollection.stream().filter((s) -> s.startsWith("b")).count();

		System.out.println(startsWithB); // 3
		// Reduce 规约
		// 这是一个最终操作，允许通过指定的函数来讲stream中的多个元素规约为一个元素，规越后的结果是通过Optional接口表示
		Optional<String> reduce = stringCollection.stream().reduce((a, b) -> a + "#" + b);
		reduce.ifPresent(System.out::println);

		// 并行Streams
		int max = 1000000;
		List<String> values = new ArrayList<>(max);
		for (int i = 0; i < max; i++) {
			UUID uuid = UUID.randomUUID();
			values.add(uuid.toString());
		}
		// 计算下面这个串行stream排序要多久
		long t0 = System.nanoTime();
		long count = values.stream().sorted().count();
		System.out.println(count);
		long t1 = System.nanoTime();
		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("sequential sort took: %d ms", millis));
		// 串行耗时: 600 ms

		// 再看一下串行的排序
		long t2 = System.nanoTime();
		long count1 = values.parallelStream().sorted().count();
		System.out.println(count1);
		long t3 = System.nanoTime();
		long millis1 = TimeUnit.NANOSECONDS.toMillis(t3 - t2);
		System.out.println(String.format("parallel sort took: %d ms", millis1));
		// 并行排序耗时: 259 ms

		// 前面提到过，Map类型不支持stream，不过Map提供了一些新的有用的方法来处理一些日常任务。
		Map<Integer, String> map = new HashMap<>();

		for (int i = 0; i < 10; i++) {
			map.putIfAbsent(i, "val" + i);
		}

		map.forEach((id, val) -> System.out.print(val));

		// 在map中计算
		map.computeIfPresent(3, (num, val) -> val + num);
		logger.info("{}", map.get(3)); // val33

		map.computeIfPresent(9, (num, val) -> null);
		logger.info("{}", map.containsKey(9)); // false

		map.computeIfAbsent(23, num -> "val" + num);
		logger.info("{}", map.containsKey(23)); // true

		map.computeIfAbsent(3, num -> "bam");
		logger.info("{}", map.get(3)); // val33

		map.remove(3, "val3");
		logger.info("{}", map.get(3));
		
		map.remove(3, "val33");
		logger.info("{}", map.get(3));
		
		logger.info("{}",map.getOrDefault(42, "not found"));  // not found
		
		map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
		logger.info("{}",map.get(9));             // val9
		 
		map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
		logger.info("{}",map.get(9));             // val9concat
        
		
		//Date API
		Clock clock=Clock.systemDefaultZone();
		long millis2 = clock.millis();
		logger.info("时间为:"+millis2);
		Instant instant = clock.instant();
		Date from = Date.from(instant);
		logger.info("时间为:"+from);
		
		//Timezones 时区
		//在新API中时区使用ZoneId来表示。时区可以很方便的使用静态方法of来获取到。 时区定义了到UTS时间的时间差，在Instant时间点对象到本地日期对象之间转换的时候是极其重要的。
		
		System.out.println(ZoneId.getAvailableZoneIds());
		// prints all available timezone ids
		 
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		ZoneId zone2 = ZoneId.of("Brazil/East");
		System.out.println(zone1.getRules());
		System.out.println(zone2.getRules());
		 
		// ZoneRules[currentStandardOffset=+01:00]
		// ZoneRules[currentStandardOffset=-03:00]
		
		LocalTime now1 = LocalTime.now(zone1);
		LocalTime now2 = LocalTime.now(zone2);
		 
		System.out.println(now1.isBefore(now2));  // false
		 
		long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
		long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
		 //相差几个小时
		System.out.println(hoursBetween);       // -3
		//相差多少分钟
		System.out.println(minutesBetween);     // -239

         
         LocalTime late = LocalTime.of(23, 59, 59);
         System.out.println(late);       // 23:59:59
          
         DateTimeFormatter germanFormatter =
             DateTimeFormatter
                 .ofLocalizedTime(FormatStyle.SHORT)
                 .withLocale(Locale.FRANCE);
          
         LocalTime leetTime = LocalTime.parse("14:37", germanFormatter);
         System.out.println(leetTime);   // 13:37

		
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
		LocalDate yesterday = tomorrow.minusDays(2);
		System.out.println(today); 
		System.out.println(tomorrow); 
		System.out.println(yesterday); 
		
		LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
		System.out.println(independenceDay);
		DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
		 
		System.out.println(dayOfWeek);    // FRIDAY
		
		 germanFormatter =
			    DateTimeFormatter
			        .ofLocalizedDate(FormatStyle.MEDIUM)
			        .withLocale(Locale.GERMAN);
			 
			LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
			System.out.println(xmas);   // 2014-12-24

			LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
			 
			DayOfWeek dayOfWeeks = sylvester.getDayOfWeek();
			System.out.println(dayOfWeeks);      // WEDNESDAY
			 
			Month month = sylvester.getMonth();
			System.out.println(month);          // DECEMBER
			 
			long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
			System.out.println(minuteOfDay);    // 1439
			
			Instant instants = sylvester
			        .atZone(ZoneId.systemDefault())
			        .toInstant();
			 
			Date legacyDate = Date.from(instants);
			System.out.println(legacyDate);     // Wed Dec 31 23:59:59 CET 2014
           
			
			//Annotation 注解
			Hint hint = Person.class.getAnnotation(Hint.class);
			System.out.println(hint);                   // null
	//TODO 有待改进		 
	/*			Hints hints1 = Person.class.getAnnotation(Hints.class);
				System.out.println(hints1.value().length);  // 2
			 
				Hint[] hints2 = Person.class.getAnnotationsByType(Hint.class);
				System.out.println(hints2.length);          // 2
	*/

			
	}

	interface PersonFactory<P extends Person> {
		P create(String firstName, String lastName);
	}

	
	static class Person {

		String firstName;
		String lastName;

		Person() {
		}

		Person(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}

		@Override
		public String toString() {
			return "Person [firstName=" + firstName + ", lastName=" + lastName + "]";
		}

	}

/*	static class Lambda4 {
		static int outerStaticNum;
		int outerNum;

		void testScopes() {
			Converter<Integer, String> stringConverter1 = (from) -> {
				outerNum = 23;
				return String.valueOf(from);
			};

			Converter<Integer, String> stringConverter2 = (from) -> {
				outerStaticNum = 72;
				return String.valueOf(from);
			};
		}
	}*/
}
