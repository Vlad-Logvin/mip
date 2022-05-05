package by.logvin.mip.web.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    private String errorMessage;
    private Integer errorCode;
}
