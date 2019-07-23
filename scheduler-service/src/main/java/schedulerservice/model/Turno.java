package schedulerservice.model;

import java.util.Calendar;
import java.util.Date;

public class Turno {


    public static int getTurno(String timestamp) {
        Date date = new Date(Long.parseLong(timestamp));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour >= 6 && hour < 14){
            return 1;
        }else if(hour >= 14 && hour < 22){
            return 2;
        }else
            return 3;
    }
}
