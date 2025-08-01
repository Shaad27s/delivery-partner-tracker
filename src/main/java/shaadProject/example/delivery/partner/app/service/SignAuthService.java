package shaadProject.example.delivery.partner.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import shaadProject.example.delivery.partner.app.DTOs.LoginRequest;
import shaadProject.example.delivery.partner.app.DTOs.Register;
import shaadProject.example.delivery.partner.app.JTWconfigruation.jtwService;
import shaadProject.example.delivery.partner.app.Module.Role;
import shaadProject.example.delivery.partner.app.Module.User;
import shaadProject.example.delivery.partner.app.Repository.UserRepo;
import shaadProject.example.delivery.partner.app.securityConfig.UserDtl;

@Service
public class SignAuthService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private jtwService jtwService;

    @Autowired
    private UserDtl userDtl;


    @Autowired
    private AuthenticationManager authenticationManager;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public String register(Register body) {
        if (userRepo.existsByEmail(body.getEmail())) {
            throw new RuntimeException("Email already exist");
        }
       User user = new User();
//        user.setEmail(body.getEmail());
//        user.setName(body.getName());
//        user.setPassword(body.getPassword());
//        user.setRole(Role.valueOf(body.getRole().toUpperCase()));

        user.setPassword(encoder.encode(body.getPassword()));
        user.setEmail(body.getEmail());
        user.setName(body.getName());
        user.setRole(Role.valueOf(body.getRole().toUpperCase()));




        userRepo.save(user);
        return "User registered";
    }

    public Object login(LoginRequest body) {

       Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(body.getEmail(),body.getPassword()));
       if(authentication.isAuthenticated()){
           UserDetails userDetails = userDtl.loadUserByUsername(body.getEmail());
           return jtwService.getToken(userDetails);
       }
       else
           return "invalid credentails";


//        User user = userRepo.findByEmail(body.getEmail())
//                .orElseThrow(()-> new RuntimeException("User not registered"));
//
//        if(!user.getPassword().equals(body.getPassword())){
//            throw new RuntimeException("Invalid password");
//
//        }
//        return  "login successful for " + user.getRole();
    }
}
