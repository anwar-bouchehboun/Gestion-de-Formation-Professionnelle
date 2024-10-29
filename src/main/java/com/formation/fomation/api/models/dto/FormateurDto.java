package com.formation.fomation.api.models.dto;

import com.formation.fomation.api.models.entity.Classe;
import com.formation.fomation.api.models.entity.Formateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormateurDto {
    private String nom;
    private String prenom;
    private String email;
    private String specialite;
    private String nomClasse;

    public static FormateurDto findById(Formateur formateur) {
        if (formateur == null) {
            return null;
        }

        return FormateurDto.builder()
                .nom(formateur.getNom())
                .prenom(formateur.getPrenom())
                .email(formateur.getEmail())
                .specialite(formateur.getSpecialite())
                .nomClasse(formateur.getClasse() != null ? formateur.getClasse().getNom() : null)
                .build();
    }

    public static List<FormateurDto> getAll(List<Formateur> formateurs) {
        if (formateurs == null || formateurs.isEmpty()) {
            return null;
        }

        return formateurs.stream()
                .map(FormateurDto::findById)
                .collect(Collectors.toList());
    }
    public static Page<FormateurDto> getAllWithPagination(Page<Formateur> formateursPage) {
        return formateursPage.map(FormateurDto::findById);
    }
}

