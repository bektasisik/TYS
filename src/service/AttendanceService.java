package service;

import domain.*;

import java.text.*;
import java.util.*;
import java.util.stream.Stream;

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

    // Yoklama almak için önce yoklama alacağı vakti seçer.
    // Seçtiği vakite göre Attendance class'ını oluşturur.
    // Oluşturduğu yoklama class'larını Accentandes listesine obje olarak ekler.
    // Talebe listesinden talebeleri tek tek yoklama alır.
    public void takeAttendance() {

        String prayerTime;
        System.out.println(formatter.format(new Date()) + " Lütfen Vakit Seçiniz.\n1)Sabah\n2)Öğle\n3)İkindi\n4)Akşam\n5)Yatsı");
        System.out.print("Vakit Seçiminiz: ");

        label:
        while (true) {
            String choice = input.next();
            switch (choice) {
                case "1":
                    prayerTime = "Sabah";
                    break label;
                case "2":
                    prayerTime = "Öğle";
                    break label;
                case "3":
                    prayerTime = "İkindi";
                    break label;
                case "4":
                    prayerTime = "Akşam";
                    break label;
                case "5":
                    prayerTime = "Yatsı";
                    break label;
                default:
                    System.out.print("Yanlış tuşa bastınız bir daha seçim yapınız: ");
            }
        }
        Attendance attendance = new Attendance(getAttendanceId(), prayerTime);
        attendances.add(attendance);
        System.out.println(prayerTime + " vakti için talebe burada ise (+) tuşuna basınız. Değil ise (-) tuşuna basınız.");

        for (Student student : students) {
            System.out.print(student.getName() + " " + student.getSurname() + ": ");
            boolean isAbsence = true;

            while (true) {
                String kontrol = input.next();
                if (kontrol.equals("+")) break;
                else if (kontrol.equals("-")) {
                    isAbsence = false;
                    student.setAbsent();
                    break;
                } else System.out.print("Yanlış tuşa bastınız (+ yada - tuşundan birine basınız.): ");
            }
            StudentAttendance studentAttendance = new StudentAttendance(student, attendance, isAbsence);
            studentAttendances.add(studentAttendance);
        }
        System.out.println("Yoklama işlemi bitmiştir. Ana sayfaya yönlendiriliyorsunuz. ");
    }

    //Yoklamalar listesini ekrana çağırır.
    public void printAttendances() {
        for (Attendance attendance : attendances) {
            System.out.println(attendance);
        }
        printStudentAttendances();
    }

    //Yoklamalar listesinden seçilen yoklamaya göre vakit ve tarihe göre alınan yoklama sonucunu gösterir.
    public void printStudentAttendances() {
        System.out.print("Lütfen listelemek istediğiniz yoklama vaktini seçiniz: ");
        while (true) {
            int choiceAttendanceId = input.nextInt();
            if (studentAttendances.stream()
                    .noneMatch(studentAttendance -> studentAttendance.getAttendance().getId() == choiceAttendanceId)) {
                System.out.print("Lütfen listede olan yoklama numaralarından birini seçiniz: ");
            } else {
                System.out.format("+----------------+-----------------+------------------+%n");
                System.out.format("|       AD       |      SOYAD      | DEVAMSIZ BİLGİSİ |%n");
                System.out.format("+----------------+-----------------+------------------+%n");
                studentAttendances.stream()
                        .filter(studentAttendance -> studentAttendance.getAttendance().getId() == choiceAttendanceId);
                String leftAlignFormat = "| %-14s | %-15s | %-14s |%n";
                for (StudentAttendance studentAttendance : studentAttendances) {
                    System.out.format(leftAlignFormat,
                            studentAttendance.getStudent().getName(),
                            studentAttendance.getStudent().getSurname(),
                            "\t" + studentAttendance.getIsAbsenceToString());
                }
                System.out.format("+----------------+-----------------+------------------+%n");
                break;
            }
        }
    }
}