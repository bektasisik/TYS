package ui;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class StudentMenu {
    private final StudentUi studentUi;
    private final Scanner input = new Scanner(System.in);

    public StudentMenu(@NotNull StudentUi studentUi) {
        this.studentUi = studentUi;
    }

    public void printStudentMenu() {
        System.out.println();
        System.out.println("+----+---------------------+");
        System.out.println("|    TALEBE İŞLEM MENÜSÜ   |");
        System.out.println("+----+---------------------+");
        System.out.println("| 1) | Talebe Ekleme       |");
        System.out.println("| 2) | Talebe Listeleme    |");
        System.out.println("| 3) | Talebe Güncelleme   |");
        System.out.println("| 4) | Talebe Silme        |");
        System.out.println("| 5) | Geri                |");
        System.out.println("+----+---------------------+");
        System.out.print("İşlem: ");

        label: // todo
        while (true) {
            String choice = input.nextLine();
            switch (choice) {
                case "1":
                    studentUi.addStudent();
                    break label;
                case "2":
                    studentUi.printStudents();
                    break label;
                case "3":
                    studentUi.updateStudent();
                    break label;
                case "4":
                    studentUi.deleteStudent();
                    break label;
                case "5":
                    break label;
                default:
                    System.out.print("Yanlış tuşa bastınız. Lütfen listeden seçiniz: ");
            }
        }
    }
}