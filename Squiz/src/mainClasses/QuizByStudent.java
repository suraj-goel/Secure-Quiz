package mainClasses;

import enumConstant.Status;
import request.QuizByStudentRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizByStudent {
    public List<Quiz> fetch(QuizByStudentRequest quizByStudentRequest){
        String query="SELECT ExamID FROM StudentExam WHERE StudentID=?;";
        List<Quiz> quizList=new ArrayList<>();
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,quizByStudentRequest.getStudentID());
            ResultSet resultSet=preparedStatement.executeQuery();
            Quiz quiz;
            while (resultSet.next()){
                quiz=new Quiz(new QuizFetch().fetch(resultSet.getString(1)));
                quizList.add(quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizList;
    }
}
