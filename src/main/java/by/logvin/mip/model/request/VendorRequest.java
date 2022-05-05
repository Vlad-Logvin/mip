package by.logvin.mip.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class VendorRequest {
    @NotNull(message = "Name can't be null")
    @Pattern(regexp = "\\w{2,255}", message = "Vendor name must consist of latin letters | numbers | underscore from 2 to 255 symbols")
    private String name;

    @Length(max = 512, message = "Max length of address is 512 symbols")
    private String address;
}
