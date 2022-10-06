package Domain;

public class StudentAttendance {
    Student student;
    Attendance attendance;
    Boolean absence;

    public StudentAttendance(Student student, Attendance attendance, Boolean absence) {
        this.student = student;
        this.attendance = attendance;
        this.absence = absence;
    }

    @Override
    public String toString() {
        return student + " " + attendance + " " + absence ;
    }
}
