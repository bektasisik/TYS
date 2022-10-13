package service;

import domain.Attendance;
import domain.Student;
import domain.StudentAttendance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AttendanceService {
    List<Attendance> attendances = new ArrayList<>();
    List<StudentAttendance> studentAttendances = new ArrayList<>();
    StudentService studentService;

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public List<StudentAttendance> getAttendancesByStudentId(int studentId) {
        return studentAttendances;
    }

    public List<StudentAttendance> getAttendancesByAttendanceId(int attendanceId) {
        return studentAttendances;
    }
    public void takeAttendance(String prayerTime, Map<Student, Boolean> attendanceMap){

    }
}
