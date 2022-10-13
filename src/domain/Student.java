package domain;

public class Student {
    private final int id;
    private String name;
    private String surname;
    private int absent;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public int getAbsent() {
        return absent;
    }

    public void increaseAbsent() {
        this.absent = absent + 1;
    }

    public Student(Integer id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return getId() + " " + getName() + " " + getSurname() + " " + getAbsent();
    }
}