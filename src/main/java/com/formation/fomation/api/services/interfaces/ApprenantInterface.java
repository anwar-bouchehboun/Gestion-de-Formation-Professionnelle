package com.formation.fomation.api.services.interfaces;

import com.formation.fomation.api.models.dto.ApprenantDto;
import com.formation.fomation.api.models.entity.Apprenant;

import java.util.List;
import java.util.Optional;

public interface ApprenantInterface {

    Apprenant createApprenant(Apprenant apprenant);
    void updateApprenant(Apprenant apprenant);
    void deleteApprenant(Long id);
    List<ApprenantDto> getAllApprenant();
    Optional<Apprenant> getById(Long id);
}
