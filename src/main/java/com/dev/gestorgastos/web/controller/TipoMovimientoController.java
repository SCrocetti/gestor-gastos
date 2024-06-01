package com.dev.gestorgastos.web.controller;

import com.dev.gestorgastos.domain.ProveedorDto;
import com.dev.gestorgastos.domain.TipoMovimientoDto;
import com.dev.gestorgastos.domain.service.ProveedorService;
import com.dev.gestorgastos.domain.service.TipoMovimientoService;
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
@RequestMapping("/tiposMovimiento")
public class TipoMovimientoController {

    @Autowired
    private TipoMovimientoService tipoMovimientoService;

    @GetMapping("/id/{id}")
    @Operation(summary = "Get by Id", description = "Get a TipoMovimiento by its idTipoMovimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "TipoMovimiento not found")
    })
    public ResponseEntity<TipoMovimientoDto> getByIdTipoMovimiento(@Parameter(description = "Id of the tipoMovimiento") @PathVariable("id")  String idTipoMovimiento) {
        return tipoMovimientoService.getByIdTipoMovimiento(Integer.parseInt(idTipoMovimiento))
                .map(tipoMovimiento-> new ResponseEntity<TipoMovimientoDto>(tipoMovimiento, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/active/nombre/{nombre}")
    @Operation(summary = "Get active by nombreTipoMovimiento", description = "Get a list of active TiposMovimiento by their nombreTipoMovimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No TipoMovimiento found")
    })
    public ResponseEntity<List<TipoMovimientoDto>> getActivosByNombreTipoMovimientoDto(@Parameter(description ="Nombre of the tipoMovimiento") @PathVariable("nombre") String nombreTipoMovimiento) {
        return tipoMovimientoService.getActiveByNombreTipoMocimientoontains(nombreTipoMovimiento)
                .map(tiposMovimiento -> {
                    if (tiposMovimiento.isEmpty()) {
                        return new ResponseEntity<List<TipoMovimientoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(tiposMovimiento, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/unaactive/nombre/{nombre}")
    @Operation(summary = "Get unactive by nombreTipoMovimiento", description = "Get a list of unactive TiposMovimiento by their nombreTipoMovimiento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No TipoMovimiento found")
    })
    public ResponseEntity<List<TipoMovimientoDto>> getInactivosByNombreTipoMovimiento(@Parameter(description ="Nombre of the tipoMovimiento") @PathVariable("nombre") String nombreTipoMovimiento) {
        return tipoMovimientoService.getUnactiveByNombreTipoMocimientoontains(nombreTipoMovimiento)
                .map(tiposMovimiento -> {
                    if (tiposMovimiento.isEmpty()) {
                        return new ResponseEntity<List<TipoMovimientoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(tiposMovimiento, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/active")
    @Operation(summary = "Get all active tiposMovimiento data", description = "Get the list of all active TiposMovimiento")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<TipoMovimientoDto>> getAllActive() {
        return tipoMovimientoService.getAll()
                .map(tiposMovimiento -> {
                    if (tiposMovimiento.isEmpty()) {
                        return new ResponseEntity<List<TipoMovimientoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(tiposMovimiento, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/unactive")
    @Operation(summary = "Get all unactive tiposMovimiento data", description = "Get the list of all unactive TiposMovimiento")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<TipoMovimientoDto>> getAllUnactive() {
        return tipoMovimientoService.getAllDeleted()
                .map(tiposMovimiento -> {
                    if (tiposMovimiento.isEmpty()) {
                        return new ResponseEntity<List<TipoMovimientoDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(tiposMovimiento, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @Operation(summary = "Saves a tipoMovimiento", description = "Saves the data of a tipoMovimiento")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<TipoMovimientoDto> save(@RequestBody TipoMovimientoDto tipoMovimientoDto) {
        return new ResponseEntity<>(tipoMovimientoService.save(tipoMovimientoDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletes a tipoMovimiento by id", description = "Deletes a tipoMovimiento by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "TipoMovimiento not found")
    })
    public ResponseEntity delete(@Parameter(description ="Id of the tipoMovimiento") @PathVariable("id") String idTipoMovimiento) {
        return  new ResponseEntity(tipoMovimientoService.delete(Integer.parseInt(idTipoMovimiento)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/undelete/{id}")
    @Operation(summary = "Undeletes a tipoMovimiento by id", description = "Undeletes a tipoMovimiento by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "TipoMovimiento not found")
    })
    public ResponseEntity unDelete(@Parameter(description ="Id of the tipoMovimiento") @PathVariable("id") String idTipoMovimiento) {
        return  new ResponseEntity(tipoMovimientoService.unDelete(Integer.parseInt(idTipoMovimiento)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
