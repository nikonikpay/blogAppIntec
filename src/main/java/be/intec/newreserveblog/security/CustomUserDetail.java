package be.intec.newreserveblog.security;

import be.intec.newreserveblog.entity.Author;
import be.intec.newreserveblog.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CustomUserDetail implements UserDetails {

    private Author author;

    public CustomUserDetail(Author author) {
        this.author = author;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = author.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for(Role role:roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return author.getPassword();
    }

    @Override
    public String getUsername() {
        return author.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullName() {
        return author.getFirstName()+" "+author.getLastName();
    }
}
