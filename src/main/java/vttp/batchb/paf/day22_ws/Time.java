package vttp.batchb.paf.day22_ws;

import java.util.Date;

public class Time {

    public static long now() {
        Date date = new Date();

        return date.getTime();
    }
}
