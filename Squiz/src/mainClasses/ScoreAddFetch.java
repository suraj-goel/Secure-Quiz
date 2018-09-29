package mainClasses;

import enumConstant.Status;
import request.RankFetchRequest;
import request.ScoreAddRequest;
import request.ScoreFetchRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreAddFetch {
    public String add(ScoreAddRequest scoreAddRequest){
        String query="UPDATE StudentExam set Score=? where ExamID=?;";
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(2,scoreAddRequest.getQuizID());
            preparedStatement.setDouble(1,scoreAddRequest.getScore());
            System.out.println(scoreAddRequest.getScore());
            preparedStatement.executeUpdate();
            return String.valueOf(Status.SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.valueOf(Status.FAILED);
    }
    public double fetch(ScoreFetchRequest scoreFetchRequest){
        String query="SELECT Score from StudentExam where StudentID=? && ExamID=?;";
        Double d=0.0;
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,scoreFetchRequest.getStudentID());
            preparedStatement.setString(2,scoreFetchRequest.getQuizID());
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                d=resultSet.getDouble(1);
            }
            return d;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }
    public List<Student> fetchByRank(RankFetchRequest rankFetchRequest){
        String query="SELECT * from StudentExam where  ExamID=? ORDER BY Score;";
        List<Student> studentList=new ArrayList<>();
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,rankFetchRequest.getQuizID());
            ResultSet resultSet=preparedStatement.executeQuery();
            Student student;
            while (resultSet.next()){
                student=new StudentFetch(resultSet.getString(1)).fetch();
                studentList.add(student);
            }
            return studentList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }
}
