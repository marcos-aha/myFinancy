package br.com.app.myFinancy.controller;

import br.com.app.myFinancy.model.EnergyBill;
import br.com.app.myFinancy.model.EnergyFront;
import br.com.app.myFinancy.service.EnergyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/energy")
@AllArgsConstructor
public class EnergyController {

    private final EnergyService service;

    @PostMapping
    public ResponseEntity<EnergyBill> create(@RequestBody @Valid EnergyFront energy) {
        return ResponseEntity.status(HttpStatus.OK).body(service.create(energy));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnergyBill> update(@RequestBody @Valid EnergyFront energy, @PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(energy,id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable UUID id){
        service.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnergyFront> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/total/{id}")
    public ResponseEntity<List<EnergyFront>> findAll(@PathVariable UUID id){
        List<EnergyFront> list =service.findALl(id).stream().map(energy -> {
            EnergyFront energyFront = new EnergyFront();
            BeanUtils.copyProperties(energy, energyFront);
            energyFront.setUsers(id);
            return energyFront;
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
