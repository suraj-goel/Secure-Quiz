package request;

import enumConstant.ServerRequest;

import java.io.Serializable;

public class RatingAddRequest implements Serializable {
    private String quizID;
    private int rating;

    public RatingAddRequest(String quizID, int rating) {
        this.quizID = quizID;
        this.rating = rating;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.valueOf(ServerRequest.RATING_ADD_REQUEST);
    }
}
