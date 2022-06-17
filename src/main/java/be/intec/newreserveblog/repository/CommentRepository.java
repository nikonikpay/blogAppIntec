package be.intec.newreserveblog.repository;

import be.intec.newreserveblog.entity.BlogPost;
import be.intec.newreserveblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {


    List<Comment> findAllByBlogPost(BlogPost blogPost);


}