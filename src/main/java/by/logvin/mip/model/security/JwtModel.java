package by.logvin.mip.model.security;

import by.logvin.mip.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtModel {
    private Long id;
    private boolean isLoggedIn;
    private String token;
    private String username;
    private Set<Role> roles;
    private Long pharmacyId;

    public JwtModel(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
}
