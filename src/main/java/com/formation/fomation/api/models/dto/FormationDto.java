package com.formation.fomation.api.models.dto;

import com.formation.fomation.api.enums.StatutFormation;
import com.formation.fomation.api.models.entity.Apprenant;
import com.formation.fomation.api.models.entity.Formateur;
import com.formation.fomation.api.models.entity.Formation;
import lombok.*;
import org.springframework.data.domain.Page;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormationDto {
    private String titre;
    private String niveau;
    private String prerequis;
    private Integer capaciteMin;
    private Integer capaciteMax;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String formateur;
    private String statut;
    private List<ApprenantDto> apprenants;

    public static FormationDto findById(Formation formation){



        List<ApprenantDto> apprenantList = formation.getApprenants() != null ?
                formation.getApprenants().stream()
                        .map(apprenant -> ApprenantDto.builder()
                                .nom(apprenant.getNom())
                                .build())
                        .collect(Collectors.toList()) :
                Collections.emptyList();

        return FormationDto.builder()
                .titre(formation.getTitre())
                .niveau(formation.getNiveau())
                .prerequis(formation.getPrerequis())
                .capaciteMax(formation.getCapaciteMax())
                .capaciteMin(formation.getCapaciteMin())
                .dateDebut(formation.getDateDebut())
                .dateFin(formation.getDateFin())
                .formateur(formation.getFormateur()!=null ?formation.getFormateur().getNom():null)
                .statut(formation.getStatut().name())
                .apprenants(apprenantList)
                .build();
    }
    public static List<FormationDto> formateurDtos(List<Formation>formations){
        return formations.stream().map(FormationDto::findById).collect(Collectors.toList());
    }

    public static Page<FormationDto> getAllWithPagination(Page<Formation> formationPage ) {
        return formationPage.map(FormationDto::findById);
    }

}
