package mk.ukim.finki.pidp.distsys.model;

import javax.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.management.relation.Role;
import java.util.Collection;
import java.util.HashSet;


@Data
@Entity
public class Client implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String gender;
    private Integer age;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private Role role;


    public Client() {

    }

    public Client(String username, String encode, String gender, int age, Role role) {
        this.username = username;
        this.password = encode;
        this.gender = gender;
        this.age = age;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
        Collection<GrantedAuthority> collection = new HashSet<>(1);
        collection.add(authority);
        return collection;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;

    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
