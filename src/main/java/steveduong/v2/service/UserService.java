package steveduong.v2.service;

import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import steveduong.v2.dto.UserRequest;
import steveduong.v2.dto.UserResponse;
import steveduong.v2.mapper.UserMapper;
import steveduong.v2.model.User;
import steveduong.v2.repository.UserRepository;

@Slf4j
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

    User user = userMapper.toEntity(request);
    log.info("createUser: {}", user);
    User savedUser = userRepository.save(user);
    return userMapper.toResponse(savedUser);
  }

  public UserResponse updateUser(int id, UserRequest request) {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

    // Check email uniqueness if email is being changed
    if (!user.getEmail().equals(request.getEmail())
        && userRepository.existsByEmail(request.getEmail())) {
      throw new RuntimeException("Email already exists: " + request.getEmail());
    }

    // Use MapStruct for efficient mapping
    User updatedFields = userMapper.updateUserFromRequest(request, user);
    updatedFields.setUpdatedAt(Instant.now());
    User savedUser = userRepository.save(updatedFields);
    return userMapper.toResponse(savedUser);
  }

  public void deleteUser(int id) {
    if (!userRepository.existsById(id)) {
      throw new RuntimeException("User not found with id: " + id);
    }
    userRepository.deleteById(id);
  }
}
