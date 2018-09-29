package request;

import enumConstant.ServerRequest;

import java.io.Serializable;

public class SubjectAddRequest implements Serializable {

    private String subjectName;
    private String subjectID;

    public SubjectAddRequest(String subjectName, String teacherID, String subjectID) {
        this.subjectName = subjectName;
        this.subjectID = subjectID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return String.valueOf(ServerRequest.SUBJECT_ADD_REQUEST);
    }
}