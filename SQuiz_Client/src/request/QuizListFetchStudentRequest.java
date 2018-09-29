package request;

import enumConstant.ServerRequest;

import java.io.Serializable;

public class QuizListFetchStudentRequest implements Serializable {
    private String studentID;
    private String subjectID;

    public QuizListFetchStudentRequest(String studentID, String subjectID) {
        this.studentID = studentID;
        this.subjectID = subjectID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    @Override
    public String toString() {
        return String.valueOf(ServerRequest.QUESTION_LIST_FETCH_STUDENT_REQUEST);
    }
}