package steveduong.service.impl;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import steveduong.dto.User;
import steveduong.exception.UserNotFoundException;
import steveduong.service.UserService;

@Service
@Slf4j
@Lazy
public class UserServiceImpl implements UserService {

  public static final ArrayList<User> users;

  public UserServiceImpl() {
    log.info("UserServiceImpl ctor");
  }

  static {
    users =
        new ArrayList<>() {
          {
            add(new User(1L, "John Doe", "john@example.com"));
            add(new User(2L, "Jane Smith", "jane@example.com"));
            add(new User(3L, "Bob Johnson", "bob@example.com"));
          }
        };
  }

  @Override
  public User getUser(long id) throws Exception {
    var userObj = users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    if (userObj == null) {
      throw new UserNotFoundException();
    }
    return userObj;
  }

  @Override
  public List<User> getAll() {
    log.info("users {}", users);
    return users;
  }
}
