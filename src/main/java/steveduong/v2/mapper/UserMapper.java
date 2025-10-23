package steveduong.v2.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import steveduong.v2.dto.UserRequest;
import steveduong.v2.dto.UserResponse;
import steveduong.v2.model.Gender;
import steveduong.v2.model.User;
import steveduong.v2.model.UserStatus;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "fullName", expression = "java(buildFullName(user))")
  @Mapping(target = "pNumber", source = "internalId")
  @Mapping(target = "gender", expression = "java(mapGenderDto(user.getGender()))")
  @Mapping(target = "status", expression = "java(mapStatusDto(user.getStatus()))")
  UserResponse toResponse(User user);

  @Named("buildFullName")
  default String buildFullName(User user) {
    if (user == null) return "";
    return java.util.stream.Stream.of(user.getFirstName(), user.getMiddleName(), user.getLastName())
        .filter(java.util.Objects::nonNull)
        .filter(name -> !name.trim().isEmpty())
        .collect(java.util.stream.Collectors.joining(" "));
  }

  @Named("mapGenderDto")
  default steveduong.v2.dto.Gender mapGenderDto(Gender gender) {
    if (gender == null) return null;
    return switch (gender) {
      case M -> steveduong.v2.dto.Gender.MALE;
      case F -> steveduong.v2.dto.Gender.FEMALE;
    };
  }

  @Named("mapStatusDto")
  default steveduong.v2.dto.UserStatus mapStatusDto(UserStatus status) {
    if (status == null) return null;
    return switch (status) {
      case ACTIVE -> steveduong.v2.dto.UserStatus.ONLINE;
      case NOT_ACTIVE -> steveduong.v2.dto.UserStatus.IN_ACTIVE;
      case DEACTIVATE -> steveduong.v2.dto.UserStatus.BLOCK;
    };
  }

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "internalId", ignore = true)
  @Mapping(target = "gender", expression = "java(mapGenderModel(request.getGender()))")
  @Mapping(target = "status", expression = "java(mapStatusModel(request.getStatus()))")
  User toEntity(UserRequest request);

  @Named("mapGenderModel")
  default Gender mapGenderModel(steveduong.v2.dto.Gender gender) {
    if (gender == null) return null;
    return switch (gender) {
      case MALE -> Gender.M;
      case FEMALE -> Gender.F;
    };
  }

  @Named("mapStatusModel")
  default UserStatus mapStatusModel(steveduong.v2.dto.UserStatus status) {
    if (status == null) return null;
    return switch (status) {
      case ONLINE -> UserStatus.ACTIVE;
      case IN_ACTIVE -> UserStatus.NOT_ACTIVE;
      case BLOCK -> UserStatus.DEACTIVATE;
    };
  }

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "internalId", ignore = true)
  @Mapping(target = "gender", expression = "java(mapGenderModel(request.getGender()))")
  @Mapping(target = "status", expression = "java(mapStatusModel(request.getStatus()))")
  User updateUserFromRequest(UserRequest request, @MappingTarget User user);
}
