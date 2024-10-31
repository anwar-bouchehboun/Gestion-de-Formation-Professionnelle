package com.formation.fomation.api.services;

import com.formation.fomation.api.models.dto.ApprenantDto;
import com.formation.fomation.api.models.entity.Apprenant;
import com.formation.fomation.api.repositories.ApprenantRepository;
import com.formation.fomation.api.services.interfaces.ApprenantInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ApprenantServices implements ApprenantInterface {

    private final ApprenantRepository apprenantRepository;
    @Override
    public Apprenant createApprenant(Apprenant apprenant) {
      return   apprenantRepository.save(apprenant);
    }

    @Override
    public void updateApprenant(Apprenant apprenant) {
       if (apprenantRepository.existsById(apprenant.getId())){
            apprenantRepository.save(apprenant);
       }
    }

    @Override
    public void deleteApprenant(Long id) {
        if(apprenantRepository.existsById(id)){
            apprenantRepository.deleteById(id);
        }
    }

    @Override
    public List<ApprenantDto> getAllApprenant() {
     List<ApprenantDto> apprenantDtos=ApprenantDto.getAll(apprenantRepository.findAll());
     return apprenantDtos;
    }

    @Override
    public Optional<Apprenant> getById(Long id) {
        return apprenantRepository.findById(id);
    }

    public Page<ApprenantDto> getAllClassesWithPagination(Pageable pageable) {
        Page<Apprenant> apprenants = apprenantRepository.findAllWithPagination(pageable);
        return ApprenantDto.getAllWithPagination(apprenants);
    }

    public List<ApprenantDto> searchByNumSalle(String numSalle) {
        List<Apprenant> apprenants = apprenantRepository.findByClasseNomContaining(numSalle);
        return ApprenantDto.getAll(apprenants);
    }
}
