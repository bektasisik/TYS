package service;

import domain.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private int sequence = 1;

    public void addStudent(String name, String surname) {
        Student student = new Student(sequence++, name, surname);
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student getStudent(int studentId) {
        return students.stream()
                .filter(student -> student.getId() == studentId).findFirst().orElseThrow();
    }

    public void updateStudent(int studentId, String name, String surname) {
        Student student = getStudent(studentId);
        student.setName(name);
        student.setSurname(surname);
    }

    public void deleteStudent(int studentId) {
        students.removeIf(student -> student.getId() == studentId);
    }
}