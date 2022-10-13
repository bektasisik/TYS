import service.AttendanceService;
import service.StudentService;
import ui.MainMenu;

public class Main {
    public static void main(String[] args) {

        //Services
        StudentService studentService = new StudentService();
        AttendanceService attendanceService = new AttendanceService(studentService.students);
        MainMenu mainMenu = new MainMenu(studentService, attendanceService);
        mainMenu.printMainMenu();

        // Listeye proje başlatıldığı zaman default 10 adet talebe ekler.
        // Bir sonraki talebe 11. sıradan başlar.
        studentService.addStudent("Veli", "Çam");
        studentService.addStudent("Abdurrahman", "Kutlu");
        studentService.addStudent("Emre", "Yavuz");
        studentService.addStudent("Kaan", "Koca");
        studentService.addStudent("Enes Bahadır", "Yıldırım");
//        studentService.addStudent("Enver", "Yıldırım");
//        studentService.addStudent("Yasin", "Büzgülü");
//        studentService.addStudent("Bektaş", "Işık");
//        studentService.addStudent("Mehmet Ercan", "Akcan");
//        studentService.addStudent("Haruncan", "Yıldırım");

    }
}