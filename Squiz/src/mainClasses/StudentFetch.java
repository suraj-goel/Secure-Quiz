package mainClasses;
//importing appropriate classes

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for fetch info
 * about a Student
 */
public class StudentFetch {

    private String studentID;//Student ID of the student whose info is to retrieved

    /**
     * Constructor for setting student ID of the student
     * @param studentID String containing student ID
     */
    public StudentFetch(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Method for getting Student ID
     * of the student whose info is to
     * be retrieved
     * @return String containing StudentID
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Method for setting Student ID
     * of the student whose info is to
     * be retrieved
     * @param studentID String containing StudentID
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Method for fetching Student info
     * by parameter initialised
     * @return Student object initialised by values corresponding to student ID
     */
    public Student fetch(){
        String query="Select * from students,users where studentID=? && students.UserID=users.UserID;";
        Student student=null;
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,this.studentID);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                student=new Student(resultSet.getString(2),this.studentID);
                student.setFirstName(resultSet.getString(4));
                student.setLastName(resultSet.getString(5));
                student.setAge(resultSet.getInt(6));
                student.setEmail(resultSet.getString(7));
                student.setJob(resultSet.getString(8));
            }
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
