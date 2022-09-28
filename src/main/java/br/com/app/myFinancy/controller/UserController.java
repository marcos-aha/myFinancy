package br.com.app.myFinancy.controller;

import br.com.app.myFinancy.dto.UserDTO;
import br.com.app.myFinancy.model.Token;
import br.com.app.myFinancy.model.UpdateUser;
import br.com.app.myFinancy.model.UserLogin;
import br.com.app.myFinancy.model.Users;
import br.com.app.myFinancy.service.TokenService;
import br.com.app.myFinancy.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final PasswordEncoder encoder;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody @Valid Users user) {
        return ResponseEntity.status(CREATED).body(userService.save(user));
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateUser(@PathVariable UUID id, @RequestBody @Valid UpdateUser user) {
        userService.update(id, user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findByID(@PathVariable UUID id) {
        return ResponseEntity.status(OK).body(userService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody UserLogin users) {
        Token token = new Token(tokenService.createToken(userService.authenticateUser(users)));
        return ResponseEntity.ok().body("Bearer "+ token.getToken());
    }

    @GetMapping("/id/{token}")
    public ResponseEntity<String> idUser(@PathVariable String token) {
        String username = tokenService.findLoginUser(token);
        return ResponseEntity.ok().body(userService.findByIdLogin(username));
    }
}
