package domain;

public class Student {
    private final int id;
    private final String name;
    private final String surname;
    private int absent;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAbsent() {
        return absent;
    }

    public void setAbsent() {
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