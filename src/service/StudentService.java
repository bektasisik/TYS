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
        System.out.print("Talebenin Adını giriniz:");
        String name = input.nextLine();
        System.out.print("Talebenin Soyadını giriniz:");
        String surname = input.nextLine();

        Student student = new Student(studentId++, name, surname);
        students.add(student);
        System.out.println("Talebe No: " + student.getId()
                + "\nTalebe Ad: " + student.getName()
                + "\nTalebe Soyadı: " + student.getSurname());
        System.out.println("Talebe Eklenmiştir.");
    }
    //Talebe listesini ekrana yazdırır.
    public void printStudents() {
        String leftAlignFormat = "| %-8s | %-14s | %-15s | %-13s |%n";
        System.out.format("+-----------+----------------+-----------------+--------------------+%n");
        System.out.format("| Talebe NO |       AD       |      SOYAD      | TOPLAM DEVAMSIZLIK |%n");
        System.out.format("+-----------+----------------+-----------------+--------------------+%n");
        for (Student student : students) {
            System.out.format(leftAlignFormat, "\t"+student.getId(), student.getName(), student.getSurname(), "\t\t"+student.getAbsent());
        }
        System.out.format("+-----------+----------------+-----------------+--------------------+%n");
    }
}