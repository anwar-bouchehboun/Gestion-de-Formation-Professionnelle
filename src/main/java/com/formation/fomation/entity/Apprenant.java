package com.formation.fomation.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "apprenants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Apprenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le nom est obligatoire")
    private String nom;

    @NotNull(message = "Le prénom est obligatoire")
    private String prenom;

    @Email(message = "L'email doit être valide")
    @NotNull(message = "L'email est obligatoire")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Le niveau est obligatoire")
    private String niveau;

    @ManyToOne
    @JoinColumn(name = "formation_id")
    private Formation formation;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    private Classe classe;
}
