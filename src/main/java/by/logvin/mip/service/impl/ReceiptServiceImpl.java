package by.logvin.mip.service.impl;

import by.logvin.mip.model.entity.Receipt;
import by.logvin.mip.persistence.ReceiptRepository;
import by.logvin.mip.service.ReceiptService;
import by.logvin.mip.service.exception.AutomatedDrugServiceException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ReceiptServiceImpl implements ReceiptService {

    private ReceiptRepository receiptRepository;

    @Override
    public Receipt findById(Long id) {
        return receiptRepository.findById(id)
                .orElseThrow(() -> new AutomatedDrugServiceException("Receipt was not found", 400));
    }

    @Override
    public List<Receipt> findAllWithinPeriod(LocalDate startDate, LocalDate endDate) {
        return receiptRepository.findAllByDateOfPurchaseBetween(startDate, endDate);
    }

    @Override
    public Receipt save(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    @Override
    public void delete(Long id) {
        receiptRepository.deleteById(id);
    }
}
