package service;

import domain.*;

import java.text.*;
import java.util.*;

public class AttendanceService {
    private final Scanner input = new Scanner(System.in);
    private final List<Attendance> attendances = new ArrayList<>();
    private final List<StudentAttendance> studentAttendances = new ArrayList<>();
    private final List<Student> students;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private int attendanceId = 1;

    public int getAttendanceId() {
        return attendanceId++;
    }

    public AttendanceService(List<Student> students) {
        this.students = students;
    }

    public void takeAttendance() {

        String prayerTime = null;
        System.out.println(formatter.format(new Date()) + " Lütfen Vakit Seçiniz.\n1)Sabah\n2)Öğle\n3)İkindi\n4)Akşam\n5)Yatsı");
        System.out.print("Vakit Seçiminiz: ");

        boolean choiceBool = true;
        while (choiceBool) {
            int choice = input.nextInt();
            switch (choice) {
                case 1 -> {
                    prayerTime = "Sabah";
                    choiceBool = false;
                }
                case 2 -> {
                    prayerTime = "Öğle";
                    choiceBool = false;
                }
                case 3 -> {
                    prayerTime = "İkindi";
                    choiceBool = false;
                }
                case 4 -> {
                    prayerTime = "Akşam";
                    choiceBool = false;
                }
                case 5 -> {
                    prayerTime = "Yatsı";
                    choiceBool = false;
                }
                default -> System.out.print("Yanlış tuşa bastınız bir daha seçim yapınız: ");
            }
        }
        Attendance attendance = new Attendance(getAttendanceId(), prayerTime);
        attendances.add(attendance);
        System.out.println(prayerTime + " vakti için talebe burada ise (+) tuşuna basınız. Değil ise (-) tuşuna basınız.");

        for (Student student : students) {
            System.out.print(student.getName() + " " + student.getSurname() + ": ");
            boolean isPlus = true, isAbsence = true;
            while (isPlus) {
                String kontrol = input.next();
                switch (kontrol) {
                    case "+" -> isPlus = false;
                    case "-" -> {
                        isAbsence = false;
                        isPlus = false;
                        student.setAbsent();
                    }
                    default -> System.out.print("Yanlış tuşa bastınız (+ yada - tuşundan birine basınız.): ");
                }
            }
            StudentAttendance studentAttendance = new StudentAttendance(student, attendance, isAbsence);
            studentAttendances.add(studentAttendance);
        }
        System.out.println("Yoklama işlemi bitmiştir. Ana sayfaya yönlendiriliyorsunuz. ");
    }

    public void printAttendances() {
        for (Attendance attendance : attendances) {
            System.out.println(attendance);
        }
        printStudentAttendances();
    }

    public void printStudentAttendances() {
        System.out.print("Lütfen listelemek istediğiniz yoklama vaktini seçiniz: ");
        boolean isMatch = true;
        while (isMatch) {
            int choiceAttendanceId = input.nextInt();
            if (studentAttendances.stream().noneMatch(studentAttendance -> studentAttendance.getAttendance().getId() == choiceAttendanceId)) {
                System.out.print("Lütfen listede olan yoklama numaralarından birini seçiniz: ");
            } else {
                studentAttendances.stream()
                        .filter(studentAttendance -> studentAttendance.getAttendance().getId() == choiceAttendanceId)
                        .forEach(System.out::println);
                isMatch = false;
            }
        }
    }
}
