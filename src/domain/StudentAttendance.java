package domain;

public class StudentAttendance {
    private final Student student;
    private final Attendance attendance;
    private final Boolean isAbsence;

    public Attendance getAttendance() {
        return attendance;
    }

    public StudentAttendance(Student student, Attendance attendance, Boolean isAbsence) {
        this.student = student;
        this.attendance = attendance;
        this.isAbsence = isAbsence;
    }

    @Override
    public String toString() {
        String isAbsenceToString = isAbsence ? "Var" : "Yok";
        return student.getName() + " " + student.getSurname() + " " + isAbsenceToString;
    }
}