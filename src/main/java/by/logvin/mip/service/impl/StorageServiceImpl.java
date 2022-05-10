package by.logvin.mip.service.impl;

import by.logvin.mip.model.entity.Storage;
import by.logvin.mip.persistence.StorageRepository;
import by.logvin.mip.service.StorageService;
import by.logvin.mip.service.exception.AutomatedDrugServiceException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class StorageServiceImpl implements StorageService {
    private StorageRepository storageRepository;

    @Override
    public Storage save(Storage storage) {
        return storageRepository.save(storage);
    }

    @Override
    public Storage findById(Long id) {
        return storageRepository.findById(id)
                .orElseThrow(() -> new AutomatedDrugServiceException("Storage was not found", 400));
    }

    @Override
    public Page<Storage> findAll(Pageable pageable) {
        return storageRepository.findAll(pageable);
    }

    @Override
    public Page<Storage> findAllStoragesByPharmacy(Long pharmacyId, Pageable pageable) {
        return storageRepository.findAllByPharmacyId(pharmacyId, pageable);
    }

    @Override
    public void delete(Long id) {
        if (!findById(id).getDrugs().isEmpty()) {
            throw new AutomatedDrugServiceException("Storage isn't empty", 400);
        }
        storageRepository.deleteById(id);
    }
}
