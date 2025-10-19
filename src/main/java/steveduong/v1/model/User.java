package steveduong.v1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
  private Long id;
  private String name;
  private String email;
}
