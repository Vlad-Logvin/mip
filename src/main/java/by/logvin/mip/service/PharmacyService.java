package by.logvin.mip.service;

import by.logvin.mip.model.entity.Pharmacy;

public interface PharmacyService {
    Pharmacy findById(Long id);

    Pharmacy save(Pharmacy pharmacy);

    void delete(Long id);
}
