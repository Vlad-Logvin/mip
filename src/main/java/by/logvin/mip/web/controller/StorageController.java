package by.logvin.mip.web.controller;

import by.logvin.mip.model.entity.Drug;
import by.logvin.mip.model.entity.Storage;
import by.logvin.mip.model.request.StorageRequest;
import by.logvin.mip.service.DrugService;
import by.logvin.mip.service.PharmacyService;
import by.logvin.mip.service.StorageService;
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
@RequestMapping("/storages")
@AllArgsConstructor
public class StorageController {

    private StorageService storageService;
    private PharmacyService pharmacyService;
    private DrugService drugService;
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public Storage findById(@PathVariable Long id) {
        return storageService.findById(id);
    }

    @GetMapping
    public Page<Storage> findAll(Pageable pageable) {
        return storageService.findAll(pageable);
    }

    @GetMapping("/{id}/drugs")
    public Page<Drug> findDrugsByStorage(@PathVariable Long id, Pageable pageable) {
        return drugService.findByStorage(id, pageable);
    }

    @PostMapping
    public Storage save(@RequestBody @Valid StorageRequest storageRequest, BindingResult bindingResult) {
        validate(bindingResult);
        Storage storage = requestToEntity(storageRequest);
        storage.setPharmacy(pharmacyService.findById(storageRequest.getPharmacyId()));
        return storageService.save(storage);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        storageService.delete(id);
    }

    private Storage requestToEntity(StorageRequest storageRequest) {
        return modelMapper.map(storageRequest, Storage.class);
    }

    private void validate(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new AutomatedDrugServiceException("Not valid data: " +
                    bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .collect(Collectors.joining(", ")), 400);
        }
    }
}
