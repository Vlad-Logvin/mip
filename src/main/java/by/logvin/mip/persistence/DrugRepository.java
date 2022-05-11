package by.logvin.mip.persistence;

import by.logvin.mip.model.entity.Drug;
import by.logvin.mip.model.entity.Storage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {
    Page<Drug> findAllByNameContainingIgnoreCaseAndStorage_Pharmacy_Id(String name, Long id, Pageable pageable);

    Page<Drug> findAllByStorage(Storage storage, Pageable pageable);

    Page<Drug> findAllByStorage_PharmacyId(Long pharmacyId, Pageable pageable);
}
