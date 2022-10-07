package mytemporarytys;

import java.util.*;

/**
 *
 */
public class StudentService2 {
    List<Student> students = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    int studentId = 0;

    public void addTalebe() {
        System.out.print("Talebenin Adını giriniz:");
        String name = input.next();
        System.out.print("Talebenin Soyadını giriniz:");
        String surname = input.next();

        Student student = new Student(++studentId, name, surname);
        students.add(student);
        System.out.println(student.toString());
    }

    public void printStudents() {
        for (Student student : students) {
            System.out.println(student.studentId + ": " + student.name + " " + student.surname + " " + student.absent);
        }
    }
}