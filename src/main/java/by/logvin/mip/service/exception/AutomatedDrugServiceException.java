package by.logvin.mip.service.exception;

import lombok.Data;

@Data
public class AutomatedDrugServiceException extends RuntimeException {
    private String errorMessage;
    private int errorCode;

    public AutomatedDrugServiceException(String errorMessage, int errorCode) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
