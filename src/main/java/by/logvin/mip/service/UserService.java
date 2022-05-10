package by.logvin.mip.service;

import by.logvin.mip.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User findById(Long id);

    User findByEmail(String email);

    Page<User> findAllByPharmacy(Long pharmacyId, Pageable pageable);

    User savePharmacyOwner(User user);

    User savePharmacist(User user);

    void delete(Long id);
}
