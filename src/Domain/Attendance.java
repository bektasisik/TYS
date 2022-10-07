package Domain;

import java.text.*;
import java.util.*;

public class Attendance {
    public int id;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    public String date = formatter.format(new Date());
    public String prayerTime;

    public Attendance(int id, String prayerTime) {
        this.id = id;
        this.prayerTime = prayerTime;
    }

    @Override
    public String toString() {
        return id + " " + date + " " + prayerTime ;
    }
}