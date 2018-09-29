package mainClasses;

import enumConstant.Status;
import request.QuizNameAddRequest;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuizNameAdd {

    public String add(QuizNameAddRequest quizNameAddRequest){
        String query="Insert into exam( ExamID,SubjectID,TeacherID, ExamName) values(?,?,?,?);";
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1, quizNameAddRequest.getExamID());
            preparedStatement.setString(2, quizNameAddRequest.getSubjectID());
            preparedStatement.setString(3, quizNameAddRequest.getTeacherID());
            preparedStatement.setString(4, quizNameAddRequest.getExamName());
            preparedStatement.executeUpdate();
            return String.valueOf(Status.SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.valueOf(Status.FAILED);
    }
}
