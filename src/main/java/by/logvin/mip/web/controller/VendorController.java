package by.logvin.mip.web.controller;

import by.logvin.mip.model.entity.Vendor;
import by.logvin.mip.model.request.VendorRequest;
import by.logvin.mip.service.VendorService;
import by.logvin.mip.service.exception.AutomatedDrugServiceException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vendors")
@AllArgsConstructor
public class VendorController {

    private VendorService vendorService;
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public Vendor findById(@PathVariable Long id) {
        return vendorService.findById(id);
    }

    @PostMapping
    public Vendor save(@RequestBody @Valid VendorRequest vendorRequest, BindingResult bindingResult) {
        validate(bindingResult);
        return vendorService.save(requestToEntity(vendorRequest));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vendorService.delete(id);
    }

    private Vendor requestToEntity(VendorRequest vendorRequest) {
        return modelMapper.map(vendorRequest, Vendor.class);
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

