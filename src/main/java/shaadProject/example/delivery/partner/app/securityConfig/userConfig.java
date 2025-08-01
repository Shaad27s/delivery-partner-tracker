package shaadProject.example.delivery.partner.app.securityConfig;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import shaadProject.example.delivery.partner.app.Module.User;

import java.util.Collection;
import java.util.Collections;

public class userConfig  implements UserDetails {

    private User user;

    public  userConfig(User user){
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));

//        new SimpleGrantedAuthority("Role" + user.getRole().name()));
    }

    @Override
    public String getPassword() {
       return  user.getPassword();
    }

    @Override
    public String getUsername() {
       return  user.getEmail();}

    @Override
    public boolean isAccountNonExpired() {
        return  true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return  true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return  true;
    }

    @Override
    public boolean isEnabled() {
        return  true;
    }
}
