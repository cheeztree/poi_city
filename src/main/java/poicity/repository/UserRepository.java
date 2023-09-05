package poicity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poicity.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String Username);
//	Optional<org.springframework.security.core.userdetails.User> findByUsername2(String Username);
	boolean existsByEmail(String email);
    User findByEmail(String email);
    boolean existsByPassword(String password);

}
