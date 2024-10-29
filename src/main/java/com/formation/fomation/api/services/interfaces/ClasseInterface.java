package com.formation.fomation.api.services.interfaces;

import com.formation.fomation.api.models.dto.ClassDto;
import com.formation.fomation.api.models.entity.Classe;

import java.util.List;
import java.util.Optional;

public interface ClasseInterface {
    Classe createClasse(Classe classe);
    Classe updateClasse(Classe classe);
    void deleteClasse(Long id);
    List<ClassDto> getAllClasse();
    Optional<Classe> getById(Long id);
}
