package mainClasses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuizFetch {

    public Quiz fetch(String examID){
        String query="Select * from exam,subjects where ExamID=? && exam.SubjectID=subjects.SubjectID && Access=0;";
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,examID);
            ResultSet resultSet=preparedStatement.executeQuery();
            Quiz quiz =null;
            while (resultSet.next()){
                quiz=new Quiz(examID);
                quiz.setExamName(resultSet.getString(4));
                quiz.setTestTime(resultSet.getInt(5));
                quiz.setTeacherID(resultSet.getString(3));
                quiz.setSubject(new SubjectFetch().fetch(resultSet.getString(6)));
            }
            return quiz;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
