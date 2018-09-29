package request;

import enumConstant.ServerRequest;
import mainClasses.Comment;

import java.io.Serializable;

public class CommentAddRequest implements Serializable {
    private Comment comment;

    public CommentAddRequest(Comment comment) {
        this.comment = comment;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return String.valueOf(ServerRequest.COMMENT_ADD_REQUEST);
    }
}
