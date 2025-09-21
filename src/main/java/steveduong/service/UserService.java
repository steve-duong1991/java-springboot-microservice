package steveduong.service;

import java.util.List;
import steveduong.dto.User;

public interface UserService {
  User getUser(long id) throws Exception;

  List<User> getAll();
}
