package by.logvin.mip.service;

import by.logvin.mip.model.entity.Drug;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface DrugService {
    Drug findById(Long id);

    Page<Drug> findPaginated(Pageable pageable);

    Page<Drug> findByName(String name, Pageable pageable);

    Page<Drug> findByStorage(Long storageId, Pageable pageable);

    Drug save(Drug drug);

    void delete(Long id);

    Drug update(Long id, Drug drug);
}
