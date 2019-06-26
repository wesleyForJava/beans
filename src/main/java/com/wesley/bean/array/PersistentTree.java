package com.wesley.bean.array;

import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.collect.Range;

public class PersistentTree {
	public static void main(String[] args) {
//        Tree t = new Tree("Mary", 22,
//                new Tree("Emily", 20,
//                        new Tree("Alan", 50, null, null),
//                        new Tree("Georgie", 23, null, null)
//                ),
//                new Tree("Tian", 29,
//                        new Tree("Raoul", 23, null, null),
//                        null
//                )
//        );
//
//        // found = 23
//        System.out.println(lookup("Raoul", -1, t));
//        // not found = -1
//        System.out.println(lookup("Jeff", -1, t));
//
//        Tree f = fupdate("Jeff", 80, t);
//        // found = 80
//        System.out.println(lookup("Jeff", -1, f));
//
//        Tree u = update("Jim", 40, t);
//        // t was not altered by fupdate, so Jeff is not found = -1
//        System.out.println(lookup("Jeff", -1, u));
//        // found = 40
//        System.out.println(lookup("Jim", -1, u));
//
//        Tree f2 = fupdate("Jeff", 80, t);
//        // found = 80
//        System.out.println(lookup("Jeff", -1, f2));
//        // f2 built from t altered by update() above, so Jim is still present = 40
//        System.out.println(lookup("Jim", -1, f2));
		
//		Stream<Integer> primes = peStream(10);
//		List<Integer> collect = primes.collect(Collectors.toList());
//		System.out.println(collect);
//		List<Integer> asList = Arrays.asList(1,2,3,4,5,6,7);
//		List<Integer> collect2 = asList.stream().iterate(2, i->i+1)
//		List<Integer> collect2 = asList.stream().filter((caidate)->IntStream.rangeClosed(2, (int)Math.sqrt(caidate)).noneMatch(i->caidate%i==0)).collect(Collectors.toList());
//		System.out.println(collect2);
//		IntStream tail = tail(numbers());
//		System.out.println(tail.findAny());
//		IntStream numbers = numbers();
//		int head = head(numbers);
//		IntStream filtered = tail(numbers).filter(n -> n % head != 0);
		//System.out.println(filtered.average());
		//IntStream primes = primes(numbers);
		
//		def numbers(n: Int): Stream[Int] = n #:: numbers(n+1)
//				def primes(numbers: Stream[Int]): Stream[Int] = {
//				numbers.head #:: primes(numbers.tail filter (n -> n % numbers.head != 0))
//		MyList<Integer> ls =
//				new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));
		LazyList<Integer> numbers = LazyList.from(2);
		int two = numbers.head();
		int three = numbers.tail().head();
		int four = numbers.tail().tail().head();
		System.out.println(two + " " + three + " " + four);
		
		
	
	} 
	/*
	 * Integer computeNumberOfNodesUsingCache(Range range) { return
	 * numberOfNodes.computeIfAbsent(range, this::computeNumberOfNodes); }
	 */

	public static MyList<Integer> primes(MyList<Integer> numbers) {
		return numbers;
//		return new LazyList<Integer>(
//				numbers.head(),
//				() -> primes(
//						numbers.tail()
//						.filter(n -> n % numbers.head() != 0)
//						)
//				);
	}
//	import java.util.function.Supplier;
	static class LazyList<T> implements MyList<T> {
		final T head;
		final Supplier<MyList<T>> tail;

		public LazyList(T head, Supplier<MyList<T>> tail) {
			this.head = head;
			this.tail = tail;
		}

		public T head() {
			return head;
		}

		public  MyList<T> tail() {
			return tail.get();
		}

		public boolean isEmpty() {
			return false;
		}
		
		public static LazyList<Integer> from(int n) {
			return new LazyList<Integer>(n, () -> from(n + 1));
		}
//		
//		public MyList<T> filter(Predicate<T> p) {
//			return isEmpty() ? this : p.test(head()) ? new LazyList<>(head(), () -> tail().filter(p)) : tail().filter(p);
//		}
		
	}
	static IntStream primes(IntStream numbers) {
		int head = head(numbers);
		return IntStream.concat(IntStream.of(head), primes(tail(numbers).filter(n -> n % head != 0)));
	}
	
	
	static IntStream tail(IntStream numbers) {
		return numbers.skip(1);
	}
	
	
	static IntStream numbers(){
		return IntStream.iterate(2, n -> n + 1);
	}
	
	static int head(IntStream numbers){
		return numbers.findFirst().getAsInt();
	} 
	
	static class Tree {
		private String key;
		private int val;
		private Tree left, right;

		public Tree(String k, int v, Tree l, Tree r) {
			key = k;
			val = v;
			left = l;
			right = r;
		}
	}

    public static int lookup(String k, int defaultval, Tree t) {
        if (t == null)
            return defaultval;
        if (k.equals(t.key))
            return t.val;
        return lookup(k, defaultval, k.compareTo(t.key) < 0 ? t.left : t.right);
    }

    public static Tree update(String k, int newval, Tree t) {
        if (t == null)
            t = new Tree(k, newval, null, null);
        else if (k.equals(t.key))
            t.val = newval;
        else if (k.compareTo(t.key) < 0)
            t.left = update(k, newval, t.left);
        else
            t.right = update(k, newval, t.right);
        return t;
    }

    public static Tree fupdate(String k, int newval, Tree t) {
        return (t == null) ?
            new Tree(k, newval, null, null) :
             k.equals(t.key) ?
               new Tree(k, newval, t.left, t.right) :
          k.compareTo(t.key) < 0 ?
            new Tree(t.key, t.val, fupdate(k,newval, t.left), t.right) :
            new Tree(t.key, t.val, t.left, fupdate(k,newval, t.right));
   }
    
    
    //质数
	public static Stream<Integer> primes(int n) {
		return Stream.iterate(2, i -> i + 1).filter(MyMathUtils::isPrime).limit(n);
	}
	
	
	public static Stream<Integer> primes1(int n){

		return Stream.iterate(2, i->i+1).filter(MyMathUtils::isPrime).limit(n);
	}

	
	public static Stream<Integer> peStream(int n){
		return Stream.iterate(2, i->i+1).peek((e)-> System.out.print("迭代:"+e.toString())).filter((candidate)->IntStream.rangeClosed(2, (int)Math.sqrt(candidate)).noneMatch(i->candidate%i==0)).peek(e->System.out.print("结果:"+e)).limit(n);
	}
	
	
	static class MyMathUtils{
		public static boolean isPrime(int candidate) {
			//int candidateRoot = (int) Math.sqrt((double) candidate);
			int sqrt = (int)Math.sqrt(candidate);
			return IntStream.rangeClosed(2, sqrt).noneMatch(i->candidate%i==0);
//			return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
		}
		
	}

	interface MyList<T> {
		T head();

		MyList<T> tail();

		default boolean isEmpty() {
			return true;
		}
	}

	static class MyLinkedList<T> implements MyList<T> {
		private final T head;
		private final MyList<T> tail;

		public MyLinkedList(T head, MyList<T> tail) {
			this.head = head;
			this.tail = tail;
		}

		public T head() {
			return head;
		}

		public MyList<T> tail() {
			return tail;
		}

		public boolean isEmpty() {
			return false;
		}
	}

	static class Empty<T> implements MyList<T> {
		public T head() {
			throw new UnsupportedOperationException();
		}

		public MyList<T> tail() {
			throw new UnsupportedOperationException();
		}
	}
}
