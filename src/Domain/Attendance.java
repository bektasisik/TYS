package Domain;

import java.text.*;
import java.util.*;

public class Attendance {
    int id;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    String date = formatter.format(new Date());
    String prayerTime;

    public Attendance(int id, String prayerTime) {
        this.id = id;
        this.prayerTime = prayerTime;
    }

    @Override
    public String toString() {
        return id + " " + date + " " + prayerTime ;
    }
}
