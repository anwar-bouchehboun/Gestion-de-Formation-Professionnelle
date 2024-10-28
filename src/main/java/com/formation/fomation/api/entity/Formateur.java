package com.formation.fomation.api.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "formateurs")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Formateur extends User {
    @NotNull(message = "La spécialité est obligatoire")
    private String specialite;

    @OneToMany(mappedBy = "formateur", cascade = CascadeType.ALL)
    private List<Formation> formations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classe_id")
    private Classe classe;
}
