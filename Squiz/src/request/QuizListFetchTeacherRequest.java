package request;

import enumConstant.ServerRequest;

import java.io.Serializable;

public class QuizListFetchTeacherRequest implements Serializable {
    private String teacherID;

    public QuizListFetchTeacherRequest(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    @Override
    public String toString() {
        return String.valueOf(ServerRequest.QUIZ_LIST_FETCH_TEACHER_REQUEST);
    }
}
