package by.logvin.mip.service;

import by.logvin.mip.model.entity.Receipt;

import java.time.LocalDate;
import java.util.Set;

public interface ReceiptService {
    Receipt findById(Long id);

    Set<Receipt> findAllWithinPeriod(LocalDate startDate, LocalDate endDate);

    Receipt save(Receipt receipt);

    void delete(Long id);
}
