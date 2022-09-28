package br.com.app.myFinancy.controller;

import br.com.app.myFinancy.model.EnergyFront;
import br.com.app.myFinancy.model.Internet;
import br.com.app.myFinancy.model.InternetFront;
import br.com.app.myFinancy.service.InternetService;
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
@RequestMapping("/internet")
@AllArgsConstructor
public class InternetController {

    private final InternetService service;

    @PostMapping
    public ResponseEntity<Internet> create(@RequestBody @Valid Internet internet) {
        return ResponseEntity.status(HttpStatus.OK).body(service.create(internet));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Internet> update(@RequestBody @Valid Internet internet, @PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(internet,id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable UUID id){
        service.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Internet> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/total/{id}")
    public ResponseEntity<List<InternetFront>> findAll(@PathVariable UUID id){
        List<InternetFront> list =service.findALl(id).stream().map(internet -> {
            InternetFront internetFront = new InternetFront();
            BeanUtils.copyProperties(internet, internetFront);
            internetFront.setUsers(id);
            return internetFront;
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
