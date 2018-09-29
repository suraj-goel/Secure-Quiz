package request;

import enumConstant.ServerRequest;

import java.io.Serializable;

public class QuizByStudentRequest implements Serializable {
    private String studentID;

    public QuizByStudentRequest(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    @Override
    public String toString() {
        return String.valueOf(ServerRequest.QUIZ_BY_STUDENT_REQUEST);
    }
}
