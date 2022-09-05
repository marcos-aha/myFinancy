package br.com.app.myFinancy.controller;

import br.com.app.myFinancy.dto.UserDTO;
import br.com.app.myFinancy.model.UpdateUser;
import br.com.app.myFinancy.model.User;
import br.com.app.myFinancy.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody @Valid User user) {
        return ResponseEntity.status(CREATED).body(userService.save(user));
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateUser(@PathVariable UUID id, @RequestBody @Valid UpdateUser user) {
        userService.update(id, user);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> listAll () {
        return ResponseEntity.status(OK).body(userService.listAll());
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


}
