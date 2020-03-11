package hu.flibielt.onbitlor.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.flibielt.onbitlor.entity.Player;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {
    private Long id;

    private String firstName;

    @Getter
    private String lastName;

    private String username;

    @Getter
    private Integer bit;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String firstName, String lastName, String username, Integer bit, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.bit = bit;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(Player player) {
        List<GrantedAuthority> authorities = player.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UserPrincipal(
                player.getId(),
                player.getFirstName(),
                player.getLastName(),
                player.getUsername(),
                player.getBit(),
                player.getEmail(),
                player.getPassword(),
                authorities
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return firstName;
    }

    public String getEmail() {
        return email;
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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}