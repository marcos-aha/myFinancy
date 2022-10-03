package br.com.app.myFinancy.controller;

import br.com.app.myFinancy.model.CardBill;
import br.com.app.myFinancy.model.CardFront;
import br.com.app.myFinancy.model.EnergyFront;
import br.com.app.myFinancy.service.CardService;
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
@RequestMapping("/card")
@AllArgsConstructor
public class CardController {
    private final CardService service;

    @PostMapping
    public ResponseEntity<CardBill> create(@RequestBody @Valid CardFront card) {
        return ResponseEntity.status(HttpStatus.OK).body(service.create(card));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardBill> update(@RequestBody @Valid CardFront card, @PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(card,id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable UUID id){
        service.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardFront> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/total/{id}")
    public ResponseEntity<List<CardFront>> findAll(@PathVariable UUID id){
        List<CardFront> list =service.findALl(id).stream().map(card -> {
            CardFront cardFront = new CardFront();
            BeanUtils.copyProperties(card, cardFront);
            cardFront.setUsers(id);
            return cardFront;
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}
