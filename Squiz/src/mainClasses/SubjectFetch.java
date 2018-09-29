package mainClasses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectFetch {

    public Subject fetch(String subjectID){
        String query="SELECT * from subjects where SubjectID=?;";
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,subjectID);
            ResultSet resultSet=preparedStatement.executeQuery();
            Subject subject=new Subject(subjectID);
            while (resultSet.next()){
                String subjectName=resultSet.getString(2);
                subject.setSubjectName(subjectName);
            }
            return subject;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
