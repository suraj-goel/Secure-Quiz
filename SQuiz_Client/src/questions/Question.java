package questions;

import java.util.List;

/***
 * Abstract Class for the
 * Question asked in exams
 */
public  class Question {

    private String questionName;//question
    private String questionType;//Type of question
    private String examID;//ID of the exam of the question
    private String questionID;//ID of the question
    private String sectionID;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int correctoption;
    private boolean isAnswered;

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public int getCorrectoption() {
        return correctoption;
    }

    public void setCorrectoption(int correctoption) {
        this.correctoption = correctoption;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    /**
     * Method to return Name
     * of the Question
     * @return String containing name of question
     */
    public String getQuestionName() {
        return questionName;
    }

    /**
     * Method for setting name
     * of the question
     * @param questionName String containing name of question
     */
    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    /**
     * Method for getting type of Question
     * @return String containing type of question
     */
    public String getQuestionType() {
        return questionType;
    }

    /**
     * Method for setting type of question
     * @param questionType String containing type of Question
     */
    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    /**
     * Method for getting Quiz ID
     * of Client
     * @return String containing exam ID
     */
    public String getExamID() {
        return examID;
    }

    /**
     * Method for setting exam ID
     * of client
     * @param examID String containing exam ID
     */
    public void setExamID(String examID) {
        this.examID = examID;
    }

    /**
     * Method for getting Id of question
     * @return String containing Id of question
     */
    public String getQuestionID() {
        return questionID;
    }

    @Override
    public String toString() {
        return this.questionName;
    }

    /**
     * Method for getting Id of question
     * @param questionID String containing Id of question
     */
    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

}