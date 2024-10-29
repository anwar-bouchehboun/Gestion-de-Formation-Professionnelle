package com.formation.fomation.api.models.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "apprenants")
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Apprenant extends User {
    @NotNull(message = "Le niveau est obligatoire")
    private String niveau;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "Le id formation est obligatoire")
    @JoinColumn(name = "formation_id")
    private Formation formation;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "Le id class est obligatoire")
    @JoinColumn(name = "classe_id")
    private Classe classe;
}
