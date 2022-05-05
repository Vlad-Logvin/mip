package by.logvin.mip.web.controller;

import by.logvin.mip.model.entity.Pharmacy;
import by.logvin.mip.model.request.PharmacyRequest;
import by.logvin.mip.service.PharmacyService;
import by.logvin.mip.service.exception.AutomatedDrugServiceException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pharmacies")
@AllArgsConstructor
public class PharmacyController {

    private PharmacyService pharmacyService;
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public Pharmacy findById(@PathVariable Long id) {
        return pharmacyService.findById(id);
    }

    @PostMapping
    public Pharmacy save(@RequestBody @Valid PharmacyRequest pharmacyRequest, BindingResult bindingResult) {
        validate(bindingResult);
        return pharmacyService.save(requestToEntity(pharmacyRequest));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pharmacyService.delete(id);
    }

    private Pharmacy requestToEntity(PharmacyRequest pharmacyRequest) {
        return modelMapper.map(pharmacyRequest, Pharmacy.class);
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

