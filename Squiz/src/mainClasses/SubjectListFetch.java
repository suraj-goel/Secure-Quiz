package mainClasses;

import request.SubjectListFetchRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectListFetch {
    public List<Subject> fetch(SubjectListFetchRequest subjectListFetchRequest){
        String query="Select * from subjects where SubjectName LIKE ?;";
        List<Subject> subjectList=new ArrayList<>();
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,"%"+subjectListFetchRequest.getSearch()+"%");
            ResultSet resultSet=preparedStatement.executeQuery();
            Subject subject;
            while (resultSet.next()){
                subject=new Subject(resultSet.getString(1),resultSet.getString(2));
                subjectList.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectList;
    }
}
