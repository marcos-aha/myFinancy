package br.com.app.myFinancy.service;

import br.com.app.myFinancy.model.CardBill;
import br.com.app.myFinancy.model.Users;
import br.com.app.myFinancy.repository.CardRepository;
import br.com.app.myFinancy.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CardService {

    private final CardRepository repository;

    private final UserRepository userRepository;

    public CardBill create(CardBill card) {
        return repository.save(card);
    }

    public CardBill update(CardBill card, UUID id) {
        repository.findById(id).map(cardExist -> {
            BeanUtils.copyProperties(card, cardExist);
            return repository.save(cardExist);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de cartão não encontrada"));
        return null;
    }

    public void delete(UUID id) {
        repository.findById(id).map(card ->{
            repository.delete(card);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de cartão não encontrada"));
    }

    public CardBill findById(UUID id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de cartão não encontrada"));

    }

    public List<CardBill> findALl(UUID id) {
        Users users= userRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));
        return users.getListCardBill();
    }
}
