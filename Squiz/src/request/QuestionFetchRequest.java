package request;

import enumConstant.ServerRequest;

import java.io.Serializable;

public class QuestionFetchRequest implements Serializable {
    String quizID;
    String studentID;

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;

    }

    public QuestionFetchRequest(String quizID) {
        this.quizID = quizID;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }

    @Override
    public String toString() {
        return String.valueOf(ServerRequest.QUESTION_FETCH_REQUEST);
    }
}
