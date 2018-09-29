package request;

import enumConstant.ServerRequest;

import java.io.Serializable;

public class CommentFetchRequest implements Serializable {
    private String QuizID;

    public CommentFetchRequest(String quizID) {
        QuizID = quizID;
    }

    public String getQuizID() {
        return QuizID;
    }

    public void setQuizID(String quizID) {
        QuizID = quizID;
    }

    @Override
    public String toString() {
        return String.valueOf(ServerRequest.COMMENT_FETCH_REQUEST);
    }
}