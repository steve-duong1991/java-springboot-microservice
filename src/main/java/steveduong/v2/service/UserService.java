package steveduong.v2.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import steveduong.v2.dto.UserRequest;
import steveduong.v2.dto.UserResponse;
import steveduong.v2.mapper.UserMapper;
import steveduong.v2.model.User;
import steveduong.v2.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public List<UserResponse> getAllUsers() {
    return userRepository.findAll().stream().map(userMapper::toResponse).toList();
  }

  public UserResponse getUserById(int id) {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    return userMapper.toResponse(user);
  }

  public UserResponse createUser(UserRequest request) {
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new RuntimeException("Email already exists: " + request.getEmail());
    }

    User user = User.builder().email(request.getEmail()).build();

    User savedUser = userRepository.save(user);
    return userMapper.toResponse(savedUser);
  }

  public UserResponse updateUser(int id, UserRequest request) {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

    //    user.setName(request.getName());
    user.setEmail(request.getEmail());

    User updatedUser = userRepository.save(user);
    return userMapper.toResponse(updatedUser);
  }

  public void deleteUser(int id) {
    if (!userRepository.existsById(id)) {
      throw new RuntimeException("User not found with id: " + id);
    }
    userRepository.deleteById(id);
  }
}
