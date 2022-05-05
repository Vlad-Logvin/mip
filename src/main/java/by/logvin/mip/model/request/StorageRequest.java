package by.logvin.mip.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class StorageRequest {
    @Length(max = 512, message = "Pharmacy address must consist of 512 or less symbols")
    @NotNull(message = "Pharmacy address can't be null")
    private String pharmacyAddress;

    @NotNull(message = "Pharmacy can't be null")
    private Long pharmacyId;
}
