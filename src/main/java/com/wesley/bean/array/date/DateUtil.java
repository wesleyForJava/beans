package com.wesley.bean.array.date;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class DateUtil {
	public static void main(String[] args) {
//		Date date = new Date(114, 2, 18);
//		Calendar c=new GregorianCalendar();
//		c.add(1, 100);
//		System.out.println(c.getTime());
//		int i = c.get(1);
//		System.out.println(i);
//		System.out.println(date.toLocaleString());
		
	LocalDate date = LocalDate.of(2014, 3, 18);
//		//获取年
//		int year = date.getYear();
//		//获取月
//		Month month = date.getMonth();
//		//获取日
//		int day = date.getDayOfMonth();
//		//获取星期几
//		DayOfWeek dow = date.getDayOfWeek();
//		//获取这个月当中有多少天
//		int len = date.lengthOfMonth();
//		//是否是闰年？
//		boolean leap = date.isLeapYear();
//		System.out.println(date);
//		System.out.println(month);
//		System.out.println(dow);
//		System.out.println(day);
//		System.out.println(len);
//		System.out.println(leap);
//		System.out.println(year);
//		System.out.println(LocalDate.now());
//		int year = date.get(ChronoField.YEAR);
//		int month = date.get(ChronoField.MONTH_OF_YEAR);
//		int day = date.get(ChronoField.DAY_OF_MONTH);
//		System.out.println(year+"年"+month+"月"+day+"日");
//		
		LocalTime time = LocalTime.of(13, 45, 20);
//		int hour = time.getHour();
//		int minute = time.getMinute();
//		int second = time.getSecond();
//		System.out.println(hour+"时"+minute+"分"+second+"秒");
//		LocalDate date = LocalDate.parse("2014-03-18");
//		LocalTime time = LocalTime.parse("13:45:20");
//		System.out.println(date);
//		System.out.println(time);
		
		LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
//		LocalDateTime dt2 = LocalDateTime.of(date, time);
//		LocalDateTime dt3 = date.atTime(13, 45, 20);
//		LocalDateTime dt4 = date.atTime(time);
//		LocalDateTime dt5 = time.atDate(date);
//		
//		
//		System.out.println(dt1);
//		System.out.println(dt2);
//		System.out.println(dt3);
//		System.out.println(dt4);
//		System.out.println(dt5);
//		LocalDate date1 = dt1.toLocalDate();
		LocalTime time1 = dt1.toLocalTime();
//		System.out.println(date1+"日"+time1+"时");
//		
//		Instant ofEpochSecond1 = Instant.ofEpochSecond(3);
//		Instant ofEpochSecond2 = Instant.ofEpochSecond(3, 0);
//		Instant ofEpochSecond3 = Instant.ofEpochSecond(2, 1_000_000_000);
//		Instant ofEpochSecond4 = Instant.ofEpochSecond(4, -1_000_000_000);
//		System.out.println(ofEpochSecond1);
//		System.out.println(ofEpochSecond2);
//		System.out.println(ofEpochSecond3);
//		System.out.println(ofEpochSecond4);
		
		//int day = Instant.now().get(ChronoField.DAY_OF_MONTH);
		Duration ofDays = Duration.ofDays(2019);
		System.out.println(ofDays);
		//System.out.println(day);
//		Temporal
//		Duration d1 = Duration.between(time1, time2);
//		Duration d1 = Duration.between(dateTime1, dateTime2);
//		Duration d2 = Duration.between(instant1, instant2);
//		Period tenDays = Period.between(LocalDate.of(2014, 3, 8),
//				LocalDate.of(2014, 3, 18));
//		System.out.println(tenDays.getDays());
		
//		Duration threeMinutes = Duration.ofMinutes(3);
//		Duration threeMinutes1 = Duration.of(3, ChronoUnit.MINUTES);
//		Period tenDays = Period.ofDays(10);
//		Period threeWeeks = Period.ofWeeks(3);
//		Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
//		System.out.println(threeMinutes.getSeconds());
//		System.out.println(threeMinutes1.getSeconds());
//		System.out.println(tenDays.getMonths());
//		System.out.println(threeWeeks.getDays());
//		System.out.println(twoYearsSixMonthsOneDay.getMonths());
		LocalDate date1 = LocalDate.of(2014, 3, 18);
		LocalDate date2 = date1.withYear(2011);
		LocalDate date3 = date2.withDayOfMonth(25);
		LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);
		System.out.println(date1);
		System.out.println(date2);
		System.out.println(date3);
		System.out.println(date4);
		
		
	}
}
