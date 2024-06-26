package com.dev.gestorgastos.web.controller;

import com.dev.gestorgastos.domain.dto.PersonaDto;
import com.dev.gestorgastos.domain.service.PersonaService;
import com.dev.gestorgastos.persistence.exception.EntityCannotBeDeletedException;
import com.dev.gestorgastos.persistence.exception.EntityCannotBeUndeletedException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Optional;

@RestController
// se le indica el path que le corresponde a este controller
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/id/{id}")
    @Operation(summary = "Get by Id", description = "Get a Persona by its idPersona")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Persona not found")
    })
    public ResponseEntity<PersonaDto> getByIdPersona(@Parameter(description = "Id of the persona") @PathVariable("id")  String idPersona) {
        return personaService.getByIdPersona(Integer.parseInt(idPersona))
                .map(persona-> new ResponseEntity<PersonaDto>(persona, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/documento/{documento}")
    @Operation(summary = "Get by documento", description = "Get a Persona by its numeroDocumento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Persona not found")
    })
    public ResponseEntity<PersonaDto>  getByNumeroDocumento(@Parameter(description ="Documento of the persona") @PathVariable("documento") String numeroDocumento) {
        return personaService.getByNumeroDocumento(numeroDocumento)
                .map(persona-> new ResponseEntity<PersonaDto>(persona, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/active")
    @Operation(summary = "Get all active personas data", description = "Get the list of all active Personas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Persona found")
    })
    public ResponseEntity<List<PersonaDto>> getAllActive() {
        return personaService.getAll()
                .map(personas -> {
                    if (personas.isEmpty()) {
                        return new ResponseEntity<List<PersonaDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(personas, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/unactive")
    @Operation(summary = "Get all unactive personas data", description = "Get the list of all unactive Personas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Persona found")
    })
    public ResponseEntity<List<PersonaDto>> getAllUnactive() {
        return personaService.getAllDeleted()
                .map(personas -> {
                    if (personas.isEmpty()) {
                        return new ResponseEntity<List<PersonaDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(personas, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/save")
    @Operation(summary = "Saves a persona", description = "Saves the data of a Persona")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "409", description = "Conflict - Persona with the same numeroDocumento already exists")
    })
    public ResponseEntity<PersonaDto> save(@RequestBody PersonaDto personaDto) {
        Optional<PersonaDto> existingPersona = personaService.getByNumeroDocumento(personaDto.getNumeroDocumento());

        if (existingPersona.isPresent()) {
            // If a persona with the same numeroDocumento already exists, return a conflict response
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(personaService.save(personaDto), HttpStatus.CREATED);
        }
    }
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletes a persona by id", description = "Deletes a persona by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Persona not found"),
            @ApiResponse(responseCode = "409", description = "Conflict - Cannot delete Persona due to conflict")
    })
    public ResponseEntity<String> delete(@Parameter(description = "Id of the persona") @PathVariable("id") String personaId) {
        try {
            boolean deleted = personaService.delete(Integer.parseInt(personaId));
            return new ResponseEntity<>(deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        } catch (EntityCannotBeDeletedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/undelete/{id}")
    @Operation(summary = "Undeletes a persona by id", description = "Undeletes a persona by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Persona not found"),
            @ApiResponse(responseCode = "409", description = "Conflict - Cannot undelete Persona due to conflict")
    })
    public ResponseEntity<String> unDelete(@Parameter(description = "Id of the persona") @PathVariable("id") String personaId) {
        try {
            return new ResponseEntity<>(personaService.unDelete(Integer.parseInt(personaId)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        } catch (EntityCannotBeUndeletedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
