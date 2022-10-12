import service.*;

import java.util.*;
import java.text.*;

public class Main {
    public static void main(String[] args) {

        //Services
        StudentService studentService = new StudentService();
        AttendanceService attendanceService = new AttendanceService(studentService.students);
        StudentMenu studentMenu = new StudentMenu(studentService);
        AttendanceMenu attendanceMenu = new AttendanceMenu(attendanceService);

        // Listeye proje başlatıldığı zaman default 10 adet talebe ekler.
        // Bir sonraki talebe 11. sıradan başlar.
        studentService.addStudent("Veli", "Çam");
        studentService.addStudent("Abdurrahman", "Kutlu");
        studentService.addStudent("Emre", "Yavuz");
        studentService.addStudent("Kaan", "Koca");
        studentService.addStudent("Enes Bahadır", "Yıldırım");
//        studentService.addStudent("Enver", "Yıldırım");
//        studentService.addStudent("Yasin", "Büzgülü");
//        studentService.addStudent("Bektaş", "Işık");
//        studentService.addStudent("Mehmet Ercan", "Akcan");
//        studentService.addStudent("Haruncan", "Yıldırım");

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
            String choice = input.next();
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