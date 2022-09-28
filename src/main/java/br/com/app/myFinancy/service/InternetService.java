package br.com.app.myFinancy.service;

import br.com.app.myFinancy.model.Internet;
import br.com.app.myFinancy.model.Users;
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

    public Internet create(Internet internet) {
        return repository.save(internet);
    }

    public Internet update(Internet internet, UUID id) {
        repository.findById(id).map(internetExist -> {
            BeanUtils.copyProperties(internet, internetExist);
            return repository.save(internetExist);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de internet não encontrada"));
        return null;
    }

    public void delete(UUID id) {
        repository.findById(id).map(internet ->{
            repository.delete(internet);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de internet não encontrada"));
    }

    public Internet findById(UUID id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta de internet não encontrada"));
    }

    public List<Internet> findALl(UUID id) {
        Users users= userRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));
        return users.getListInternet();
    }
}
