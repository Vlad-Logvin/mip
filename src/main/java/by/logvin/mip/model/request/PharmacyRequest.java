package by.logvin.mip.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class PharmacyRequest {

    @NotNull(message = "Pharmacy name can't be null")
    @Pattern(regexp = "\\w{2,255}", message = "Pharmacy name must consist of latin letters | numbers | underscore from 2 to 255 symbols")
    private String name;
}
