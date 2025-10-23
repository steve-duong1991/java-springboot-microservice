package steveduong.v2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.Data;

@Data
public class UserRequest {

  private String email;

  private String firstName;

  private String middleName;

  private String lastName;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate dateOfBirth;

  private Gender gender;

  private UserStatus status;
}
