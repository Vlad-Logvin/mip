package by.logvin.mip.service;

import by.logvin.mip.model.entity.Vendor;

public interface VendorService {
    Vendor findById(Long id);

    Vendor save(Vendor vendor);

    void delete(Long id);
}
