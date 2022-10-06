package Domain;

public class Student {
    public int id;
    public String name;
    public String surname;
    public int absent = 0;

    public Student(Integer id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + surname + " " + absent;
    }
}
