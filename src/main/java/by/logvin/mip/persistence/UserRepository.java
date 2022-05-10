package by.logvin.mip.persistence;

import by.logvin.mip.model.entity.Pharmacy;
import by.logvin.mip.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Page<User> findAllByPharmacy(Pharmacy pharmacy, Pageable pageable);
}
