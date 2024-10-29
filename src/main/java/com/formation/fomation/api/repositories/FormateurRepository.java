package com.formation.fomation.api.repositories;

import com.formation.fomation.api.models.entity.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormateurRepository extends JpaRepository<Formateur,Long> {
    @Query("SELECT f FROM Formateur f LEFT JOIN FETCH f.classe")
    List<Formateur> findAllWithClasse();
}
