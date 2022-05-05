package by.logvin.mip.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderRequest {
    @NotNull(message = "Drug can't be null")
    private Long drugId;

    @NotNull(message = "Receipt can't be null")
    private Long receiptId;

    @NotNull(message = "Quantity can't be null")
    private Integer quantity;
}
