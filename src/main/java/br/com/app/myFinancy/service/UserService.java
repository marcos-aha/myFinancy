package br.com.app.myFinancy.service;

import br.com.app.myFinancy.dto.UserDTO;
import br.com.app.myFinancy.model.UpdateUser;
import br.com.app.myFinancy.model.UserLogin;
import br.com.app.myFinancy.model.Users;
import br.com.app.myFinancy.repository.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.http.HttpStatus.*;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    private UserDTO convertForDTO(Users user) {
        UserDTO dto = new UserDTO();
        copyProperties(user, dto);
        return dto;
    }
    public UserDTO save(Users user) {
        user.setIsActive(true);
        user.setPassword(encoder.encode(user.getPassword()));
        return convertForDTO(userRepository.save(user));
    }

    public void update(UUID id, UpdateUser user) {
        userRepository.findById(id).map(userExist -> {
            if (!user.getPassword().equals(userExist.getPassword())) {
                user.setPassword(encoder.encode(user.getPassword()));
            } else {
                user.setPassword(userExist.getPassword());
            }
            userExist.setName(user.getName());
            userExist.setIncome(user.getIncome());
            userExist.setPassword(user.getPassword());
            userExist.setEmail(user.getEmail());
            userRepository.save(userExist);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuário não encontrado."));

    }


    public UpdateUser findById(UUID id) {
        Users user = userRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuário não encontrado."));
        UpdateUser dto = new UpdateUser();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }


    public void deleteUser(UUID id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuário não encontrado."));
        if(!user.getIsActive()) {
            throw new ResponseStatusException(NOT_FOUND, "Conta já se encontra desativada.");
        }
        user.setIsActive(false);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        String[] roles = new String[] {"ADMIN", "USER"};
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

    public UserDetails authenticateUser(UserLogin users) {
        UserDetails user = loadUserByUsername(users.getUsername());
        Boolean validPassword = encoder.matches(users.getPassword(), user.getPassword());
        if(validPassword) {
            return user;
        }
        throw new NullPointerException();
    }

    public String findByIdLogin(String username) {
        return userRepository.findByUsername(username).get().getId().toString();
    }

    public List<Double> findAll(UUID id) {
        Users user = userRepository.findById(id).get();
        List<Double> list = user.getListEnergyBill().stream().filter(energyBill -> energyBill.getDueDate().getMonth()
                .equals(LocalDate.now().getMonth())).map(energy -> energy.getPrice()).collect(Collectors.toList());
        user.getListWaterBill().stream().filter(water -> water.getDueDate().getMonth()
                .equals(LocalDate.now().getMonth())).map(energy -> energy.getPrice()).forEach(water -> list.add(water));
        user.getListOtherExpenses().stream().filter(other -> other.getBuyDate().getMonth()
                .equals(LocalDate.now().getMonth())).map(energy -> energy.getPrice()).forEach(other -> list.add(other));
        user.getListCardBill().stream().filter(card -> card.getDueDate().getMonth()
                .equals(LocalDate.now().getMonth())).map(energy -> energy.getPrice()).forEach(card -> list.add(card));
        user.getListInternet().stream().filter(internet -> internet.getDueDate().getMonth()
                .equals(LocalDate.now().getMonth())).map(energy -> energy.getPrice()).forEach(internet -> list.add(internet));
        return list;

    }
}
