package mainClasses;

import request.SectionFetchRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SectionFetch {

    public List<Sections> sectionsListFetch(SectionFetchRequest sectionFetchRequest){
        String query="Select * from sections where examID=?;";
        List<Sections> sectionsList=new ArrayList<>();
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,sectionFetchRequest.getExamID());
            ResultSet resultSet=preparedStatement.executeQuery();
            Sections sections;
            while (resultSet.next()){
                sections=new Sections();
                sections.setSectionID(resultSet.getString(3));
                sections.setSectionName(resultSet.getString(1));
                sections.setSectionTime(resultSet.getInt(2));
                sectionsList.add(sections);
            }
            return sectionsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sectionsList;
    }

}
