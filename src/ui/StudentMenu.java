package ui;

import domain.Student;
import org.jetbrains.annotations.NotNull;
import service.StudentServiceOld;

import java.util.List;
import java.util.Scanner;

public class StudentMenu {
    private final StudentServiceOld studentServiceOld;
    private final Scanner input = new Scanner(System.in);
    public List<Student> students;

    public StudentMenu(@NotNull StudentServiceOld studentServiceOld) {
        this.students = studentServiceOld.students;
        this.studentServiceOld = studentServiceOld;
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

        label:
        while (true) {
            String choice = input.nextLine();
            switch (choice) {
                case "1":
                    studentServiceOld.addStudent();
                    break label;
                case "2":
                    studentServiceOld.printStudents();
                    break label;
                case "3":
                    studentServiceOld.updateStudent();
                    break label;
                case "4":
                    studentServiceOld.deleteStudent();
                    break label;
                case "5":
                    break label;
                default:
                    System.out.print("Yanlış tuşa bastınız. Lütfen listeden seçiniz: ");
            }
        }
    }
}
