package steveduong.v1.service;

import java.util.List;
import steveduong.v1.dto.User;

public interface UserService {
  User getUser(long id) throws Exception;

  List<User> getAll();
}
