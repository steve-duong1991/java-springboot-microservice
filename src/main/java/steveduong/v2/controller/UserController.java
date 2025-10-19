package steveduong.v2.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import steveduong.v1.dto.User;

@RestController
@RequestMapping("/v2/api/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<User> getAllUsers() {
    log.info("getAllUsers v2");
    return Collections.emptyList();
  }
}
