package Service;

import Domain.*;

import java.util.*;
import java.util.function.Predicate;

public class AttendanceService {
    private final Scanner input = new Scanner(System.in);
    private final List<Attendance> attendances = new ArrayList<>();
    private final List<StudentAttendance> studentAttendances = new ArrayList<>();
    private final List<Student> students;
    private int attendanceId = 1;


    public AttendanceService(List<Student> students) {
        this.students = students;
    }

    public void takeAttendance() {
        // todo
        String vakit = null;
        System.out.println("Lütfen Vakit Seçiniz.\n1)Sabah\n2)Öğle\n3)İkindi\n4)Akşam\n5)Yatsı");
        System.out.print("Vakit Seçiminiz: ");

        boolean choiceBool = true;
        while (choiceBool) {
            int choice = input.nextInt();
            switch (choice) {
                case 1 -> {
                    vakit = "Sabah";
                    choiceBool = false;
                }
                case 2 -> {
                    vakit = "Öğle";
                    choiceBool = false;
                }
                case 3 -> {
                    vakit = "İkindi";
                    choiceBool = false;
                }
                case 4 -> {
                    vakit = "Akşam";
                    choiceBool = false;
                }
                case 5 -> {
                    vakit = "Yatsı";
                    choiceBool = false;
                }
                default -> System.out.print("Yanlış tuşa bastınız bir daha seçim yapınız: ");
            }
        }
        Attendance attendance = new Attendance(attendanceId++, vakit);
        attendances.add(attendance);
        for (Student student : students) {
            // todo
            String kontrol = input.next();
            boolean isAbsent = Objects.equals(kontrol, "+");
            StudentAttendance studentAttendance = new StudentAttendance(student, attendance, isAbsent);
            if(!isAbsent){
                student.absent++;
            }
            studentAttendances.add(studentAttendance);
        }
    }

    public void printAttendances() {
        for (Attendance attendance : attendances) {
            System.out.println(attendance.toString());
        }
    }

    public void printStudentAttendances(int attendanceId) {
        // todo
        List<StudentAttendance> choiceList = new ArrayList<>();
        //TODO
        //choiceList.add(studentAttendances.stream().filter());
        for (StudentAttendance studentAttendance : choiceList) {
            System.out.println(studentAttendance.toString());
        }
    }
}
