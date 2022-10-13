import service.AttendanceServiceOld;
import service.StudentServiceOld;
import ui.MainMenu;

public class Main {
    public static void main(String[] args) {

        //Services
        StudentServiceOld studentServiceOld = new StudentServiceOld();
        AttendanceServiceOld attendanceServiceOld = new AttendanceServiceOld(studentServiceOld.students);
        MainMenu mainMenu = new MainMenu(studentServiceOld, attendanceServiceOld);
        mainMenu.printMainMenu();

        // Listeye proje başlatıldığı zaman default 10 adet talebe ekler.
        // Bir sonraki talebe 11. sıradan başlar.
        studentServiceOld.addStudent("Veli", "Çam");
        studentServiceOld.addStudent("Abdurrahman", "Kutlu");
        studentServiceOld.addStudent("Emre", "Yavuz");
        studentServiceOld.addStudent("Kaan", "Koca");
        studentServiceOld.addStudent("Enes Bahadır", "Yıldırım");
//        studentService.addStudent("Enver", "Yıldırım");
//        studentService.addStudent("Yasin", "Büzgülü");
//        studentService.addStudent("Bektaş", "Işık");
//        studentService.addStudent("Mehmet Ercan", "Akcan");
//        studentService.addStudent("Haruncan", "Yıldırım");

    }
}