package edu.fa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateSample {
	public static void main(String[] args) throws ParseException {
		Date date= new Date();
		System.out.println(date);
//		System.out.println(date.getTime());
//		System.out.println(date.getHours());
//		System.out.println(date.after(new Date()));
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy HH:mm");
		System.out.println(sdf.format(date));
		Date date2= sdf.parse("11-12-2019 15:49");
		System.out.println(date2);
		System.out.println(sdf.format(date2));
	}
}
