package by.logvin.mip.service.impl;

import by.logvin.mip.model.entity.Report;
import by.logvin.mip.persistence.ReportRepository;
import by.logvin.mip.service.ReceiptService;
import by.logvin.mip.service.ReportService;
import by.logvin.mip.service.exception.AutomatedDrugServiceException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class ReportServiceImpl implements ReportService {

    private ReportRepository reportRepository;
    private ReceiptService receiptService;

    @Override
    public Report findById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new AutomatedDrugServiceException("Report was not found", 400));
    }

    @Override
    public Page<Report> findAll(Pageable pageable) {
        return reportRepository.findAll(pageable);
    }

    @Override
    public Report save(Report report) {
        report.setReceipts(receiptService.findAllWithinPeriod(report.getStartDate(), report.getEndDate()));
        return reportRepository.save(report);
    }

    @Override
    public void delete(Long id) {
        reportRepository.deleteById(id);
    }
}
