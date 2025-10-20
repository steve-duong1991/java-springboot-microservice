package steveduong.v2.service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import steveduong.v2.dto.UserRequest;
import steveduong.v2.dto.UserResponse;
import steveduong.v2.model.Gender;
import steveduong.v2.model.User;
import steveduong.v2.model.UserStatus;
import steveduong.v2.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
  private final Map<Gender, steveduong.v2.dto.Gender> genderMap = createGenderMap();
  private final Map<UserStatus, steveduong.v2.dto.UserStatus> userStatusMap = createUserStatusMap();

  private static Map<Gender, steveduong.v2.dto.Gender> createGenderMap() {
    Map<Gender, steveduong.v2.dto.Gender> map = new EnumMap<>(Gender.class);
    map.put(Gender.M, steveduong.v2.dto.Gender.MALE);
    map.put(Gender.F, steveduong.v2.dto.Gender.FEMALE);
    return map;
  }

  private static Map<UserStatus, steveduong.v2.dto.UserStatus> createUserStatusMap() {
    Map<UserStatus, steveduong.v2.dto.UserStatus> map = new EnumMap<>(UserStatus.class);
    map.put(UserStatus.ACTIVE, steveduong.v2.dto.UserStatus.ONLINE);
    map.put(UserStatus.NOT_ACTIVE, steveduong.v2.dto.UserStatus.IN_ACTIVE);
    map.put(UserStatus.DEACTIVATE, steveduong.v2.dto.UserStatus.BLOCK);
    return map;
  }

  private final UserRepository userRepository;

  public List<UserResponse> getAllUsers() {
    return userRepository.findAll().stream().map(this::toResponse).toList();
  }

  public UserResponse getUserById(int id) {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    return toResponse(user);
  }

  public UserResponse createUser(UserRequest request) {
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new RuntimeException("Email already exists: " + request.getEmail());
    }

    User user = User.builder().email(request.getEmail()).build();

    User savedUser = userRepository.save(user);
    return toResponse(savedUser);
  }

  public UserResponse updateUser(int id, UserRequest request) {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

    //    user.setName(request.getName());
    user.setEmail(request.getEmail());

    User updatedUser = userRepository.save(user);
    return toResponse(updatedUser);
  }

  public void deleteUser(int id) {
    if (!userRepository.existsById(id)) {
      throw new RuntimeException("User not found with id: " + id);
    }
    userRepository.deleteById(id);
  }

  private UserResponse toResponse(User user) {
    return UserResponse.builder()
        .id(user.getId())
        .fullName(user.getFirstName() + " " + user.getLastName())
        .pNumber(user.getInternalId())
        .email(user.getEmail())
        .dateOfBirth(user.getDateOfBirth())
        .createdAt(user.getCreatedAt())
        .updatedAt(user.getUpdatedAt())
        .gender(genderMap.get(user.getGender()))
        .status(userStatusMap.get(user.getStatus()))
        .build();
  }
}
