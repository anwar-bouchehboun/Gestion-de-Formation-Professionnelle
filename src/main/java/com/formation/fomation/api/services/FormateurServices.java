package com.formation.fomation.api.services;

import com.formation.fomation.api.models.dto.FormateurDto;
import com.formation.fomation.api.models.entity.Formateur;
import com.formation.fomation.api.repositories.FormateurRepository;
import com.formation.fomation.api.services.interfaces.FormateurInterfaces;
import lombok.RequiredArgsConstructor;
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
    public Formateur updateFormateur(Formateur formateur) {
        if(formateurRepository.existsById(formateur.getId())){
            formateurRepository.save(formateur);
        }
        return null;
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


    @Override
    public Optional<Formateur> getById(Long id) {
        return formateurRepository.findById(id);
    }
}
