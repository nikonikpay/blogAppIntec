package be.intec.newreserveblog.repository;

import be.intec.newreserveblog.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByUserName(String username);


}