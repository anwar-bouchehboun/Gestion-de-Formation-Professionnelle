package com.formation.fomation.api.services;

import com.formation.fomation.api.exceptions.ClasseNotFoundException;
import com.formation.fomation.api.models.dto.ClassDto;
import com.formation.fomation.api.models.dto.FormateurDto;
import com.formation.fomation.api.models.entity.Classe;
import com.formation.fomation.api.models.entity.Formateur;
import com.formation.fomation.api.repositories.FormateurRepository;
import com.formation.fomation.api.services.interfaces.FormateurInterfaces;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FormateurServices implements FormateurInterfaces {
    private final FormateurRepository formateurRepository;


    @Override
    public Formateur createFormateur(Formateur formateur) {

        return formateurRepository.save(formateur);
    }

    @Override
    public void updateFormateur(Formateur formateur) {
        if(formateurRepository.existsById(formateur.getId())){
          formateurRepository.save(formateur);
        }
    }

    @Override
    public void deleteFormateur(Long id) {
        if(formateurRepository.existsById(id)){
            formateurRepository.deleteById(id);
        }

    }

    @Override
    public List<FormateurDto> getAllFormateur() {
        List<FormateurDto> formateurDtos=FormateurDto.getAll(formateurRepository.findAll());
        return formateurDtos;
    }


    public Page<FormateurDto> getAllClassesWithPagination(Pageable pageable) {
        Page<Formateur> formateursPage = formateurRepository.findAllWithPagination(pageable);
        return FormateurDto.getAllWithPagination(formateursPage);
    }

    public List<FormateurDto> searchByNumSalle(String numSalle) {
        List<Formateur> formateurs = formateurRepository.findByClasseNomContaining(numSalle);
        return FormateurDto.getAll(formateurs);
    }
    @Override
    public Optional<Formateur> getById(Long id) {
        return formateurRepository.findById(id);
    }
}
