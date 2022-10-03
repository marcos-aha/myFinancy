package br.com.app.myFinancy.service;

import br.com.app.myFinancy.model.*;
import br.com.app.myFinancy.repository.InternetRepository;
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
public class InternetService {

    private final InternetRepository repository;

    private final UserRepository userRepository;

    public Internet create(InternetFront internetFront) {
        return userRepository.findById(internetFront.getUsers()).map(useExist ->{
            Internet internet= new Internet();
            BeanUtils.copyProperties(internetFront,internet);
            internet.setUsers(useExist);
            repository.save(internet);
            return internet;
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));
    }

    public Internet update(InternetFront internetFront, UUID id) {
        return repository.findById(id).map(internetExist -> {
            internetExist.setPrice(internetFront.getPrice());
            internetExist.setDueDate(internetFront.getDueDate());
            return repository.save(internetExist);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de energia não encontrada"));
    }

    public void delete(UUID id) {
        repository.findById(id).map(internet ->{
            repository.delete(internet);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de internet não encontrada"));
    }

    public InternetFront findById(UUID id) {
        Internet internet = repository.findById(id).get();
        InternetFront internetFront = new InternetFront();
        BeanUtils.copyProperties(internet, internetFront);
        internetFront.setUsers(internet.getUsers().getId());
        return internetFront;
    }

    public List<Internet> findALl(UUID id) {
        Users users= userRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));
        return users.getListInternet();
    }
}
