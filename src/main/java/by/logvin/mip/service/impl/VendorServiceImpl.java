package by.logvin.mip.service.impl;

import by.logvin.mip.model.entity.Vendor;
import by.logvin.mip.persistence.VendorRepository;
import by.logvin.mip.service.VendorService;
import by.logvin.mip.service.exception.AutomatedDrugServiceException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class VendorServiceImpl implements VendorService {

    private VendorRepository vendorRepository;

    @Override
    public Vendor findById(Long id) {
        return vendorRepository.findById(id).orElseThrow(() ->
                new AutomatedDrugServiceException("Vendor was not found", 400));
    }

    @Override
    public Vendor save(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public void delete(Long id) {
        vendorRepository.deleteById(id);
    }
}
