package mainClasses;

import request.QuizListFetchStudentRequest;
import request.QuizListFetchRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizListFetch {
    public List<Quiz> fetchByStudent(QuizListFetchStudentRequest quizListFetchStudentRequest){
        String query="SELECT * FROM exam LEFT JOIN (SELECT * from StudentExam where StudentExam.StudentID=?) temp ON temp.examID=exam.ExamID WHERE temp.StudentId IS NULL && SubjectID=?;";
        List<Quiz> quizList =new ArrayList<>();
        try {
            Quiz quiz;
            PreparedStatement preparedStatement = Main.connection.prepareStatement(query);
            preparedStatement.setString(2,quizListFetchStudentRequest.getSubjectID());
            preparedStatement.setString(1,quizListFetchStudentRequest.getStudentID());
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                quiz=new Quiz();
                quiz.setExamName(resultSet.getString(4));
                quiz.setExamID(resultSet.getString(1));
                quiz.setTeacherID(resultSet.getString(3));
                quiz.setSubject(new SubjectFetch().fetch(resultSet.getString(2)));
                quizList.add(quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizList;
    }

    private boolean fetch(QuizListFetchRequest quizListFetchRequest, String query, List<Quiz> quizList) {
        try {
            PreparedStatement preparedStatement= Main.connection.prepareStatement(query);
            preparedStatement.setString(2,"%"+ quizListFetchRequest.getTeacherID()+"%");
            preparedStatement.setString(1,"%"+ quizListFetchRequest.getSubjectID()+"%");
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Quiz quiz =new Quiz();
                quiz.setExamID(resultSet.getString(1));
                quiz.setSubject(new SubjectFetch().fetch(resultSet.getString(2)));
                quiz.setTeacherID(resultSet.getString(3));
                quiz.setExamName(resultSet.getString(4));
                quizList.add(quiz);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Quiz> fetchBySubject(QuizListFetchRequest quizListFetchRequest){
        String query="Select * from exam where SubjectID Like ? && TeacherID Like ? && exam.Access=1;";
        List<Quiz> quizList =new ArrayList<>();
        if (fetch(quizListFetchRequest, query, quizList)) return quizList;
        return quizList;
    }
}
