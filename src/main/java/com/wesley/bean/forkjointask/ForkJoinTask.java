package com.wesley.bean.forkjointask;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTask extends RecursiveTask<Long>{
	private static final long serialVersionUID = 1L;

    private static final Long MAX = 1000000000L;	
    private static final Long THRESHOLD =1000L;	
    private Long start;
    private Long end;
	
    
    
	protected ForkJoinTask(Long start, Long end) {
		this.start = start;
		this.end = end;
	}


    public static void main(String[] args) {
		     test();
		     System.out.println("============");
		     testForkJoinTask();
	}


	private static void testForkJoinTask() {
		System.out.println("test Fork/Join");
		 Long start=System.currentTimeMillis();
		 ForkJoinPool forkJoinPool=new ForkJoinPool();
		 Long sum = forkJoinPool.invoke(new ForkJoinTask(1L,MAX));
		 System.out.println(sum);
		 System.out.println(System.currentTimeMillis()-start+"ms");
	}


	private static void test() {
		System.out.println("test");
		Long start=System.currentTimeMillis();
		Long sum=0L;
		for (Long i = 0L; i <= MAX; i++) {
			sum+=i;
		}
		System.out.println(sum);
		System.out.println(System.currentTimeMillis()-start+"ms");
	}

	@Override
	protected Long compute() {
		long sum =0;
		if(end - start <= THRESHOLD) {
		for(long i = start; i <=end; i++) {sum += i;}
		return sum;
		}else{          
		long mid = (start +	end) / 2;
		ForkJoinTask task1 = new ForkJoinTask(start, mid);
		  task1.fork();            
		ForkJoinTask task2 = new ForkJoinTask(mid + 1, end); 
		task2.fork();
		return	 task1.join() + task2.join();
		        }
		    }

}
