package task_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task_tracker.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
