package com.formation.fomation.api.models.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

import java.util.List;

@Entity
@Table(name = "formateurs")
@Data
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Formateur extends User {
    @NotNull(message = "La spécialité est obligatoire")
    private String specialite;

    @OneToMany(mappedBy = "formateur", cascade = CascadeType.ALL)
    private List<Formation> formations;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classe_id")
    private Classe classe;
}
