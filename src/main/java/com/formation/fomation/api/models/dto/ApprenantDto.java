package com.formation.fomation.api.models.dto;

import com.formation.fomation.api.models.entity.Apprenant;
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
public class ApprenantDto {
    private String nom;
    private String prenom;
    private String email;
    private String niveau;
    private String nomClasse;
    private String nomformation;

    public static ApprenantDto findById(Apprenant apprenant){
        if (apprenant == null) {
            return null;
        }
        return ApprenantDto.builder()
                .nom(apprenant.getNom())
                .prenom(apprenant.getPrenom())
                .email(apprenant.getEmail())
                .niveau(apprenant.getNiveau())
                .nomformation(apprenant.getFormation()!=null ?apprenant.getFormation().getTitre():null)
                .nomClasse(apprenant.getClasse() != null ? apprenant.getClasse().getNom() : null)
                .build();
    }
    public static List<ApprenantDto> getAll(List<Apprenant> apprenants) {
        if (apprenants == null || apprenants.isEmpty()) {
            return null;
        }

        return apprenants.stream()
                .map(ApprenantDto::findById)
                .collect(Collectors.toList());
    }
    public static Page<ApprenantDto> getAllWithPagination(Page<Apprenant> apprenantPage) {
        return apprenantPage.map(ApprenantDto::findById);
    }

}
