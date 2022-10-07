package Domain;

public class StudentAttendance {
    Student student;
    public Attendance attendance;
    Boolean isAbsence;

    public StudentAttendance(Student student, Attendance attendance, Boolean isAbsence) {
        this.student = student;
        this.attendance = attendance;
        this.isAbsence = isAbsence;
    }

    @Override
    public String toString() {
        return student + " " + attendance + " " + isAbsence ;
    }
}