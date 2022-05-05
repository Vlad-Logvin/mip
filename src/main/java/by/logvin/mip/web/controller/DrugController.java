package by.logvin.mip.web.controller;

import by.logvin.mip.model.entity.Drug;
import by.logvin.mip.model.request.DrugRequest;
import by.logvin.mip.service.DrugService;
import by.logvin.mip.service.StorageService;
import by.logvin.mip.service.VendorService;
import by.logvin.mip.service.exception.AutomatedDrugServiceException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/drugs")
@AllArgsConstructor
@Validated
public class DrugController {

    private DrugService drugService;
    private StorageService storageService;
    private VendorService vendorService;
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public Drug findById(@PathVariable Long id) {
        return drugService.findById(id);
    }

    @GetMapping
    public Page<Drug> findPaginated(Pageable pageable) {
        return drugService.findPaginated(pageable);
    }

    @GetMapping("/search")
    public Page<Drug> findByName(@RequestParam(name = "name") String name, Pageable pageable) {
        return drugService.findByName(name, pageable);
    }

    @PostMapping
    public Drug save(@RequestBody @Valid DrugRequest drugRequest, BindingResult bindingResult) throws IOException {
        validate(bindingResult);
        Drug drug = requestToEntity(drugRequest);
        drug.setStorage(storageService.findById(drugRequest.getStorageId()));
        drug.setVendor(vendorService.findById(drugRequest.getVendorId()));
        drug.setImage(drugRequest.getImage().getBytes());
        return drugService.save(drug);
    }

    @PutMapping("/{id}")
    public Drug update(@PathVariable Long id, @RequestBody @Valid DrugRequest drugRequest,
                       BindingResult bindingResult) {
        validate(bindingResult);
        return drugService.update(id, requestToEntity(drugRequest));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        drugService.delete(id);
    }

    private void validate(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new AutomatedDrugServiceException("Not valid data: " + bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", ")), 400);
        }
    }

    private Drug requestToEntity(DrugRequest drugRequest) {
        return modelMapper.map(drugRequest, Drug.class);
    }
}
