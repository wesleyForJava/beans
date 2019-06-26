package com.wesley.bean.array;

import java.util.function.Function;
import java.util.stream.Stream;

	public class ToolUtils {
	    //测量工具
	    public static <T, R> long measureMethod(Function<T, R> function, T n) {
	        long fastest = Long.MAX_VALUE;
	        //运行测试10次取最快的毫秒值R
	        for (int i = 0; i < 10; i++) {
	            long start = System.nanoTime();
	            function.apply(n);
	            //运行毫秒值
	            long duration = (System.nanoTime() - start);
	            //检查此次执行是否为最快的一次
	            if (duration < fastest) fastest = duration;
	        }
	        return fastest;
	    }
	    public static long parallelSum(long n) {
	    	return Stream.iterate(1L, i -> i + 1)
	    	.limit(n)
	    	.parallel()
	    	.reduce(0L, Long::sum);
	    	}
	    public static long streamSum(long n) {
	    	return Stream.iterate(1L, i -> i + 1)
	    			.limit(n)
	    			.parallel()
	    			.reduce(0L, Long::sum);
	    }
//	public class CollectorHarness {
//		public void main(String[] args) {
//			long fastest = Long.MAX_VALUE;
//			for (int i = 0; i < 10; i++) {
//				long start = System.nanoTime();
//				partitionPrimes(1_000_000);
//				long duration = (System.nanoTime() - start) / 1_000_000;
//				if (duration < fastest)
//					fastest = duration;
//			}
//			System.out.println("Fastest execution done in " + fastest + " msecs");
//		}
//	}
	    public static void main(String[] args) {
	    	 int availableProcessors = Runtime.getRuntime().availableProcessors();
	    	 System.out.println(availableProcessors);
//	    	 long start = System.nanoTime();
//	    	long parallelSum = parallelSum(10);
//	    	  long duration = (System.nanoTime() - start);
//	    	  System.out.println(duration);
//	    	  long start1= System.nanoTime();
//	    	  long streamSum = streamSum(10);
//	    	  long dura = (System.nanoTime() - start1);
//	    	  System.out.println(dura);
//	    	  List<Integer> asList = Arrays.asList(1,2,3,445,45,4,5,4,5);
//	    	  asList.stream().parallel()
//	    	  .filter(null)
//	    	  .sequential()
//	    	  .map(null)
//	    	  .parallel()
//	    	  .reduce(null);
	    	 measureSumPerf(null, 1000);
	    	 System.out.println("Sequential sum done in:" +
	    			 measureSumPerf(ParallelStreams::sequentialSum, 10_000_000) + " msecs");
		}

	public static long measureSumPerf(Function<Long, Long> adder, long n) {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			long sum = adder.apply(n);
			long duration = (System.nanoTime() - start) / 1_000_000;
			System.out.println("Result: " + sum);
			if (duration < fastest)
				fastest = duration;
		}
		return fastest;
	}
}
