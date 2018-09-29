package request;

import enumConstant.ServerRequest;
import mainClasses.Comment;

import java.io.Serializable;

/**
 * Class for adding request to
 * the Server for adding comments
 * to a question
 */
public class CommentAddRequest implements Serializable {
    private Comment comment;//Comment Object for the coomet to be added

    /**
     * Constructor for initializing
     * comment object
     * @param comment
     */
    public CommentAddRequest(Comment comment) {
        this.comment = comment;
    }

    /**
     * Method
     * @return
     */
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
