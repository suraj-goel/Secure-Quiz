package mainClasses;

import request.QuizListFetchTeacherRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizScoreListFetch {
    public List<Quiz> fetch(QuizListFetchTeacherRequest quizScoreFetchTeacherRequest){
        List<Quiz> quizList=new ArrayList<>();
        String query="Select ExamID from exam where TeacherID=?;";
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,quizScoreFetchTeacherRequest.getTeacherID());
            ResultSet resultSet=preparedStatement.executeQuery();
            Quiz quiz;
            while (resultSet.next()){
                quiz=new QuizFetch().fetch(resultSet.getString(1));
                quizList.add(quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizList;
    }
}
