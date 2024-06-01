package com.dev.gestorgastos.web.controller;

import com.dev.gestorgastos.domain.DenominacionDto;
import com.dev.gestorgastos.domain.service.DenominacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @ApiResponse(responseCode = "200", description = "OK")
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
    @ApiResponse(responseCode = "200", description = "OK")
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
            @ApiResponse(responseCode = "404", description = "Denominacion not found")
    })
    public ResponseEntity delete(@Parameter(description ="Id of the denominacion") @PathVariable("id") String denominacionId) {
        return  new ResponseEntity(denominacionService.delete(Integer.parseInt(denominacionId)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/undelete/{id}")
    @Operation(summary = "Undeletes a denominacion by id", description = "Undeletes a denominacion by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Denominacion not found")
    })
    public ResponseEntity unDelete(@Parameter(description ="Id of the denominacion") @PathVariable("id") String denominacionId) {
        return  new ResponseEntity(denominacionService.unDelete(Integer.parseInt(denominacionId)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
