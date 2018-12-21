package SHARED;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActualDateAndTime {

    public static String DateAndTimeStamp(){
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
        return timeStamp+" ";
    }
}
