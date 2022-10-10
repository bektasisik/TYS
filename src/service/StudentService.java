package service;

import domain.*;

import java.util.*;

public class StudentService {
    private final Scanner input = new Scanner(System.in);
    private int studentId = 1;
    public List<Student> students = new ArrayList<>();

    public void addStudent(String name, String surname) {
        Student student = new Student(studentId++, name, surname);
        students.add(student);
    }

    // Kullanıcıdan AD-SOYAD bilgilerini alır.
    // Aldığı bilgiler ile student class'ını oluşturur.
    // O class'ı bir obje haline getirir ve listeye ekler.
    public void addStudent() {
        String name, surname;
        while (true) {
            name = getNameInput("Talebe adı", 14);
            surname = getNameInput("Talebe soyadı", 15);

            if (name.length() + surname.length() < 5) {
                System.out.println("Yanlış AD-SOYAD girdiniz.");
                continue;
            }
            break;
        }
        Student student = new Student(studentId++, name, surname);
        students.add(student);
        System.out.println("\nTalebe No: " + student.getId()
                + "\nTalebe Ad: " + student.getName()
                + "\nTalebe Soyadı: " + student.getSurname());
        System.out.println("Talebe Eklenmiştir.\n");
    }

    //Talebe listesini ekrana yazdırır.
    public void printStudents() {
        if (students.size() == 0) {
            System.out.println("\nTalebe listeniz boş. Ana sayfaya yönlendiriliyorsunuz.");
        } else {
            String leftAlignFormat = "| %-8s | %-14s | %-15s | %-13s |%n";
            System.out.println();
            System.out.format("+-----------+----------------+-----------------+--------------------+%n");
            System.out.format("| Talebe NO |       AD       |      SOYAD      | TOPLAM DEVAMSIZLIK |%n");
            System.out.format("+-----------+----------------+-----------------+--------------------+%n");
            for (Student student : students) {
                System.out.format(leftAlignFormat, "\t" + student.getId(), student.getName(), student.getSurname(), "\t\t" + student.getAbsent());
            }
            System.out.format("+-----------+----------------+-----------------+--------------------+%n");
        }
    }

    private String getNameInput(String message, int maxLength) {
        String name = "";
        while (true) {
            System.out.format("\n%s giriniz: ", message);
            name = input.nextLine();
            if (!name.matches("[a-zA-ZğüşöçıİĞÜŞÖÇ]+")) {
                System.out.println("\nLütfen sadece harf kullanın.");
                continue;
            }
            if (name.length() > maxLength) {
                System.out.format("\n%s uzunluğu max %s olmalıdır.", message, maxLength);
                continue;
            }
            break;
        }
        return name;
    }
}