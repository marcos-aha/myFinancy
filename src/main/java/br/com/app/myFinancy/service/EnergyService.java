package br.com.app.myFinancy.service;

import br.com.app.myFinancy.model.EnergyBill;
import br.com.app.myFinancy.model.EnergyFront;
import br.com.app.myFinancy.model.Users;
import br.com.app.myFinancy.repository.EnergyRepository;
import br.com.app.myFinancy.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class EnergyService {
    
    private final EnergyRepository repository;

    private final UserRepository userRepository;

    public EnergyBill create(EnergyFront energy) {
        userRepository.findById(energy.getUsers()).map(useExist ->{
            EnergyBill energyBill = new EnergyBill();
            BeanUtils.copyProperties(energy,energyBill);
            energyBill.setUsers(useExist);
            repository.save(energyBill);
            return energyBill;
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));
        return null;
    }

    public EnergyBill update(EnergyBill energy, UUID id) {
        repository.findById(id).map(energyExist -> {
            BeanUtils.copyProperties(energy, energyExist);
            return repository.save(energyExist);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de energia não encontrada"));
        return null;
    }

    public void delete(UUID id) {
        repository.findById(id).map(energy ->{
            repository.delete(energy);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de energia não encontrada."));
    }

    public EnergyBill findById(UUID id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de energia não encontrada."));

    }

    public List<EnergyBill> findALl(UUID id) {
        Users users= userRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));
        return users.getListEnergyBill();
    }
}
