package request;

import enumConstant.ServerRequest;

import java.io.Serializable;

public class QuizAddRequest implements Serializable {
    String quizID;

    public QuizAddRequest(String quizID) {
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
        return String.valueOf(ServerRequest.QUIZ_ADD_REQUEST);
    }
}
