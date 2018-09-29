package request;

import enumConstant.ServerRequest;

import java.io.Serializable;

public class ScoreAddRequest implements Serializable {
    private String quizID;
    private String studentID;
    private double score;

    public ScoreAddRequest(String quizID, double score) {
        this.quizID = quizID;
        this.score = score;
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return String.valueOf(ServerRequest.SCORE_ADD_REQUEST);
    }
}
