package com.dev.gestorgastos.web.controller;

import com.dev.gestorgastos.domain.PersonaDto;
import com.dev.gestorgastos.domain.service.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
// se le indica el path que le corresponde a este controller
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/id/{id}")
    public ResponseEntity<PersonaDto> getByIdPersona(@PathVariable("id") Integer idPersona) {
        return personaService.getByIdPersona(idPersona)
                .map(persona-> new ResponseEntity<PersonaDto>(persona, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/documento/{documento}")
    public ResponseEntity<PersonaDto>  getByNumeroDocumento(@PathVariable("documento") String numeroDocumento) {
        return personaService.getByNumeroDocumento(numeroDocumento)
                .map(persona-> new ResponseEntity<PersonaDto>(persona, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/all")
    public ResponseEntity<List<PersonaDto>> getAll() {
        return new ResponseEntity<> (personaService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
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
    public ResponseEntity delete(@PathVariable("id") Integer personaId) {
        return  new ResponseEntity(personaService.delete(personaId) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
