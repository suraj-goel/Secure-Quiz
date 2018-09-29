package request;

import enumConstant.ServerRequest;

import java.io.Serializable;

public class RankFetchRequest implements Serializable {
    private String quizID;

    public RankFetchRequest(String quizID) {
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
        return String.valueOf(ServerRequest.RANK_FETCH_REQUEST);
    }
}
