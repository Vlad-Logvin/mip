package by.logvin.mip.model.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Email(message = "user.requirements.email")
    @NotNull(message = "user.requirements.email.not_null")
    private String email;

    @NotNull(message = "user.requirements.password.not_null")
    @Pattern(regexp = "[a-zA-Z_0-9]{1,40}",
            message = "user.requirements.password.pattern")
    private String password;
}
