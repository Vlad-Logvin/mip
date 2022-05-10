package by.logvin.mip.persistence;

import by.logvin.mip.model.entity.Storage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
    Page<Storage> findAllByPharmacyId(Long pharmacyId, Pageable pageable);
}
