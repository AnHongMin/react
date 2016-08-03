package com.mpc.util;


import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

/**
 * 
 * Date 관련 유틸리티
 * 
 * @author hongmin.an
 *
 */
public class DateUtil {
	/**
	 * String 형태의 날짜값을 Calendar(날짜만)
	 * 
	 * @param ymd
	 *            연월일
	 * @return
	 */
	public static Calendar calStringToCal(String ymd) {
		Calendar cal = Calendar.getInstance();
		int year = Integer.parseInt(ymd.substring(0, 4));
		int month = Integer.parseInt(ymd.substring(4, 6));
		int date = Integer.parseInt(ymd.substring(6, 8));
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(year, month - 1, date);
		return cal;
	}

	/**
	 * String 형태의 날짜값을 Calendar(날짜와 시간포함)
	 * 
	 * @param ymdhms
	 *            연월일시분초
	 * @return
	 */
	public static Calendar calStringToCal2(String ymdhms) {
		Calendar cal = Calendar.getInstance();
		int year = Integer.parseInt(ymdhms.substring(0, 4));
		int month = Integer.parseInt(ymdhms.substring(4, 6));
		int date = Integer.parseInt(ymdhms.substring(6, 8));
		int hourOfDay = Integer.parseInt(ymdhms.substring(8, 10));
		int minute = Integer.parseInt(ymdhms.substring(10, 12));
		int second = Integer.parseInt(ymdhms.substring(12, 14));
		cal.set(year, month - 1, date, hourOfDay, minute, second);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		return cal;
	}

	/**
	 * String 형태의 날짜값을 Date(날짜만)
	 * 
	 * @param ymd
	 *            연월일
	 * @return
	 */
	public static Date dateStringToDate(String ymd) {
		return calStringToCal(ymd).getTime();
	}

	/**
	 * String 형태의 날짜값을 Date(날짜와 시간포함)
	 * 
	 * @param ymdhms
	 *            연월일시분초
	 * @return
	 */
	public static Date dateStringToDate2(String ymdhms) {
		return calStringToCal2(ymdhms).getTime();
	}

	/**
	 * 시간만큼 더한 날짜
	 * 
	 * @param ymd
	 *            연월일
	 * @param amount
	 *            일자
	 * @return
	 */
	public static String getYmdaddHours(String ymdhms, int amount) {
		ymdhms = ymdhms.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		Date dt = DateUtils.addHours(dateStringToDate2(ymdhms), amount);
		return DateFormatUtils.format(dt, "yyyyMMddHHmmss").replaceAll("-", "");
	}

	/**
	 * 일자만큼 더한 날짜
	 * 
	 * @param ymd
	 *            연월일
	 * @param amount
	 *            일자
	 * @return
	 */
	public static String getYmdaddDay(String ymd, int amount) {
		Date dt = DateUtils.addDays(dateStringToDate(ymd), amount);
		return DateFormatUtils.format(dt, "yyyy-MM-dd").replaceAll("-", "");
	}

	/**
	 * 개월만큼 더한 날짜
	 * 
	 * @param ymd
	 *            연월일
	 * @param amount
	 *            월
	 * @return
	 */
	public static String getYmdaddMonth(String ymd, int amount) {
		return DateFormatUtils.format(DateUtils.addMonths(dateStringToDate(ymd), amount), "yyyy-MM-dd").replaceAll("-", "");
	}

	/**
	 * Date 형태의 날짜값을 String(YYYYMMDD)
	 * 
	 * @param date
	 *            Date
	 * @return
	 */
	public static String strDateToString(Date date) {
		return DateFormatUtils.format(date, "yyyy-MM-dd").replaceAll("-", "");
	}

	/**
	 * 국가별 현재일자(YYYYMMDD)
	 * 
	 * @param tz_id
	 *            타임존
	 * @return String 연월일(8자리)
	 * @throws Exception
	 */
	public static String getCurYmd(String tz_id) throws Exception {
		if (StringUtils.trimToEmpty(tz_id).equals(""))
			tz_id = "America/New_York";
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(tz_id));
		String year = ObjectUtils.toString(cal.get(Calendar.YEAR));
		String month = ObjectUtils.toString(cal.get(Calendar.MONTH) + 1);
		if (month.length() < 2)
			month = "0" + month;
		String day = ObjectUtils.toString(cal.get(Calendar.DAY_OF_MONTH));
		if (day.length() < 2)
			day = "0" + day;
		return year + month + day;
	}

	/**
	 * 국가별 현재시간(YYYYMMDDHHMISE)
	 * 
	 * @param tz_id
	 *            타임존 아이디
	 * @return String 현재시간(연월일시분초 14자리)
	 * @throws Exception
	 */
	public static String getCurTime(String tz_id) throws Exception {
		if (StringUtils.trimToEmpty(tz_id).equals(""))
			tz_id = "America/New_York";
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(tz_id));
		String year = ObjectUtils.toString(cal.get(Calendar.YEAR));
		String month = ObjectUtils.toString(cal.get(Calendar.MONTH) + 1);
		if (month.length() < 2)
			month = "0" + month;
		String day = ObjectUtils.toString(cal.get(Calendar.DAY_OF_MONTH));
		if (day.length() < 2)
			day = "0" + day;
		String hour = ObjectUtils.toString(cal.get(Calendar.HOUR_OF_DAY));
		if (hour.length() < 2)
			hour = "0" + hour;
		String minute = ObjectUtils.toString(cal.get(Calendar.MINUTE));
		if (minute.length() < 2)
			minute = "0" + minute;
		String second = ObjectUtils.toString(cal.get(Calendar.SECOND));
		if (second.length() < 2)
			second = "0" + second;
		return year + month + day + hour + minute + second;
	}

	/**
	 * 현재일자
	 * 
	 * @param tz_id
	 *            타임존 아이디
	 * @return Date Date
	 * @throws Exception
	 */
	public static Date getCurDate(String tz_id) throws Exception {
		if (StringUtils.trimToEmpty(tz_id).equals(""))
			tz_id = "America/New_York";
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(tz_id));
		return cal.getTime();
	}

	/**
	 * 로케일에 해당하는 현재일자(YYYYMMDD)
	 * 
	 * @param locale
	 *            로케일
	 * @return String 연월일(8자리)
	 * @throws Exception
	 */
	public static String strGetCurYmd(Locale locale) throws Exception {
		Calendar cal = calGetCurCal(locale);
		String year = ObjectUtils.toString(cal.get(Calendar.YEAR));
		String month = ObjectUtils.toString(cal.get(Calendar.MONTH) + 1);
		if (month.length() < 2)
			month = "0" + month;
		String day = ObjectUtils.toString(cal.get(Calendar.DAY_OF_MONTH));
		if (day.length() < 2)
			day = "0" + day;
		return year + month + day;
	}

	public static Calendar calGetCurCal(Locale locale) throws Exception {
		return Calendar.getInstance(locale);
	}

	/**
	 * 해당 날짜가 포함된 주의 첫째날
	 * 
	 * @param cur_date
	 *            현재일자(연월일)
	 * @return 일자(2자리)
	 * @throws Exception
	 */
	public static String strGetStartDateWeek(String cur_date) throws Exception {
		String result = cur_date.substring(6);
		int tmp = Integer.parseInt(result);
		Calendar cal = calStringToCal(cur_date);
		if (cal.get(Calendar.DAY_OF_WEEK) != 1) {
			tmp = tmp - (cal.get(Calendar.DAY_OF_WEEK) - 1);
			if (tmp > 0) {
				result = tmp < 10 ? "0" + tmp : "" + tmp;
			}
		}
		return result;
	}

	/**
	 * 해당 날짜가 포함된 주의 마지막날
	 * 
	 * @param cur_date
	 *            현재일자(연월일)
	 * @return 일자(2자리)
	 * @throws Exception
	 */
	public static String strGetEndDateWeek(String cur_date) throws Exception {
		String result = cur_date.substring(6);
		int tmp = Integer.parseInt(result);
		Calendar cal = calStringToCal(cur_date);
		if (cal.get(Calendar.DAY_OF_WEEK) != 7) {
			tmp = tmp + (7 - cal.get(Calendar.DAY_OF_WEEK));
			result = tmp < 10 ? "0" + tmp : "" + tmp;
		}
		return result;
	}

	/**
	 * 현재일자 기준 월의 시작일자
	 * 
	 * @param cur_date
	 *            현재일자(연월일)
	 * @return String 연월일(8자리)
	 * @throws Exception
	 */
	public static String strGetStartMonthDate(String cur_date) throws Exception {
		Date date = DateUtils.truncate(dateStringToDate(cur_date), Calendar.MONTH);
		return strDateToString(date);
	}

	/**
	 * 현재일자 기준 월의 마지막일자
	 * 
	 * @param cur_date
	 *            현재일자(연월일)
	 * @return String 연월일(8자리)
	 * @throws Exception
	 */
	public static String strGetEndMonthDate(String cur_date) throws Exception {
		Date date = DateUtils.truncate(dateStringToDate(cur_date), Calendar.MONTH);
		return strDateToString(DateUtils.add(DateUtils.add(date, Calendar.MONTH, 1), Calendar.DAY_OF_MONTH, -1));
	}

	/**
	 * 해당 날짜가 포함된 주의 월요일
	 * 
	 * @param cur_date
	 *            현재일자(연월일)
	 * @return 일자(8자리)
	 * @throws Exception
	 */
	public static String getStartDateWeekMonday(String cur_date) throws Exception {
		Calendar cal = calStringToCal(cur_date);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 1:일요일 ~ 7:토요일
		int amount = 0;
		switch (day) {
		case 1:
			amount = -6;
			break;
		case 2:
			amount = 0;
			break;
		case 3:
			amount = -1;
			break;
		case 4:
			amount = -2;
			break;
		case 5:
			amount = -3;
			break;
		case 6:
			amount = -4;
			break;
		case 7:
			amount = -5;
			break;
		default:
			break;
		}
		Date dt = DateUtils.addDays(dateStringToDate(cur_date), amount);
		return DateFormatUtils.format(dt, "yyyyMMdd");
	}

	/**
	 * 해당 날짜가 포함된 주의 화요일
	 * 
	 * @param cur_date
	 *            현재일자(연월일)
	 * @return 일자(8자리)
	 * @throws Exception
	 */
	public static String getStartDateWeekTuesday(String cur_date) throws Exception {
		Calendar cal = calStringToCal(cur_date);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 1:일요일 ~ 7:토요일
		int amount = 0;
		switch (day) {
		case 1:
			amount = -5;
			break;
		case 2:
			amount = 1;
			break;
		case 3:
			amount = 0;
			break;
		case 4:
			amount = -1;
			break;
		case 5:
			amount = -2;
			break;
		case 6:
			amount = -3;
			break;
		case 7:
			amount = -4;
			break;
		default:
			break;
		}
		Date dt = DateUtils.addDays(dateStringToDate(cur_date), amount);
		return DateFormatUtils.format(dt, "yyyyMMdd");
	}

	/**
	 * 해당 날짜가 포함된 주의 수요일
	 * 
	 * @param cur_date
	 *            현재일자(연월일)
	 * @return 일자(8자리)
	 * @throws Exception
	 */
	public static String getStartDateWeekWednesday(String cur_date) throws Exception {
		Calendar cal = calStringToCal(cur_date);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 1:일요일 ~ 7:토요일
		int amount = 0;
		switch (day) {
		case 1:
			amount = -4;
			break;
		case 2:
			amount = 2;
			break;
		case 3:
			amount = 1;
			break;
		case 4:
			amount = 0;
			break;
		case 5:
			amount = -1;
			break;
		case 6:
			amount = -2;
			break;
		case 7:
			amount = -3;
			break;
		default:
			break;
		}
		Date dt = DateUtils.addDays(dateStringToDate(cur_date), amount);
		return DateFormatUtils.format(dt, "yyyyMMdd");
	}

	/**
	 * 해당 날짜가 포함된 주의 목요일
	 * 
	 * @param cur_date
	 *            현재일자(연월일)
	 * @return 일자(8자리)
	 * @throws Exception
	 */
	public static String getStartDateWeekThursday(String cur_date) throws Exception {
		Calendar cal = calStringToCal(cur_date);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 1:일요일 ~ 7:토요일
		int amount = 0;
		switch (day) {
		case 1:
			amount = -3;
			break;
		case 2:
			amount = 3;
			break;
		case 3:
			amount = 2;
			break;
		case 4:
			amount = 1;
			break;
		case 5:
			amount = 0;
			break;
		case 6:
			amount = -1;
			break;
		case 7:
			amount = -2;
			break;
		default:
			break;
		}
		Date dt = DateUtils.addDays(dateStringToDate(cur_date), amount);
		return DateFormatUtils.format(dt, "yyyyMMdd");
	}

	/**
	 * 해당 날짜가 포함된 주의 금요일
	 * 
	 * @param cur_date
	 *            현재일자(연월일)
	 * @return 일자(8자리)
	 * @throws Exception
	 */
	public static String getStartDateWeekFriday(String cur_date) throws Exception {
		Calendar cal = calStringToCal(cur_date);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 1:일요일 ~ 7:토요일
		int amount = 0;
		switch (day) {
		case 1:
			amount = -2;
			break;
		case 2:
			amount = 4;
			break;
		case 3:
			amount = 3;
			break;
		case 4:
			amount = 2;
			break;
		case 5:
			amount = 1;
			break;
		case 6:
			amount = 0;
			break;
		case 7:
			amount = -1;
			break;
		default:
			break;
		}
		Date dt = DateUtils.addDays(dateStringToDate(cur_date), amount);
		return DateFormatUtils.format(dt, "yyyyMMdd");
	}

	/**
	 * 해당 날짜가 포함된 주의 토요일
	 * 
	 * @param cur_date
	 *            현재일자(연월일)
	 * @return 일자(8자리)
	 * @throws Exception
	 */
	public static String getStartDateWeekSaturday(String cur_date) throws Exception {
		Calendar cal = calStringToCal(cur_date);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 1:일요일 ~ 7:토요일
		int amount = 0;
		switch (day) {
		case 1:
			amount = -1;
			break;
		case 2:
			amount = 5;
			break;
		case 3:
			amount = 4;
			break;
		case 4:
			amount = 3;
			break;
		case 5:
			amount = 2;
			break;
		case 6:
			amount = 1;
			break;
		case 7:
			amount = 0;
			break;
		default:
			break;
		}
		Date dt = DateUtils.addDays(dateStringToDate(cur_date), amount);
		return DateFormatUtils.format(dt, "yyyyMMdd");
	}

	/**
	 * 해당 날짜가 포함된 주의 일요일
	 * 
	 * @param cur_date
	 *            현재일자(연월일)
	 * @return 일자(8자리)
	 * @throws Exception
	 */
	public static String getStartDateWeekSunday(String cur_date) throws Exception {
		Calendar cal = calStringToCal(cur_date);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 1:일요일 ~ 7:토요일
		int amount = 0;
		switch (day) {
		case 1:
			amount = 0;
			break;
		case 2:
			amount = 6;
			break;
		case 3:
			amount = 5;
			break;
		case 4:
			amount = 4;
			break;
		case 5:
			amount = 3;
			break;
		case 6:
			amount = 2;
			break;
		case 7:
			amount = 1;
			break;
		default:
			break;
		}
		Date dt = DateUtils.addDays(dateStringToDate(cur_date), amount);
		return DateFormatUtils.format(dt, "yyyyMMdd");
	}

	/**
	 * 시작일과 종료일의 날짜차이
	 * @param from
	 * @param to
	 * @return
	 * @throws Exception
	 */
	public static long getDiffDays(String from, String to) throws Exception {
		SimpleDateFormat df= new SimpleDateFormat("yyyyMMdd");
		long diff = df.parse(to).getTime() - df.parse(from).getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);
		return diffDays;
	}

	/**
	 * 시작일과 종료일의 시간차이
	 * @param from
	 * @param to
	 * @return
	 * @throws Exception
	 */
	public static long getDiffHours(String from, String to) throws Exception {
		SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long diff = df.parse(to).getTime() - df.parse(from).getTime();
		long diffHours = diff / (60 * 60 * 1000);
		return diffHours;
	}

	/**
	 * 선택날짜 월의 리스트를 가지고 옵니다.
	 * 
	 * @param cur_date
	 *            현재일자(연월일)
	 * @return 일자(8자리)
	 * @throws Exception
	 */
	public static ArrayList<Hashtable<String, String>> getNowMonthDay(int int_select_year, int int_select_month, int int_select_day) throws Exception {
		Calendar day = Calendar.getInstance();
		day.set(int_select_year, int_select_month - 1, int_select_day);
		ArrayList<Hashtable<String, String>> arr_temp = new ArrayList<Hashtable<String, String>>();
		for (int int_i = 1; int_i <= day.getActualMaximum(Calendar.DATE); int_i++) {
			Hashtable<String, String> hash_temp = new Hashtable<String, String>();
			day.set(Calendar.DATE, int_i);
			hash_temp.clear();
			hash_temp.put("YEAR", Integer.toString(day.get(Calendar.YEAR)));
			hash_temp.put("MONTH",Integer.toString(day.get(Calendar.MONTH) + 1));
			hash_temp.put("DATE", Integer.toString(day.get(Calendar.DATE)));
			hash_temp.put("DAY_OF_WEEK", Integer.toString(day.get(Calendar.DAY_OF_WEEK)));
			hash_temp.put("WEEK_OF_YEAR", Integer.toString(day.get(Calendar.WEEK_OF_YEAR)));
			hash_temp.put("WEEK_OF_MONTH", Integer.toString(day.get(Calendar.WEEK_OF_MONTH)));
			arr_temp.add(hash_temp);
		}
		return arr_temp;
	}

	/**
	 * 월간 활동정보 달력에서 앞부분의 빈날짜
	 * 
	 * @param month_day_list
	 *            일자 리스트
	 * @return String 빈날짜 html
	 * @throws Exception
	 */
	private static String strFrontBlank(ArrayList<Hashtable<String, String>> month_day_list) throws Exception {
		StringBuffer sb = new StringBuffer();
		Hashtable<String, String> ht = (Hashtable<String, String>) month_day_list.get(0);
		String week = ObjectUtils.toString(ht.get("WEEK"));
		int day_blank = 0;
		if (week.equals("1")) {
			day_blank = 7 - Integer.parseInt(week);
		} else {
			day_blank = Integer.parseInt(week) - 2;
		}
		for (int j = 0; j < day_blank; j++) {
			sb.append("\n <td class='td02'></td>");
		}
		return sb.toString();
	}

	/**
	 * 월간 활동정보 달력에서 뒤부분의 빈날짜
	 * 
	 * @param month_day_list
	 *            일자 리스트
	 * @return String 빈날짜 html
	 * @throws Exception
	 */
	private static String strEndBlank(ArrayList<Hashtable<String, String>> month_day_list) throws Exception {
		StringBuffer sb = new StringBuffer();
		Hashtable<String, String> ht = (Hashtable<String, String>) month_day_list.get(month_day_list.size() - 1);
		String week = ObjectUtils.toString(ht.get("WEEK"));
		int day_blank = 0;
		if (!week.equals("1"))
			day_blank = 7 - Integer.parseInt(week) + 1;

		for (int j = 0; j < day_blank; j++) {
			sb.append("\n <td class='td02'></td>");
		}
		return sb.toString();
	}

	/**
	 * 주어진 날짜를 입력받은 패턴에 맞는 날짜 문자열을 반환한다.
	 * 
	 * @param date
	 *            Date 객체
	 * @param pattern
	 *            구하고자 하는 날짜 패턴
	 * @return 입력받은 Date 객체로부터 입력받은 패턴에 맞게 생성된 날짜 문자열을 반환한다. 만일 Date 객체가 null
	 *         이거나 pattern이 null 이라면 공백 문자열("")을 반환한다.
	 */
	public static String getDate(Date date, String pattern) {
		if (date == null || pattern == null) {
			return "";
		}
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		return sf.format(date);
	}

	public static Date StringtoDateByFormat(String currentDate, String dateFormat) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat, java.util.Locale.ENGLISH);
		ParsePosition poss = new ParsePosition(0);
		Date date = format.parse(currentDate, poss);
		return date;
	}
	
	public static String getNowMonthDayTable(String ymd, ArrayList<HashMap<String, String>> msgList) throws Exception {
		int int_select_year = Integer.parseInt(ymd.substring(0,4));
		int int_select_month = Integer.parseInt(ymd.substring(4,6));
		int int_select_day = Integer.parseInt(ymd.substring(6,8));
		StringBuffer sb = new StringBuffer();
		ArrayList<Hashtable<String, String>> month_day_list = getNowMonthDay(int_select_year, int_select_month, int_select_day);
		sb.append("<table class='cals_table'>");
		sb.append("<thead>");
		sb.append("	<tr>");
		sb.append("		<th scope='col'>mon</th>");
		sb.append("		<th scope='col'>tue</th>");
		sb.append("		<th scope='col'>wed</th>");
		sb.append("		<th scope='col'>thu</th>");
		sb.append("		<th scope='col'>fri</th>");
		sb.append("		<th scope='col'>sat</th>");
		sb.append("		<th scope='col'>sun</th>");
		sb.append("	</tr>");
		sb.append("</thead>");
		sb.append("<tbody>");
		sb.append("<tr>");
		
		String front_blank = strFrontBlank(month_day_list);
		sb.append(front_blank);
		
		for(int i=0; i<month_day_list.size(); i++){
			Hashtable<String, String> month_day = month_day_list.get(i);
			String week = month_day.get("DAY_OF_WEEK");
			sb.append("<td class='td01'>"+month_day.get("DATE")+"</td>");
			if("1".equals(week)){
				sb.append("</tr><tr>");
			}
		}
		String end_blank = strEndBlank(month_day_list);
		sb.append(end_blank);
		sb.append("</tbody>");
		sb.append("</table>");
		return sb.toString();
	}
}