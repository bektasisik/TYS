package Service;

import Domain.Student;

import java.util.*;

public class StudentService {
    private final Scanner input = new Scanner(System.in);
    private int studentId = 1;
    public List<Student> students = new ArrayList<>();


    public void addStudent(){
        System.out.print("Talebenin Adını giriniz:");
        String name = input.next();
        System.out.print("Talebenin Soyadını giriniz:");
        String surname = input.next();

        Student student = new Student(studentId++, name, surname);
        students.add(student);
        System.out.println(student.toString());
    }

    public void printStudents(){
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }
}
