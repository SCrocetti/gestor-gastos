package com.dev.gestorgastos.web.controller;

import com.dev.gestorgastos.domain.dto.DenominacionDto;
import com.dev.gestorgastos.domain.service.DenominacionService;
import com.dev.gestorgastos.persistence.exception.EntityCannotBeDeletedException;
import com.dev.gestorgastos.persistence.exception.EntityCannotBeUndeletedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// se le indica el path que le corresponde a este controller
@RequestMapping("/denominaciones")
public class DenominacionController {

    @Autowired
    private DenominacionService denominacionService;

    @GetMapping("/id/{id}")
    @Operation(summary = "Get by Id", description = "Get a Denominacion by its idDenominacion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Denominacion not found")
    })
    public ResponseEntity<DenominacionDto> getByIdDenominacion(@Parameter(description = "Id of the denominacion") @PathVariable("id")  String idDenominacion) {
        return denominacionService.getByIdDenominacion(Integer.parseInt(idDenominacion))
                .map(denominacion-> new ResponseEntity<DenominacionDto>(denominacion, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/active/nombre/{nombre}")
    @Operation(summary = "Get activos by nombreDenominacion", description = "Get a list of active Denominaciones by their nombreDenominacion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Denominacion found")
    })
    public ResponseEntity<List<DenominacionDto>> getActivosByNombreDenominacion(@Parameter(description ="Nombre of the denominacion") @PathVariable("nombre") String nombreDenominacion) {
        return denominacionService.getActivosByNombreDenominacionContains(nombreDenominacion)
                .map(denominaciones -> {
                    if (denominaciones.isEmpty()) {
                        return new ResponseEntity<List<DenominacionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(denominaciones, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/unactive/nombre/{nombre}")
    @Operation(summary = "Get inactivos by nombreDenominacion", description = "Get a list of unactive Denominaciones by their nombreDenominacion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Denominacion found")
    })
    public ResponseEntity<List<DenominacionDto>> getInactivosByNombreDenominacion(@Parameter(description ="Nombre of the denominacion") @PathVariable("nombre") String nombreDenominacion) {
        return denominacionService.getInactivosByNombreDenominacionContains(nombreDenominacion)
                .map(denominaciones -> {
                    if (denominaciones.isEmpty()) {
                        return new ResponseEntity<List<DenominacionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(denominaciones, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/active")
    @Operation(summary = "Get all active denominaciones data", description = "Get the list of all active Denominaciones")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Denominacion found")
    })
    public ResponseEntity<List<DenominacionDto>> getAllActive() {
        return denominacionService.getAll()
                .map(denominaciones -> {
                    if (denominaciones.isEmpty()) {
                        return new ResponseEntity<List<DenominacionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(denominaciones, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/unactive")
    @Operation(summary = "Get all unactive denominaciones data", description = "Get the list of all unactive Denominaciones")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Denominacion found")
    })
    public ResponseEntity<List<DenominacionDto>> getAllUnactive() {
        return denominacionService.getAllDeleted()
                .map(denominaciones -> {
                    if (denominaciones.isEmpty()) {
                        return new ResponseEntity<List<DenominacionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(denominaciones, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/save")
    @Operation(summary = "Saves a denominacion", description = "Saves the data of a Denominacion")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<DenominacionDto> save(@RequestBody DenominacionDto denominacionDto) {
        return new ResponseEntity<>(denominacionService.save(denominacionDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletes a denominacion by id", description = "Deletes a denominacion by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Denominacion not found"),
            @ApiResponse(responseCode = "409", description = "Conflict - Cannot delete Denominacion due to conflict")
    })
    public ResponseEntity delete(@Parameter(description ="Id of the denominacion") @PathVariable("id") String denominacionId) {
        try {
            boolean deleted = denominacionService.delete(Integer.parseInt(denominacionId));
            return new ResponseEntity<>(deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        } catch (EntityCannotBeDeletedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/undelete/{id}")
    @Operation(summary = "Undeletes a denominacion by id", description = "Undeletes a denominacion by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Denominacion not found"),
            @ApiResponse(responseCode = "409", description = "Conflict - Cannot undelete Denominacion due to conflict")
    })
    public ResponseEntity unDelete(@Parameter(description ="Id of the denominacion") @PathVariable("id") String denominacionId) {
        try {
            return  new ResponseEntity(denominacionService.unDelete(Integer.parseInt(denominacionId)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        } catch (EntityCannotBeUndeletedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
