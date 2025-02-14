package auth_api.com.auth_api.controllers;

import auth_api.com.auth_api.domain.user.AuthDTO;
import auth_api.com.auth_api.domain.user.RegisterDTO;
import auth_api.com.auth_api.domain.user.User;
import auth_api.com.auth_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthDTO data) {
        var usernamePass = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePass);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Validated RegisterDTO register){
        if(this.repository.findByLogin(register.login()) != null) return ResponseEntity.badRequest().build();

        String encryptPassword = new BCryptPasswordEncoder().encode(register.password());

        User newUser = new User(register.login(),encryptPassword, register.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
