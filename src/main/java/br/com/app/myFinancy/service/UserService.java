package br.com.app.myFinancy.service;

import br.com.app.myFinancy.dto.UserDTO;
import br.com.app.myFinancy.model.UpdateUser;
import br.com.app.myFinancy.model.User;
import br.com.app.myFinancy.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;
import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private UserDTO convertForDTO(User user) {
        UserDTO dto = new UserDTO();
        copyProperties(user, dto);
        dto.setIncomeTotal(dto.getIncome().stream()
                .reduce(BigDecimal.valueOf(0), (ac, income) -> income.add(ac)));
        return dto;
    }
    public UserDTO save(User user) {
        user.setIsActive(true);
        return convertForDTO(userRepository.save(user));
    }

    public void update(UUID id, UpdateUser user) {
        userRepository.findById(id).map(userExist -> {
            user.setId(userExist.getId());
            if (user.getNewPassword()!=null) {
                if(user.getOldPassword().equals(userExist.getPassword())) {
                    userExist.setPassword(user.getNewPassword());
                } else {
                    throw new ResponseStatusException(NOT_FOUND, "Senha incorreta.");
                }
            }
            copyProperties(user, userExist);
            userRepository.save(userExist);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuário não encontrado."));

    }

    public List<UserDTO> listAll() {
        return userRepository.findAll().stream().filter(User::getIsActive).map(this::convertForDTO).collect(toList());
    }

    public UserDTO findById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuário não encontrado."));
        return convertForDTO(user);
    }


    public void deleteUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuário não encontrado."));
        if(!user.getIsActive()) {
            throw new ResponseStatusException(NOT_FOUND, "Conta já se encontra desativada.");
        }
        user.setIsActive(false);
        userRepository.save(user);
    }
}
