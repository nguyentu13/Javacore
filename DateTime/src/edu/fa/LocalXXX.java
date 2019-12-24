package edu.fa;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LocalXXX {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalDate ld= LocalDate.now();
		System.out.println(ld);
		DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd-MM-yyyy");
		System.out.println(ld.format(dtf));
		String strdate= "20-08-2017";
		System.out.println(LocalDate.parse(strdate, dtf));
		System.out.println(ld.getMonth().toString());
		
		LocalTime lt= LocalTime.now();
		System.out.println(lt);
		
		LocalDateTime ldt=LocalDateTime.now();
		System.out.println(ldt);
		
		Period period= Period.ofDays(5);
		//Them 5 ngay
		System.out.println(ld.plus(period));
		System.out.println(ldt.plus(period));
		period = Period.of(3, 2, 1);
		//lui ve 3 nam 2 thang 1 ngay
		System.out.println(ld.minus(period));
		
		Duration duration = Duration.ofHours(5);
		System.out.println(lt.minus(duration));
		System.out.println(ldt.plus(duration));
		
		ZoneId zone = ZoneId.of("Asia/Bangkok");
		ZonedDateTime zdt=ZonedDateTime.of(ldt, zone);
		System.out.println(zdt);
		String newTimeZone ="2019-12-11T16:16:02.163+07:00[Asia/Bangkok]";
		System.out.println(ZonedDateTime.parse(newTimeZone));
		
	}

}
