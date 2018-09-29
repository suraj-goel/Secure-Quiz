package mainClasses;
//importing appropriate classes
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class for connection of
 * Mysql Database with Java Code
 */
public class MysqlConnection {

    /**
     * Method for creating connection between
     * Mysql Database and Java Code
     * @return Connection Object , null if Connection not Established
     */
    public static Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/SQuiz",Main.user,Main.password);
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
