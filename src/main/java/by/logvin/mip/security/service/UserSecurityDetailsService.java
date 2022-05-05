package by.logvin.mip.security.service;

import by.logvin.mip.model.entity.User;
import by.logvin.mip.model.security.UserSecurityDetails;
import by.logvin.mip.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserSecurityDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).map(this::entityToDetails).orElse(null);
    }

    private UserSecurityDetails entityToDetails(User userEntity) {
        return new UserSecurityDetails(userEntity);
    }
}
