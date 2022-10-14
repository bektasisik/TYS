package ui;

import domain.Student;
import service.AttendanceService;
import service.StudentService;

import java.lang.instrument.IllegalClassFormatException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class StudentUi {

    private final StudentService studentService;
    private final Scanner input = new Scanner(System.in);

    public StudentUi(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Kullanıcıdan AD-SOYAD bilgilerini alır.
     * Aldığı bilgiler ile student class'ını oluşturur.
     * O class'ı bir obje haline getirir ve listeye ekler.
     */
    public void addStudent() {
        while (true) {
            String name, surname;
            Student student;
            while (true) {
                System.out.print("\nTalebe adı giriniz: ");
                name = input.nextLine();
                System.out.print("\nTalebe soyadı giriniz: ");
                surname = input.nextLine();
                try {
                    student = studentService.addStudent(name, surname);
                } catch (IllegalClassFormatException e) {
                    // todo System.out.format("\n%s uzunluğu max %s olmalıdır.", message, maxLength);
                    continue;
                }
                break;
            }
            System.out.println("\nTalebe No: " + student.getId() + "\nTalebe Ad: " + student.getName() + "\nTalebe Soyadı: " + student.getSurname());
            System.out.println("Talebe Eklenmiştir.\n");
            System.out.print("Talebe Eklenmeye devam edilsin mi?(e/h):");
            String addAgain = input.nextLine();
            if ("e".equals(addAgain)) {
                continue;
            } else if ("h".equals(addAgain)) {
                break;
            } else {
                System.out.print("Yanlış bir tuşa bastınız. (e/h):");
            }
            break;
        }
    }

    /**
     * Talebe listesini ekrana yazdırır.
     */
    public void printStudents() {
        List<Student> students = studentService.getStudents();
        if (students.size() == 0) {
            System.out.println("\nTalebe listeniz boş. Ana sayfaya yönlendiriliyorsunuz.");
        } else {
            String leftAlignFormat = "| %-8s | %-20s | %-19s | %-12s |%n";
            System.out.println();
            System.out.format("+-----------+----------------------+---------------------+--------------------+%n");
            System.out.format("| Talebe NO |          AD          |        SOYAD        | TOPLAM DEVAMSIZLIK |%n");
            System.out.format("+-----------+----------------------+---------------------+--------------------+%n");
            for (Student student : students) {
                System.out.format(leftAlignFormat, "\t  " + student.getId(), student.getName(), student.getSurname(), "\t\t\t" + student.getAbsent());
            }
            System.out.format("+-----------+----------------------+---------------------+--------------------+%n");
        }
    }

    /**
     * Seçilen talebeye yeni ad soyad verilerek listede güncellenmesini sağlar.
     */
    public void updateStudent() {
        printStudents();
        System.out.print("Güncellemek istediğiniz talebenin numarasını giriniz: ");
        while (true) {
            String choice = input.nextLine();
            int studentId;
            try {
                studentId = Integer.parseInt(choice);
            } catch (Exception e) {
                System.out.print("Hatalı giriş yaptınız. Lütfen Listedeki numaralardan seçiniz: ");
                continue;
            }

            Student student;
            try {
                student = studentService.getStudent(studentId);
            } catch (Exception e) {
                System.out.print("Lütfen listede var olan bir sayı giriniz: ");
                continue;
            }
            System.out.print("Seçilen talebe: " + student.getName() + " " + student.getSurname());
//            String newName = studentService.getStudent(studentId).setName();
            studentService.updateStudent(studentId, student.getName(), student.getSurname());
            System.out.println("Talebe Güncellenmiştir. Ana sayfaya yönlendiliriliyorsunuz.");
            break;
        }
    }

    /**
     * Talebe listesinden seçilen ID ye göre talebe siler.
     */
    public void deleteStudent() {
        printStudents();
        System.out.print("Silmek istediğiniz talebenin numarasını giriniz: ");
        while (true) {
            String choice = input.nextLine();
            int studentId;
            try {
                studentId = Integer.parseInt(choice);
            } catch (Exception e) {
                System.out.print("Hatalı giriş yaptınız. Lütfen Listedeki numaralardan seçiniz: ");
                continue;
            }

            try {
                studentService.deleteStudent(studentId);
            } catch (Exception e) {
                System.out.print("Lütfen listede var olan bir sayı giriniz: ");
                continue;
            }

            // todo attendanceService.getStudentAttendances().removeIf(studentAttendances -> studentAttendances.getStudent().getId()==studentId);
            System.out.println("Seçilen talebe silinmiştir.");
            break;
        }
    }
}
