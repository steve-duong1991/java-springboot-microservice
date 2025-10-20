package steveduong.v2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "user")
@Table(name = "user", schema = "public")
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false, unique = true, name = "internal_id")
  private UUID internalId;

  @Column(nullable = false, unique = true, name = "email")
  private String email;

  @Column(nullable = false, name = "first_name")
  private String firstName;

  @Column(name = "middle_name")
  private String middleName;

  @Column(nullable = false, name = "last_name")
  private String lastName;

  @Column(nullable = false, name = "dob")
  private LocalDate dateOfBirth;

  @Enumerated(EnumType.STRING)
  @Column(name = "gender", columnDefinition = "gender_enum")
  private Gender gender;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", columnDefinition = "user_status_enum")
  private UserStatus status;
}
