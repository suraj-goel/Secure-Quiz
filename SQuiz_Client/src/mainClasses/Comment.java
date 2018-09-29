package mainClasses;

import java.io.Serializable;

public class Comment implements Serializable {
    private String quizID;
    private String commentID;
    private String comment;
    private String replyID;
    private String posterID;
    private String posterName;

    public Comment(String quizID, String commentID, String posterID) {
        this.quizID = quizID;
        this.commentID = commentID;
        this.posterID = posterID;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReplyID() {
        return replyID;
    }

    public void setReplyID(String replyID) {
        this.replyID = replyID;
    }

    public String getPosterID() {
        return posterID;
    }

    public void setPosterID(String posterID) {
        this.posterID = posterID;
    }

    @Override
    public String toString() {
        return this.comment;
    }
}