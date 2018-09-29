package mainClasses;

import enumConstant.Status;
import request.SignUpRequest;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUp {

    private String signup(String query,Teacher user,String password){
        String query1="Insert into users values(?,?,?,?,?,?,?);";
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query1);
            preparedStatement.setString(1,user.getUserID());
            preparedStatement.setString(2,user.getFirstName());
            preparedStatement.setString(3,user.getLastName());
            preparedStatement.setInt(4,user.getAge());
            preparedStatement.setString(5,user.getEmail());
            preparedStatement.setString(6,user.getJob());
            preparedStatement.setString(7,password);
            preparedStatement.executeUpdate();
            preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,user.getTeacherID());
            preparedStatement.setString(2,user.getUserID());
            preparedStatement.executeUpdate();
            return String.valueOf(Status.SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.valueOf(Status.FAILED);
    }

    private String signup(String query,Student user,String password){
        String query1="Insert into users values(?,?,?,?,?,?,?);";
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query1);
            preparedStatement.setString(1,user.getUserID());
            preparedStatement.setString(2,user.getFirstName());
            preparedStatement.setString(3,user.getLastName());
            preparedStatement.setInt(4,user.getAge());
            preparedStatement.setString(5,user.getEmail());
            preparedStatement.setString(6,user.getJob());
            preparedStatement.setString(7,password);
            preparedStatement.executeUpdate();
            preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,user.getStudentID());
            preparedStatement.setString(2,user.getUserID());
            preparedStatement.executeUpdate();
            return String.valueOf(Status.SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.valueOf(Status.FAILED);
    }

    public String signup(SignUpRequest signUpRequest){
        String query2="Insert into students values(?,?);";
        if (signUpRequest.getStudent()==null){
            query2="Insert into teachers values(?,?);";
            return signup(query2,signUpRequest.getTeacher(),signUpRequest.getPassword());
        }
        return signup(query2,signUpRequest.getStudent(),signUpRequest.getPassword());
    }

}
