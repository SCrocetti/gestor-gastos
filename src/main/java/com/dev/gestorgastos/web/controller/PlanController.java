package com.dev.gestorgastos.web.controller;

import com.dev.gestorgastos.domain.dto.PlanDto;
import com.dev.gestorgastos.domain.service.PlanService;
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
import java.util.List;

@RestController
// se le indica el path que le corresponde a este controller
@RequestMapping("/planes")
public class PlanController {

    @Autowired
    private PlanService planService;

    @GetMapping("/id/{id}")
    @Operation(summary = "Get by Id", description = "Get a Plan by its idPlan")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Plan not found")
    })
    public ResponseEntity<PlanDto> getByIdPlan(@Parameter(description = "Id of the plan") @PathVariable("id")  String idPlan) {
        return planService.getByIdPlan(Integer.parseInt(idPlan))
                .map(plan-> new ResponseEntity<PlanDto>(plan, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/active/descripcion/{descripcion}")
    @Operation(summary = "Get active Plans by descripcion", description = "Get a list of active Plans by their descripcion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Plan found")
    })
    public ResponseEntity<List<PlanDto>> getActivosByDescripcion(@Parameter(description ="Descripcion of the plan") @PathVariable("descripcion") String descripcion) {
        return planService.getActiveByDescripcion(descripcion)
                .map(planes -> {
                    if (planes.isEmpty()) {
                        return new ResponseEntity<List<PlanDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(planes, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/unaactive/descripcion/{descripcion}")
    @Operation(summary = "Get unactive Plans by descripcion", description = "Get a list of unactive Plans by their descripcion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Plan found")
    })
    public ResponseEntity<List<PlanDto>> getInctivosByDescripcion(@Parameter(description ="Descripcion of the plan") @PathVariable("descripcion") String descripcion) {
        return planService.getUnactiveByDescripcion(descripcion)
                .map(planes -> {
                    if (planes.isEmpty()) {
                        return new ResponseEntity<List<PlanDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(planes, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/fechaInicioBetween")
    @Operation(summary = "Get all active plans with fechaInicio between two dates", description = "Get the list of all active plans with fechaInicio between two dates")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Plan found")
    })
    public ResponseEntity<List<PlanDto>> getActiveFechaInicioBetween(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return planService.getActiveByFechaInicioBetween(startDate,endDate)
                .map(planes -> {
                    if (planes.isEmpty()) {
                        return new ResponseEntity<List<PlanDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(planes, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/fechaFinBetween")
    @Operation(summary = "Get all active plans with fechaFin between two dates", description = "Get the list of all active plans with fechaFin between two dates")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "No Plan found")
    })
    public ResponseEntity<?> getActiveFechaFinBetween(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            return planService.getActiveByFechaFinBetween(startDate,endDate)
                    .map(planes -> {
                        if (planes.isEmpty()) {
                            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                        } else {
                            return new ResponseEntity<>(planes, HttpStatus.OK);
                        }
                    })
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/active")
    @Operation(summary = "Get all active planes data", description = "Get the list of all active Planes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Plan found")
    })
    public ResponseEntity<List<PlanDto>> getAllActive() {
        return planService.getAll()
                .map(planes -> {
                    if (planes.isEmpty()) {
                        return new ResponseEntity<List<PlanDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(planes, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/unactive")
    @Operation(summary = "Get all unactive planes data", description = "Get the list of all unactive Planes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No Plan found")
    })
    public ResponseEntity<List<PlanDto>> getAllUnactive() {
        return planService.getAllDeleted()
                .map(planes -> {
                    if (planes.isEmpty()) {
                        return new ResponseEntity<List<PlanDto>>(HttpStatus.NOT_FOUND);
                    } else {
                        return new ResponseEntity<>(planes, HttpStatus.OK);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @Operation(summary = "Saves a plan", description = "Saves the data of a plan")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<?> save(@RequestBody PlanDto planDto) {
        try {
            return new ResponseEntity<>(planService.save(planDto), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletes a plan by id", description = "Deletes a plan by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Plan not found"),
            @ApiResponse(responseCode = "409", description = "Conflict - Cannot delete Plan due to a conflict")
    })
    public ResponseEntity delete(@Parameter(description ="Id of the plan") @PathVariable("id") String idPlan) {
        try {
            return new ResponseEntity<>(planService.delete(Integer.parseInt(idPlan)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        } catch (EntityCannotBeDeletedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/undelete/{id}")
    @Operation(summary = "Undeletes a plan by id", description = "Undeletes a plan by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Plan not found"),
            @ApiResponse(responseCode = "409", description = "Conflict - Cannot undelete Plan due to a conflict")
    })
    public ResponseEntity unDelete(@Parameter(description ="Id of the plan") @PathVariable("id") String idPlan) {
        try {
            return new ResponseEntity(planService.unDelete(Integer.parseInt(idPlan)) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        } catch (EntityCannotBeUndeletedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
