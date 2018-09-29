package request;

import enumConstant.ServerRequest;

import java.io.Serializable;

public class RatingFetchRequest implements Serializable {
    private String quizID;

    public RatingFetchRequest(String quizID) {
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
        return String.valueOf(ServerRequest.RATING_FETCH_REQUEST);
    }
}
