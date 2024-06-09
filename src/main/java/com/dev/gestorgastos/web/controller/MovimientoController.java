package com.dev.gestorgastos.web.controller;

import com.dev.gestorgastos.domain.dto.MovimientoDto;
import com.dev.gestorgastos.domain.service.MovimientoService;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
// se le indica el path que le corresponde a este controller
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping("/id/{id}")
    @Operation(summary = "Get by Id", description = "Get a Movimiento by its idMovimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Movimiento not found")
    })
    public ResponseEntity<MovimientoDto> getByIdMovimiento(@Parameter(description = "Id of the movimiento") @PathVariable("id")  String idMovimiento) {
        return movimientoService.getByIdMovimiento(Integer.parseInt(idMovimiento))
                .map(movimiento-> new ResponseEntity<MovimientoDto>(movimiento, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/active/nombreTipoMovimiento/{nombreTipoMovimiento}")
    @Operation(summary = "Get active by nombreTipoMovimiento", description = "Get a list of active Movimientos by their nombreTipoMovimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Movimiento found")
    })
    public ResponseEntity<List<MovimientoDto>> getActivosByNombreTipoMovimiento(@Parameter(description ="Nombre of the tipoMovimiento of the movimiento") @PathVariable("nombreTipoMovimiento") String nombreTipoMovimiento) {
        return movimientoService.getActivosByNombreTipoMovimientoContains(nombreTipoMovimiento)
                .map(movimientos -> {
                    if (movimientos.isEmpty()) {
                        return new ResponseEntity<List<MovimientoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(movimientos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/unactive/nombreTipoMovimiento/{nombreTipoMovimiento}")
    @Operation(summary = "Get unactive by nombreTipoMovimiento", description = "Get a list of unactive Movimientos by their nombreTipoMovimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Movimiento found")
    })
    public ResponseEntity<List<MovimientoDto>> getInactivosByNombreTipoMovimiento(@Parameter(description ="Nombre of the tipoMovimiento of the movimiento") @PathVariable("nombreTipoMovimiento") String nombreTipoMovimiento) {
        return movimientoService.getInactivosByNombreTipoMovimientoContains(nombreTipoMovimiento)
                .map(movimientos -> {
                    if (movimientos.isEmpty()) {
                        return new ResponseEntity<List<MovimientoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(movimientos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/active/tipoMovimiento/{idTipoMovimiento}")
    @Operation(summary = "Get active by idTipoMovimiento", description = "Get a list of active Movimientos by their idTipoMovimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Movimiento found")
    })
    public ResponseEntity<List<MovimientoDto>> getActivosByIdTipoMovimiento(@Parameter(description ="Id of the tipoMovimiento of the movimiento") @PathVariable("idTipoMovimiento") String idTipoMovimiento) {
        return movimientoService.getActivosByIdTipoMovimiento(Integer.parseInt(idTipoMovimiento))
                .map(movimientos -> {
                    if (movimientos.isEmpty()) {
                        return new ResponseEntity<List<MovimientoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(movimientos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/active/cuenta/{idCuenta}")
    @Operation(summary = "Get active by idCuenta", description = "Get a list of active Movimientos by their idCuenta")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Movimiento found")
    })
    public ResponseEntity<List<MovimientoDto>> getActivosByIdCuenta(@Parameter(description ="Id of the cuenta of the movimiento") @PathVariable("idCuenta") String idCuenta) {
        return movimientoService.getActivosByIdCuenta(Integer.parseInt(idCuenta))
                .map(movimientos -> {
                    if (movimientos.isEmpty()) {
                        return new ResponseEntity<List<MovimientoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(movimientos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/active/presupuesto/{idPresupuestoMovimiento}")
    @Operation(summary = "Get active by idPresupuestoMovimiento", description = "Get a list of active Movimientos by their idPresupuestoMovimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Movimiento found")
    })
    public ResponseEntity<List<MovimientoDto>> getActivosByIdPresupuestoMovimiento(@Parameter(description ="Id of the PresupuestoMovimiento of the movimiento") @PathVariable("idPresupuestoMovimiento") String idPresupuestoMovimiento) {
        return movimientoService.getActivosByIdPresupuestoMovimiento(Integer.parseInt(idPresupuestoMovimiento))
                .map(movimientos -> {
                    if (movimientos.isEmpty()) {
                        return new ResponseEntity<List<MovimientoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(movimientos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/fechaHoraBetween")
    @Operation(summary = "Get all active Movimientos with fechaHora between two moments", description = "Get the list of all active Movimientos with fechaHora between two moments")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "No Movimiento found")
    })
    public ResponseEntity<?> getActiveFechaHoraBetween(
            @RequestParam("startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
            @RequestParam("endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime) {
        try {
            return movimientoService.getActivosByFechaHoraBetween(startDateTime,endDateTime)
                    .map(movimientos -> {
                        if (movimientos.isEmpty()) {
                            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                        } else {
                            return new ResponseEntity<>(movimientos, HttpStatus.OK);
                        }
                    })
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/active")
    @Operation(summary = "Get all active movimientos data", description = "Get the list of all active Movimientos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Movimiento found")
    })
    public ResponseEntity<List<MovimientoDto>> getAllActive() {
        return movimientoService.getAll()
                .map(movimientos -> {
                    if (movimientos.isEmpty()) {
                        return new ResponseEntity<List<MovimientoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(movimientos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/unactive")
    @Operation(summary = "Get all unactive movimientos data", description = "Get the list of all unactive Movimientos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Movimiento found")
    })
    public ResponseEntity<List<MovimientoDto>> getAllUnactive() {
        return movimientoService.getAllDeleted()
                .map(movimientos -> {
                    if (movimientos.isEmpty()) {
                        return new ResponseEntity<List<MovimientoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(movimientos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @Operation(summary = "Saves a movimiento", description = "Saves the data of a movimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<?> save(@RequestBody MovimientoDto movimientoDto) {
        try {
            return ResponseEntity.status(201).body(movimientoService.save(movimientoDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletes a movimiento by id", description = "Deletes a movimiento by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Movimiento not found"),
            @ApiResponse(responseCode = "409", description = "Conflict - Cannot delete Movimiento due to a conflict")
    })
    public ResponseEntity delete(@Parameter(description ="Id of the movimiento") @PathVariable("id") String idMovimiento) {
        try {
            return new ResponseEntity<>(movimientoService.delete(Integer.parseInt(idMovimiento)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        } catch (EntityCannotBeDeletedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/undelete/{id}")
    @Operation(summary = "Undeletes a movimiento by id", description = "Undeletes a movimiento by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Movimiento not found"),
            @ApiResponse(responseCode = "409", description = "Conflict - Cannot undelete Movimiento due to a conflict")
    })
    public ResponseEntity unDelete(@Parameter(description ="Id of the movimiento") @PathVariable("id") String idMovimiento) {
        try {
            return new ResponseEntity(movimientoService.unDelete(Integer.parseInt(idMovimiento)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        } catch (EntityCannotBeUndeletedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
