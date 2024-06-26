package com.dev.gestorgastos.web.controller;

import com.dev.gestorgastos.domain.dto.ProveedorDto;
import com.dev.gestorgastos.domain.service.ProveedorService;
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
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/id/{id}")
    @Operation(summary = "Get by Id", description = "Get a Proveedor by its idProveedor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Proveedor not found")
    })
    public ResponseEntity<ProveedorDto> getByIdProveedor(@Parameter(description = "Id of the proveedor") @PathVariable("id")  String idProveedor) {
        return proveedorService.getByIdProveedor(Integer.parseInt(idProveedor))
                .map(proveedor-> new ResponseEntity<ProveedorDto>(proveedor, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/active/nombre/{nombre}")
    @Operation(summary = "Get active by nombreProveedor", description = "Get a list of active Proveedores by their nombreProveedor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Proveedor found")
    })
    public ResponseEntity<List<ProveedorDto>> getActivosByNombreProveedor(@Parameter(description ="Nombre of the proveedor") @PathVariable("nombre") String nombreProveedor) {
        return proveedorService.getActiveByNombreProveedorContains(nombreProveedor)
                .map(proveedores -> {
                    if (proveedores.isEmpty()) {
                        return new ResponseEntity<List<ProveedorDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(proveedores, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/unaactive/nombre/{nombre}")
    @Operation(summary = "Get unactive by nombreProveedor", description = "Get a list of unactive Proveedores by their nombreProveedor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Proveedor found")
    })
    public ResponseEntity<List<ProveedorDto>> getInactivosByNombreProveedor(@Parameter(description ="Nombre of the proveedor") @PathVariable("nombre") String nombreProveedor) {
        return proveedorService.getUnactiveByNombreProveedorContains(nombreProveedor)
                .map(proveedores -> {
                    if (proveedores.isEmpty()) {
                        return new ResponseEntity<List<ProveedorDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(proveedores, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/active")
    @Operation(summary = "Get all active proveedores data", description = "Get the list of all active Proveedores")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Proveedor found")
    })
    public ResponseEntity<List<ProveedorDto>> getAllActive() {
        return proveedorService.getAll()
                .map(proveedores -> {
                    if (proveedores.isEmpty()) {
                        return new ResponseEntity<List<ProveedorDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(proveedores, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/unactive")
    @Operation(summary = "Get all unactive proveedores data", description = "Get the list of all unactive Proveedores")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Proveedor found")
    })
    public ResponseEntity<List<ProveedorDto>> getAllUnactive() {
        return proveedorService.getAllDeleted()
                .map(proveedores -> {
                    if (proveedores.isEmpty()) {
                        return new ResponseEntity<List<ProveedorDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(proveedores, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @Operation(summary = "Saves a proveedor", description = "Saves the data of a proveedor")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<ProveedorDto> save(@RequestBody ProveedorDto proveedorDto) {
        return new ResponseEntity<>(proveedorService.save(proveedorDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletes a proveedor by id", description = "Deletes a proveedor by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Proveedor not found"),
            @ApiResponse(responseCode = "409", description = "Conflict - Cannot delete Proveedor due to a conflict")
    })
    public ResponseEntity delete(@Parameter(description ="Id of the proveedor") @PathVariable("id") String proveedorId) {
        try {
            boolean deleted = proveedorService.delete(Integer.parseInt(proveedorId));
            return new ResponseEntity<>(deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        } catch (EntityCannotBeDeletedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/undelete/{id}")
    @Operation(summary = "Undeletes a proveedor by id", description = "Undeletes a proveedor by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Proveedor not found"),
            @ApiResponse(responseCode = "409", description = "Conflict - Cannot undelete Proveedor due to a conflict")
    })
    public ResponseEntity unDelete(@Parameter(description ="Id of the proveedor") @PathVariable("id") String proveedorId) {
        try {
            return  new ResponseEntity(proveedorService.unDelete(Integer.parseInt(proveedorId)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        } catch (EntityCannotBeUndeletedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
