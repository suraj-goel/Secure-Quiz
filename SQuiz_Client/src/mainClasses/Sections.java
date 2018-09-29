package mainClasses;

import questions.Question;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Sections implements Serializable {

    private List<Question> questionList ;
    private int sectionTime;
    private String sectionID;
    private String sectionName;

    public Sections() {
        this.questionList=new ArrayList<>();
    }

    public void addQuestion(Question question){
        questionList.add(question);
    }
    public List<Question> getQuestions() {
        return questionList;
    }

    public void setQuestions(List<Question> questionList) {
        this.questionList = questionList;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public int getSectionTime() {
        return sectionTime;
    }

    public void setSectionTime(int sectionTime) {
        this.sectionTime = sectionTime;
    }

    @Override
    public String toString() {
        return this.getSectionName();
    }
}
