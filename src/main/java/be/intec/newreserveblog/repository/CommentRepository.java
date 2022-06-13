package be.intec.newreserveblog.repository;

import be.intec.newreserveblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {



}