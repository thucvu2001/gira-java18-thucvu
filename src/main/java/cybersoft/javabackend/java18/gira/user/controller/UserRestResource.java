package cybersoft.javabackend.java18.gira.user.controller;

import cybersoft.javabackend.java18.gira.common.model.ResponseDTO;
import cybersoft.javabackend.java18.gira.common.util.ResponseUtils;
import cybersoft.javabackend.java18.gira.user.dto.UserDTO;
import cybersoft.javabackend.java18.gira.user.model.User;
import cybersoft.javabackend.java18.gira.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserRestResource {
    private final UserService userService;

    public UserRestResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/find-all-users")
    public ResponseEntity<ResponseDTO> findAllUser() {
        return ResponseUtils.get(
                userService.findAllDto(UserDTO.class),
                HttpStatus.OK
        );
    }

    @PostMapping("/save-user")
    public ResponseEntity<ResponseDTO> saveUser(@RequestBody @Valid UserDTO userDTO) {
        return ResponseUtils.get(
                userService.save(userDTO, User.class, UserDTO.class),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update-user/{user-id}")
    public ResponseEntity<ResponseDTO> updateUser(@RequestBody @Valid UserDTO user, @PathVariable("user-id") UUID userId) {
        return ResponseUtils.get(userService.update(user, userId, User.class, UserDTO.class), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete-user/{user-id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable("user-id") UUID userId) {
        return ResponseUtils.get(userService.deleteById(userId), HttpStatus.OK);
    }
}
