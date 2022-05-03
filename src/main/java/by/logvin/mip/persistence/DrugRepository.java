package by.logvin.mip.persistence;

import by.logvin.mip.model.entity.Drug;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {
    Page<Drug> findAllByNameLike(String name, Pageable pageable);
}
