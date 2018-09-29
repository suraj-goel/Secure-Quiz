package request;

import enumConstant.ServerRequest;

import java.io.Serializable;

public class ScoreFetchRequest implements Serializable {
    private String quizID;
    private String studentID;

    public ScoreFetchRequest(String quizID, String studentID) {
        this.quizID = quizID;
        this.studentID = studentID;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    @Override
    public String toString() {
        return String.valueOf(ServerRequest.SCORE_FETCH_REQUEST);
    }
}
