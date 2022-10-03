package br.com.app.myFinancy.controller;


import br.com.app.myFinancy.model.OtherExpenses;
import br.com.app.myFinancy.model.OtherExpensesFront;

import br.com.app.myFinancy.service.OtherExpensesService;
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
@RequestMapping("/otherExpenses")
@AllArgsConstructor
public class OtherExpensesController {

    private final OtherExpensesService service;

    @PostMapping
    public ResponseEntity<OtherExpenses> create(@RequestBody @Valid OtherExpensesFront expensesFront) {
        return ResponseEntity.status(HttpStatus.OK).body(service.create(expensesFront));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OtherExpenses> update(@RequestBody @Valid OtherExpensesFront expensesFront, @PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(expensesFront,id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable UUID id){
        service.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OtherExpensesFront> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/total/{id}")
    public ResponseEntity<List<OtherExpensesFront>> findAll(@PathVariable UUID id){
        List<OtherExpensesFront> list =service.findALl(id).stream().map(otherExpenses -> {
            OtherExpensesFront expensesFront = new OtherExpensesFront();
            BeanUtils.copyProperties(otherExpenses, expensesFront);
            expensesFront.setUsers(id);
            return expensesFront;
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
