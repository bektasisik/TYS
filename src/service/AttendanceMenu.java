package service;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class AttendanceMenu {
    AttendanceService attendanceService;
    private final Scanner input = new Scanner(System.in);

    public AttendanceMenu(@NotNull AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
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

        label:
        while (true) {
            String choice = input.nextLine();
            switch (choice) {
                case "1":
                    attendanceService.takeAttendance();
                    break label;
                case "2":
                    attendanceService.printAttendances();
                    break label;
                case "3":
                    attendanceService.printWithStudentId();
                    break label;
                case "4":
                    break label;
                default:
                    System.out.print("Yanlış tuşa bastınız. Lütfen listeden seçiniz: ");
            }
        }
    }
}


