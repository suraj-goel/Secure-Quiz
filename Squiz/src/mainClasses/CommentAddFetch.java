package mainClasses;

import enumConstant.Status;
import request.CommentAddRequest;
import request.CommentFetchRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentAddFetch {
    public String add(CommentAddRequest commentAddRequest){
        String query="Insert into Comments(UserID, ExamID, Comment, CommentID, PosterName) values(?,?,?,?,?);";
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,commentAddRequest.getComment().getPosterID());
            preparedStatement.setString(2,commentAddRequest.getComment().getQuizID());
            preparedStatement.setString(3,commentAddRequest.getComment().getComment());
            preparedStatement.setString(4,commentAddRequest.getComment().getCommentID());
            preparedStatement.setString(5,commentAddRequest.getComment().getPosterName());
            preparedStatement.executeUpdate();
            return String.valueOf(Status.SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.valueOf(Status.FAILED);
    }
    public List<Comment> fetch(CommentFetchRequest commentFetchRequest){
        String query="SELECT * from Comments where ExamID=? order by commentTime desc ;";
        List<Comment> commentList=new ArrayList<>();
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,commentFetchRequest.getQuizID());
            Comment comment;
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                comment=new Comment(resultSet.getString(2),resultSet.getString(4),resultSet.getString(1));
                comment.setComment(resultSet.getString(3));
                comment.setPosterName(resultSet.getString(6));
                commentList.add(comment);
            }
            return commentList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentList;
    }
}
