package by.logvin.mip.service;

import by.logvin.mip.model.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReportService {
    Report findById(Long id);

    Page<Report> findAll(Pageable pageable);

    Report save(Report report);

    void delete(Long id);
}
