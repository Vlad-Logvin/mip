package by.logvin.mip.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class DrugRequest {

    @NotNull(message = "Name can't be null")
    @Pattern(regexp = "\\w{2,255}", message = "Name must consist of latin letters | numbers | underscore from 2 to 255 symbols")
    private String name;

    @Length(max = 1024, message = "Description must consist of 1024 or less symbols")
    private String description;

    @NotNull(message = "Price can't be null")
    private Double price;

    @NotNull(message = "Quantity can't be null")
    private Integer quantity;

    @NotNull(message = "Storage can't be null")
    private Long storageId;

    @NotNull(message = "Vendor can't be null")
    private Long vendorId;
}
