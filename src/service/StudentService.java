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

    public void printStudents() {
        System.out.println("Talebe Numarası: Ad Soyad");
        for (Student student : students) {
            System.out.println(student.getId() + ": " + student.getName() + " " + student.getSurname());
        }
    }
}