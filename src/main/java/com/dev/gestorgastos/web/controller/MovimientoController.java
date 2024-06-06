package com.dev.gestorgastos.web.controller;

import com.dev.gestorgastos.domain.dto.MovimientoDto;
import com.dev.gestorgastos.domain.service.MovimientoService;
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

    @GetMapping("/active/presupuesto/{idPresupuesto}")
    @Operation(summary = "Get active by idPresupuesto", description = "Get a list of active Movimientos by their idPresupuesto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Movimiento found")
    })
    public ResponseEntity<List<MovimientoDto>> getActivosByIdPresupuesto(@Parameter(description ="Id of the plan of the movimiento") @PathVariable("idPresupuesto") String idPresupuesto) {
        return movimientoService.getActivosByIdPresupuesto(Integer.parseInt(idPresupuesto))
                .map(movimientos -> {
                    if (movimientos.isEmpty()) {
                        return new ResponseEntity<List<MovimientoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(movimientos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
            @ApiResponse(responseCode = "404", description = "Movimiento not found")
    })
    public ResponseEntity delete(@Parameter(description ="Id of the movimiento") @PathVariable("id") String idMovimiento) {
        return  new ResponseEntity(movimientoService.delete(Integer.parseInt(idMovimiento)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/undelete/{id}")
    @Operation(summary = "Undeletes a movimiento by id", description = "Undeletes a movimiento by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Movimiento not found")
    })
    public ResponseEntity unDelete(@Parameter(description ="Id of the movimiento") @PathVariable("id") String idMovimiento) {
        return  new ResponseEntity(movimientoService.unDelete(Integer.parseInt(idMovimiento)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
