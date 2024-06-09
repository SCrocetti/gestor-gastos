package com.dev.gestorgastos.web.controller;

import com.dev.gestorgastos.domain.dto.TransaccionDto;
import com.dev.gestorgastos.domain.service.TransaccionService;
import com.dev.gestorgastos.persistence.exception.EntityCannotBeDeletedException;
import com.dev.gestorgastos.persistence.exception.EntityCannotBeUndeletedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
// se le indica el path que le corresponde a este controller
@RequestMapping("/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping("/id/{id}")
    @Operation(summary = "Get by Id", description = "Get a Transaccion by its idTransaccion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Transaccion not found")
    })
    public ResponseEntity<TransaccionDto> getByIdTransaccion(@Parameter(description = "Id of the transaccion") @PathVariable("id")  String idTransaccion) {
        return transaccionService.getByIdTransaccion(Integer.parseInt(idTransaccion))
                .map(transaccion -> new ResponseEntity<TransaccionDto>(transaccion, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/active/nombreTipoTransaccion/{nombreTipoTransaccion}")
    @Operation(summary = "Get active by nombreTipoTransaccion", description = "Get a list of active Transacciones by their nombreTipoTransaccion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Transaccion found")
    })
    public ResponseEntity<List<TransaccionDto>> getActivosByNombreTipoTransaccion(@Parameter(description ="Nombre of the tipoTransaccion of the transaccion") @PathVariable("nombreTipoTransaccion") String nombreTipoTransaccion) {
        return transaccionService.getActivosByNombreTipoTransaccionContains(nombreTipoTransaccion)
                .map(transacciones -> {
                    if (transacciones.isEmpty()) {
                        return new ResponseEntity<List<TransaccionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(transacciones, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/unactive/nombreTipoTransaccion/{nombreTipoTransaccion}")
    @Operation(summary = "Get unactive by nombreTipoTransaccion", description = "Get a list of unactive Transacciones by their nombreTipoTransaccion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Transaccion found")
    })
    public ResponseEntity<List<TransaccionDto>> getInactivosByNombreTipoTransaccion(@Parameter(description ="Nombre of the tipoTransaccion of the transaccion") @PathVariable("nombreTipoTransaccion") String nombreTipoTransaccion) {
        return transaccionService.getInactivosByNombreTipoTransaccionContains(nombreTipoTransaccion)
                .map(transacciones -> {
                    if (transacciones.isEmpty()) {
                        return new ResponseEntity<List<TransaccionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(transacciones, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/active/tipoTransaccion/{idTipoTransaccion}")
    @Operation(summary = "Get active by idTipoTransaccion", description = "Get a list of active Transacciones by their idTipoTransaccion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Transaccion found")
    })
    public ResponseEntity<List<TransaccionDto>> getActivosByIdTipoTransaccion(@Parameter(description ="Id of the tipoTransaccion of the transaccion") @PathVariable("idTipoTransaccion") String idTipoTransaccion) {
        return transaccionService.getActivosByIdTipoTransaccion(Integer.parseInt(idTipoTransaccion))
                .map(transacciones -> {
                    if (transacciones.isEmpty()) {
                        return new ResponseEntity<List<TransaccionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(transacciones, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/active/cuenta/{idCuentaEgreso}")
    @Operation(summary = "Get active by idCuentaEgreso", description = "Get a list of active Transacciones by their idCuentaEgreso")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Transaccion found")
    })
    public ResponseEntity<List<TransaccionDto>> getActivosByIdCuentaEgreso(@Parameter(description ="Id of the cuentaEgreso of the transaccion") @PathVariable("idCuentaEgreso") String idCuentaEgreso) {
        return transaccionService.getActivosByIdCuentaEgreso(Integer.parseInt(idCuentaEgreso))
                .map(transacciones -> {
                    if (transacciones.isEmpty()) {
                        return new ResponseEntity<List<TransaccionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(transacciones, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/active/cuenta/{idCuentaIngreso}")
    @Operation(summary = "Get active by idCuentaIngreso", description = "Get a list of active Transacciones by their idCuentaIngreso")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Transaccion found")
    })
    public ResponseEntity<List<TransaccionDto>> getActivosByIdCuentaIngreso(@Parameter(description ="Id of the cuentaIngreso of the transaccion") @PathVariable("idCuentaIngreso") String idCuentaIngreso) {
        return transaccionService.getActivosByIdCuentaIngreso(Integer.parseInt(idCuentaIngreso))
                .map(transacciones -> {
                    if (transacciones.isEmpty()) {
                        return new ResponseEntity<List<TransaccionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(transacciones, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/active/presupuesto/{idPresupuestoTransaccion}")
    @Operation(summary = "Get active by idPresupuestoTransaccion", description = "Get a list of active Transacciones by their idPresupuestoTransaccion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Transaccion found")
    })
    public ResponseEntity<List<TransaccionDto>> getActivosByIdPresupuestoTransaccion(@Parameter(description ="Id of the PresupuestoTransaccion of the transaccion") @PathVariable("idPresupuestoTransaccion") String idPresupuestoTransaccion) {
        return transaccionService.getActivosByIdPresupuestoTransaccion(Integer.parseInt(idPresupuestoTransaccion))
                .map(transacciones -> {
                    if (transacciones.isEmpty()) {
                        return new ResponseEntity<List<TransaccionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(transacciones, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/fechaHoraBetween")
    @Operation(summary = "Get all active Transacciones with fechaHora between two moments", description = "Get the list of all active Transacciones with fechaHora between two moments")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "No Transaccion found")
    })
    public ResponseEntity<?> getActiveFechaHoraBetween(
            @RequestParam("startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
            @RequestParam("endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime) {
        try {
            return transaccionService.getActivosByFechaHoraBetween(startDateTime,endDateTime)
                    .map(transacciones -> {
                        if (transacciones.isEmpty()) {
                            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                        } else {
                            return new ResponseEntity<>(transacciones, HttpStatus.OK);
                        }
                    })
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/active")
    @Operation(summary = "Get all active transacciones data", description = "Get the list of all active Transacciones")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Transaccion found")
    })
    public ResponseEntity<List<TransaccionDto>> getAllActive() {
        return transaccionService.getAll()
                .map(transacciones -> {
                    if (transacciones.isEmpty()) {
                        return new ResponseEntity<List<TransaccionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(transacciones, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/unactive")
    @Operation(summary = "Get all unactive transacciones data", description = "Get the list of all unactive Transacciones")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Transaccion found")
    })
    public ResponseEntity<List<TransaccionDto>> getAllUnactive() {
        return transaccionService.getAllDeleted()
                .map(transacciones -> {
                    if (transacciones.isEmpty()) {
                        return new ResponseEntity<List<TransaccionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(transacciones, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @Operation(summary = "Saves a transaccion", description = "Saves the data of a transaccion")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<?> save(@RequestBody TransaccionDto transaccionDto) {
        try {
            return ResponseEntity.status(201).body(transaccionService.save(transaccionDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletes a transaccion by id", description = "Deletes a transaccion by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Transaccion not found"),
            @ApiResponse(responseCode = "409", description = "Conflict - Cannot delete Transaccion due to a conflict")
    })
    public ResponseEntity delete(@Parameter(description ="Id of the transaccion") @PathVariable("id") String idTransaccion) {
        try {
            return new ResponseEntity<>(transaccionService.delete(Integer.parseInt(idTransaccion)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        } catch (EntityCannotBeDeletedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/undelete/{id}")
    @Operation(summary = "Undeletes a transaccion by id", description = "Undeletes a transaccion by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Transaccion not found"),
            @ApiResponse(responseCode = "409", description = "Conflict - Cannot undelete Transaccion due to a conflict")
    })
    public ResponseEntity unDelete(@Parameter(description ="Id of the transaccion") @PathVariable("id") String idTransaccion) {
        try {
            return new ResponseEntity(transaccionService.unDelete(Integer.parseInt(idTransaccion)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        } catch (EntityCannotBeUndeletedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}