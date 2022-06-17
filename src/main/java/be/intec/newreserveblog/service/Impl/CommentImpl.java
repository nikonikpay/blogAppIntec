package be.intec.newreserveblog.service.Impl;

import be.intec.newreserveblog.entity.BlogPost;
import be.intec.newreserveblog.entity.Comment;
import be.intec.newreserveblog.repository.CommentRepository;
import be.intec.newreserveblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentImpl implements CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }




    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void updateComment(Comment comment) {

    }

    @Override
    public void deleteComment(Comment comment) {

    }

    @Override
    public void deleteCommentById(Long commentId) {

    }

    @Override
    public List<Comment> getAllComments(BlogPost blogPost) {
        return commentRepository.findAllByBlogPost(blogPost);
    }
}
