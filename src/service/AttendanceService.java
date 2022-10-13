package service;

import domain.Attendance;
import domain.Student;
import domain.StudentAttendance;

import java.util.*;

public class AttendanceService {
    private final List<Attendance> attendances = new ArrayList<>();
    private final List<StudentAttendance> studentAttendances = new ArrayList<>();
    private int sequence = 1;

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public Attendance getAttendance(int attendanceId) {
        return attendances.stream()
                .filter(attendance -> attendance.getId() == attendanceId).findFirst().orElseThrow();
    }

    public List<StudentAttendance> getAttendancesByStudentId(int studentId) {
        return studentAttendances.stream()
                .filter(studentAttendance -> studentAttendance.getStudent().getId() == studentId).toList();
    }

    public List<StudentAttendance> getAttendancesByAttendanceId(int attendanceId) {
        return studentAttendances.stream()
                .filter(studentAttendance -> studentAttendance.getAttendance().getId() == attendanceId).toList();
    }

    public void takeAttendance(String prayerTime, Map<Student, Boolean> attendanceMap) {
        Attendance attendance = new Attendance(sequence++, prayerTime);
        attendances.add(attendance);

        attendanceMap.forEach((student, isAbsence) -> {
            StudentAttendance studentAttendance = new StudentAttendance(student, attendance, isAbsence);
            student.increaseAbsent();
            studentAttendances.add(studentAttendance);
        });
    }
}
