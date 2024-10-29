package com.formation.fomation.api.services.interfaces;

import com.formation.fomation.api.models.dto.FormateurDto;
import com.formation.fomation.api.models.entity.Formateur;

import java.util.List;
import java.util.Optional;

public interface FormateurInterfaces {

    Formateur createFormateur(Formateur formateur);
    Formateur updateFormateur(Formateur formateur);
    void deleteFormateur(Long id);
    List<FormateurDto> getAllFormateur();
    Optional<Formateur> getById(Long id);
}
