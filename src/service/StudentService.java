package service;

import domain.Student;

import java.lang.instrument.IllegalClassFormatException;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private int sequence = 1;

    public StudentService() throws IllegalClassFormatException {
        initializeStudents();
    }

    private void initializeStudents() throws IllegalClassFormatException {
        addStudent("Veli", "Çam");
        addStudent("Abdurrahman", "Kutlu");
        addStudent("Emre", "Yavuz");
        addStudent("Kaan", "Koca");
        addStudent("Enes Bahadır", "Yıldırım");
        addStudent("Enver", "Yıldırım");
        addStudent("Yasin", "Büzgülü");
        addStudent("Bektaş", "Işık");
        addStudent("Mehmet Ercan", "Akcan");
        addStudent("Haruncan", "Yıldırım");
    }

    public Student addStudent(String name, String surname) throws IllegalClassFormatException {
        validateStudent(name, surname);
        Student student = new Student(sequence++, name, surname);
        students.add(student);
        return student;
    }

    private void validateStudent(String name, String surname) throws IllegalClassFormatException {
        if (!(isValidName(name) && isValidName(surname))) {
            throw new IllegalClassFormatException();
        }
    }

    private boolean isValidName(String name){
        return name != null && name.length() > 2 && name.length() < 20 && name.matches("[a-zA-Z ğüşöçıİĞÜŞÖÇ]+\\S\\D\\Z");
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