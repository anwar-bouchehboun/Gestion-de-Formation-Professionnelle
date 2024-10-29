package com.formation.fomation.api.services;

import com.formation.fomation.api.models.dto.ClassDto;
import com.formation.fomation.api.models.entity.Classe;
import com.formation.fomation.api.repositories.ClasseRepository;
import com.formation.fomation.api.services.interfaces.ClasseInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ClassServices implements ClasseInterface {
    private final ClasseRepository classeRepository;

    @Override
    public Classe createClasse(Classe classe) {

       return classeRepository.save(classe);

    }

    @Override
    public Classe updateClasse(Classe classe) {
       if(classeRepository.existsById(classe.getId())){
        return  classeRepository.save(classe);
       }
        return null;
    }

    @Override
    public void deleteClasse(Long id) {
        if(classeRepository.existsById(id)){
          classeRepository.deleteById(id);
        }

    }


    public List<ClassDto> getAllClasse() {
        List<ClassDto> classDtos = ClassDto.getAll(classeRepository.findAll());

       return classDtos ;

    }

    public Page<ClassDto> getAllClassesWithPagination(Pageable pageable) {
        Page<Classe> classePage = classeRepository.findAllWithPagination(pageable);
        return ClassDto.getAllWithPagination(classePage);
    }

    @Override
    public Optional<Classe> getById(Long id) {
        return classeRepository.findById(id);
    }

    public List<ClassDto> searchByNumSalle(String numSalle) {
        List<Classe> classes = classeRepository.findByNumSalleContaining(numSalle);
        return ClassDto.getAll(classes);
    }
}
