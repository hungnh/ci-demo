package uet.hungnh.ci.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uet.hungnh.ci.persistence.entity.AppUser;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
}
