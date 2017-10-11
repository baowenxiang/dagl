package cn.proem.dagl.web.temperature.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeUtil {
	public static boolean valiDateTimeWithLongFormat(String timeStr) {
		String format = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) "
				+ "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
		Pattern pattern = Pattern.compile(format);
		Matcher matcher = pattern.matcher(timeStr);
		if (matcher.matches()) {
			pattern = Pattern.compile("(\\d{4})-(\\d+)-(\\d+).*");
			matcher = pattern.matcher(timeStr);
			if (matcher.matches()) {
				int y = Integer.valueOf(matcher.group(1));
				int m = Integer.valueOf(matcher.group(2));
				int d = Integer.valueOf(matcher.group(3));
				if (d > 28) {
					Calendar c = Calendar.getInstance();
					c.set(y, m-1, 1);
					int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
					return (lastDay >= d);
				}
			}
			return true;
		}
		return false;
	}
	
	public static boolean checkTime(String time) {  
        if (checkHHMM(time)) {  
            String[] temp = time.split(":");  
            if ((temp[0].length() == 2 || temp[0].length() == 1) && temp[1].length() == 2) {  
                int h,m;  
                try {  
                    h = Integer.parseInt(temp[0]);  
                    m = Integer.parseInt(temp[1]);  
                } catch (NumberFormatException e) {  
                    return false;  
                }     
                if (h >= 0 && h <= 24 && m <= 60 && m >= 0) {  
                    return true;  
                }  
            }  
        }  
        return false;  
    }  
	
	public static boolean checkHHMM(String time) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");  
         try {  
             @SuppressWarnings("unused")  
            Date t = dateFormat.parse(time);  
         }  
         catch (Exception ex) {  
             return false;  
         }  
        return true;  
    }  
}
