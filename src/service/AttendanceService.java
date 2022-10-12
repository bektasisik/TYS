package service;

import domain.*;

import java.text.*;
import java.util.*;

public class AttendanceService {
    private final Scanner input = new Scanner(System.in);
    private final List<Attendance> attendances = new ArrayList<>();
    private final List<StudentAttendance> studentAttendances = new ArrayList<>();
    private final List<Student> students;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private int attendanceId = 1;

    public AttendanceService(List<Student> students) {
        this.students = students;
    }

    /**
     * Yoklama almak için önce yoklama alacağı vakti seçer.
     * Yoklama almak için önce yoklama alacağı vakti seçer.
     * Oluşturduğu yoklama class'larını Accentandes listesine obje olarak ekler.
     * Talebe listesinden talebeleri tek tek yoklama alır.
     */
    public void takeAttendance() {
        if (students.size() == 0) {
            System.out.println("\nTalebe listeniz boş. Ana sayfaya yönlendiriliyorsunuz.");
        } else {
            String prayerTime;
            System.out.println("\n" + formatter.format(new Date()) + " Lütfen Vakit Seçiniz.");
            System.out.println("+----+--------+");
            System.out.println("| 1) | Sabah  |");
            System.out.println("| 2) | Öğle   |");
            System.out.println("| 3) | İkindi |");
            System.out.println("| 4) | Akşam  |");
            System.out.println("| 5) | Yatsı  |");
            System.out.println("+----+--------+");

            System.out.print("Vakit Seçiminiz: ");

            label:
            while (true) {
                String choice = input.next();
                switch (choice) {
                    case "1":
                        prayerTime = "Sabah";
                        break label;
                    case "2":
                        prayerTime = "Öğle";
                        break label;
                    case "3":
                        prayerTime = "İkindi";
                        break label;
                    case "4":
                        prayerTime = "Akşam";
                        break label;
                    case "5":
                        prayerTime = "Yatsı";
                        break label;
                    default:
                        System.out.print("Yanlış tuşa bastınız bir daha seçim yapınız: ");
                }
            }
            Attendance attendance = new Attendance(attendanceId++, prayerTime);
            attendances.add(attendance);
            System.out.println("\n" + prayerTime + " vakti için talebe burada ise (+) tuşuna basınız. Değil ise (-) tuşuna basınız.");
            for (Student student : students) {
                System.out.print(student.getName() + " " + student.getSurname() + ": ");
                boolean isAbsence = true;
                while (true) {
                    String kontrol = input.next();
                    if (kontrol.equals("+")) break;
                    else if (kontrol.equals("-")) {
                        isAbsence = false;
                        student.setAbsent();
                        break;
                    } else System.out.print("Yanlış tuşa bastınız (+ yada - tuşundan birine basınız.): ");
                }
                StudentAttendance studentAttendance = new StudentAttendance(student, attendance, isAbsence);
                studentAttendances.add(studentAttendance);
            }
            System.out.println("Yoklama işlemi bitmiştir. Ana sayfaya yönlendiriliyorsunuz. ");
        }
    }

    /**
     * Yoklamalar listesini ekrana çağırır.
     */
    public void printAttendances() {
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

    /**
     * Yoklamalar listesinden seçilen yoklamaya göre vakit ve tarihe göre alınan yoklama sonucunu gösterir.
     */
    public void printStudentAttendances() {
        if (studentAttendances.size() == 0) {
            System.out.println("Listeniz Boş. Ana Ekrana yönlendiriliyorsunuz.");
        } else {
            System.out.print("Lütfen listelemek istediğiniz yoklama vaktini seçiniz: ");
            while (true) {
                try {
                    String choiceAttendanceId = input.nextLine();
                    Optional<Attendance> attendanceOptional = attendances.stream().filter(attendance -> attendance.getId() == Integer.parseInt(choiceAttendanceId)).findFirst();
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
                    List<StudentAttendance> studentAttendanceFilter = studentAttendances.stream()
                            .filter(studentAttendance -> studentAttendance.getAttendance().getId() == attendance.getId()).toList();

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
                } catch (Exception e) {
                    System.out.print("Hatalı giriş yaptınız. Lütfen Listedeki numaralardan seçiniz: ");
                }
            }
        }
    }

    /**
     * Talebe ID'ye göre tum talebeyi listeler.
     */
    public void printWithStudentId() {
        if (attendances.size() == 0) {
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
        if (studentAttendances.size() == 0) {
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
                List<StudentAttendance> studentAttendanceFilter = studentAttendances.stream()
                        .filter(studentAttendance -> studentAttendance.getStudent().getId() == student.getId()).toList();

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