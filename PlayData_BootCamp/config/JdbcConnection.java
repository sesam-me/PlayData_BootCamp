package config;
//      Jdbc 사용해서 데이터베이스랑 연결
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    private final String url = "jdbc:mysql://localhost:3306/test" + "?serverTimezone=Asia/Seoul&characterEncoding=UTF-8";
    private final String root = "root";
    private final String password = "1q2w3e4r!!";


    public Connection getJdbc() {
        Connection conn;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, root, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }
}
