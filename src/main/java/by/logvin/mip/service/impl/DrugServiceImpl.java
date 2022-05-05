package by.logvin.mip.service.impl;

import by.logvin.mip.model.entity.Drug;
import by.logvin.mip.persistence.DrugRepository;
import by.logvin.mip.service.DrugService;
import by.logvin.mip.service.StorageService;
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
    private StorageService storageService;

    @Override
    public Drug findById(Long id) {
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
    public Page<Drug> findByStorage(Long storageId, Pageable pageable) {
        return drugRepository.findAllByStorage(storageService.findById(storageId), pageable);
    }

    @Override
    public Drug save(Drug drug) {
        return drugRepository.save(drug);
    }

    @Override
    public void delete(Long id) {
        drugRepository.deleteById(id);
    }

    @Override
    public Drug update(Long id, Drug drug) {
        Drug drugToUpdate = findById(id);
        drugToUpdate.setName(drug.getName());
        drugToUpdate.setPrice(drug.getPrice());
        drugToUpdate.setQuantity(drug.getQuantity());
        drugToUpdate.setDescription(drug.getDescription());
        drugToUpdate.setImage(drug.getImage());
        drugRepository.save(drugToUpdate);
        return drugToUpdate;
    }
}
