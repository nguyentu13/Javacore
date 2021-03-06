package com.xtel.vngolf.api.common.utils;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import com.tbv.utils.textbase.StringUtil;
import com.tbv.utils.textbase.StringUtils;
import com.xtel.vngolf.api.config.CoreConfig;

public class AppUtils {

	public static String formatMsisdn_84(String msisdn) {
		return formatMsisdn(msisdn, "84", "84");
	}

	public static String formatMsisdn(String msisdn, String countryCode, String prefix) {
		// msisdn = BaseUtils.formatMsisdn(msisdn, "84", "84");
		if (StringUtil.isNullOrEmpty(msisdn) || StringUtil.isNullOrEmpty(countryCode) || "#".equals(prefix)) {
			return msisdn;
		}
		msisdn = msisdn.replace(" ", "");

		if (msisdn.startsWith("+" + countryCode)) {
			msisdn = msisdn.replaceFirst("\\+" + countryCode, "");

		} else if (msisdn.startsWith("0")) {
			msisdn = msisdn.replaceFirst("0", "");

		} else {
			if (countryCode.startsWith("84")) { // VN
				if (msisdn.startsWith(countryCode) && msisdn.length() >= 11) {
					msisdn = msisdn.replaceFirst(countryCode, "");
				}
			} else if (msisdn.startsWith(countryCode)) {
				msisdn = msisdn.replaceFirst(countryCode, "");
			}
		}
		msisdn = prefix + msisdn;
		return msisdn;
	}

	public static boolean isValidMsisdnVn(String msisdn) {
		if(StringUtils.isNullOrEmpty(msisdn)){
			return false;
		}

		try {
			Long.parseLong(msisdn);
		} catch (Exception e) {
			return false;
		}
		msisdn = formatMsisdn(msisdn, "84", "0");
		if (msisdn.length() != 10 && msisdn.length() != 11) {
			return false;
		}
		if ((msisdn.startsWith("09") || msisdn.startsWith("08") || // vina
				msisdn.startsWith("07") || // mobi
				msisdn.startsWith("05") || // vnp, gtel
				msisdn.startsWith("03") // viettel
		) && msisdn.length() != 10) {

			return false;
		}
		if (msisdn.startsWith("01") && msisdn.length() != 11) {
			return false;
		}
		return true;
	}
	
	public static String formatTime(Object date) {
		try {
			SimpleDateFormat df = new SimpleDateFormat(CoreConfig.API_DATE_TIME_OUTPUT_FORMAT);
			return df.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	public boolean isValidEmail(String email) {

		if (StringUtil.isNullOrEmpty(email)) {
			return false;
		}

		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null) {
			return false;
		}

		return pat.matcher(email).matches();
	}

}