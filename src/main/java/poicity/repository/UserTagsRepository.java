package poicity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poicity.entity.UserTags;

@Repository
public interface UserTagsRepository extends JpaRepository<UserTags, Long>{

}
