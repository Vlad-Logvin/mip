package by.logvin.mip.model.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtModel {
    private Long id;
    private boolean isLoggedIn;
    private String token;
    private String username;

    public JwtModel(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
}
