package mytemporarytys;

import java.util.*;
import java.text.*;

public class InspectionService {
    Scanner input = new Scanner(System.in);
    StudentService2 studentService2;

    StudentInspectionService studentInspectionService;
    VakitService vakitService;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    String tarih = formatter.format(new Date());
    int choice;
    String vakit;
    int yoklamaId;


    public InspectionService(StudentService2 studentService2) {
        this.studentService2 = studentService2;
    }

    public void choiceTime(){
        System.out.println(tarih);

        System.out.println("Lütfen Vakit Seçiniz.\n1)Sabah\n2)Öğle\n3)İkindi\n4)Akşam\n5)Yatsı");
        System.out.print("Vakit Seçiminiz: ");

        boolean choiceBool = true;
        while (choiceBool) {
            choice = input.nextInt();
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
        System.out.println(vakit);
    }
    public void takeYoklama() {
        choiceTime();
        System.out.println("Talebe burada ise (+) tuşuna basınız. Değil ise (-) tuşuna basınız.");
        yoklamaId++;

        for (Student student : studentService2.students) {
            System.out.print(student.name + " " + student.surname + ": ");
            String kontrol = input.next();
            boolean isAbsent = true;
            if (Objects.equals(kontrol, "-")) {
                student.absent++;
                isAbsent = false;
            }
            System.out.println(yoklamaId + " " + student + " " + isAbsent + " " + vakit + " " + tarih);
            //vakitService.addVakitList(yoklamaId, student, isAbsent, vakit, tarih);
        }
    }
}
