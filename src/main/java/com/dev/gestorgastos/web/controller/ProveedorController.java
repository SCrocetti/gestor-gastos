package com.dev.gestorgastos.web.controller;

import com.dev.gestorgastos.domain.DenominacionDto;
import com.dev.gestorgastos.domain.ProveedorDto;
import com.dev.gestorgastos.domain.service.DenominacionService;
import com.dev.gestorgastos.domain.service.ProveedorService;
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

    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "Get by nombreProveedor", description = "Get a list of Proveedores by their nombreProveedor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Proveedor found")
    })
    public ResponseEntity<List<ProveedorDto>> getByNombreProveedor(@Parameter(description ="Nombre of the proveedor") @PathVariable("nombre") String nombreProveedor) {
        return proveedorService.getByNombreProveedorContains(nombreProveedor)
                .map(proveedores -> {
                    if (proveedores.isEmpty()) {
                        return new ResponseEntity<List<ProveedorDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(proveedores, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/all")
    @Operation(summary = "Get all proveedores data", description = "Get the list of all Proveedores")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<ProveedorDto>> getAll() {
        return new ResponseEntity<> (proveedorService.getAll(), HttpStatus.OK);
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
            @ApiResponse(responseCode = "404", description = "Proveedor not found")
    })
    public ResponseEntity delete(@Parameter(description ="Id of the proveedor") @PathVariable("id") String proveedorId) {
        return  new ResponseEntity(proveedorService.delete(Integer.parseInt(proveedorId)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
