package steveduong.v2.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.Instant;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseEntity {
  @Column(nullable = false, name = "created_at", insertable = false)
  private Instant createdAt;

  @Column(name = "updated_at", insertable = false)
  private Instant updatedAt;
}
