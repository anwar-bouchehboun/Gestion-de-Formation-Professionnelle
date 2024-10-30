package com.formation.fomation.api.repositories;

import com.formation.fomation.api.models.entity.Apprenant;
import com.formation.fomation.api.models.entity.Formation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormationRepository extends JpaRepository<Formation,Long> {
    @Query("SELECT DISTINCT f FROM Formation f")
    Page<Formation> findAllWithPagination(Pageable pageable);

    @Query("SELECT DISTINCT f FROM Formation f " +
           "LEFT JOIN FETCH f.apprenants " +
           "LEFT JOIN FETCH f.formateur " +
           "WHERE f IN :formations")
    List<Formation> findAllWithDetails(@Param("formations") List<Formation> formations);

    @Query("SELECT DISTINCT f FROM Formation f " +
           "LEFT JOIN f.apprenants a " +
           "LEFT JOIN f.formateur fr " +
           "WHERE LOWER(a.nom) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
           "OR LOWER(fr.nom) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Formation> findByNomContaining(@Param("searchTerm") String searchTerm);
}

