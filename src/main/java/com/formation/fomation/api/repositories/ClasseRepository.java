package com.formation.fomation.api.repositories;

import com.formation.fomation.api.models.entity.Classe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {

     @Query("SELECT c FROM Classe c")
    Page<Classe> findAllWithPagination(Pageable pageable);
    
    @Query("SELECT c FROM Classe c WHERE c.numSalle LIKE CONCAT('%', :numSalle, '%')")
    List<Classe> findByNumSalleContaining(@Param("numSalle") String numSalle);
    

}
