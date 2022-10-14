package ui;

import domain.Attendance;
import domain.Student;
import domain.StudentAttendance;
import service.AttendanceService;
import service.StudentService;

import java.text.SimpleDateFormat;
import java.util.*;

public class AttendanceUi {
    AttendanceService attendanceService;
    private final int sequence = 1;
    private final List<Student> students;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Scanner input = new Scanner(System.in);

    public AttendanceUi(AttendanceService attendanceService, StudentService studentService) {
        this.attendanceService = attendanceService;
        this.students = studentService.getStudents();
    }

    public void takeAttendance() {
        if (students.size() == 0) {
            System.out.println("\nTalebe listeniz boş. Ana sayfaya yönlendiriliyorsunuz.");
        } else {
            System.out.println("\n" + formatter.format(new Date()) + " Lütfen Vakit Seçiniz.");
            System.out.println("+----+--------+");
            System.out.println("| 1) | Sabah  |");
            System.out.println("| 2) | Öğle   |");
            System.out.println("| 3) | İkindi |");
            System.out.println("| 4) | Akşam  |");
            System.out.println("| 5) | Yatsı  |");
            System.out.println("+----+--------+");

            System.out.print("Vakit Seçiminiz: ");

            while (true) {
                String prayerTime;
                String choice = input.nextLine();
                switch (choice) {
                    case "1" -> {
                        prayerTime = "Sabah";
                        return;
                    }
                    case "2" -> {
                        prayerTime = "Öğle";
                        return;
                    }
                    case "3" -> {
                        prayerTime = "İkindi";
                        return;
                    }
                    case "4" -> {
                        prayerTime = "Akşam";
                        return;
                    }
                    case "5" -> {
                        prayerTime = "Yatsı";
                        return;
                    }
                    default -> System.out.print("Yanlış tuşa bastınız bir daha seçim yapınız: ");
                }
            }
//            attendanceService.takeAttendance(prayerTime,);
        }
    }

    public void printAttendances() {
        List<Attendance> attendances = attendanceService.getAttendances();
        if (attendances.size() == 0) {
            System.out.println("\nYoklama listeniz boş. Ana sayfaya yönlendiriliyorsunuz.");
        } else {
            String leftAlignFormat = "| %-7s | %-13s | %-7s |%n";
            System.out.format("\n+----------+-----------------+---------+%n");
            System.out.format("|    No    |      Tarih      |  Vakit  |%n");
            System.out.format("+----------+-----------------+---------+%n");
            for (Attendance attendance : attendances) {
                System.out.format(leftAlignFormat,
                        "\t " + attendance.getId(),
                        "\t" + attendance.getDate(),
                        " " + attendance.getPrayerTime());
            }
            System.out.format("+----------+-----------------+---------+%n");
            printStudentAttendances();
        }
    }

    public void printStudentAttendances() {
        List<Attendance> attendances = attendanceService.getAttendances();
        if (attendanceService.getStudentAttendances().size() == 0) {
            System.out.println("Listeniz Boş. Ana Ekrana yönlendiriliyorsunuz.");
        } else {
            System.out.print("Lütfen listelemek istediğiniz yoklama vaktini seçiniz: ");
            while (true) {
                String choice = input.nextLine();
                int attendId;
                try {
                    attendId = Integer.parseInt(choice);
                } catch (Exception e) {
                    System.out.print("Hatalı giriş yaptınız. Lütfen Listedeki numaralardan seçiniz: ");
                    continue;
                }
                Optional<Attendance> attendanceOptional = attendances.stream().filter(attendance -> attendance.getId() == attendId).findFirst();
                if (attendanceOptional.isEmpty()) {
                    System.out.print("Lütfen listede olan yoklama numaralarından birini seçiniz: ");
                    continue;
                }
                Attendance attendance = attendanceOptional.get();
                System.out.format("\n+------------------------------------------------------------------+%n");
                String leftAlignFormat1 = "| \t\t\t\t%-12s - %-32s |%n";
                System.out.format(leftAlignFormat1,
                        "\t" + attendance.getDate(),
                        " " + attendance.getPrayerTime());
                System.out.format("+----------------------+---------------------+---------------------+%n");
                System.out.format("|          AD          |        SOYAD        | DEVAMSIZLIK BİLGİSİ |%n");
                System.out.format("+----------------------+---------------------+---------------------+%n");
                String leftAlignFormat2 = "| %-20s | %-19s | %-16s |%n";
                List<StudentAttendance> studentAttendanceFilter = attendanceService.getAttendancesByAttendanceId(attendId);

                if (studentAttendanceFilter.size() == 0) {
                    System.out.println("Listeniz Boş. Ana Ekrana yönlendiriliyorsunuz.");
                } else {
                    studentAttendanceFilter.forEach(studentAttendance -> System.out.format(leftAlignFormat2,
                            studentAttendance.getStudent().getName(),
                            studentAttendance.getStudent().getSurname(),
                            "\t\t  " + studentAttendance.getIsAbsenceToString()));
                    System.out.format("+----------------------+---------------------+---------------------+%n");
                }
                break;
            }
        }
    }

    /**
     * Talebe ID'ye göre tum talebeyi listeler.
     */
    public void printWithStudentId() {
        if (attendanceService.getAttendances().size() == 0) {
            System.out.println("\nYoklama listeniz boş. Ana sayfaya yönlendiriliyorsunuz.");
        } else {
            String leftAlignFormat = "| %-7s | %-20s | %-19s |%n";
            System.out.format("+----------+----------------------+---------------------+%n");
            System.out.format("|    No    |          AD          |        SOYAD        |%n");
            System.out.format("+----------+----------------------+---------------------+%n");
            for (Student student : students) {
                System.out.format(leftAlignFormat,
                        "\t " + student.getId(),
                        student.getName(),
                        student.getSurname());
            }
            System.out.format("+----------+----------------------+---------------------+%n");
            printAttendanceWithStudentId();
        }
    }

    /**
     * printWithStudentId() ile listelenen talebelerin seçilen talebeye göre hangi vakitte olmadığını listeler.
     */
    public void printAttendanceWithStudentId() {
        if (attendanceService.getStudentAttendances().size() == 0) {
            System.out.println("\nTalebe listeniz Boş. Ana Ekrana yönlendiriliyorsunuz.");
        } else {
            System.out.print("\nLütfen listelemek istediğiniz talebeyi seçiniz: ");
            while (true) {
                String choice = input.nextLine();
                int studentId;
                try {
                    studentId = Integer.parseInt(choice);
                } catch (Exception e) {
                    System.out.print("Hatalı giriş yaptınız. Lütfen Listedeki numaralardan seçiniz: ");
                    continue;
                }

                Optional<Student> studentOptional = students.stream().filter(student -> student.getId() == studentId).findFirst();
                if (studentOptional.isEmpty()) {
                    System.out.print("Lütfen listede olan yoklama numaralarından birini seçiniz: ");
                    continue;
                }
                Student student = studentOptional.get();
                System.out.format("+----------------+---------+------------------+%n");
                String leftAlignFormat1 = "| %-42s |%n";
                System.out.format(leftAlignFormat1,
                        "\t  " + student.getName() + " " + student.getSurname());
                System.out.format("+----------------+---------+------------------+%n");
                System.out.format("|      Tarih     |  Vakit  | DEVAMSIZ BİLGİSİ |%n");
                System.out.format("+----------------+---------+------------------+%n");
                String leftAlignFormat2 = "| %-14s | %-7s | %-11s |%n";
                List<StudentAttendance> studentAttendanceFilter = attendanceService.getAttendancesByStudentId(studentId);

                if (studentAttendanceFilter.size() == 0) {
                    System.out.println("Listeniz Boş. Ana Ekrana yönlendiriliyorsunuz.");
                } else {
                    studentAttendanceFilter.forEach(studentAttendance -> System.out.format(leftAlignFormat2,
                            " " + studentAttendance.getAttendance().getDate(),
                            " " + studentAttendance.getAttendance().getPrayerTime(),
                            "\t\t" + studentAttendance.getIsAbsenceToString() + "\t"));
                    System.out.format("+----------------+---------+------------------+%n");
                }
                break;
            }
        }
    }
}
