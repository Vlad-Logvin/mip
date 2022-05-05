package by.logvin.mip.service;

import by.logvin.mip.model.entity.Report;

public interface ReportService {
    Report findById(Long id);

    Report save(Report report);

    void delete(Long id);
}
