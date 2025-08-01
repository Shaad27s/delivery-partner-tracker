package shaadProject.example.delivery.partner.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shaadProject.example.delivery.partner.app.DTOs.LoginRequest;
import shaadProject.example.delivery.partner.app.DTOs.Register;
import shaadProject.example.delivery.partner.app.Module.User;
import shaadProject.example.delivery.partner.app.service.SignAuthService;

@RestController
@RequestMapping("/auth")
public class SignInController {

    @Autowired
    private SignAuthService signAuthService;

    @PostMapping("/register")
    public ResponseEntity<?>register(@RequestBody Register body){
        return  ResponseEntity.ok(signAuthService.register(body));
    }

    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody LoginRequest body){
        return ResponseEntity.ok(signAuthService.login(body));
    }
}
