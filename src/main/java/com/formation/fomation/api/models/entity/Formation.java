package com.formation.fomation.api.models.entity;


import javax.persistence.*;
import javax.validation.constraints.*;


import com.formation.fomation.api.enums.StatutFormation;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "formations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le titre est obligatoire")
    private String titre;

    @NotNull(message = "Le niveau est obligatoire")
    private String niveau;

    @NotNull(message = "Les prérequis sont obligatoires")
    @Column(columnDefinition = "TEXT")
    private String prerequis;

    @Min(value = 1)
    private Integer capaciteMin;

    @Min(value = 1)
    private Integer capaciteMax;

    @NotNull
    private LocalDate dateDebut;

    @NotNull
    private LocalDate dateFin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "formateur_id")
    private Formateur formateur;

    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL)
    private List<Apprenant> apprenants;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatutFormation statut;
}
