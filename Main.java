import config.JdbcConnection;
import controller.MovieController;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        MovieController.getController().mainMenu();
    }
}
