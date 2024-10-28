package com.formation.fomation.entity;

import javax.persistence.*;
import javax.validation.constraints.*;


import com.formation.fomation.enums.StatutFormation;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "formation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le titre est obligatoire")
    private String titre;

    @NotBlank(message = "Le niveau est obligatoire")
    private String niveau;

    @NotBlank(message = "Les prérequis sont obligatoires")
    @Column(columnDefinition = "TEXT")
    private String prerequis;

    @Min(value = 1, message = "La capacité minimale doit être supérieure à 0")
    private Integer capaciteMin;

    @Min(value = 1, message = "La capacité maximale doit être supérieure à 0")
    private Integer capaciteMax;

    @NotNull(message = "La date de début est obligatoire")
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin est obligatoire")
    private LocalDate dateFin;

    @ManyToOne
    @JoinColumn(name = "formateur_id")
    private Formateur formateur;

    @OneToMany(mappedBy = "formation")
    private List<Apprenant> apprenants;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Le statut est obligatoire")
    private StatutFormation statut;
}

