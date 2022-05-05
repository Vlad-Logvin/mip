package by.logvin.mip.service.impl;

import by.logvin.mip.model.entity.Role;
import by.logvin.mip.persistence.RoleRepository;
import by.logvin.mip.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;


    @Override
    public Role findPharmacyOwner() {
        Optional<Role> role = roleRepository.findByRole("ROLE_PHARMACY_OWNER");
        if (role.isEmpty()) {
            return roleRepository.save(new Role("ROLE_PHARMACY_OWNER"));
        } else {
            return role.get();
        }
    }

    @Override
    public Role findPharmacist() {
        return roleRepository.findByRole("ROLE_PHARMACIST").orElse(roleRepository.save(new Role("ROLE_PHARMACIST")));
    }
}
