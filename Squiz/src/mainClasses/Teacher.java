package mainClasses;

import java.io.Serializable;
import java.util.List;

/**
 * Class for users that
 * are Teachers of a certain classroom
 */
public class Teacher extends User implements Serializable {

    private String teacherID;//unique ID for a teacher
    private String teacherLoginStatus;

    /**
     * Constructor for setting user properties and teacher ID
     * @param userID String containing User ID
     * @param teacherID String containing Teacher ID
     */
    public Teacher(String userID,String teacherID) {
        this.setUserID(userID);
        this.teacherID = teacherID;
    }

    /**
     * Method for getting ID of a Teacher
     * @return String containing ID of teacher
     */
    public String getTeacherID() {
        return teacherID;
    }

    /**
     * Method for setting ID of a Teacher
     * @param teacherID String containing ID of teacher
     */
    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherLoginStatus() {
        return teacherLoginStatus;
    }

    public void setTeacherLoginStatus(String teacherLoginStatus) {
        this.teacherLoginStatus = teacherLoginStatus;
    }
}
