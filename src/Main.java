import service.*;

import java.util.*;
import java.text.*;

public class Main {
    public static void main(String[] args) {

        //Services
        StudentService studentService = new StudentService();
        AttendanceService attendanceService = new AttendanceService(studentService.students);

        studentService.addStudent("Veli", "Çam");
        studentService.addStudent("Abdurrahman", "Kutlu");
        studentService.addStudent("Emre", "Yavuz");
        studentService.addStudent("Kaan", "Koca");
        studentService.addStudent("Enes Bahadır", "Yıldırım");
        studentService.addStudent("Enver", "Yıldırım");
        studentService.addStudent("Yasin", "Büzgülü");
        studentService.addStudent("Bektaş", "Işık");
        studentService.addStudent("Mehmet Ercan", "Akcan");
        studentService.addStudent("Haruncan", "Yıldırım");


//        StudentService studentService1 = new StudentService();
//        MyExampleTYS.InspectionService inspectionService = new MyExampleTYS.InspectionService(studentService1);
//        MyExampleTYS.VakitService vakitService = new MyExampleTYS.VakitService();
//        MyExampleTYS.VakitInspection vakitInspection = new MyExampleTYS.VakitInspection();
//        YoklamaListesi yoklamaListesi = new YoklamaListesi();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Scanner input = new Scanner(System.in);
        System.out.println(formatter.format(new Date()) + " Talebe Yoklama Sistemine Hoşgeldiniz...");

        boolean choiceBool = true;
        while (choiceBool) {
            System.out.println("Yapmak istediğiniz işlemi seçiniz...");
            System.out.println("1) Sisteme Talebe Ekleme");
            System.out.println("2) Talebe Listesi");
            System.out.println("3) Yoklama Alma");
            System.out.println("4) Yoklama Sonucu Listesi");
            System.out.println("5) Sistemden Çıkış");
            System.out.print("İşlem: ");
            String choice = input.next();
            switch (choice) {
                //addStudent();
                case "1" -> {
                    boolean isAddAgain = true;
                    String addAgain;
                    studentService.addStudent();
                    System.out.print("Talebe Eklenmeye devam edilsin mi?(e/h):");
                    while (isAddAgain){
                        addAgain = input.next();
                        if ("e".equals(addAgain)) {
                            studentService.addStudent();
                            System.out.print("Talebe Eklenmeye devam edilsin mi?(e/h):");
                        } else if ("h".equals(addAgain)) {
                            isAddAgain = false;
                        } else {
                            System.out.print("Yanlış bir tuşa bastınız. (e/h):");
                        }
                    }
                }
                //printStudents();
                case "2" -> studentService.printStudents();
                //takeAttendance();
                case "3" -> attendanceService.takeAttendance();
                //printAttendances();
                case "4" -> attendanceService.printAttendances();
                //cıkış
                case "5" -> choiceBool = false;

                default -> System.out.println("Yanlış Tuşa bastınız.");
            }
        }
    }
}