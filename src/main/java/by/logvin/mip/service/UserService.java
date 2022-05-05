package by.logvin.mip.service;

import by.logvin.mip.model.entity.User;

public interface UserService {
    User findById(Long id);

    User findByEmail(String email);

    User savePharmacyOwner(User user);

    User savePharmacist(User user);

    void delete(Long id);
}
