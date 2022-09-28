package br.com.app.myFinancy.service;

import br.com.app.myFinancy.dto.UserDTO;
import br.com.app.myFinancy.model.UpdateUser;
import br.com.app.myFinancy.model.UserLogin;
import br.com.app.myFinancy.model.Users;
import br.com.app.myFinancy.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

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
            if (!encoder.matches(user.getPassword(), userExist.getPassword())) {
                user.setPassword(encoder.encode(user.getPassword()));
            }
            user.setId(userExist.getId());
            copyProperties(user, userExist);
            userRepository.save(userExist);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuário não encontrado."));

    }


    public UserDTO findById(UUID id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuário não encontrado."));
        return convertForDTO(user);
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
        return org.springframework.security.core.userdetails.User.builder()
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
}
