//package com.xtel.vngolf.api.main;
//package com.xtel.vngofl.api.main;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.tbv.utils.textbase.StringUtils;
//import com.xtel.vngofl.api.config.CoreConfig;
//import com.xtel.vngofl.api.listener.entities.CmsUser;
//
//public class GenJson {
//
//	public static void main(String[] args) {
//		Gson gson = new GsonBuilder().setDateFormat(CoreConfig.API_DATE_TIME_OUTPUT_FORMAT).serializeNulls()
//				.setPrettyPrinting().create();
//
//		String json = gson.toJson(new CmsUser());
//		System.out.println(json);
//
//		String test = StringUtils.encodeMD5("Xtel2020!$");
//		System.out.println(test);
//	}
//
//}
