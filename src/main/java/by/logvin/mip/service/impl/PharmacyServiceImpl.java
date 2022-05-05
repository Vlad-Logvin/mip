package by.logvin.mip.service.impl;

import by.logvin.mip.model.entity.Pharmacy;
import by.logvin.mip.persistence.PharmacyRepository;
import by.logvin.mip.service.PharmacyService;
import by.logvin.mip.service.exception.AutomatedDrugServiceException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class PharmacyServiceImpl implements PharmacyService {

    private PharmacyRepository pharmacyRepository;

    @Override
    public Pharmacy findById(Long id) {
        return pharmacyRepository.findById(id)
                .orElseThrow(() -> new AutomatedDrugServiceException("Pharmacy was not found", 400));
    }

    @Override
    public Pharmacy save(Pharmacy pharmacy) {
        return pharmacyRepository.save(pharmacy);
    }

    @Override
    public void delete(Long id) {
        pharmacyRepository.deleteById(id);
    }
}
