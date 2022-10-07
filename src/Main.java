import Service.*;

import java.util.*;
import java.text.*;

public class Main {
    public static void main(String[] args) {

        //Services
        StudentService studentService = new StudentService();
        AttendanceService attendanceService = new AttendanceService(studentService.students);

//        StudentService studentService1 = new StudentService();
//        MyExampleTYS.InspectionService inspectionService = new MyExampleTYS.InspectionService(studentService1);
//        MyExampleTYS.VakitService vakitService = new MyExampleTYS.VakitService();
//        MyExampleTYS.VakitInspection vakitInspection = new MyExampleTYS.VakitInspection();
//        YoklamaListesi yoklamaListesi = new YoklamaListesi();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Scanner input = new Scanner(System.in);
        System.out.println(formatter.format(date) + " Talebe Yoklama Sistemine Hoşgeldiniz...");

        boolean choiceBool = true;
        while (choiceBool) {
            System.out.println("Yapmak istediğiniz işlemi seçiniz...");
            System.out.println("1: Sisteme Talebe Ekleme");
            System.out.println("2: Talebe Listesi");
            System.out.println("3: Yoklama Alma");
            System.out.println("4: Yoklama Sonucu Listesi");
            System.out.println("5: Sistemden Çıkış");
            System.out.print("İşlem: ");
            int choice = input.nextInt();
            switch (choice) {
                //addStudent();
                case 1 -> {
                    String devam;
                    do {
                        studentService.addStudent();
                        System.out.print("Talebe Eklenmeye devam edilsin mi?(e):");
                        devam = input.next();
                    } while (Objects.equals(devam, "e"));
                }
                //printStudents();
                case 2 -> studentService.printStudents();
                //takeAttendance();
                case 3 -> attendanceService.takeAttendance();
                //printAttendance();
                case 4 -> {
                    attendanceService.printAttendances();
                    System.out.print("Lütfen listelemek istediğiniz yoklama vaktini seçiniz: ");
                    //TODO while kontrolü ekle
                    int id = input.nextInt();
                    attendanceService.printStudentAttendances(id);
                }
                //cıkış
                case 5 -> choiceBool = false;

                default -> System.out.println("Yanlış Tuşa bastınız.");
            }
        }
    }
}