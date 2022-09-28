package br.com.app.myFinancy.service;

import br.com.app.myFinancy.model.Users;
import br.com.app.myFinancy.model.WaterBill;
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

    public WaterBill create(WaterBill water) {
        return repository.save(water);
    }

    public WaterBill update(WaterBill water, UUID id) {
        repository.findById(id).map(waterExist -> {
            BeanUtils.copyProperties(water, waterExist);
            return repository.save(waterExist);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de água não encontrada"));
        return null;
    }

    public void delete(UUID id) {
        repository.findById(id).map(water ->{
            repository.delete(water);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de água não encontrada"));
    }

    public WaterBill findById(UUID id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de água não encontrada"));
    }

    public List<WaterBill> findALl(UUID id) {
        Users users= userRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));
        return users.getListWaterBill();
    }

}
