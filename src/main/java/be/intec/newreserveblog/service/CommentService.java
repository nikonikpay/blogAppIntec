package be.intec.newreserveblog.service;

import be.intec.newreserveblog.entity.BlogPost;
import be.intec.newreserveblog.entity.Comment;
import org.springframework.data.domain.Page;

import java.util.List;
public interface CommentService {

    void saveComment(Comment comment) ;
    void updateComment(Comment comment);
    void deleteComment(Comment comment);
    void deleteCommentById(Long commentId);


    List<Comment> getAllComments();


}
