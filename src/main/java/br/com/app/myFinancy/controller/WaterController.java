package br.com.app.myFinancy.controller;

import br.com.app.myFinancy.model.EnergyFront;
import br.com.app.myFinancy.model.WaterBill;
import br.com.app.myFinancy.model.WaterFront;
import br.com.app.myFinancy.service.WaterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/water")
@AllArgsConstructor
public class WaterController {

    private final WaterService service;

    @PostMapping
    public ResponseEntity<WaterBill> create(@RequestBody @Valid WaterBill water) {
        return ResponseEntity.status(HttpStatus.OK).body(service.create(water));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WaterBill> update(@RequestBody @Valid WaterBill water, @PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(water,id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable UUID id){
        service.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaterBill> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/total/{id}")
    public ResponseEntity<List<WaterFront>> findAll(@PathVariable UUID id){
        List<WaterFront> list =service.findALl(id).stream().map(water -> {
            WaterFront waterFront = new WaterFront();
            BeanUtils.copyProperties(water, waterFront);
            waterFront.setUsers(id);
            return waterFront;
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
