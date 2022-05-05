package by.logvin.mip.web.exception;

import by.logvin.mip.service.exception.AutomatedDrugServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(AutomatedDrugServiceException.class)
    public ExceptionResponse automatedDrugServiceException(AutomatedDrugServiceException exception) {
        return new ExceptionResponse(exception.getErrorMessage(), exception.getErrorCode());
    }
}
