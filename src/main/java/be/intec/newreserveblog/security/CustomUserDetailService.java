package be.intec.newreserveblog.security;

import be.intec.newreserveblog.entity.Author;
import be.intec.newreserveblog.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Author author = authorRepository.findByUserName(username);
        if (author == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetail(author);
    }
}
