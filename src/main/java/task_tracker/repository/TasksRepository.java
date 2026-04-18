package task_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task_tracker.entity.Tasks;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {
}
