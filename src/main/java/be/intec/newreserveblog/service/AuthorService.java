package be.intec.newreserveblog.service;

import be.intec.newreserveblog.entity.Author;
import be.intec.newreserveblog.entity.Role;
import org.springframework.data.domain.Page;

import java.util.List;
public interface AuthorService {

    void createAuthor(Author author) ;
    void updateAuthor(Author author);
    void deleteAuthor(Author author);
    public void deleteAuthorById(Long authorId);
    Author getAuthor(Author author);
    Author getAuthorById(Long Id);
    Author getAuthorByUsername(String username);
    Page<Author> getAllAuthors(int pageNumber,String sortField,String sortDirection);

    List<Role> getRoles();

    void createAuthorWithDefaultRole(Author author);
}
