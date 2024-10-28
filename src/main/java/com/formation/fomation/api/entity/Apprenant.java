package com.formation.fomation.api.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "apprenants")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Apprenant extends User {
    @NotNull(message = "Le niveau est obligatoire")
    private String niveau;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Le id formation est obligatoire")
    @JoinColumn(name = "formation_id")
    private Formation formation;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Le id class est obligatoire")
    @JoinColumn(name = "classe_id")
    private Classe classe;
}
