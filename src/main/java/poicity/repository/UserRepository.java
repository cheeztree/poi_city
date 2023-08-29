package poicity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poicity.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
