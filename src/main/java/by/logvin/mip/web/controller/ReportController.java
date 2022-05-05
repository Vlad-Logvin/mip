package by.logvin.mip.web.controller;

import by.logvin.mip.model.entity.Report;
import by.logvin.mip.model.request.ReportRequest;
import by.logvin.mip.service.ReportService;
import by.logvin.mip.service.exception.AutomatedDrugServiceException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
public class ReportController {

    private ReportService reportService;
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public Report findById(@PathVariable Long id) {
        return reportService.findById(id);
    }

    @GetMapping
    public Page<Report> findAll(Pageable pageable) {
        return reportService.findAll(pageable);
    }

    @PostMapping
    public Report save(@RequestBody @Valid ReportRequest reportRequest, BindingResult bindingResult) {
        validate(bindingResult);
        return reportService.save(requestToEntity(reportRequest));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        reportService.delete(id);
    }

    private Report requestToEntity(ReportRequest reportRequest) {
        return modelMapper.map(reportRequest, Report.class);
    }

    private void validate(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new AutomatedDrugServiceException("Not valid data: " + bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", ")), 400);
        }
    }
}

