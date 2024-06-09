package com.dev.gestorgastos.web.controller;

import com.dev.gestorgastos.domain.dto.PresupuestoTransaccionDto;
import com.dev.gestorgastos.domain.service.PresupuestoTransaccionService;
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
@RequestMapping("/presupuestosTransacciones")
public class PresupuestoTransaccionController {

    @Autowired
    private PresupuestoTransaccionService presupuestoTransaccionService;

    @GetMapping("/id/{id}")
    @Operation(summary = "Get by Id", description = "Get a PresupuestoTransaccion by its idPresupuestoTransaccion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "PresupuestoTransaccion not found")
    })
    public ResponseEntity<PresupuestoTransaccionDto> getByIdPresupuestoTransaccion(@Parameter(description = "Id of the presupuestoTransaccion") @PathVariable("id")  String idPresupuestoTransaccion) {
        return presupuestoTransaccionService.getByIdPresupuestoTransaccion(Integer.parseInt(idPresupuestoTransaccion))
                .map(presupuesto-> new ResponseEntity<PresupuestoTransaccionDto>(presupuesto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/active/nombreTipoTransaccion/{nombreTipoTransaccion}")
    @Operation(summary = "Get active by nombreTipoTransaccion", description = "Get a list of active PresupuestosTransacciones by their nombreTipoTransaccion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No PresupuestoTransaccion found")
    })
    public ResponseEntity<List<PresupuestoTransaccionDto>> getActivosByNombreTipoTransaccion(@Parameter(description ="Nombre of the tipoTransaccion of the presupuestoTransaccion") @PathVariable("nombre") String nombreTipoTransaccion) {
        return presupuestoTransaccionService.getActivosByNombreTipoTransaccion(nombreTipoTransaccion)
                .map(presupuestos -> {
                    if (presupuestos.isEmpty()) {
                        return new ResponseEntity<List<PresupuestoTransaccionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/unactive/nombreTipoTransaccion/{nombreTipoTransaccion}")
    @Operation(summary = "Get unactive by nombreTipoTransaccion", description = "Get a list of unactive PresupuestosTransacciones by their nombreTipoTransaccion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Presupuesto found")
    })
    public ResponseEntity<List<PresupuestoTransaccionDto>> getInactivosByNombreTipoTransaccion(@Parameter(description ="Nombre of the tipoTransaccion of the presupuestoTransaccion") @PathVariable("nombre") String nombreTipoTransaccion) {
        return presupuestoTransaccionService.getInactivosByNombreTipoTransaccion(nombreTipoTransaccion)
                .map(presupuestos -> {
                    if (presupuestos.isEmpty()) {
                        return new ResponseEntity<List<PresupuestoTransaccionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/active/tipoTransaccion/{idTipoTransaccion}")
    @Operation(summary = "Get active by idTipoTransaccion", description = "Get a list of active PresupuestosTransacciones by their idTipoTransaccion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No PresupuestoTransaccion found")
    })
    public ResponseEntity<List<PresupuestoTransaccionDto>> getActivosByIdTipoTransaccion(@Parameter(description ="Id of the tipoTransaccion of the presupuestoTransaccion") @PathVariable("idTipoTransaccion") String idTipoTransaccion) {
        return presupuestoTransaccionService.getActivosByIdTipoTransaccion(Integer.parseInt(idTipoTransaccion))
                .map(presupuestos -> {
                    if (presupuestos.isEmpty()) {
                        return new ResponseEntity<List<PresupuestoTransaccionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/active/cuenta/{idCuentaEgreso}")
    @Operation(summary = "Get active by idCuentaEgreso", description = "Get a list of active PresupuestosTransacciones by their idCuentaEgreso")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No PresupuestoTransaccion found")
    })
    public ResponseEntity<List<PresupuestoTransaccionDto>> getActivosByIdCuentaEgreso(@Parameter(description ="Id of the cuentaEgreso of the presupuestoTransaccion") @PathVariable("idCuentaEgreso") String idCuentaEgreso) {
        return presupuestoTransaccionService.getActivosByIdCuentaEgreso(Integer.parseInt(idCuentaEgreso))
                .map(presupuestos -> {
                    if (presupuestos.isEmpty()) {
                        return new ResponseEntity<List<PresupuestoTransaccionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/active/cuenta/{idCuentaIngreso}")
    @Operation(summary = "Get active by idCuentaIngreso", description = "Get a list of active PresupuestosTransacciones by their idCuentaIngreso")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No PresupuestoTransaccion found")
    })
    public ResponseEntity<List<PresupuestoTransaccionDto>> getActivosByIdCuentaIngreso(@Parameter(description ="Id of the cuentaIngreso of the presupuestoTransaccion") @PathVariable("idCuentaIngreso") String idCuentaIngreso) {
        return presupuestoTransaccionService.getActivosByIdCuentaIngreso(Integer.parseInt(idCuentaIngreso))
                .map(presupuestos -> {
                    if (presupuestos.isEmpty()) {
                        return new ResponseEntity<List<PresupuestoTransaccionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/active/plan/{idPlan}")
    @Operation(summary = "Get active by idPlan", description = "Get a list of active PresupuestosTransacciones by their idPlan")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No PresupuestoTransaccion found")
    })
    public ResponseEntity<List<PresupuestoTransaccionDto>> getActivosByIdPlan(@Parameter(description ="Id of the plan of the presupuestoTransaccion") @PathVariable("idPlan") String idPlan) {
        return presupuestoTransaccionService.getActivosByIdPlan(Integer.parseInt(idPlan))
                .map(presupuestos -> {
                    if (presupuestos.isEmpty()) {
                        return new ResponseEntity<List<PresupuestoTransaccionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/active")
    @Operation(summary = "Get all active presupuestosTransacciones data", description = "Get the list of all active PresupuestosTransacciones")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No PresupuestoTransaccion found")
    })
    public ResponseEntity<List<PresupuestoTransaccionDto>> getAllActive() {
        return presupuestoTransaccionService.getAll()
                .map(presupuestos -> {
                    if (presupuestos.isEmpty()) {
                        return new ResponseEntity<List<PresupuestoTransaccionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/unactive")
    @Operation(summary = "Get all unactive presupuestosTransacciones data", description = "Get the list of all unactive PresupuestosTransacciones")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No PresupuestoTransacciones found")
    })
    public ResponseEntity<List<PresupuestoTransaccionDto>> getAllUnactive() {
        return presupuestoTransaccionService.getAllDeleted()
                .map(presupuestos -> {
                    if (presupuestos.isEmpty()) {
                        return new ResponseEntity<List<PresupuestoTransaccionDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @Operation(summary = "Saves a presupuestoTransaccion", description = "Saves the data of a presupuestoTransaccion")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<?> save(@RequestBody PresupuestoTransaccionDto presupuestoTransaccionDto) {
        try {
            return new ResponseEntity<>(presupuestoTransaccionService.save(presupuestoTransaccionDto), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletes a presupuestoTransaccion by id", description = "Deletes a presupuestoTransaccion by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "PresupuestoTransaccion not found"),
            @ApiResponse(responseCode = "409", description = "Conflict - Cannot delete PresupuestoTransaccion due to conflict")
    })
    public ResponseEntity delete(@Parameter(description ="Id of the presupuestoTransaccion") @PathVariable("id") String idPresupuestoTransaccion) {
        try {
            return  new ResponseEntity(presupuestoTransaccionService.delete(Integer.parseInt(idPresupuestoTransaccion)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        } catch (EntityCannotBeDeletedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/undelete/{id}")
    @Operation(summary = "Undeletes a presupuestoTransaccion by id", description = "Undeletes a presupuestoTransaccion by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "PresupuestoTransaccion not found"),
            @ApiResponse(responseCode = "409", description = "Conflict - Cannot undelete PresupuestoTransaccion due to conflict")
    })
    public ResponseEntity unDelete(@Parameter(description ="Id of the presupuestoTransaccion") @PathVariable("id") String idPresupuestoTransaccion) {
        try {
            return  new ResponseEntity(presupuestoTransaccionService.unDelete(Integer.parseInt(idPresupuestoTransaccion)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        } catch (EntityCannotBeUndeletedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
