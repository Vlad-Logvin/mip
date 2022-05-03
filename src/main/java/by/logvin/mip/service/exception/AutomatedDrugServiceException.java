package by.logvin.mip.service.exception;

public class AutomatedDrugServiceException extends RuntimeException {
    private String errorMessage;
    private int errorCode;

    public AutomatedDrugServiceException(String errorMessage, int errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public AutomatedDrugServiceException(Throwable cause, String errorMessage, int errorCode) {
        super(cause);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
