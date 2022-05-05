package by.logvin.mip.service;

import by.logvin.mip.model.entity.Role;

public interface RoleService {
    Role findPharmacyOwner();

    Role findPharmacist();
}
