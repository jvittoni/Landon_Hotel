package edu.wgu.d387_sample_code.time;

import org.springframework.web.bind.annotation.CrossOrigin;
import java.time.*;
import java.time.format.DateTimeFormatter;

@CrossOrigin(origins = "http://localhost:4200")

public class TimeZoneConvert {

    public static String getTime(){
        ZonedDateTime zdt = ZonedDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        ZonedDateTime et = zdt.withZoneSameInstant(ZoneId.of("America/New_York"));
        ZonedDateTime mt = zdt.withZoneSameInstant(ZoneId.of("America/Denver"));
        ZonedDateTime utc = zdt.withZoneSameInstant(ZoneId.of("UCT"));

        String times = et.format(timeFormatter) + "ET, " + mt.format(timeFormatter) + "MT, " + utc.format(timeFormatter) + "UTC";

        return times;
    }
}
