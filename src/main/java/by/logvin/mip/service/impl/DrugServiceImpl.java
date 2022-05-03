package by.logvin.mip.service.impl;

import by.logvin.mip.model.entity.Drug;
import by.logvin.mip.persistence.DrugRepository;
import by.logvin.mip.service.DrugService;
import by.logvin.mip.service.exception.AutomatedDrugServiceException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class DrugServiceImpl implements DrugService {

    private DrugRepository drugRepository;

    @Override
    public Drug findById(long id) {
        return drugRepository.findById(id)
                .orElseThrow(() -> new AutomatedDrugServiceException("Drug was not found", 400));
    }

    @Override
    public Page<Drug> findPaginated(Pageable pageable) {
        return drugRepository.findAll(pageable);
    }

    @Override
    public Page<Drug> findByName(String name, Pageable pageable) {
        return drugRepository.findAllByNameLike(name, pageable);
    }

    @Override
    public Drug save(Drug drug) {
        try {
            return drugRepository.save(drug);
        } catch (Exception e) {
            throw new AutomatedDrugServiceException("Drug was not saved", 400);
        }
    }

    @Override
    public Drug delete(long id) {
        Drug drugToDelete = findById(id);
        drugToDelete.setDeleted(true);
        drugToDelete = drugRepository.save(drugToDelete);
        return drugToDelete;
    }

    @Override
    public Drug update(long id, Drug drug) {
        Drug drugToUpdate = findById(id);
        drugToUpdate.setName(drug.getName());
        drugToUpdate.setPrice(drug.getPrice());
        drugToUpdate.setQuantity(drug.getQuantity());
        drugToUpdate.setDescription(drug.getDescription());
        return drugToUpdate;
    }
}
