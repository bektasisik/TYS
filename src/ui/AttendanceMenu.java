package ui;

import org.jetbrains.annotations.NotNull;
import service.AttendanceServiceOld;

import java.util.Scanner;

public class AttendanceMenu {
    private final AttendanceServiceOld attendanceServiceOld;
    private final Scanner input = new Scanner(System.in);

    public AttendanceMenu(@NotNull AttendanceServiceOld attendanceServiceOld) {
        this.attendanceServiceOld = attendanceServiceOld;
    }

    public void printAttendanceMenu() {
        System.out.println();
        System.out.println("+----+--------------------------------+");
        System.out.println("|         YOKLAMA İŞLEM MENÜSÜ        |");
        System.out.println("+----+--------------------------------+");
        System.out.println("| 1) | Yoklama Alma                   |");
        System.out.println("| 2) | Vakite Göre Yoklama Sırala     |");
        System.out.println("| 3) | Talebeye Göre Yoklama Sırala   |");
        System.out.println("| 4) | Geri                           |");
        System.out.println("+----+--------------------------------+");
        System.out.print("İşlem: ");

        while (true) {
            String choice = input.nextLine();
            switch (choice) {
                case "1":
                    attendanceServiceOld.takeAttendance();
                    return;
                case "2":
                    attendanceServiceOld.printAttendances();
                    return;
                case "3":
                    attendanceServiceOld.printWithStudentId();
                    return;
                case "4":
                    return;
                default:
                    System.out.print("Yanlış tuşa bastınız. Lütfen listeden seçiniz: ");
            }
        }
    }
}


