package poicity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import poicity.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String Username);
//	Optional<org.springframework.security.core.userdetails.User> findByUsername2(String Username);
	
}
