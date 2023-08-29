package poicity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poicity.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

}
