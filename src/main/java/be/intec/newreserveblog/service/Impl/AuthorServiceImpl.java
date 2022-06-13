package be.intec.newreserveblog.service.Impl;

import be.intec.newreserveblog.entity.Author;
import be.intec.newreserveblog.entity.Role;
import be.intec.newreserveblog.repository.AuthorRepository;
import be.intec.newreserveblog.repository.RoleRepository;
import be.intec.newreserveblog.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    private BCryptPasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository,
                             BCryptPasswordEncoder passwordEncoder,
                             RoleRepository roleRepository) {
        this.authorRepository = authorRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void createAuthor(Author author) {
        author.setPassword(passwordEncoder.encode(author.getPassword()));
        authorRepository.save(author);
    }

    @Override
    public void createAuthorWithDefaultRole(Author author) {

        author.setPassword(passwordEncoder.encode(author.getPassword()));
        Role roleUser = roleRepository.findByName("Author");
        author.addRole(roleUser);
        authorRepository.save(author);
    }


    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                               .get();
    }

    public Author getAuthorByUsername(String username) {
        return authorRepository.findByUserName(username);
    }

    @Override
    public void updateAuthor(Author author) {

    }

    @Override
    public void deleteAuthor(Author author) {
        authorRepository.delete(author);
    }

    @Override
    public void deleteAuthorById(Long authorId) {
        authorRepository.deleteById(authorId);
    }

    @Override
    public Author getAuthor(Author author) {
        return null;
    }

    @Override
    public Page<Author> getAllAuthors(int pageNumber, String sortField, String sortDirection) {
        Sort sort = Sort.by("firstName");
        sort = sortDirection.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 2, sort);
        return authorRepository.findAll(pageable);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
