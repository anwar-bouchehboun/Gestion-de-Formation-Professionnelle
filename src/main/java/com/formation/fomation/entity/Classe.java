package com.formation.fomation.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

    @NotBlank(message = "Le nom de la classe est obligatoire")
    @Column(nullable = false)
    private String nom;

    @NotBlank(message = "Le numéro de salle est obligatoire")
    @Pattern(regexp = "^[A-Z0-9]{1,5}$", message = "Le numéro de salle doit contenir entre 1 et 5 caractères alphanumériques")
    @Column(name = "numero_salle", nullable = false)
    private String numSalle;

    @OneToMany(mappedBy = "classe")
    private List<Apprenant> apprenants;

    @OneToMany(mappedBy = "classe")
    private List<Formateur> formateurs;
}
