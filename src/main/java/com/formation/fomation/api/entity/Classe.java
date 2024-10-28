package com.formation.fomation.api.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

import java.util.List;

@Entity
@Table(name = "classes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le nom de la classe est obligatoire")
    @Column(nullable = false)
    private String nom;

    @NotNull(message = "Le numéro de salle est obligatoire")
    @Pattern(regexp = "^[A-Z0-9]{1,5}$")
    @Column(name = "numero_salle", nullable = false)
    private String numSalle;

    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL)
    private List<Apprenant> apprenants;

    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL)
    private List<Formateur> formateurs;
}
