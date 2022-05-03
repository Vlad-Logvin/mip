package by.logvin.mip.service;

import by.logvin.mip.model.entity.Drug;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface DrugService {
    Drug findById(long id);

    Page<Drug> findPaginated(Pageable pageable);

    Page<Drug> findByName(String name, Pageable pageable);

    Drug save(Drug drug);

    Drug delete(long id);

    Drug update(long id, Drug drug);
}
