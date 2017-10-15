
package check;

import java.util.Date;

public class CheckTime {
    
    public static String getTimesOfDay() {
        Date curDate = new Date();
        String time = curDate.toString().substring(11, 13);
        System.out.println(curDate);
        if(time.charAt(0) == '0') {
            time = time.substring(1);
        }
        System.out.println("Время: " + time);
        int hour = Integer.parseInt(time);
        if(hour > 5 && hour < 10) {
            return "Доброе утро, ";
        } else if (hour < 18) {
            return "Добрый день, ";
        } else if(hour < 22) {
            return "Добрый вечер, ";
        } else {
            return "Добрая ночь, ";
        }
    }
    
}
