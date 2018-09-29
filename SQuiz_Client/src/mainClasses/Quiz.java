package mainClasses;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class Quiz implements Serializable {
    private List<Sections> sectionsList;
    private String examName;
    private String examID;
    private String teacherID;
    private int testTime;
    private Subject subject;

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    public Quiz(){}
    public Quiz(String examID) {
        this.examID = examID;
    }
    public Quiz(Quiz quiz){
        this.setExamID(quiz.getExamID());
        this.setTeacherID(quiz.getTeacherID());
        this.setExamName(quiz.getExamName());
        this.setTestTime(quiz.getTestTime());
        this.setSubject(quiz.getSubject());
        this.setSectionsList(quiz.getSectionsList());
    }

    public List<Sections> getSectionsList() {
        return sectionsList;
    }

    public void setSectionsList(List<Sections> sectionsList) {
        this.sectionsList = sectionsList;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamID() {
        return examID;
    }

    public void setExamID(String examID) {
        this.examID = examID;
    }

    public int getTestTime() {
        return testTime;
    }

    public void setTestTime(int testTime) {
        this.testTime = testTime;
    }

    @Override
    public String toString() {
        return this.getExamName();
    }
}