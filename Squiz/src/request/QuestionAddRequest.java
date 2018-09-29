package request;

import enumConstant.ServerRequest;
import questions.Question;

import java.io.Serializable;
import java.util.List;

public class QuestionAddRequest implements Serializable {
    List<Question> questionList;

    public QuestionAddRequest(List<Question> questionList) {
        this.questionList = questionList;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public String toString() {
        return String.valueOf(ServerRequest.QUESTION_ADD_REQUEST);
    }
}
