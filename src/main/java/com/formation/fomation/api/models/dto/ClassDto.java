package com.formation.fomation.api.models.dto;

import com.formation.fomation.api.models.entity.Apprenant;
import com.formation.fomation.api.models.entity.Classe;
import com.formation.fomation.api.models.entity.Formateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassDto {

    private String nom;
    private String numSalle;



    public static ClassDto FindById(Classe classe) {
        if (classe == null) {
            return null;
        }

        return ClassDto.builder()
                .nom(classe.getNom())
                .numSalle(classe.getNumSalle())
                .build();
    }

    public static List<ClassDto> getAll(List<Classe> classes) {
        if (classes == null || classes.isEmpty()) {
            return null;
        }

        return classes.stream()
                .map(classe -> ClassDto.builder()
                        .nom(classe.getNom())
                        .numSalle(classe.getNumSalle())
                        .build())
                .collect(Collectors.toList());
    }
}
