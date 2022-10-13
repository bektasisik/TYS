package service;

import domain.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    List<Student> students =new ArrayList<>();

    public void addStudent(String name, String surname){

    }
    public List<Student> getStudents(){
        return students;
    }
    public String getStudent(int studentId){
        return null;
    }
    public void updateStudent(String name, String surname){

    }
    public void deleteStudent(int studentId){

    }
}