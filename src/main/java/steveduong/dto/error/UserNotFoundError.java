package steveduong.dto.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserNotFoundError {

  private String error;

  private int status;
}
