package code.DTO;

import code.Domain.User.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Data @Builder @AllArgsConstructor
public class LoginDto implements UserDetails
{
    private Long no;
    private String id;
    private String pw;
    private String name;

    private final Set<GrantedAuthority> authorities;    // 부여된 인증들의 권한

    public LoginDto(UserEntity user, Collection<? extends GrantedAuthority> authorityList)
    {
        no = user.getUserNo();
        id = user.getUserId();
        pw = user.getUserPassword();
        name = user.getUserName();
        authorities = (Set)Collections.unmodifiableSet( new LinkedHashSet<>( authorityList ));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.pw;
    }

    @Override
    public String getUsername() {
        return this.id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true ;
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
}


