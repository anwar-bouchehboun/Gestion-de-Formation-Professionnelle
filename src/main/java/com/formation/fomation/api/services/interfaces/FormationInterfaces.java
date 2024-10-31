package com.formation.fomation.api.services.interfaces;

import com.formation.fomation.api.models.dto.FormationDto;
import com.formation.fomation.api.models.entity.Formation;

import java.util.List;
import java.util.Optional;

public interface FormationInterfaces {

    Formation createFormateur(Formation formation);
    void updateFormateur(Formation formation);
    void deleteFormation(Long id);
    List<FormationDto> getAllFormation();
    Optional<Formation> getById(Long id);
    List<FormationDto> getDetails();
}
