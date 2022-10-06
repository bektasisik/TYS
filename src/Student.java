public class Student {
    public String name;
    public String surname;
    public int studentId;
    public int absent = 0;

    public Student(Integer studentId, String name, String surname) {
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return studentId + " " + name + " " + surname + " " + absent;
    }
}
