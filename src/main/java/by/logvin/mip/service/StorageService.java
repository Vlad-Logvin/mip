package by.logvin.mip.service;

import by.logvin.mip.model.entity.Storage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StorageService {
    Storage save(Storage storage);

    Storage findById(Long id);

    Page<Storage> findAll(Pageable pageable);

    Page<Storage> findAllStoragesByPharmacy(Long pharmacyId, Pageable pageable);

    void delete(Long id);
}
