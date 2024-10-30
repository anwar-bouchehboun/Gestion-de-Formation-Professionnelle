package com.formation.fomation.api.repositories;


import com.formation.fomation.api.models.entity.Apprenant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {
    @Query("SELECT f FROM Apprenant f LEFT JOIN f.classe")
    Page<Apprenant> findAllWithPagination(Pageable pageable);
    @Query("SELECT f FROM Apprenant f LEFT JOIN f.classe c WHERE LOWER(c.nom) LIKE LOWER(CONCAT('%', :className, '%'))")
    List<Apprenant> findByClasseNomContaining(@Param("className") String className);

}
