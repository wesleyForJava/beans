package com.wesley.bean.array;

import java.lang.annotation.Repeatable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Spliterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.google.common.collect.Range;

public class PatternMatching {

    public static void main(String[] args) {
      //  simplify();
  HashMap<String, String> map=new HashMap<>();
  map.put("jack", "1");
  map.put("jack2", "1");
  map.put("jack3", "2");
  map.put("rock", "4");
//        Expr e = new BinOp("+", new Number(5), new Number(3));
//        Expr match = simplify(e);
//        System.out.println(match);
        
 //       Expr e = new BinOp("+", new Number(5), new BinOp("*", new Number(3), new Number(4)));
//        Integer result = evaluate(e);
//        System.out.println(e + " = " + result);
        
//        Integer evaluate = evaluate(e);
//        System.out.println(evaluate);
//        
//        Map<String, List<String>> map = new HashMap<>();
//        List<String> list;
//
//        // 一般这样写
//        list = map.get("list-1");
//        if (list == null) {
//            list = new LinkedList<>();
//            map.put("list-1", list);
//        }
//        list.add("one");
//        System.out.println(list);
//        System.out.println(map);
//
//        // 使用 computeIfAbsent 可以这样写
//        list = map.computeIfAbsent("list-1", k -> new ArrayList<>());
//        list.add("one");
//        System.out.println(list);
//        System.out.println(map);
//        System.out.println(repeat(3, (Integer x) -> 2*x).apply(10));
//        Map<String, Integer> map = #{"Raoul" -> 23, "Mario" -> 40, "Alan" -> 53};
//        Function<Integer, Boolean> p = (Integer x) -> booleanExpression;
       // var myMap = new HashMap<String, List<String>>();
        
//        double d1 = 3.14;
//        double d2 = d1;
//        Double o1 = d1;
//        Double o2 = d2;
//        Double ox = o1;
//        System.out.println(d1 == d2 ? "yes" : "no");
//        System.out.println(o1 == o2 ? "yes" : "no");
//        System.out.println(o1 == ox ? "yes" : "no");
    	
//    	Author[] authors = Book.class.getAnnotationsByType(Author.class);
//    	Arrays.asList(authors).forEach(a -> { System.out.println(a.name()); });
    //	List<@NonNull Car> cars = new ArrayList<>();
    	
//    	List<Car> cars = Collections.<Car>emptyList();
        //如果没有获取到则返回默认值
//    	String count = map.getOrDefault("jacks", "5");
//    	System.out.println(count);
    	List<Integer> asList = Arrays.asList(1,2,3,4,5);
//    	asList.removeIf((e)->e.equals(1));
//    	//System.out.println(removeIf);
//    	System.out.println(asList);
//  AtomicInteger atomicInteger=new AtomicInteger();
//  int andAdd = atomicInteger.getAndAdd(1);
//  System.out.println(andAdd);
  
//	  LongAdder adder = new LongAdder();
//	  adder.add(10);
//	  adder.add(10);
//	  long sum = adder.sum();
//	  System.out.println(sum);
//	  LongAccumulator acc = new LongAccumulator(Long::sum, 10);
//	  acc.accumulate(10);
//	  acc.accumulate(10);
//	  long result = acc.get();
//	  System.out.println(result);
//	  List<Integer> collect = asList.stream().skip(4).collect(Collectors.toList());
//	  System.out.println(collect.toString());
//	  
//	  
//	  ConcurrentHashMap<String, Integer> maps = new ConcurrentHashMap<>();
//	  maps.put("1", 9);
//	  maps.put("1", 2);
//	  maps.put("1", 2);
//	  maps.put("1", 8);
//	  maps.put("1", 2);
//	  Optional<Integer> maxValue =  Optional.of(maps.reduceValues(1, Integer::max));
//	  int[] evenNumbers = new int[10];
//	  Arrays.setAll(evenNumbers, i -> i * 2);
//	  System.out.println(evenNumbers[3]);
	  
	  int[] ones = new int[] {1,2,3,4};
	  //Arrays.fill(ones, 2);
	  Arrays.parallelPrefix(ones, (a, b) -> a + b);
	for (int i = 0; i < ones.length; i++) {
		System.out.print(ones[i]+"\t");
	}
	  
//	  Integer integer = maxValue.get();
//	  System.out.println(integer);
	  
	  
	  
	  
    }
    
//    private <T> Future<?> getOperationResult(List<BlockingQueue<T>> queues,
//    		Function<Stream<T>, ?> f) {
//    	BlockingQueue<T> queue = new LinkedBlockingQueue<>();
//    	queues.add(queue);
//    	Spliterator<T> spliterator =new BlockingQueueSpliterator<>(queue);
//    	Stream<T> source = StreamSupport.stream(spliterator, false);
//    	return CompletableFuture.supplyAsync( () -> f.apply(source) );
//    	}
//	private ForkingStreamConsumer<T> build() {
//		List<BlockingQueue<T>> queues = new ArrayList<>();
//		Map<Object, Future<?>> actions = forks.entrySet().stream().reduce(new HashMap<Object, Future<?>>(),
//				(map, e) -> {
//					map.put(e.getKey(), getOperationResult(queues, e.getValue()));
//					return map;
//				}, (m1, m2) -> {
//					m1.putAll(m2);
//					return m1;
//				});
//		return new ForkingStreamConsumer<>(queues, actions);
//	}
//    public String getData(String url){
//    	String data = cache.get(url);
//    	if(data == null){
//    	data = getData(url);
//    	cache.put(url, data);
//    	}
//    	return data;
//    	}
    
//    public String getData(String url){
//    	return cache.computeIfAbsent(url, this::getData);
//    	}
//    @interface Author { String name(); }
//    @interface Authors {
//    Author[] value();
//    }
//    @Authors(
//    { @Author(name="Raoul"), @Author(name="Mario") , @Author(name="Alan")}
//    )
//    class Book{}
    
//    @NonNull String name = person.getName();
//    List<@NonNull Car> cars = new ArrayList<>();
    
    @Repeatable(Authors.class)
    @interface Author { String name(); }
    @interface Authors {
    Author[] value();
    }
    
    @Author(name="Raoul") @Author(name="Mario") @Author(name="Alan")
    class Book{ }
    
    static <A> Function<A,A> repeat(int n, Function<A,A> f) {
    	return n==0 ? x -> x: compose(f, repeat(n-1, f));
    	}
	final Map<Range, Integer> numberOfNodes = new HashMap<>();

	Integer computeNumberOfNodesUsingCache(Range range) {
		Integer result = numberOfNodes.get(range);
		if (result != null) {
			return result;
		}
		result = computeNumberOfNodes(range);
		numberOfNodes.put(range, result);
		return result;
	}
	
	Integer computeNumberOfNodesUsingCaches(Range range) {
		return numberOfNodes.computeIfAbsent(range,this::computeNumberOfNodes);
		}

    private Integer computeNumberOfNodes(Range range) {
		// TODO Auto-generated method stub
		return null;
	}

    static <A,B,C> Function<A,C> compose(Function<B,C> g, Function<A,B> f) {
    	return x -> g.apply(f.apply(x));
    	}
    
	private static void simplify() {
        TriFunction<String, Expr, Expr, Expr> binopcase =
                (opname, left, right) -> {
                    if ("+".equals(opname)) {
                        if (left instanceof Number && ((Number) left).val == 0) {
                            return right;
                        }
                        if (right instanceof Number && ((Number) right).val == 0) {
                            return left;
                        }
                    }
                    if ("*".equals(opname)) {
                        if (left instanceof Number && ((Number) left).val == 1) {
                            return right;
                        }
                        if (right instanceof Number && ((Number) right).val == 1) {
                            return left;
                        }
                    }
                    return new BinOp(opname, left, right);
                };
        Function<Integer, Expr> numcase = val -> new Number(val);
        Supplier<Expr> defaultcase = () -> new Number(0);

        Expr e = new BinOp("+", new Number(5), new Number(0));
        Expr match = patternMatchExpr(e, binopcase, numcase, defaultcase);
        if (match instanceof Number) {
            System.out.println("Number: " + match);
        } else if (match instanceof BinOp) {
            System.out.println("BinOp: " + match);
        }
    }

    private static Integer evaluate(Expr e) {
        Function<Integer, Integer> numcase = val -> val;
        Supplier<Integer> defaultcase = () -> 0;
        TriFunction<String, Expr, Expr, Integer> binopcase =
                (opname, left, right) -> {
                    if ("+".equals(opname)) {
                        if (left instanceof Number && right instanceof Number) {
                            return ((Number) left).val + ((Number) right).val;
                        }
                        if (right instanceof Number && left instanceof BinOp) {
                            return ((Number) right).val + evaluate((BinOp) left);
                        }
                        if (left instanceof Number && right instanceof BinOp) {
                            return ((Number) left).val + evaluate((BinOp) right);
                        }
                        if (left instanceof BinOp && right instanceof BinOp) {
                            return evaluate((BinOp) left) + evaluate((BinOp) right);
                        }
                    }
                    if ("*".equals(opname)) {
                        if (left instanceof Number && right instanceof Number) {
                            return ((Number) left).val * ((Number) right).val;
                        }
                        if (right instanceof Number && left instanceof BinOp) {
                            return ((Number) right).val * evaluate((BinOp) left);
                        }
                        if (left instanceof Number && right instanceof BinOp) {
                            return ((Number) left).val * evaluate((BinOp) right);
                        }
                        if (left instanceof BinOp && right instanceof BinOp) {
                            return evaluate((BinOp) left) * evaluate((BinOp) right);
                        }
                    }
                    return defaultcase.get();
                };

        return patternMatchExpr(e, binopcase, numcase, defaultcase);
    }

    static class Expr {
    }

    static class Number extends Expr {
        int val;
        public Number(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "" + val;
        }
    }

    static class BinOp extends Expr {
        String opname;
        Expr left, right;
        public BinOp(String opname, Expr left, Expr right) {
            this.opname = opname;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "(" + left + " " + opname + " " + right + ")";
        }
    }

    static <T> T MyIf(boolean b, Supplier<T> truecase, Supplier<T> falsecase) {
        return b ? truecase.get() : falsecase.get();
    }

    static interface TriFunction<S, T, U, R> {
        R apply(S s, T t, U u);
    }

    static <T> T patternMatchExpr(Expr e,
            TriFunction<String, Expr, Expr, T> binopcase,
            Function<Integer, T> numcase, Supplier<T> defaultcase) {

        if (e instanceof BinOp) {
            return binopcase.apply(((BinOp) e).opname, ((BinOp) e).left, ((BinOp) e).right);
        } else if (e instanceof Number) {
            return numcase.apply(((Number) e).val);
        } else {
            return defaultcase.get();
        }
    }

}
