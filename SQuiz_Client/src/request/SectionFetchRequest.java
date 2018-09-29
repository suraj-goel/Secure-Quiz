package request;

import enumConstant.ServerRequest;

import java.io.Serializable;

public class SectionFetchRequest implements Serializable {

    private String ExamID;

    public SectionFetchRequest(String examID) {
        ExamID = examID;
    }

    public String getExamID() {
        return ExamID;
    }

    public void setExamID(String examID) {
        ExamID = examID;
    }

    @Override
    public String toString() {
        return String.valueOf(ServerRequest.SECTION_FETCH_REQUEST);
    }
}