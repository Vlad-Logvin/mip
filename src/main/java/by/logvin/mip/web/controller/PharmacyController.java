package by.logvin.mip.web.controller;

import by.logvin.mip.model.entity.Pharmacy;
import by.logvin.mip.model.entity.Storage;
import by.logvin.mip.model.entity.User;
import by.logvin.mip.model.request.PharmacyRequest;
import by.logvin.mip.service.PharmacyService;
import by.logvin.mip.service.StorageService;
import by.logvin.mip.service.UserService;
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
@RequestMapping("/pharmacies")
@AllArgsConstructor
@CrossOrigin
public class PharmacyController {

    private PharmacyService pharmacyService;
    private UserService userService;
    private StorageService storageService;
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public Pharmacy findById(@PathVariable Long id) {
        return pharmacyService.findById(id);
    }

    @GetMapping("/{id}/users")
    public Page<User> findByPharmacy(@PathVariable Long id, Pageable pageable) {
        return userService.findAllByPharmacy(id, pageable);
    }

    @GetMapping("/{id}/storages")
    public Page<Storage> findStoragesByPharmacy(@PathVariable Long id, Pageable pageable) {
        return storageService.findAllStoragesByPharmacy(id, pageable);
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

