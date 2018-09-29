package mainClasses;

import enumConstant.ServerRequest;
import enumConstant.Status;
import request.RatingAddRequest;
import request.RatingFetchRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingAddFetch {
    public double fetch(RatingFetchRequest ratingFetchRequest){
        String query="SELECT rating from exam where ExamID=?;";
        String rating="";
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,ratingFetchRequest.getQuizID());
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                rating=resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(rating);
        int i=(int)Double.parseDouble(""+rating.substring(0,rating.indexOf('/')));
        int j=Integer.parseInt(""+rating.substring(rating.indexOf('/')+1));
        return i/j;
    }
    public String add(RatingAddRequest ratingAddRequest){
        String query="UPDATE exam SET rating=? where ExamID=?;";
        String rating="";
        try {
            PreparedStatement preparedStatement1=Main.connection.prepareStatement(query);
            query="SELECT rating from exam where ExamID=?;";
            try {
                PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
                preparedStatement.setString(1,ratingAddRequest.getQuizID());
                ResultSet resultSet=preparedStatement.executeQuery();
                while (resultSet.next()){
                    rating=resultSet.getString(1);
                }
                System.out.println(rating);
                int i=(int) Double.parseDouble(""+rating.substring(0,rating.indexOf('/')));
                int j=(int)Double.parseDouble(""+rating.substring(rating.indexOf('/')+1));
                i+=ratingAddRequest.getRating();
                j++;
                rating=""+i+"/"+j;
                System.out.println(rating);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            preparedStatement1.setString(1,rating);
            preparedStatement1.setString(2,ratingAddRequest.getQuizID());
            preparedStatement1.executeUpdate();
            return String.valueOf(Status.SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.valueOf(Status.FAILED);
    }
}
