package steveduong.v2.dto;

import lombok.Data;

@Data
public class UserRequest {
  //  @NotBlank(message = "Name is required")
  //  private String name;

  //  @NotBlank(message = "Email is required")
  //  @Email(message = "Email should be valid")
  private String email;
}
