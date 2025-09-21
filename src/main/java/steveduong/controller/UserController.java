package steveduong.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import steveduong.dto.User;
import steveduong.service.CustomerService;
import steveduong.service.UserService;

@RestController
@RequestMapping("/api/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

  @Autowired @Lazy UserService userService;

  // require @RequiredArgsConstructor at the class level,
  // and final in field declaration
  final CustomerService customerService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<User> getAllUsers() {
    log.info("getAllUsers");
    return userService.getAll();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @SneakyThrows
  public User getUser(@PathVariable long id) {
    log.info("getUser with params {}", id);
    return userService.getUser(id);
  }

  @GetMapping("/customer/{id}")
  @ResponseStatus(HttpStatus.OK)
  public User getCustomer(@PathVariable long id) {
    log.info("getCustomer with params {}", id);
    return customerService.getCustomer();
  }
}
