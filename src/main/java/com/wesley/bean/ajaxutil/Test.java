package com.wesley.bean.ajaxutil;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.HijrahDate;
import java.time.chrono.IsoChronology;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.ValueRange;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

	
	public static void main(String[] args) {
		String pathString="/ryxl//flowratedbt";
		String pathString1="/ryxl/flowratedbt";
		System.out.println(pathString.replace("//", "/"));
		System.out.println(pathString1.replace("//", "/"));
		
		CopyOnWriteArrayList<String> cowArrayList=new CopyOnWriteArrayList<String>();
		cowArrayList.add("1");
		cowArrayList.add("2");
		cowArrayList.stream().forEach((e)->System.out.print(e+"\r"));
		
//		Random random = new Random();
//        long start = System.currentTimeMillis();
//
//        int value = 100;
//        //使用数组速度更快
//        int[] list = new int[value];
//
//        for (int j = 0; j < value; ++j) {
//            list[j] = j+1;
//        }
//
//        int index = 0;
//        int count = 0;
//        int tmp = 0;
//        while (value > 0) {
//            index = random.nextInt(value);
//            //System.out.println(list[count + index]);
//            tmp = list[count + index];
//            list[count + index] = list[count];
//            list[count] = tmp;
//            ++count;
//            --value;
//        }
//
//        long end = System.currentTimeMillis();
//
//        //----验证是否正确
//        Arrays.sort(list);
//        int i = 0, size = list.length;
//        for (; i < size; ++i) {
//            //System.out.println(list[i]);
//            if (list[i] != (i + 1))
//                System.out.println(i + "error" + list[i]);
//         }
//        //----验证是否正确
//
//        System.out.println("creat4------");
//        System.out.println("执行耗时 : " + (end - start) / 1000f + " 秒 ");
//        System.out.println("完了，集合大小为" + list.length);
        List<Integer> collect = Stream.iterate(new int[] {0,1}, t->new int[] {t[1],t[0]+t[1]}).map(t->t[0]).limit(10).collect(Collectors.toList());
//        System.out.println(collect.toString());
        
//        LocalDate date1 = LocalDate.parse("20140318",
//        		DateTimeFormatter.BASIC_ISO_DATE);
//        		LocalDate date2 = LocalDate.parse("2014-03-18",
//        		DateTimeFormatter.ISO_LOCAL_DATE);
//        		System.out.println(date1);
//        		System.out.println(date2);
        		
//        		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy|MM|dd");
//        		256 第 12 章 新的日期和时间 API
//        		LocalDate date1 = LocalDate.of(2014, 3, 18);
//        		String formattedDate = date1.format(formatter);
//        		String formattedDate1 = date1.format(formatter1);
//        		System.out.println(formattedDate);
//        		System.out.println(formattedDate1);
//        		LocalDate date2 = LocalDate.parse(formattedDate, formatter);
//        		System.out.println(date1);
//        		System.out.println(date2);
        		
//        		DateTimeFormatter italianFormatter =
//        				DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.CHINA);
//        				LocalDate date = LocalDate.of(2014, 3, 18);
//        				System.out.println(date.toString());
//        				String formattedDate2 = date.format(italianFormatter); // 18. marzo 2014
//        				System.out.println(formattedDate2);
//        				LocalDate date3 = LocalDate.parse(formattedDate2, italianFormatter);
//        				System.out.println(date3.getDayOfMonth());
//        				System.out.println(date3.toString());
//        		DateTimeFormatter italianFormatter = new DateTimeFormatterBuilder()
//        				.appendText(ChronoField.DAY_OF_MONTH)
//        				.appendLiteral(". ")
//        				.appendText(ChronoField.MONTH_OF_YEAR)
//        				.appendLiteral(" ")
//        				.appendText(ChronoField.YEAR)
//        				.parseCaseInsensitive()
//        				.toFormatter(Locale.ITALIAN);
//        		LocalDate date3 = LocalDate.parse(LocalDate.of(2019, 7, 18).format(italianFormatter), italianFormatter);
//	               System.out.println(date3);
        		
//        		ZoneId romeZone = ZoneId.of("Asia/Shanghai");
//        		System.out.println(romeZone.getRules());
//        		System.out.println(romeZone.getDisplayName(TextStyle.FULL_STANDALONE, Locale.CHINA));
//        		System.out.println(romeZone.getId());
        		
//        		Set<String> availableZoneIds = romeZone.getAvailableZoneIds();
//        		Iterator<String> iterator = availableZoneIds.iterator();
//        		while (iterator.hasNext()) {
//				System.out.println(iterator.next());
//				}
//				ZoneId zoneId = TimeZone.getDefault().toZoneId();
//				System.out.println(zoneId);
//				
//				LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
//				ZonedDateTime zdt1 = date.atStartOfDay(romeZone);
//				LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
//				ZonedDateTime zdt2 = dateTime.atZone(romeZone);
//				Instant instant = Instant.now();
//				ZonedDateTime zdt3 = instant.atZone(romeZone);
//				System.out.println(zdt1);
//				System.out.println(zdt2);
//				System.out.println(zdt3);
				
//				LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
//				LocalDateTime date = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
//				Instant instantFromDateTime = dateTime.toInstant(romeZone);
//				System.out.println(instantFromDateTime);
        		
//        		Instant instant = Instant.now();
//        		LocalDateTime timeFromInstant = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Shanghai"));
//        		System.out.println(timeFromInstant);
//        		ZoneOffset newYorkOffset = ZoneOffset.of("-05:00");
//        		System.out.println(newYorkOffset);
//        		
//        		LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
//        		OffsetDateTime dateTimeInNewYork = OffsetDateTime.of(date, newYorkOffset);
//        		System.out.println(dateTime);
//        		System.out.println(dateTimeInNewYork);
        		
        		
        		LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
        		JapaneseDate japaneseDate = JapaneseDate.from(date);
        		System.out.println(japaneseDate.toString());
        		
        		Chronology japaneseChronology = Chronology.ofLocale(Locale.JAPAN);
        		ChronoLocalDate now = japaneseChronology.dateNow();
        		System.out.println(now);
        		
        		
        		HijrahDate ramadanDate =
        				HijrahDate.now().with(ChronoField.DAY_OF_MONTH, 1)
        				.with(ChronoField.MONTH_OF_YEAR, 9);
        				System.out.println("Ramadan starts on " +
        				IsoChronology.INSTANCE.date(ramadanDate) +
        				" and ends on " +
        				IsoChronology.INSTANCE.date(
        				ramadanDate.with(
        				TemporalAdjusters.lastDayOfMonth())));
        				System.out.println(ramadanDate);
	}
}
