package auth_api.com.auth_api.service;

import auth_api.com.auth_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    @Autowired
    private UserRepository repository;

    public UserDetails loadUserByUserName(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }
}
