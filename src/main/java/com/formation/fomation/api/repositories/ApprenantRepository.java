package com.formation.fomation.api.repositories;


import com.formation.fomation.api.models.entity.Apprenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {

}
