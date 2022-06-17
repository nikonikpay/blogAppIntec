package be.intec.newreserveblog.repository;

import be.intec.newreserveblog.entity.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikePost, Long> {
}