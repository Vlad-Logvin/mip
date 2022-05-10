package by.logvin.mip.security.service;

import by.logvin.mip.model.entity.User;
import by.logvin.mip.model.security.JwtModel;
import by.logvin.mip.model.security.UserSecurityDetails;
import by.logvin.mip.persistence.UserRepository;
import by.logvin.mip.security.jwt.JwtConfiguration;
import by.logvin.mip.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
public class SecurityService {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfiguration jwtConfiguration;
    private final SecretKey secretKey;

    @Autowired
    public SecurityService(UserRepository userRepository, UserDetailsService userDetailsService,
                           AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
                           JwtConfiguration jwtConfiguration, SecretKey secretKey) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtConfiguration = jwtConfiguration;
        this.secretKey = secretKey;
    }

    public boolean isOwnAccount(Long id, String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent() && user.get().getId().equals(id);
    }

    public JwtModel autologin(HttpServletRequest request, HttpServletResponse response, String username,
                              String password) {
        UserSecurityDetails userDetails = (UserSecurityDetails) userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            return new JwtModel(false);
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, password, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
        Authentication auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (auth.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = jwtConfiguration.getTokenPrefix() +
                    new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager, jwtConfiguration,
                            secretKey).getToken(auth);
            response.addHeader(HttpHeaders.AUTHORIZATION, token);
            return new JwtModel(userDetails.getId(), true, token, userDetails.getUsername(), userDetails.getRoles(),
                    userDetails.getPharmacyId());
        }
        return new JwtModel(false);
    }

    public String getHashedPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
