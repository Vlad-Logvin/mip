package by.logvin.mip.service.impl;

import by.logvin.mip.model.entity.User;
import by.logvin.mip.persistence.UserRepository;
import by.logvin.mip.security.service.SecurityService;
import by.logvin.mip.service.PharmacyService;
import by.logvin.mip.service.RoleService;
import by.logvin.mip.service.UserService;
import by.logvin.mip.service.exception.AutomatedDrugServiceException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private SecurityService securityService;
    private PharmacyService pharmacyService;
    private RoleService roleService;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new AutomatedDrugServiceException("User was not found", 400));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new AutomatedDrugServiceException("User was not found", 400));
    }

    @Override
    public Page<User> findAllByPharmacy(Long pharmacyId, Pageable pageable) {
        return userRepository.findAllByPharmacy(pharmacyService.findById(pharmacyId), pageable);
    }

    @Override
    public User savePharmacyOwner(User user) {
        user.setRoles(Set.of(roleService.findPharmacyOwner()));
        user.setPassword(securityService.getHashedPassword(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User savePharmacist(User user) {
        user.setRoles(Set.of(roleService.findPharmacist()));
        user.setPassword(securityService.getHashedPassword(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
