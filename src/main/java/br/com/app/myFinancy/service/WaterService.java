package br.com.app.myFinancy.service;

import br.com.app.myFinancy.model.*;
import br.com.app.myFinancy.repository.UserRepository;
import br.com.app.myFinancy.repository.WaterRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class WaterService {
    private final WaterRepository repository;

    private final UserRepository userRepository;

    public WaterBill create(WaterFront water) {
        return userRepository.findById(water.getUsers()).map(userExist -> {
            WaterBill waterBill = new WaterBill();
            BeanUtils.copyProperties(water,waterBill);
            waterBill.setUsers(userExist);
            repository.save(waterBill);
            return waterBill;
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

    }

    public WaterBill update(WaterFront water, UUID id) {
        return repository.findById(id).map(waterExist -> {
            waterExist.setPrice(water.getPrice());
            waterExist.setExpenditure(water.getExpenditure());
            waterExist.setDueDate(water.getDueDate());
            return repository.save(waterExist);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de água não encontrada"));
    }

    public void delete(UUID id) {
        repository.findById(id).map(water ->{
            repository.delete(water);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de água não encontrada"));
    }

    public WaterFront findById(UUID id) {
        WaterBill waterBill = repository.findById(id).get();
        WaterFront water = new WaterFront();
        BeanUtils.copyProperties(waterBill, water);
        water.setUsers(waterBill.getUsers().getId());
        return water;
    }

    public List<WaterBill> findALl(UUID id) {
        Users users= userRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));
        return users.getListWaterBill();
    }

}
