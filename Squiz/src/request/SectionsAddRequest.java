package request;

import enumConstant.ServerRequest;

import java.io.Serializable;

public class SectionsAddRequest implements Serializable {

    private String sectionName;
    private int sectionTime;
    private String sectionID;
    private String examID;

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

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public String getExamID() {
        return examID;
    }

    public void setExamID(String examID) {
        this.examID = examID;
    }

    @Override
    public String toString() {
        return String.valueOf(ServerRequest.SECTION_ADD_REQUEST);
    }
}
