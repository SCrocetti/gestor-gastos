package com.dev.gestorgastos.web.controller;

import com.dev.gestorgastos.domain.dto.TipoTransaccionDto;
import com.dev.gestorgastos.domain.service.TipoTransaccionService;
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
@RequestMapping("/tiposTransaccion")
public class TipoTransaccionController {

    @Autowired
    private TipoTransaccionService tipoTransaccionService;

    @GetMapping("/id/{id}")
    @Operation(summary = "Get by Id", description = "Get a TipoTransaccion by its idTipoTransaccion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "TipoTransaccion not found")
    })
    public ResponseEntity<TipoTransaccionDto> getByIdTipoTransaccion(@Parameter(description = "Id of the idTipoTransaccion") @PathVariable("id")  String idTipoTransaccion) {
        return tipoTransaccionService.getByIdTipoTransaccion(Integer.parseInt(idTipoTransaccion))
                .map(tipoTransaccion-> new ResponseEntity<TipoTransaccionDto>(tipoTransaccion, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/active/nombre/{nombre}")
    @Operation(summary = "Get active by nombreTipoTransaccion", description = "Get a list of active TiposTransaccion by their nombreTipoTransaccion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No TipoTransaccion found")
    })
    public ResponseEntity<List<TipoTransaccionDto>> getActiveByNombreTipoTransaccion(@Parameter(description ="Nombre of the tipoTransaccion") @PathVariable("nombre") String nombreTipoTransaccion) {
        return tipoTransaccionService.getActiveByNombreTipoTransaccionContains(nombreTipoTransaccion)
                .map(tiposTransaccion -> {
                    if (tiposTransaccion.isEmpty()) {
                        return new ResponseEntity<List<TipoTransaccionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(tiposTransaccion, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/unaactive/nombre/{nombre}")
    @Operation(summary = "Get unactive by nombreTipoTransaccion", description = "Get a list of unactive TiposTransaccion by their nombreTipoTransaccion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No TipoTransaccion found")
    })
    public ResponseEntity<List<TipoTransaccionDto>> getInactivosByNombreTipoTransaccion(@Parameter(description ="Nombre of the tipoTransaccion") @PathVariable("nombre") String nombreTipoTransaccion) {
        return tipoTransaccionService.getUnactiveByNombreTipoTransaccionContains(nombreTipoTransaccion)
                .map(tiposTransaccion-> {
                    if (tiposTransaccion.isEmpty()) {
                        return new ResponseEntity<List<TipoTransaccionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(tiposTransaccion, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/active")
    @Operation(summary = "Get all active tiposTransaccion data", description = "Get the list of all active TiposTransaccion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No TipoTransaccion found")
    })
    public ResponseEntity<List<TipoTransaccionDto>> getAllActive() {
        return tipoTransaccionService.getAll()
                .map(tiposTransaccion -> {
                    if (tiposTransaccion.isEmpty()) {
                        return new ResponseEntity<List<TipoTransaccionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(tiposTransaccion, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/unactive")
    @Operation(summary = "Get all unactive tiposTransaccion data", description = "Get the list of all unactive TiposTransaccion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No TipoTransaccion found")
    })
    public ResponseEntity<List<TipoTransaccionDto>> getAllUnactive() {
        return tipoTransaccionService.getAllDeleted()
                .map(tiposTransaccion -> {
                    if (tiposTransaccion.isEmpty()) {
                        return new ResponseEntity<List<TipoTransaccionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(tiposTransaccion, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @Operation(summary = "Saves a tipoTransaccion", description = "Saves the data of a tipoTransaccion")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<TipoTransaccionDto> save(@RequestBody TipoTransaccionDto tipoTransaccionDto) {
        return new ResponseEntity<>(tipoTransaccionService.save(tipoTransaccionDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletes a tipoTransaccion by id", description = "Deletes a tipoTransaccion by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "TipoTransaccion not found"),
            @ApiResponse(responseCode = "409", description = "Conflict - Cannot delete TipoTransaccion due to a conflict")
    })
    public ResponseEntity delete(@Parameter(description ="Id of the tipoTransaccion") @PathVariable("id") String idTipoTransaccion) {
        try {
            boolean deleted = tipoTransaccionService.delete(Integer.parseInt(idTipoTransaccion));
            return new ResponseEntity<>(deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        } catch (EntityCannotBeDeletedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/undelete/{id}")
    @Operation(summary = "Undeletes a tipoTransaccion by id", description = "Undeletes a tipoTransaccion by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "TipoTransaccion not found"),
            @ApiResponse(responseCode = "409", description = "Conflict - Cannot undelete TipoTransaccion due to a conflict")
    })
    public ResponseEntity unDelete(@Parameter(description ="Id of the tipoTransaccion") @PathVariable("id") String idTipoTransaccion) {
        try {
            return new ResponseEntity(tipoTransaccionService.unDelete(Integer.parseInt(idTipoTransaccion)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        } catch (EntityCannotBeUndeletedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
