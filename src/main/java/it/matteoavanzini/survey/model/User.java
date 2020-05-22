package it.matteoavanzini.survey.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User implements UserDetails {
    @Id    
    @GeneratedValue(strategy = GenerationType.AUTO)    
    private long id;
    
    private String username;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Role> roles;
    
    public User() {
        roles = new ArrayList<>();
    }

    @Override    
    public Collection<? extends GrantedAuthority> getAuthorities() {        
        return roles
                .stream()
                .map(r->new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());    
    }

    public void addRole(String role) {
        roles.add(new Role(role));
    }

    @Override    
    public String getPassword() {        
        return password;    
    }

    @Override    
    public String getUsername() {        
        return username;    
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

    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            User other = (User) o;
            return other.id == this.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int) id;
    }
}