package steveduong.v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import steveduong.v2.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  boolean existsByEmail(String email);
}
