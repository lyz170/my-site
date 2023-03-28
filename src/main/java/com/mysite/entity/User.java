package com.mysite.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@ToString
@Entity(name = "T_USER")
public class User implements UserDetails, Auditable, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 128)
    private String username;

    @Column(nullable = false, length = 128)
    private String password;

    @Column(length = 128)
    private String location;

    @Column(length = 128)
    private String company;

    @Column(length = 128)
    private String email;

    @Column(length = 32)
    private String phone;

    @Column(nullable = false)
    private Long createBy;

    @Column(nullable = false)
    private LocalDateTime createDatetime;

    @Column(nullable = false)
    private Long updateBy;

    @Column(nullable = false)
    private LocalDateTime updateDatetime;

    @ManyToMany(fetch = FetchType.EAGER) // can't lazy-load here
    @JoinTable(name = "T_USER_ROLE_MAPPING", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        if (!CollectionUtils.isEmpty(roles)) {
            roles.forEach(role -> {
                GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_" + role.getRoleName());
                auths.add(auth);
            });
        }
        return auths;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
