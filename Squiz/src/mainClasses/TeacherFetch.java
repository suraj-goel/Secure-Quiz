package mainClasses;
//importing appropriate classes
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for fetch info
 * about a Teacher
 */
public class TeacherFetch {

    private String teacherID;//Teacher ID of the teacher whose info is to retrieved

    /**
     * Constructor for setting teacher ID of the teacher
     * @param teacherID String containing teacher ID
     */
    public TeacherFetch(String teacherID) {
        this.teacherID = teacherID;
    }

    /**
     * Method for getting Teacher ID
     * of the teacher whose info is to
     * be retrieved
     * @return String containing TeacherID
     */
    public String getTeacherID() {
        return teacherID;
    }

    /**
     * Method for setting Teacher ID
     * of the teacher whose info is to
     * be retrieved
     * @param teacherID String containing TeacherID
     */
    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    /**
     * Method for fetching Teacher info
     * by parameter initialised
     * @return Teacher object initialised by values corresponding to teacher ID
     */
    public Teacher fetch(){
        String query="Select * from teachers,users where teacherID=? && teachers.UserID=users.UserID;";
        Teacher teacher=null;
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,this.teacherID);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                teacher=new Teacher(resultSet.getString(2),teacherID);
                teacher.setFirstName(resultSet.getString(4));
                teacher.setLastName(resultSet.getString(5));
                teacher.setAge(resultSet.getInt(6));
                teacher.setEmail(resultSet.getString(7));
                teacher.setJob(resultSet.getString(8));
            }
            return teacher;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
