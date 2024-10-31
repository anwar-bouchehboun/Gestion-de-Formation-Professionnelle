package com.formation.fomation.api.controllers;

import com.formation.fomation.api.models.dto.ApprenantDto;
import com.formation.fomation.api.models.entity.Apprenant;
import com.formation.fomation.api.services.ApprenantServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/apprenants")
@RequiredArgsConstructor
public class ApprenantController {

    private final ApprenantServices apprenantServices;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Apprenant createApprenant(@Valid @RequestBody Apprenant apprenant) {
        log.info("Creating new apprenant: {}", apprenant);
        return apprenantServices.createApprenant(apprenant);
    }

    @PutMapping("/{id}")
    public void updateApprenant(@PathVariable Long id, @Valid @RequestBody Apprenant apprenant) {
        apprenant.setId(id);
        apprenantServices.updateApprenant(apprenant);
        log.info("Updated apprenant with ID: {}", id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteApprenant(@PathVariable Long id) {
        apprenantServices.deleteApprenant(id);
        log.info("Deleted apprenant with ID: {}", id);
    }

    @GetMapping
    public List<ApprenantDto> getAllApprenants() {
        return apprenantServices.getAllApprenant();
    }

    @GetMapping("/{id}")
    public ApprenantDto getApprenantById(@PathVariable Long id) {
        Optional<Apprenant> apprenant = apprenantServices.getById(id);
        return apprenant.map(ApprenantDto::findById)
                        .orElseThrow(() -> new RuntimeException("Apprenant not found with ID: " + id));
    }

    @GetMapping("/page")
    public Page<ApprenantDto> getAllApprenantsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return apprenantServices.getAllClassesWithPagination(pageable);
    }

    @GetMapping("/search/{numSalle}")
    public List<ApprenantDto> searchApprenantsByNumSalle(@PathVariable String numSalle) {
        return apprenantServices.searchByNumSalle(numSalle);
    }
}
