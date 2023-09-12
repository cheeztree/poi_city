package poicity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poicity.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String Username);
//	Optional<org.springframework.security.core.userdetails.User> findByUsername2(String Username);
	boolean existsByEmail(String email);
    User findByEmail(String email);
    boolean existsByPassword(String password);
    boolean existsByUsername(String username);

}
