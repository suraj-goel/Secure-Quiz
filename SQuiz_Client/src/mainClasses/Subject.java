package mainClasses;

import java.io.Serializable;
import java.util.List;

/**
 * Class for the various subjects
 * in each classroom
 */
public class Subject implements Serializable {

    private List<String> teacherID;//list of Unique ID of Teachers of the subject
    private List<String> examID;
    @Deprecated
    private int numberStudents;//Number of students studying in class
    @Deprecated
    private List<Student> studentList;//List of students in the classroom
    private String subjectName;//Name of subject
    private String subjectID;

    /**
     * Constructor for initializing teacher
     * to a subject
     * @param teacherID List of String containing teacher Id
     */
    public Subject(List<String> teacherID) {
        this.teacherID = teacherID;
    }

    /**
     * Constructor for initializing current object
     * using an existing subject object
     * @param subject Object of subject type
     */
    public Subject(Subject subject){
        this.teacherID=subject.getTeacherID();
        this.subjectName=subject.getSubjectName();
    }

    public Subject(String subjectID,String subjectName) {
        this.subjectID = subjectID;
        this.setSubjectName(subjectName);
    }

    public Subject(String subjectID) {
        this.subjectID = subjectID;
    }

    public List<String> getExamID() {
        return examID;
    }

    public void setExamID(List<String> examID) {
        this.examID = examID;
    }


    /**
     *
     * @return
     */
    public String getSubjectID() {
        return subjectID;
    }

    /**
     *
     * @param subjectID
     */
    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    /**
     * Method for returning list of string
     * containing teacher Id
     * @return List of String containing teacher Id
     */
    public List<String> getTeacherID() {
        return teacherID;
    }

    /**
     * Method for setting list of string
     * containing teacher Id
     * @param teacherID List of string containing teacher Id
     */
    public void setTeacherID(List<String> teacherID) {
        this.teacherID = teacherID;
    }

    /**
     * Method for getting the
     * name of subject
     * @return String containing name of subject
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Method for setting name of subject
     * @param subjectName String containing name of subject
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getNumberStudents() {
        return numberStudents;
    }

    public void setNumberStudents(int numberStudents) {
        this.numberStudents = numberStudents;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return this.subjectName;
    }
}