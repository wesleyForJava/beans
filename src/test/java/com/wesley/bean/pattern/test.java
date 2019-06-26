package com.wesley.bean.pattern;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import org.junit.Test;

public class test {

	
	@Test
	public void testMoveRightBy() throws Exception {
	Point p1 = new Point(5, 5);
	Point p2 = p1.moveRightBy(10);
	assertEquals(15, p2.getX());
	assertEquals(5, p2.getY());
	}
	public final static Comparator<Point> compareByXAndThenY =
			comparing(Point::getX).thenComparing(Point::getY);
	
	@Test
	public void testMovi()throws Exception{
	
		List<Point> points = Arrays.asList(new Point(12, 2));
        points.stream().map(p -> p.getX()).forEach(System.out::println);
        int compare = compareByXAndThenY.compare(new Point(1, 2),new Point(2, 5));
        System.out.println(compare);
	}
	
	public static List<Point> moveAllPointsRightBy(List<Point> points, int x){
		return points.stream()
		.map(p -> new Point(p.getX() + x, p.getY()))
		.collect(toList());
		}
	
	@Test
	public void testComparingTwoPoints() throws Exception {
		Point p1 = new Point(10, 20);
		Point p2 = new Point(10, 11);
		int result = compareByXAndThenY.compare(p1, p2);
		assertEquals(1, result);
	}
	
	
	@Test
	public void testMoveAllPointsRightBy() throws Exception {
		List<Point> points = Arrays.asList(new Point(5, 5), new Point(10, 5));
		List<Point> expectedPoints = Arrays.asList(new Point(15, 5), new Point(20, 5));
		List<Point> newPoints = moveAllPointsRightBy(points, 10);
		assertEquals(expectedPoints, newPoints);
	}
	
	
	@Test
	public void testFilter() throws Exception{
		List<Integer> numbers = Arrays.asList(2, 3, 4, 5);
		numbers.stream()
		.map(x -> x + 17)
		.filter(x -> x % 2 == 0)
		.limit(3)
		.forEach(System.out::println);
	}
	public static void main(String[] args) {
		List<Integer> result = Stream.of(2, 3, 4, 5)
                .peek(x -> System.out.println("taking from stream: " + x)).map(x -> x + 17)
                .peek(x -> System.out.println("after map: " + x)).filter(x -> x % 2 == 0)
                .peek(x -> System.out.println("after filter: " + x)).limit(3)
                .peek(x -> System.out.println("after limit: " + x)).collect(toList());
	}
}
