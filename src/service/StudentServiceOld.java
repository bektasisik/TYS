package service;

import domain.*;

import java.util.*;

public class StudentServiceOld {
    private final Scanner input = new Scanner(System.in);
    private int sequence = 1;
    public List<Student> students = new ArrayList<>();

    public void addStudent(String name, String surname) {
        Student student = new Student(sequence++, name, surname);
        students.add(student);
    }

    /**
     * Kullanıcıdan AD-SOYAD bilgilerini alır.
     * Aldığı bilgiler ile student class'ını oluşturur.
     * O class'ı bir obje haline getirir ve listeye ekler.
     */
    public void addStudent() {
        while (true) {
            String addAgain;
            String name, surname;
            while (true) {
                name = getNameInput("adı", 20);
                surname = getNameInput("soyadı", 19);

                if (name.length() < 3) {
                    System.out.println("İsim uzunluğu min 3 karakter olmalıdır.");
                    continue;
                }
                if (surname.length() < 2) {
                    System.out.println("Soyisim uzunluğu min 2 karakter olmalıdır.");
                    continue;
                }
                if (name.length() + surname.length() < 5) {
                    System.out.println("Yanlış AD-SOYAD girdiniz.");
                    continue;
                }
                break;
            }
            Student student = new Student(sequence++, name, surname);
            students.add(student);
            System.out.println("\nTalebe No: " + student.getId()
                    + "\nTalebe Ad: " + student.getName()
                    + "\nTalebe Soyadı: " + student.getSurname());
            System.out.println("Talebe Eklenmiştir.\n");
            System.out.print("Talebe Eklenmeye devam edilsin mi?(e/h):");
            addAgain = input.nextLine();
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
        if (students.size() == 0) {
            System.out.println("\nTalebe listeniz boş. Ana sayfaya yönlendiriliyorsunuz.");
        } else {
            String leftAlignFormat = "| %-8s | %-20s | %-19s | %-12s |%n";
            System.out.println();
            System.out.format("+-----------+----------------------+---------------------+--------------------+%n");
            System.out.format("| Talebe NO |          AD          |        SOYAD        | TOPLAM DEVAMSIZLIK |%n");
            System.out.format("+-----------+----------------------+---------------------+--------------------+%n");
            for (Student student : students) {
                System.out.format(leftAlignFormat,
                        "\t  " + student.getId(),
                        student.getName(),
                        student.getSurname(),
                        "\t\t\t" + student.getAbsent());
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
            String choiceStudentId = input.nextLine();
            int studentId;
            try {
                studentId = Integer.parseInt(choiceStudentId);
            } catch (Exception e) {
                System.out.print("Hatalı giriş yaptınız. Lütfen Listedeki numaralardan seçiniz: ");
                continue;
            }
            Optional<Student> studentOptional = students.stream().filter(student -> student.getId() == studentId).findFirst();
            if (studentOptional.isEmpty()) {
                System.out.print("Lütfen listede var olan bir sayı giriniz: ");
                continue;
            }
            Student student = studentOptional.get();
            System.out.print("Seçilen talebe: " + student.getName() + " " + student.getSurname());
            student.setName(getNameInput("adı", 20));
            student.setSurname(getNameInput("soyadı", 19));
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
            if (students.stream().anyMatch(students -> students.getId() == studentId)) {
                students.removeIf(student -> student.getId() == studentId);
                // todo attendanceService.getStudentAttendances().removeIf(studentAttendances -> studentAttendances.getStudent().getId()==studentId);
                System.out.println("Seçilen talebe silinmiştir.");
                break;
            } else System.out.print("Lütfen listede var olan bir sayı giriniz: ");
        }
    }

    /**
     * Kullanıcıya isim ve soyisim girmesini sağlar.
     */
    private String getNameInput(String message, int maxLength) {
        String getInput;
        while (true) {
            System.out.format("\nTalebe %s giriniz: ", message);
            getInput = input.nextLine();
            if (!getInput.matches("[a-zA-Z ğüşöçıİĞÜŞÖÇ]+\\S\\D\\Z")) {
                System.out.println("\nLütfen sadece harf kullanın.");
                continue;
            }
            if (getInput.length() > maxLength) {
                System.out.format("\n%s uzunluğu max %s olmalıdır.", message, maxLength);
                continue;
            }
            break;
        }
        return getInput;
    }
}