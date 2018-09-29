package mainClasses;

import java.io.Serializable;

/**
 * Class for users that
 * are Users of certain class
 */
public abstract class User implements Serializable {
    private String userID;//Unique ID of a User
    private String firstName;//First Name of a User
    private String lastName;//Last Name of a User
    private int age;//Age of a User
    private String email;//Email of a user
    private String job;//Role of the user

    /**
     * Method for getting ID of a User
     * @return String containing ID of user
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Method for setting ID of a User
     * @param userID String containing ID of user
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Method for getting First Name of the user
     * @return String Containing First Name of User
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Method for setting First Name of the user
     * @param firstName String Containing First Name of User
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Method for getting Last Name of the user
     * @return String Containing Last Name of User
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Method for setting Last Name of the user
     * @param lastName String Containing Last Name of User
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Method for getting Age of the user
     * @return Age of user in Integer
     */
    public int getAge() {
        return age;
    }

    /**
     * Method for setting Age of the user
     * @param age Age of User in Integer
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Method for getting Email of the user
     * @return String Containing email of User
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method for setting Email of the user
     * @param email String Containing email of User
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}