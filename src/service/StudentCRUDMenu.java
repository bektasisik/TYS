package service;

import domain.Student;

import java.util.List;
import java.util.Scanner;

public class StudentCRUDMenu {
    private final StudentService studentService;
    public List<Student> students;
    private final Scanner input = new Scanner(System.in);

    public StudentCRUDMenu(StudentService studentService) {
        this.students = studentService.students;
        this.studentService = studentService;
    }

    public void studentMenu() {
        System.out.println("+----+---------------------+");
        System.out.println("| 1) | Talebe Ekleme       |");
        System.out.println("| 2) | Talebe Listeleme    |");
        System.out.println("| 3) | Talebe Güneclleme   |");
        System.out.println("| 4) | Talebe Silme        |");
        System.out.println("| 5) | Geri                |");
        System.out.println("+----+---------------------+");
        System.out.print("İşlem: ");

        label:
        while (true) {
            String choise = input.nextLine();
            switch (choise) {
                case "1":
                    studentService.addStudent();
                    break label;
                case "2":
                    studentService.printStudents();
                    break label;
                case "3":
                    studentService.updateStudent();
                    break label;
                case "4":
                    studentService.deleteStudent();
                    break label;
                case "5":
                    break label;
                default:
                    System.out.print("Yanlış tuşa bastınız. Lütfen listeden seçiniz: ");
            }
        }
    }
}
