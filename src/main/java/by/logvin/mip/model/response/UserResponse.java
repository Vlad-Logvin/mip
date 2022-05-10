package by.logvin.mip.model.response;

import by.logvin.mip.model.entity.Pharmacy;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private String surname;
    private String address;
    @JsonIdentityReference(alwaysAsId = true)
    private Pharmacy pharmacy;
}
