package br.com.app.myFinancy.service;

import br.com.app.myFinancy.model.*;
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

    public CardBill create(CardFront cardFront) {
        System.out.println(cardFront);
        return userRepository.findById(cardFront.getUsers()).map(useExist ->{
            CardBill cardBill = new CardBill();
            BeanUtils.copyProperties(cardFront,cardBill);
            cardBill.setUsers(useExist);
            repository.save(cardBill);
            return cardBill;
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));
    }

    public CardBill update(CardFront card, UUID id) {
        return repository.findById(id).map(cardExist -> {
            cardExist.setDescription(card.getDescription());
            cardExist.setPrice(card.getPrice());
            cardExist.setDueDate(card.getDueDate());
            cardExist.setClosingDate(card.getClosingDate());
            return repository.save(cardExist);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de energia não encontrada"));
    }

    public void delete(UUID id) {
        repository.findById(id).map(card ->{
            repository.delete(card);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de cartão não encontrada"));
    }

    public CardFront findById(UUID id) {
        CardBill card = repository.findById(id).get();
        CardFront cardFront = new CardFront();
        BeanUtils.copyProperties(card, cardFront);
        cardFront.setUsers(card.getUsers().getId());
        return cardFront;

    }

    public List<CardBill> findALl(UUID id) {
        Users users= userRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));
        return users.getListCardBill();
    }
}
