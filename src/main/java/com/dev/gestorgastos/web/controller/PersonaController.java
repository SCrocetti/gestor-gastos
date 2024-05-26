package com.dev.gestorgastos.web.controller;

import com.dev.gestorgastos.domain.PersonaDto;
import com.dev.gestorgastos.domain.service.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.*;
import java.util.List;
import java.util.Optional;

@RestController
// se le indica el path que le corresponde a este controller
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/id/{id}")
    @ApiOperation("Search a persona with an id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Persona not found")
    })
    public ResponseEntity<PersonaDto> getByIdPersona(@ApiParam(value ="Id of the persona",required = true,example = "7") @PathVariable("id")  Integer idPersona) {
        return personaService.getByIdPersona(idPersona)
                .map(persona-> new ResponseEntity<PersonaDto>(persona, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/documento/{documento}")
    @ApiOperation("Search a persona with a documento")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Persona not found")
    })
    public ResponseEntity<PersonaDto>  getByNumeroDocumento(@ApiParam(value ="Documento of the persona",required = true,example = "666789") @PathVariable("documento") String numeroDocumento) {
        return personaService.getByNumeroDocumento(numeroDocumento)
                .map(persona-> new ResponseEntity<PersonaDto>(persona, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/all")
    @ApiOperation("Get all personas data")
    @ApiResponse(code=200, message = "OK")
    public ResponseEntity<List<PersonaDto>> getAll() {
        return new ResponseEntity<> (personaService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    @ApiOperation("Saves a new persona")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 409, message = "Conflict - Persona with the same numeroDocumento already exists")
    })
    public ResponseEntity<PersonaDto> save(@RequestBody PersonaDto personaDto) {
        Optional<PersonaDto> existingPersona = personaService.getByNumeroDocumento(personaDto.getNumeroDocumento());

        if (existingPersona.isPresent()) {
            // If a persona with the same numeroDocumento already exists, return a conflict response
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(personaService.save(personaDto), HttpStatus.CREATED);
        }
    }
    @DeleteMapping("/delete/{id}")
    @ApiOperation("Deletes a persona by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Persona not found")
    })
    public ResponseEntity delete(@ApiParam(value ="Id of the persona",required = true,example = "9") @PathVariable("id") Integer personaId) {
        return  new ResponseEntity(personaService.delete(personaId) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
