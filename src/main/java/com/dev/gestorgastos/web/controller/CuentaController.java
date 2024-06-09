package com.dev.gestorgastos.web.controller;

import com.dev.gestorgastos.domain.dto.CuentaDto;
import com.dev.gestorgastos.domain.dto.MovimientoDto;
import com.dev.gestorgastos.domain.service.CuentaService;
import com.dev.gestorgastos.persistence.exception.EntityCannotBeDeletedException;
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
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/id/{id}")
    @Operation(summary = "Get by Id", description = "Get a Cuenta by its idCuenta")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Cuenta not found")
    })
    public ResponseEntity<CuentaDto> getByIdCuenta(@Parameter(description = "Id of the cuenta") @PathVariable("id")  String idCuenta) {
        return cuentaService.getByIdCuenta(Integer.parseInt(idCuenta))
                .map(cuenta-> new ResponseEntity<CuentaDto>(cuenta, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/active/descripcion/{descripcion}")
    @Operation(summary = "Get active by descripcion", description = "Get a list of active Cuentas by their descripcion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Cuenta found")
    })
    public ResponseEntity<List<CuentaDto>> getActivosByDescripcion(@Parameter(description ="Descripcion of the cuenta") @PathVariable("descripcion") String descripcion) {
        return cuentaService.getActiveByDescripcion(descripcion)
                .map(cuentas -> {
                    if (cuentas.isEmpty()) {
                        return new ResponseEntity<List<CuentaDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(cuentas, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/unaactive/descripcion/{descripcion}")
    @Operation(summary = "Get unactive by descripcion", description = "Get a list of unactive Cuentas by their descripcion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Proveedor found")
    })
    public ResponseEntity<List<CuentaDto>> getInctivosByDescripcion(@Parameter(description ="Descripcion of the cuenta") @PathVariable("descripcion") String descripcion) {
        return cuentaService.getUnactiveByDescripcion(descripcion)
                .map(cuentas -> {
                    if (cuentas.isEmpty()) {
                        return new ResponseEntity<List<CuentaDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(cuentas, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/active/denominacion/{idDenominacion}")
    @Operation(summary = "Get active by its idDenominacion", description = "Get a list of active Cuentas by their idDenominacion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Cuenta found")
    })
    public ResponseEntity<List<CuentaDto>> getActivosByIdDenominacion(@Parameter(description ="IdDenominacion of the cuenta") @PathVariable("idDenominacion") String idDenominacion) {
        return cuentaService.getActiveByIdDenominacion(Integer.parseInt(idDenominacion))
                .map(cuentas -> {
                    if (cuentas.isEmpty()) {
                        return new ResponseEntity<List<CuentaDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(cuentas, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/active/proveedor/{idProveedor}")
    @Operation(summary = "Get active by its idProveedor", description = "Get a list of active Cuentas by their idProveedor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Cuenta found")
    })
    public ResponseEntity<List<CuentaDto>> getActivosByIdProveedor(@Parameter(description ="IdProveedor of the cuenta") @PathVariable("idProveedor") String idProveedor) {
        return cuentaService.getActiveByIdProveedor(Integer.parseInt(idProveedor))
                .map(cuentas -> {
                    if (cuentas.isEmpty()) {
                        return new ResponseEntity<List<CuentaDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(cuentas, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/active/persona/{idPersona}")
    @Operation(summary = "Get active by its idPersona", description = "Get a list of active Cuentas by their idPersona")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Cuenta found")
    })
    public ResponseEntity<List<CuentaDto>> getActivosByIdPersona(@Parameter(description ="IdPersona of the cuenta") @PathVariable("idPersona") String idPersona) {
        return cuentaService.getActiveByIdPersona(Integer.parseInt(idPersona))
                .map(cuentas -> {
                    if (cuentas.isEmpty()) {
                        return new ResponseEntity<List<CuentaDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(cuentas, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/active")
    @Operation(summary = "Get all active cuentas data", description = "Get the list of all active Cuentas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Cuenta found")
    })
    public ResponseEntity<List<CuentaDto>> getAllActive() {
        return cuentaService.getAll()
                .map(cuentas -> {
                    if (cuentas.isEmpty()) {
                        return new ResponseEntity<List<CuentaDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(cuentas, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/unactive")
    @Operation(summary = "Get all unactive cuentas data", description = "Get the list of all unactive Cuentas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Cuenta found")
    })
    public ResponseEntity<List<CuentaDto>> getAllUnactive() {
        return cuentaService.getAllDeleted()
                .map(cuentas -> {
                    if (cuentas.isEmpty()) {
                        return new ResponseEntity<List<CuentaDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(cuentas, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @Operation(summary = "Saves a cuenta", description = "Saves the data of a cuenta")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<?> save(@RequestBody CuentaDto cuentaDto) {
        try {
            return ResponseEntity.status(201).body(cuentaService.save(cuentaDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletes a cuenta by id", description = "Deletes a cuenta by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Cuenta not found"),
            @ApiResponse(responseCode = "409", description = "Conflict - Cannot delete Cuenta with active asociated Information")
    })
    public ResponseEntity delete(@Parameter(description ="Id of the cuenta") @PathVariable("id") String idCuenta) {
        try {
            boolean deleted = cuentaService.delete(Integer.parseInt(idCuenta));
            return new ResponseEntity<>(deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        } catch (EntityCannotBeDeletedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/undelete/{id}")
    @Operation(summary = "Undeletes a cuenta by id", description = "Undeletes a cuenta by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Cuenta not found")
    })
    public ResponseEntity unDelete(@Parameter(description ="Id of the cuenta") @PathVariable("id") String idCuenta) {
        return  new ResponseEntity(cuentaService.unDelete(Integer.parseInt(idCuenta)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
