package br.com.app.myFinancy.service;

import br.com.app.myFinancy.model.*;
import br.com.app.myFinancy.repository.EnergyRepository;
import br.com.app.myFinancy.repository.OtherExpensesRepository;
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
public class OtherExpensesService {

    private final OtherExpensesRepository repository;

    private final UserRepository userRepository;

    public OtherExpenses create(OtherExpensesFront expensesFront) {
        return userRepository.findById(expensesFront.getUsers()).map(useExist ->{
            OtherExpenses otherExpenses = new OtherExpenses();
            BeanUtils.copyProperties(expensesFront,otherExpenses);
            otherExpenses.setUsers(useExist);
            repository.save(otherExpenses);
            return otherExpenses;
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));
    }

    public OtherExpenses update(OtherExpensesFront expensesFront, UUID id) {
        return repository.findById(id).map(expensesExist -> {
            expensesExist.setPrice(expensesFront.getPrice());
            expensesExist.setDescription(expensesFront.getDescription());
            expensesExist.setBuyDate(expensesFront.getBuyDate());
            return repository.save(expensesExist);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de energia não encontrada"));
    }

    public void delete(UUID id) {
        repository.findById(id).map(energy ->{
            repository.delete(energy);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de energia não encontrada."));
    }

    public OtherExpensesFront findById(UUID id) {
        OtherExpenses otherExpenses = repository.findById(id).get();
        OtherExpensesFront expensesFront = new OtherExpensesFront();
        BeanUtils.copyProperties(otherExpenses, expensesFront);
        expensesFront.setUsers(otherExpenses.getUsers().getId());
        return expensesFront;
    }

    public List<OtherExpenses> findALl(UUID id) {
        Users users= userRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));
        return users.getListOtherExpenses();
    }
}
