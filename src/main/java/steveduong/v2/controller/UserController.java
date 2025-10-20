package steveduong.v2.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import steveduong.v2.dto.UserRequest;
import steveduong.v2.dto.UserResponse;
import steveduong.v2.service.UserService;

@RestController
@RequestMapping("/v2/api/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<UserResponse> getAllUsers() {
    log.info("getAllUsers v2");
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public UserResponse getUserById(@PathVariable int id) {
    log.info("getUserById v2: {}", id);
    return userService.getUserById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserResponse createUser(@Valid @RequestBody UserRequest request) {
    log.info("createUser v2: {}", request);
    return userService.createUser(request);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public UserResponse updateUser(@PathVariable int id, @Valid @RequestBody UserRequest request) {
    log.info("updateUser v2: {} - {}", id, request);
    return userService.updateUser(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable int id) {
    log.info("deleteUser v2: {}", id);
    userService.deleteUser(id);
  }
}
