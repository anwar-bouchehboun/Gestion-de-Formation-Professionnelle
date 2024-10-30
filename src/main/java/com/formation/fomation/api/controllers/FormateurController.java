package com.formation.fomation.api.controllers;


import com.formation.fomation.api.exceptions.ClasseNotFoundException;
import com.formation.fomation.api.models.dto.ClassDto;
import com.formation.fomation.api.models.dto.FormateurDto;
import com.formation.fomation.api.models.entity.Classe;
import com.formation.fomation.api.models.entity.Formateur;
import com.formation.fomation.api.services.FormateurServices;
import com.formation.fomation.api.utilitaire.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/formateurs")
@RequiredArgsConstructor
public class FormateurController {

    private final FormateurServices formateurServices;

    @Operation(summary = "Créer une nouvelle formateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "formateur créée avec succès", content = @Content(schema = @Schema(implementation = FormateurDto.class)))
    })
    @PostMapping
    public Formateur createFormateur(@Valid @RequestBody Formateur formateur){
        log.info("Création d'une nouvelle Formateur : {}", formateur);
        Formateur createdFormateur = formateurServices.createFormateur(formateur);
        log.info("Formateur créée avec succès : {}", createdFormateur);
        return createdFormateur;
    }
    @Operation(summary = "Obtenir toutes les classes")
    @ApiResponse(responseCode = "200", description = "Liste de toutes les classes", content = @Content(schema = @Schema(implementation = FormateurDto.class)))
    @GetMapping
    public List<FormateurDto> getAllFormateur(){

        List<FormateurDto> formateur=formateurServices.getAllFormateur();

        return formateur;
    }

    @Operation(summary = "Obtenir une classe par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classe trouvée", content = @Content(schema = @Schema(implementation = FormateurDto.class))),
            @ApiResponse(responseCode = "404", description = "Classe non trouvée")
    })
    @GetMapping("/{id}")
    public FormateurDto getFormateurById(@PathVariable Long id) {
        try {
            return formateurServices.getById(id)
                    .map(FormateurDto::findById)
                    .orElseThrow(() -> new ClasseNotFoundException("Fromateurs trouvée avec l'id: {} " + id + " non trouvée."));
        } catch (ClasseNotFoundException e) {
            log.error("Classe non trouvée avec l'ID: {}", id);
            throw e;
        }
    }


    @Operation(summary = "Mettre à jour une formateur existante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "formateur mise à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "formateur non trouvée")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Formateur updateFormateur(@PathVariable Long id, @Valid @RequestBody Formateur formateur) {
        try {
            Optional<Formateur> check = formateurServices.getById(id);
            if (check.isPresent()) {
                formateur.setId(id);
                log.info("Mise à jour du formateur avec l'ID : {}", id);
                return formateurServices.updateFormateur(formateur);
            } else {
                throw new ClasseNotFoundException("Aucun formateur trouvé avec l'ID : " + id);
            }
        } catch (ClasseNotFoundException e) {
            throw new ClasseNotFoundException("Erreur lors de la mise à jour : corps de la requête manquant ou invalide");
        }
    }

    @Operation(summary = "Supprimer une formateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "supprimer supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "supprimer non trouvée")
    })
    @DeleteMapping("/{id}")
    public void deleteFormateur(@PathVariable Long id) {
        Optional<Formateur> check= formateurServices.getById(id);
            if (check.isPresent()){
                formateurServices.deleteFormateur(id);
                log.info("formateur supprimer avec succès : {}", id);
            }else {
                throw new ClasseNotFoundException("Aucune Formateur trouvée avec le numéro : " + id);

            }


    }
    @Operation(summary = "Rechercher une formateur par numSalle de salle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des Formateur correspondant au nom de salle", content = @Content(schema = @Schema(implementation = FormateurDto.class))),
            @ApiResponse(responseCode = "404", description = "Salle  non trouvée")
    })
    @GetMapping("/search/{numSalle}")
    @ResponseStatus(HttpStatus.OK)
    public List<FormateurDto> searchByNumSalle(@PathVariable String numSalle) {
        List<FormateurDto> formateurDtoList = formateurServices.searchByNumSalle(numSalle);
        if (formateurDtoList == null || formateurDtoList.isEmpty()) {
            log.info("Aucune formateur trouvée avec le nom de salle: {}", numSalle);
            throw new ClasseNotFoundException("Aucune Formateur trouvée avec le nom  salle: " + numSalle);
        }
        log.info("Classes trouvées avec le numéro de salle {}: {}", numSalle, formateurDtoList.size());
        return formateurDtoList;
    }
    @GetMapping("/page")
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<FormateurDto> getAllClassesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<FormateurDto> pageClasses = formateurServices.getAllClassesWithPagination(pageable);

        return new PageResponse<>(
                pageClasses.getContent(),
                pageClasses.getNumber(),
                pageClasses.getTotalElements(),
                pageClasses.getTotalPages()
        );
    }
}
