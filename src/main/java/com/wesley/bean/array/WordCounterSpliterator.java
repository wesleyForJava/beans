package com.wesley.bean.array;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;

import static java.util.Comparator.comparing;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import com.wesley.bean.array.FilteringApples.Apple;
import com.wesley.bean.array.FilteringApples.CaloricLevel;
import com.wesley.bean.array.ForkJoinSumCalculator.WordCounter;

import groovy.util.logging.Slf4j;
import net.bytebuddy.asm.Advice.This;
@Slf4j
public class WordCounterSpliterator implements Spliterator<Character> {
	
	private static final Logger logger=LoggerFactory.getLogger(This.class);
	
    private final String words;

    //当前字符位置
    private int currentChar = 0;

    public WordCounterSpliterator(String words) {
        this.words = words;
    }

    /**
     * 处理当前字符串如果还有要处理的返回true
     * @param action
     * @return
     */
    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
    	//处理当前字符
        action.accept(words.charAt(currentChar++));
        return currentChar < words.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        // 获取需要解析字符串的大小，若小于10则不进行拆分，顺序处理
        int currentSize = words.length() - currentChar;
        if (currentSize < 10) {
            return null;//返回 null 表示要解析的 String已经足够小，可以顺序处理
        }
        // 尝试拆分位置设定为string的中间位置，系统拆分位置到下一个空格
        //将试探拆分位置设定为要解析的words的中间
        for (int splitPos = currentSize/2 + currentChar; splitPos < words.length(); splitPos++) {
        	//让拆分位置前进直到下一个空格
        	if (Character.isWhitespace(words.charAt(splitPos))) {
        		//创建一个新WordCounter-Spliterator 来解析 words从开始到拆分位置的部分
                Spliterator<Character> spliterator = new WordCounterSpliterator(words.substring(currentChar, splitPos));
               //将这个 WordCounter-Spliterator 的 起 始位置设为拆分位置
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    
    // 还需要遍历的元素的 estimatedSize 就是这个 Spliterator 解析的 String 的总长度和当前遍历的位置的差。
    @Override
    public long estimateSize() {
        return words.length() - currentChar;
    }

//    characteristic 方法告诉框架这个 Spliterator 是 ORDERED （顺序就是 String
//    中各个 Character 的次序）、 SIZED （ estimatedSize 方法的返回值是精确的）、
//    SUBSIZED （ trySplit 方法创建的其他 Spliterator 也有确切大小）、 NONNULL （ String
//    中 不 能 有 为 null 的 Character ） 和 IMMUTABLE （ 在 解 析 String 时 不 能 再 添 加
//    Character ，因为 String 本身是一个不可变类）的。
    @Override
    public int characteristics() {
    	//16+64+16384+256+
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
//        return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
    }

    public static int wordCount(String words) {
        //String转换成原始类型流
        Spliterator<Character> spliterator = new WordCounterSpliterator(words);
        /**
         *  第二个boolean表示创建并行流
         *  这里发现使用并行true
         *  但对于少量的数据并行执行时间会变得更长了
         */
        Stream<Character> stream = StreamSupport.stream(spliterator, false);
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCounter();
    }

    public static void main(String[] args) {
    	  final String SENTENCE =
          		" Nel mezzo del cammin di nostra             vita " +
          		"mi ritrovai in una selva oscura" +
          		" ché la dritta via era smarrita ";
//    	  int length = SENTENCE.length();
//    	  System.out.println(length);
//        System.out.println("多少"+wordCount(SENTENCE));
//        System.out.println(ToolUtils.measureMethod(WordCounterSpliterator::wordCount,SENTENCE));
    	  Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
    	  Stream<Character> stream = StreamSupport.stream(spliterator, true);
    	  System.out.println("Found " + ForkJoinSumCalculator.countWords(stream) + " words");
    //	  Runnable r2 = () -> System.out.println("Hello");
//    	  int a = 10;
//    	  Runnable r1 = () -> {
//    	  int a = 2;
//    	  System.out.println(a);
//    	  };
           
//    	 	doSomething(() -> System.out.println("Danger danger!!"));
    	 	Map<CaloricLevel, List<Dish>> dishesByCaloricLevel =
    	 			Dish.menu.stream()
    	 			.collect(
    	 			groupingBy(dish -> {
    	 			if (dish.getCalories() <= 400) return CaloricLevel.DIET;
    	 			else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
    	 			else return CaloricLevel.FAT;
    	 			}));
    	 	System.out.println(dishesByCaloricLevel);
    	 	Map<CaloricLevel, List<Dish>> dishesByCaloricLevel1 =
    	 			Dish.menu.stream().collect(groupingBy(Dish::getCaloricLevel));
    	 	System.out.println(dishesByCaloricLevel1);
    	    List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                    new Apple(155, "green"),
                    new Apple(120, "red"));
//    	 	   inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
//    	 	   System.out.println(inventory);
    	 			inventory.sort(comparing(Apple::getWeight));
    	 			System.out.println(inventory);
    	 			int totalCalories =
    	 					Dish.menu.stream().map(Dish::getCalories)
    	 					.reduce(0, (c1, c2) -> c1 + c2);
    	 			System.out.println(totalCalories);
    	 			Integer integer = Dish.menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
    	 			System.out.println(integer);
    	 			int sumTotal =  Dish.menu.stream().collect(summingInt(Dish::getCalories));
    	 			System.out.println(sumTotal);
    	 			
					List<String> dishNames = new ArrayList<>();
					for (Dish dish : Dish.menu) {
						if (dish.getCalories() > 300) {
							dishNames.add(dish.getName());
						}
					}
					
					Dish.menu.parallelStream().filter(d -> d.getCalories() > 300)
					.map(Dish::getName)
					.collect(toList());
					logger.warn( "Problem: " + generateDiagnostic());
    }
    
    private static String generateDiagnostic() {
    	System.out.println("nininin");
		return null;
	}

	interface Task{
    	public void log(Level level, Supplier<String> msgSupplier);
    	public void execute();
    	}
    	public static void doSomething(Runnable r){ r.run(); }
    	public static void doSomething(Task a){ a.execute(); }
   
}
