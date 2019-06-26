package com.wesley.bean.array;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;


/**
 * 使用分支/合并框架执行并行求和
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
	private static final long serialVersionUID = 1L;

	//求和数组
    private final long[] numbers;

    //子任务处理的数组的起始和终止位置
    private final int start;
    private final int end;

    //不在将任务分解为子任务的数据大小
    public static final long THRESHOLD = 10_1000;

    //公共构造函数用于创建主任务
    public ForkJoinSumCalculator(long[] number) {
        this(number, 0, number.length);
    }

    //私有的构造函数用于递归为主任务创建子任务
    private ForkJoinSumCalculator(long[] number, int start, int end) {
        this.numbers = number;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
    	//该任务负责求和的部分的大 小
        int length = end - start;
        //当大小小于阈值，顺序计算结果
        if (length <= THRESHOLD) {
        	return computeSequentially();
        }
        //创建一个子任务来为数组的前一半求和
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        //利用另一个ForkJoinPool线程异步执行新创建的子任务
        leftTask.fork();
        //创建一个任务为数组的后一半求和
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        //同步执行第二个子 任务，有可能允许进一步递归划分
        Long rightResult = rightTask.compute();
        //读取第一个子任务的结果， 如果尚未完成就等待
      // Long rightResult = rightTask.fork().join();
       // Long leftResult = leftTask.compute();
        Long leftResult = leftTask.join();
        //  return leftResult + rightResult;
        //该任务的结果是两个子任务结果的组合
        return rightResult + leftResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static Long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

    public static void main(String[] args) throws IOException {
        /**
         * 这里被1_000_000L折腾很久，开始传1_000_000,存在ForkJoinSumCalculator::forkJoinSum的Long无法转为R
         * 最后发现forkJoinSum的参数改成 int n就会好了，其他方式不管用
         * 最后发先measureMethod的第二个参数将会是forkJoinSum的入参，类型需要一致
         */
//    	int sum=0;
//        for (int i = 1; i <=1010000; i++) {
//            sum +=i;
//        }
//    	System.out.println(sum);
//    	long sum2 = LongStream.rangeClosed(0, 1010000).sum();
//    	System.out.println(sum2);
    	Long forkJoinSum = forkJoinSum(10100001);
    	Long forkJoinSum1 = forkJoinSum(100);
    	System.out.println(forkJoinSum);
    	System.out.println(forkJoinSum1);
        System.out.println("forkJoinSum->Fastest cost " +
                ToolUtils.measureMethod(ForkJoinSumCalculator::forkJoinSum, 1_000_0000L) + " ms");
//        虽然分支/合并框架还算简单易用，不幸的是它也很容易被误用。以下是几个有效使用它的
//        最佳做法。
//          对一个任务调用 join 方法会阻塞调用方，直到该任务做出结果。因此，有必要在两个子
//        任务的计算都开始之后再调用它。否则，你得到的版本会比原始的顺序算法更慢更复杂，
//        因为每个子任务都必须等待另一个子任务完成才能启动。
//          不应该在 RecursiveTask 内部使用 ForkJoinPool 的 invoke 方法。相反，你应该始终直
//        接调用 compute 或 fork 方法，只有顺序代码才应该用 invoke 来启动并行计算。
//          对子任务调用 fork 方法可以把它排进 ForkJoinPool 。同时对左边和右边的子任务调用
//        它似乎很自然，但这样做的效率要比直接对其中一个调用 compute 低。这样做你可以为
//        其中一个子任务重用同一线程，从而避免在线程池中多分配一个任务造成的开销。
//          调试使用分支/合并框架的并行计算可能有点棘手。特别是你平常都在你喜欢的IDE里面
//        看栈跟踪（stack trace）来找问题，但放在分支合并计算上就不行了，因为调用 compute
//        的线程并不是概念上的调用方，后者是调用 fork 的那个。
//          和并行流一样，你不应理所当然地认为在多核处理器上使用分支/合并框架就比顺序计
//        算快。我们已经说过，一个任务可以分解成多个独立的子任务，才能让性能在并行化时
//        有所提升。所有这些子任务的运行时间都应该比分出新任务所花的时间长；一个惯用方
//        法是把输入/输出放在一个子任务里，计算放在另一个里，这样计算就可以和输入/输出
//        同时进行。此外，在比较同一算法的顺序和并行版本的性能时还有别的因素要考虑。就
//        像任何其他Java代码一样，分支/合并框架需要“预热”或者说要执行几遍才会被JIT编
//        译器优化。这就是为什么在测量性能之前跑几遍程序很重要，我们的测试框架就是这么
//        做的。同时还要知道，编译器内置的优化可能会为顺序版本带来一些优势（例如执行死
//        码分析——删去从未被使用的计算）。
//        对于分支/合并拆分策略还有最后一点补充：你必须选择一个标准，来决定任务是要进一步
//        拆分还是已小到可以顺序求值。我们会在下一节中就此给出一些提示。
        
//		ORDERED
//		元素有既定的顺序（例如 List ），因此 Spliterator 在遍历和划分时也会遵循这一顺序
//		DISTINCT
//		对于任意一对遍历过的元素 x 和 y ， x.equals(y) 返回 false
//		SORTED
//		遍历的元素按照一个预定义的顺序排序
//		SIZED
//		该 Spliterator 由一个已知大小的源建立（例如 Set ），因此 estimatedSize() 返回的是准确值
//		NONNULL
//		保证遍历的元素不会为 null
//		IMMUTABLE
//		Spliterator 的数据源不能修改。这意味着在遍历时不能添加、删除或修改任何元素
//		CONCURRE
//		NT
//		该 Spliterator 的数据源可以被其他线程同时修改而无需同步
//		SUBSIZED
//		该 Spliterator 和所有从它拆分出来的 Spliterator 都是 SIZED
        final String SENTENCE =
        		" Nel mezzo del cammin di nostra   vita " +
        		"mi ritrovai in una selva oscura" +
        		" ché la dritta via era smarrita ";
        		System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");
        		Stream<Character> stream = IntStream.range(0, SENTENCE.length())
        				.mapToObj(SENTENCE::charAt);
        		int countWords = countWords(stream);
        		System.out.println(countWords);
//						在这个列表中， accumulate 方法定义了如何更改 WordCounter 的状态，或更确切地说是用
//						哪个状态来建立新的 WordCounter ，因为这个类是不可变的。每次遍历到 Stream 中的一个新的
//						Character 时，就会调用 accumulate 方法。具体来说，就像代码清单7-4中的 countWords-
//						Iteratively 方法一样，当上一个字符是空格，新字符不是空格时，计数器就加一。图7-7展示
//						了 accumulate 方法遍历到新的 Character 时， WordCounter 的状态转换。
//						调用第二个方法 combine 时，会对作用于 Character 流的两个不同子部分的两个
//						WordCounter 的部分结果进行汇总，也就是把两个 WordCounter 内部的计数器加起来。
        		Stream<Character> streams = IntStream.range(0, SENTENCE.length())
        				.mapToObj(SENTENCE::charAt);
        			//	System.out.println("Found " + countWords(streams) + " words");
//        				2. 让 WordCounter 并行工作
//        				你可以尝试用并行流来加快字数统计，如下所示：
        				System.out.println("Found " + countWords(streams.parallel()) + " words");
//        				不幸的是，这次的输出是：
//        				Found 25 words
//        				显然有什么不对，可到底是哪里不对呢？问题的根源并不难找。因为原始的 String 在任意
//        				位置拆分，所以有时一个词会被分为两个词，然后数了两次。这就说明，拆分流会影响结果，而
//        				把顺序流换成并行流就可能使结果出错。
//        				如何解决这个问题呢？解决方案就是要确保 String 不是在随机位置拆开的，而只能在词尾
//        				拆开。要做到这一点，你必须为 Character 实现一个 Spliterator ，它只能在两个词之间拆开
//        				String （如下所示），然后由此创建并行流。
        				
        				String oneLine  =processFile((BufferedReader b) -> b.readLine());
        				System.out.println(oneLine);
        			    String twoLines =processFile((BufferedReader b) -> b.readLine() + b.readLine());
        			    System.out.println(twoLines);

        			    int brr [] [] ={{1,2,3,4},{5,6,7,8},{8,9,10,11}};
        			    for (int[] is : brr) {
							for(int i=0;i<=is.length;i++) {
								System.out.print(i+"");
							}
						}
        			    TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
        			    		temporal -> {
        			    		DayOfWeek dow =
        			    		DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        			    		int dayToAdd = 1;
        			    		if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
        			    		if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
        			    		return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        			    		});
        			    	//	date = date.with(nextWorkingDay);
        				
        			    LocalDate date = LocalDate.of(2014, 3, 18);
        			    String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        			    String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        			    System.out.println(s1);
        			    System.out.println(s2);
        			    
        			    
        			    
    }

	public static String processFile(BufferedReaderProcessor p) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("D:\\Wesley\\Git\\beans\\src\\main\\java\\com\\wesley\\bean\\array\\data.txt"))) {
			return p.process(br);
		}
	}
	public interface BufferedReaderProcessor{
		String process(BufferedReader b) throws IOException;
		}
	
	
	public static int countWords(Stream<Character> stream) {
		WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate,
				WordCounter::combine);
		return wordCounter.getCounter();
	}
    
    
	static class WordCounter {
		private final int counter;
		private final boolean lastSpace;

		public WordCounter(int counter, boolean lastSpace) {
			this.counter = counter;
			this.lastSpace = lastSpace;
		}

		
		//和迭代算法一样,accumulate 方法 一个个遍历 Character
		public WordCounter accumulate(Character c) {
			if (Character.isWhitespace(c)) {
				//false
				return lastSpace ? this : new WordCounter(counter, true);
			} else {
				//else就不是空格 lastSpace是true代表上一个字符是空格加一  如果为false 返回原样返回
				//上一个字符是空格，而当前遍历的字符不是空格时，将单词计数器加1
				return lastSpace ? new WordCounter(counter + 1, false) : this;
			}
		}

		//合并两个 Word-Counter ，把其计数器加起来
		public WordCounter combine(WordCounter wordCounter) {
//			System.out.println("counter:"+counter);
//			System.out.println("wordCounter.counter:"+wordCounter.counter);
			return new WordCounter(counter+wordCounter.counter, wordCounter.lastSpace);
		}

		public int getCounter() {
			return counter;
		}
	}
	public interface Spliterator<T> {
		boolean tryAdvance(Consumer<? super T> action);

		Spliterator<T> trySplit();

		long estimateSize();

		int characteristics();
	}
	
	public static int countWordsIteratively(String s) {
		int counter = 0;
		boolean lastSpace = true;
		for (char c : s.toCharArray()) {
			if (Character.isWhitespace(c)) {
				lastSpace = true;
			} else {
				if (lastSpace)
					counter++;
				lastSpace = false;
			}
		}
		return counter;
	}

}
