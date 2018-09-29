package mainClasses;

import enumConstant.Status;
import request.SectionsAddRequest;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SectionAdd {
    public String add(SectionsAddRequest sectionsAddRequest){
        String query="Insert Into sections values (?,?,?,?);";
        try {
            PreparedStatement preparedStatement = Main.connection.prepareStatement(query);
            preparedStatement.setString(1,sectionsAddRequest.getSectionName());
            preparedStatement.setInt(2,sectionsAddRequest.getSectionTime());
            preparedStatement.setString(3,sectionsAddRequest.getSectionID());
            preparedStatement.setString(4,sectionsAddRequest.getExamID());
            preparedStatement.executeUpdate();
            return String.valueOf(Status.SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.valueOf(Status.FAILED);
    }
}
