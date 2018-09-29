package mainClasses;

import enumConstant.Status;
import request.SubjectAddRequest;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SubjectAdd {

    public String add(SubjectAddRequest subjectAddRequest){
        String subjectID=subjectAddRequest.getSubjectID();
        String subjectName=subjectAddRequest.getSubjectName();
        String query="INSERT INTO subjects values (?,?)";
        System.out.println(String.valueOf(Status.SUCCESS));
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,subjectID);
            preparedStatement.setString(2,subjectName);
            preparedStatement.executeUpdate();
            return String.valueOf(Status.SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.valueOf(Status.FAILED);
    }
}
