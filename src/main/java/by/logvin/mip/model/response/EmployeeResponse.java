package by.logvin.mip.model.response;

import lombok.Data;

@Data
public class EmployeeResponse {
    private Long id;
    private String email;
    private String name;
    private String surname;
    private String address;
}
