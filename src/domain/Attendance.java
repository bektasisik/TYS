package domain;

import java.text.*;
import java.util.*;

public class Attendance {
    private final int id;
    private final String prayerTime;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private final String date = formatter.format(new Date());

    public int getId() {
        return id;
    }
    public String getPrayerTime() {
        return prayerTime;
    }
    public String getDate() {
        return date;
    }

    public Attendance(int id, String prayerTime) {
        this.id = id;
        this.prayerTime = prayerTime;
    }

    @Override
    public String toString() {
        return getId() + ": " + getDate() + " " + getPrayerTime();
    }
}