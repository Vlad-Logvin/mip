package by.logvin.mip.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class EmployeeRequest {
    private Long id;

    @Email(message = "Not valid email")
    @NotNull(message = "Email can't be null")
    private String email;

    @NotNull(message = "Password can't be null")
    @Pattern(regexp = "\\w{8,32}", message = "Password must consist of latin letters | numbers | underscore from 8 to 32 symbols")
    private String password;

    @NotNull(message = "Name can't be null")
    @Pattern(regexp = "[a-zA-Z_]{1,64}", message = "Name must consist of latin letters | underscore from 8 to 64 symbols")
    private String name;

    @NotNull(message = "Surname can't be null")
    @Pattern(regexp = "[a-zA-Z_]{1,64}", message = "Surname must consist of latin letters | underscore from 8 to 64 symbols")
    private String surname;

    @Length(max = 512, message = "Max length of address is 512 symbols")
    private String address;
}
