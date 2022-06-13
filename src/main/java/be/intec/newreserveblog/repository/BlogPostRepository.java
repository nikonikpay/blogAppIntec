package be.intec.newreserveblog.repository;

import be.intec.newreserveblog.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {


}