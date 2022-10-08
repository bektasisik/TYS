package mytemporarytys;

import java.util.*;

public class VakitService {

    List<VakitInspection> vakitYoklama;

    public VakitService() {
        vakitYoklama = new ArrayList<>();
    }

    public void addVakitList(Integer yoklamaId, Student student, boolean isAbsent, String vakit, String tarih) {
        String isAbsentToString;
        if (!isAbsent) isAbsentToString = "Gelmedi";
        else isAbsentToString = "Geldi";
        vakitYoklama.add(new VakitInspection(yoklamaId, student, isAbsentToString, vakit, tarih));


        //System.out.println(yoklamaId + " " + studentId + " " + studentName + " " + studentSurname + " " + isAbsent + " " + vakit + " " + tarih);
//        System.out.println(vakitYoklamalistesi);

//        System.out.println("Adı: " + studentName + " \nSoyadı: " + studentSurname + " \nTalebe numarası: " + studentId);
    }

    public void printVakitTalebes() {
        for (VakitInspection vakitInspection : vakitYoklama) {
            System.out.println(vakitInspection.student.name + " " + vakitInspection.student.surname + " " + vakitInspection.isAbsentToString);
        }
    }
}
