package mainClasses;

import java.io.Serializable;

/**
 * Class for users that
 * are Students of a certain classroom
 */
public class Student extends User implements Serializable {

    private String studentID;//Unique ID of a Student
    private String studentLoginStatus;

    /**
     * Constructor for setting user properties and student ID
     * @param userID String containing User ID
     * @param studentID String containing Student ID
     */
    public Student(String userID,String studentID) {
        super();
        this.setUserID(userID);
        this.studentID = studentID;
    }

    /**
     * Method for getting ID of a Student
     * @return String containing ID of student
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Method for setting ID of a Student
     * @param studentID String containing ID of student
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentLoginStatus() {
        return studentLoginStatus;
    }

    public void setStudentLoginStatus(String studentLoginStatus) {
        this.studentLoginStatus = studentLoginStatus;
    }

    @Override
    public String toString() {
        return this.getFirstName();
    }
}
