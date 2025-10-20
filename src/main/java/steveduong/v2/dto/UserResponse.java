package steveduong.v2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
  private int id;

  @JsonProperty("p_number")
  private UUID pNumber;

  private String email;

  @JsonProperty("full_name")
  private String fullName;

  @JsonFormat(pattern = "dd-MM-yyyy")
  @JsonProperty("date_of_birth")
  private LocalDate dateOfBirth;

  private Gender gender;

  private UserStatus status;

  @JsonProperty("created_at")
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ssXXX", timezone = "UTC")
  private Instant createdAt;

  @JsonProperty("updated_at")
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ssXXX", timezone = "UTC")
  private Instant updatedAt;
}
