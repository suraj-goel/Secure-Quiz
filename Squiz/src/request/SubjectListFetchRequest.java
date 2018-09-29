package request;

import enumConstant.ServerRequest;

import java.io.Serializable;

public class SubjectListFetchRequest implements Serializable {
    private String search;

    public SubjectListFetchRequest() {
        this.search="";
    }

    public SubjectListFetchRequest(String search) {
        this.search = search;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public String toString() {
        return String.valueOf(ServerRequest.SUBJECT_LIST_FETCH_REQUEST);
    }
}