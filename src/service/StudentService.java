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
            name = getNameInput("adı", 20);
            surname = getNameInput("soyadı", 19);

            if (name.length()<3){
                System.out.println("İsim uzunluğu min 3 karakter olmalıdır.");
                continue;
            }
            if (surname.length()<2){
                System.out.println("Soyisim uzunluğu min 2 karakter olmalıdır.");
                continue;
            }
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
            String leftAlignFormat = "| %-8s | %-20s | %-19s | %-12s |%n";
            System.out.println();
            System.out.format("+-----------+----------------------+---------------------+--------------------+%n");
            System.out.format("| Talebe NO |          AD          |        SOYAD        | TOPLAM DEVAMSIZLIK |%n");
            System.out.format("+-----------+----------------------+---------------------+--------------------+%n");
            for (Student student : students) {
                System.out.format(leftAlignFormat,
                        "\t  " + student.getId(),
                        student.getName(),
                        student.getSurname(),
                        "\t\t\t" + student.getAbsent());
            }
            System.out.format("+-----------+----------------------+---------------------+--------------------+%n");
        }
    }

    private String getNameInput(String message, int maxLength) {
        String name = "";
        while (true) {
            System.out.format("\nTalebe %s giriniz: ", message);
            name = input.nextLine();
            if (!name.matches("[a-zA-Z ğüşöçıİĞÜŞÖÇ]+\\S\\D\\Z")) {
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