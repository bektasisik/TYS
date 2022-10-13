import service.AttendanceService;
import service.AttendanceServiceOld;
import service.StudentService;
import service.StudentServiceOld;
import ui.AttendanceUi;
import ui.MainMenu;
import ui.StudentUi;

import java.lang.instrument.IllegalClassFormatException;

public class Main {
    public static void main(String[] args) throws IllegalClassFormatException {
        //Services
        MainMenu mainMenu = new MainMenu(
                new StudentUi(new StudentService()),
                new AttendanceUi(new AttendanceService()));
        mainMenu.printMainMenu();
    }
}