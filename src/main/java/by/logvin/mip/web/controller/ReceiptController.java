package by.logvin.mip.web.controller;

import by.logvin.mip.model.entity.Receipt;
import by.logvin.mip.model.request.ReceiptRequest;
import by.logvin.mip.service.ReceiptService;
import by.logvin.mip.service.exception.AutomatedDrugServiceException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/receipts")
@AllArgsConstructor
@CrossOrigin
public class ReceiptController {

    private ReceiptService receiptService;
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public Receipt findById(@PathVariable Long id) {
        return receiptService.findById(id);
    }

    @PostMapping
    public Receipt save(@RequestBody @Valid ReceiptRequest receiptRequest, BindingResult bindingResult) {
        validate(bindingResult);
        return receiptService.save(requestToEntity(receiptRequest));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        receiptService.delete(id);
    }

    private Receipt requestToEntity(ReceiptRequest receiptRequest) {
        return modelMapper.map(receiptRequest, Receipt.class);
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

