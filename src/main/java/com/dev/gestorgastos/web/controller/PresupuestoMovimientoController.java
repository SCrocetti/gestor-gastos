package com.dev.gestorgastos.web.controller;

import com.dev.gestorgastos.domain.dto.PresupuestoMovimientoDto;
import com.dev.gestorgastos.domain.service.PresupuestoMovimientoService;
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
@RequestMapping("/presupuestosMovimientos")
public class PresupuestoMovimientoController {

    @Autowired
    private PresupuestoMovimientoService presupuestoMovimientoService;

    @GetMapping("/id/{id}")
    @Operation(summary = "Get by Id", description = "Get a PresupuestoMovimiento by its idPresupuestoMovimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "PresupuestoMovimiento not found")
    })
    public ResponseEntity<PresupuestoMovimientoDto> getByIdPresupuestoMovimiento(@Parameter(description = "Id of the presupuestoMovimiento") @PathVariable("id")  String idPresupuestoMovimiento) {
        return presupuestoMovimientoService.getByIdPresupuestoMovimiento(Integer.parseInt(idPresupuestoMovimiento))
                .map(presupuesto-> new ResponseEntity<PresupuestoMovimientoDto>(presupuesto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/active/nombreTipoMovimiento/{nombreTipoMovimiento}")
    @Operation(summary = "Get active by nombreTipoMovimiento", description = "Get a list of active PresupuestosMovimientos by their nombreTipoMovimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No PresupuestoMovimiento found")
    })
    public ResponseEntity<List<PresupuestoMovimientoDto>> getActivosByNombreTipoMovimiento(@Parameter(description ="Nombre of the tipoMovimiento of the presupuestoMovimiento") @PathVariable("nombre") String nombreTipoMovimiento) {
        return presupuestoMovimientoService.getActivosByNombreTipoMovimiento(nombreTipoMovimiento)
                .map(presupuestos -> {
                    if (presupuestos.isEmpty()) {
                        return new ResponseEntity<List<PresupuestoMovimientoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/unactive/nombreTipoMovimiento/{nombreTipoMovimiento}")
    @Operation(summary = "Get unactive by nombreTipoMovimiento", description = "Get a list of unactive PresupuestosMovimientos by their nombreTipoMovimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Presupuesto found")
    })
    public ResponseEntity<List<PresupuestoMovimientoDto>> getInactivosByNombreTipoMovimiento(@Parameter(description ="Nombre of the tipoMovimiento of the presupuestoMovimiento") @PathVariable("nombre") String nombreTipoMovimiento) {
        return presupuestoMovimientoService.getInactivosByNombreTipoMovimiento(nombreTipoMovimiento)
                .map(presupuestos -> {
                    if (presupuestos.isEmpty()) {
                        return new ResponseEntity<List<PresupuestoMovimientoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/active/tipoMovimiento/{idTipoMovimiento}")
    @Operation(summary = "Get active by idTipoMovimiento", description = "Get a list of active PresupuestosMovimientos by their idTipoMovimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No PresupuestoMovimiento found")
    })
    public ResponseEntity<List<PresupuestoMovimientoDto>> getActivosByIdTipoMovimiento(@Parameter(description ="Id of the tipoMovimiento of the presupuestoMovimiento") @PathVariable("idTipoMovimiento") String idTipoMovimiento) {
        return presupuestoMovimientoService.getActivosByIdTipoMovimiento(Integer.parseInt(idTipoMovimiento))
                .map(presupuestos -> {
                    if (presupuestos.isEmpty()) {
                        return new ResponseEntity<List<PresupuestoMovimientoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/active/cuenta/{idCuenta}")
    @Operation(summary = "Get active by idCuenta", description = "Get a list of active PresupuestosMovimientos by their idCuenta")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No PresupuestoMovimiento found")
    })
    public ResponseEntity<List<PresupuestoMovimientoDto>> getActivosByIdCuenta(@Parameter(description ="Id of the cuenta of the presupuestoMovimiento") @PathVariable("idCuenta") String idCuenta) {
        return presupuestoMovimientoService.getActivosByIdCuenta(Integer.parseInt(idCuenta))
                .map(presupuestos -> {
                    if (presupuestos.isEmpty()) {
                        return new ResponseEntity<List<PresupuestoMovimientoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/active/plan/{idPlan}")
    @Operation(summary = "Get active by idPlan", description = "Get a list of active PresupuestosMovimientos by their idPlan")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No PresupuestoMovimiento found")
    })
    public ResponseEntity<List<PresupuestoMovimientoDto>> getActivosByIdPlan(@Parameter(description ="Id of the plan of the presupuestoMovimiento") @PathVariable("idPlan") String idPlan) {
        return presupuestoMovimientoService.getActivosByIdPlan(Integer.parseInt(idPlan))
                .map(presupuestos -> {
                    if (presupuestos.isEmpty()) {
                        return new ResponseEntity<List<PresupuestoMovimientoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/active")
    @Operation(summary = "Get all active presupuestosMovimientos data", description = "Get the list of all active PresupuestosMovimientos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No PresupuestoMovimiento found")
    })
    public ResponseEntity<List<PresupuestoMovimientoDto>> getAllActive() {
        return presupuestoMovimientoService.getAll()
                .map(presupuestos -> {
                    if (presupuestos.isEmpty()) {
                        return new ResponseEntity<List<PresupuestoMovimientoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/unactive")
    @Operation(summary = "Get all unactive presupuestosMovimientos data", description = "Get the list of all unactive PresupuestosMovimientos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No PresupuestoMovimientos found")
    })
    public ResponseEntity<List<PresupuestoMovimientoDto>> getAllUnactive() {
        return presupuestoMovimientoService.getAllDeleted()
                .map(presupuestos -> {
                    if (presupuestos.isEmpty()) {
                        return new ResponseEntity<List<PresupuestoMovimientoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @Operation(summary = "Saves a presupuestoMovimiento", description = "Saves the data of a presupuestoMovimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<?> save(@RequestBody PresupuestoMovimientoDto presupuestoMovimientoDto) {
        try {
            return new ResponseEntity<>(presupuestoMovimientoService.save(presupuestoMovimientoDto), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletes a presupuestoMovimiento by id", description = "Deletes a presupuestoMovimiento by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "PresupuestoMovimiento not found"),
            @ApiResponse(responseCode = "409", description = "Conflict - Cannot delete PresupuestoMovimiento due to conflict")
    })
    public ResponseEntity delete(@Parameter(description ="Id of the presupuestoMovimiento") @PathVariable("id") String idPresupuestoMovimiento) {
        try {
            return  new ResponseEntity(presupuestoMovimientoService.delete(Integer.parseInt(idPresupuestoMovimiento)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        } catch (EntityCannotBeDeletedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/undelete/{id}")
    @Operation(summary = "Undeletes a presupuestoMovimiento by id", description = "Undeletes a presupuestoMovimiento by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "PresupuestoMovimiento not found"),
            @ApiResponse(responseCode = "409", description = "Conflict - Cannot undelete PresupuestoMovimiento due to conflict")
    })
    public ResponseEntity unDelete(@Parameter(description ="Id of the presupuestoMovimiento") @PathVariable("id") String idPresupuestoMovimiento) {
        try {
            return  new ResponseEntity(presupuestoMovimientoService.unDelete(Integer.parseInt(idPresupuestoMovimiento)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        } catch (EntityCannotBeUndeletedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
