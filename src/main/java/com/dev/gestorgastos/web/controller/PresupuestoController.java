package com.dev.gestorgastos.web.controller;

import com.dev.gestorgastos.domain.dto.PresupuestoDto;
import com.dev.gestorgastos.domain.service.PresupuestoService;
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
@RequestMapping("/presupuestos")
public class PresupuestoController {

    @Autowired
    private PresupuestoService presupuestoService;

    @GetMapping("/id/{id}")
    @Operation(summary = "Get by Id", description = "Get a Presupuesto by its idPresupuesto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Presupuesto not found")
    })
    public ResponseEntity<PresupuestoDto> getByIdPresupuesto(@Parameter(description = "Id of the presupuesto") @PathVariable("id")  String idPresupuesto) {
        return presupuestoService.getByIdPresupuesto(Integer.parseInt(idPresupuesto))
                .map(presupuesto-> new ResponseEntity<PresupuestoDto>(presupuesto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/active/nombreTipoMovimiento/{nombreTipoMovimiento}")
    @Operation(summary = "Get active by nombreTipoMovimiento", description = "Get a list of active Presupuestos by their nombreTipoMovimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Presupuesto found")
    })
    public ResponseEntity<List<PresupuestoDto>> getActivosByNombreTipoMovimiento(@Parameter(description ="Nombre of the tipoMovimiento of the presupuesto") @PathVariable("nombre") String nombreTipoMovimiento) {
        return presupuestoService.getActivosByNombreTipoMovimiento(nombreTipoMovimiento)
                .map(presupuestos -> {
                    if (presupuestos.isEmpty()) {
                        return new ResponseEntity<List<PresupuestoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/unactive/nombreTipoMovimiento/{nombreTipoMovimiento}")
    @Operation(summary = "Get unactive by nombreTipoMovimiento", description = "Get a list of unactive Presupuestos by their nombreTipoMovimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Presupuesto found")
    })
    public ResponseEntity<List<PresupuestoDto>> getInactivosByNombreTipoMovimiento(@Parameter(description ="Nombre of the tipoMovimiento of the presupuesto") @PathVariable("nombre") String nombreTipoMovimiento) {
        return presupuestoService.getInactivosByNombreTipoMovimiento(nombreTipoMovimiento)
                .map(presupuestos -> {
                    if (presupuestos.isEmpty()) {
                        return new ResponseEntity<List<PresupuestoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/active/tipoMovimiento/{idTipoMovimiento}")
    @Operation(summary = "Get active by idTipoMovimiento", description = "Get a list of active Presupuestos by their idTipoMovimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Presupuesto found")
    })
    public ResponseEntity<List<PresupuestoDto>> getActivosByIdTipoMovimiento(@Parameter(description ="Id of the tipoMovimiento of the presupuesto") @PathVariable("idTipoMovimiento") String idTipoMovimiento) {
        return presupuestoService.getActivosByIdTipoMovimiento(Integer.parseInt(idTipoMovimiento))
                .map(presupuestos -> {
                    if (presupuestos.isEmpty()) {
                        return new ResponseEntity<List<PresupuestoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/active/cuenta/{idCuenta}")
    @Operation(summary = "Get active by idCuenta", description = "Get a list of active Presupuestos by their idCuenta")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Presupuesto found")
    })
    public ResponseEntity<List<PresupuestoDto>> getActivosByIdCuenta(@Parameter(description ="Id of the cuenta of the presupuesto") @PathVariable("idCuenta") String idCuenta) {
        return presupuestoService.getActivosByIdCuenta(Integer.parseInt(idCuenta))
                .map(presupuestos -> {
                    if (presupuestos.isEmpty()) {
                        return new ResponseEntity<List<PresupuestoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/active/plan/{idPlan}")
    @Operation(summary = "Get active by idPlan", description = "Get a list of active Presupuestos by their idPlan")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Presupuesto found")
    })
    public ResponseEntity<List<PresupuestoDto>> getActivosByIdPlan(@Parameter(description ="Id of the plan of the presupuesto") @PathVariable("idPlan") String idPlan) {
        return presupuestoService.getActivosByIdPlan(Integer.parseInt(idPlan))
                .map(presupuestos -> {
                    if (presupuestos.isEmpty()) {
                        return new ResponseEntity<List<PresupuestoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/active")
    @Operation(summary = "Get all active presupuestos data", description = "Get the list of all active Presupuestos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Presupuesto found")
    })
    public ResponseEntity<List<PresupuestoDto>> getAllActive() {
        return presupuestoService.getAll()
                .map(presupuestos -> {
                    if (presupuestos.isEmpty()) {
                        return new ResponseEntity<List<PresupuestoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/unactive")
    @Operation(summary = "Get all unactive presupuestos data", description = "Get the list of all unactive Presupuestos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Presupuesto found")
    })
    public ResponseEntity<List<PresupuestoDto>> getAllUnactive() {
        return presupuestoService.getAllDeleted()
                .map(presupuestos -> {
                    if (presupuestos.isEmpty()) {
                        return new ResponseEntity<List<PresupuestoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(presupuestos, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @Operation(summary = "Saves a presupuesto", description = "Saves the data of a presupuesto")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<PresupuestoDto> save(@RequestBody PresupuestoDto presupuestoDto) {
        return new ResponseEntity<>(presupuestoService.save(presupuestoDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletes a presupuesto by id", description = "Deletes a presupuesto by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Presupuesto not found")
    })
    public ResponseEntity delete(@Parameter(description ="Id of the presupuesto") @PathVariable("id") String idPresupuesto) {
        return  new ResponseEntity(presupuestoService.delete(Integer.parseInt(idPresupuesto)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/undelete/{id}")
    @Operation(summary = "Undeletes a presupuesto by id", description = "Undeletes a presupuesto by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Presupuesto not found")
    })
    public ResponseEntity unDelete(@Parameter(description ="Id of the presupuesto") @PathVariable("id") String idPresupuesto) {
        return  new ResponseEntity(presupuestoService.unDelete(Integer.parseInt(idPresupuesto)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
