package mainClasses;

import enumConstant.LoginStatus;
import request.StudentLoginRequest;
import request.TeacherLoginRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    public Teacher teacherLogin(TeacherLoginRequest teacherLoginRequest){
        String query="Select * from users where Email=?;";
        Teacher teacher=new Teacher(null,null);
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,teacherLoginRequest.getEmail());
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                if (resultSet.getString(7).equals(teacherLoginRequest.getPassword())){
                    String query1="Select * from teachers where UserID=?;";
                    System.out.println(resultSet.getString(1));

                    PreparedStatement preparedStatement1=Main.connection.prepareStatement(query1);
                    preparedStatement1.setString(1,resultSet.getString(1));
                    ResultSet resultSet1=preparedStatement1.executeQuery();
                    while (resultSet1.next()){
                        System.out.println(resultSet1.getString(1));
                        teacher=new TeacherFetch(resultSet1.getString(1)).fetch();
                        teacher.setTeacherLoginStatus(String.valueOf(LoginStatus.SUCCESS));
                        return teacher;
                    }
                }
                teacher.setTeacherLoginStatus(String.valueOf(LoginStatus.WRONG_CREDENTIALS));
                return teacher;
            }
            teacher.setTeacherLoginStatus(String.valueOf(LoginStatus.NO_SUCH_USER));
            return teacher;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        teacher.setTeacherLoginStatus(String.valueOf(LoginStatus.SERVER_SIDE_ERROR));
        return teacher;
    }
    public Student studentLogin(StudentLoginRequest studentLoginRequest){
        String query="Select * from users where Email=?;";
        Student student=new Student(null,null);
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,studentLoginRequest.getEmail());
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                if (resultSet.getString(7).equals(studentLoginRequest.getPassword())){
                    String query1="Select * from students where UserID=?;";
                    PreparedStatement preparedStatement1=Main.connection.prepareStatement(query1);
                    preparedStatement1.setString(1,resultSet.getString(1));
                    ResultSet resultSet1=preparedStatement1.executeQuery();
                    while (resultSet1.next()){
                        student=new StudentFetch(resultSet1.getString(1)).fetch();
                        student.setStudentLoginStatus((String.valueOf(LoginStatus.SUCCESS)));
                        return student;
                    }
                }
                student.setStudentLoginStatus(String.valueOf(LoginStatus.WRONG_CREDENTIALS));
                return student;
            }
            student.setStudentLoginStatus(String.valueOf(LoginStatus.NO_SUCH_USER));
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        student.setStudentLoginStatus(String.valueOf(LoginStatus.SERVER_SIDE_ERROR));
        return student;
    }
}
