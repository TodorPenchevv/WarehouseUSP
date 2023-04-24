package app.business.tools;

import java.util.Calendar;
import java.util.Date;

public class DateConverter {
    public static String convert(Calendar calendar){
        Date date = calendar.getTime();
        String newDate;

        String day, month, year;


        if (date.getDate()<10)
            day = "0"+date.getDate();
        else
            day = String.valueOf(date.getDate());

        if (date.getMonth()<10)
            month = "0"+(date.getMonth()+1);
        else
            month = String.valueOf(date.getMonth()+1);

        year = String.valueOf(date.getYear()+1900);


        return day+"."+month+"."+year;
    }
}
