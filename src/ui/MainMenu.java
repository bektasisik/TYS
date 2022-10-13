package ui;

import service.AttendanceServiceOld;
import service.StudentServiceOld;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    StudentServiceOld studentServiceOld;
    AttendanceServiceOld attendanceServiceOld;
    StudentMenu studentMenu;
    AttendanceMenu attendanceMenu;

    public MainMenu(StudentServiceOld studentServiceOld, AttendanceServiceOld attendanceServiceOld) {
        this.studentServiceOld = studentServiceOld;
        this.attendanceServiceOld = attendanceServiceOld;
        this.studentMenu = new StudentMenu(studentServiceOld);
        this.attendanceMenu = new AttendanceMenu(attendanceServiceOld);
    }

    public void printMainMenu() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Scanner input = new Scanner(System.in);
        System.out.println(formatter.format(new Date()) + " Talebe Yoklama Sistemine Hoşgeldiniz...");

        //İşlem Menüsü seçimi kontrolü yapılır.
        boolean choiceBool = true;
        while (choiceBool) {
            System.out.println("\nYapmak istediğiniz işlemi seçiniz...");
            System.out.println("+----+--------------------------------------+");
            System.out.println("| 1) | Talebe İşlemleri                     |");
            System.out.println("| 2) | Yoklama İşlemleri                    |");
            System.out.println("| 3) | Sistemden Çıkış                      |");
            System.out.println("+----+--------------------------------------+");
            System.out.print("İşlem: ");
            String choice = input.nextLine();
            switch (choice) {
                // printStudentMenu();
                case "1" -> studentMenu.printStudentMenu();
                // takeAttendance();
                case "2" -> attendanceMenu.printAttendanceMenu();
                // exit
                case "3" -> choiceBool = false;
                default -> System.out.println("Yanlış Tuşa bastınız.");
            }
        }
    }
}
