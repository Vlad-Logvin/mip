package by.logvin.mip.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class ReportRequest {

    @NotNull(message = "Report name can't be null")
    @Pattern(regexp = "\\w{2,255}", message = "Report name must consist of latin letters | numbers | underscore from 2 to 255 symbols")
    private String name;

    @NotNull(message = "Start date can't be null")
    private LocalDate startDate;

    @NotNull(message = "End date can't be null")
    private LocalDate endDate;
}
