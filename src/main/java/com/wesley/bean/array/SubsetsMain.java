package com.wesley.bean.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.LongStream;



public class SubsetsMain {

    public static void main(String[] args) {
//        List<List<Integer>> subs = subsets(Arrays.asList(1, 4, 9));
//        subs.forEach(System.out::println);
//		Random rand = new Random();
//		boolean[] bool = new boolean[100];
//		int[] number = new int[100];
//		int randINT;
//		for (int i = 0; i < 100; i++) {
//			do {
//				randINT = rand.nextInt(99);
//				System.out.println(randINT);
//			} while (bool[randINT]);
//			number[randINT] = randINT;
//			bool[randINT] = true;
//		}
       ;
     
    List<String> a1 = Arrays.asList("1","2","3");
	List<String> b1 = Arrays.asList("4","5","6");
//	List<String> concats = concats(a1,b1);
        List<String> concats1 = concats1(a1,b1);
//        System.out.println(concats);
//        System.out.println(concats1);
//        int factorialIterative = factorialIterative(4);
//        int factorialIteratives = factorialRecursive(4);
//        System.out.println(factorialIterative);
//        System.out.println(factorialIteratives);
//        long factorialStreams = factorialStreams(4);
//        System.out.println(factorialStreams);
        long factorialTailRecursive = factorialTailRecursive(5);
        System.out.println(factorialTailRecursive);
        
        Function<String, Integer> strToInt = Integer::parseInt;
        Integer apply = strToInt.apply("5");
        System.out.println(apply);
        
//        Comparator<Apple> c = comparing(Apple::getWeight);
//        Function<String, String> transformationPipeline
//        = addHeader.andThen(Letter::checkSpelling)
//        .andThen(Letter::addFooter);
    }
    
    static long factorialTailRecursive(long n) {
    	return factorialHelper(1, n);
    }
    private static long factorialHelper(long acc, long n) {
    	return n == 1 ? acc : factorialHelper(acc * n, n-1);
	}

	static long factorialStreams(long n){
    	return LongStream.rangeClosed(1, n)
    	.reduce(1, (long a, long b) -> a * b);
    	}
//    public void searchForGold(List<String> l, Stats stats){
//    	for(String s: l){
//    	if("gold".equals(s)){
//    	stats.incrementFor("gold");
//    	}
//    	}
//    	}
    
	static int factorialIterative(int n) {
		int r = 1;
		for (int i = 1; i <= n; i++) {
			r *= i;
		}
		return r;
		
	}

	static int factorialRecursive(int n) {
		return n == 1 ? 1 : n * factorialRecursive(n - 1);
	}
	
    public static List<List<Integer>> subsets(List<Integer> l) {
        if (l.isEmpty()) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans;
        }
        Integer first = l.get(0);
        List<Integer> rest = l.subList(1,l.size());
        List<List<Integer>> subans = subsets(rest);
        List<List<Integer>> subans2 = insertAll(first, subans);
        return concat(subans, subans2);
    }

    public static List<List<Integer>> insertAll(Integer first, List<List<Integer>> lists) {
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> l : lists) {
            List<Integer> copyList = new ArrayList<>();
            copyList.add(first);
            copyList.addAll(l);
            result.add(copyList);
        }
        return result;
    }

    static List<List<Integer>> concat(List<List<Integer>> a, List<List<Integer>> b) {
//    	a.addAll(b);
//    	return a;
    	
        List<List<Integer>> r = new ArrayList<>(a);
        r.addAll(b);
        return r;
    }
    
    
    static List<String> concats(List<String> a,List<String>b){
		a.addAll(b);
		return a;
    }
    static List<String> concats1(List<String> a,List<String>b){
    	   List<String> r = new ArrayList<>(a);
           r.addAll(b);
    	return r;
    }
}
