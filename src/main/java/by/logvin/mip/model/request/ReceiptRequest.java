package by.logvin.mip.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReceiptRequest {
    @NotNull(message = "Solder can't be null")
    private Long solderId;

    @NotNull(message = "Grand total can't be null")
    private Double grandTotal;
}
