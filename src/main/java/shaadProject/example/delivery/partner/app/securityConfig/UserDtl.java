package shaadProject.example.delivery.partner.app.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shaadProject.example.delivery.partner.app.Module.User;
import shaadProject.example.delivery.partner.app.Repository.UserRepo;

import java.util.Optional;

@Service
public class UserDtl  implements   UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("🕵️ Searching in DB for email: " + email);
        Optional<User>  optionalUser =userRepo.findByEmail(email);

        User user = optionalUser.orElseThrow(() -> {
            System.out.println("User not found " + email);
         return new UsernameNotFoundException("User not found");
        });
        System.out.println("✅ Found user: " + user.getEmail());


        return new userConfig(user);
    }
}
