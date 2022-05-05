package by.logvin.mip.persistence;

import by.logvin.mip.model.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    List<Receipt> findAllByDateOfPurchaseBetween(LocalDate startDate, LocalDate endDate);
}
