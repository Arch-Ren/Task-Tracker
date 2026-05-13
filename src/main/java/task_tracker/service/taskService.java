package task_tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task_tracker.entity.Tasks;
import task_tracker.repository.TasksRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class taskService {
    
    @Autowired
    private TasksRepository tasksRepository;

    private final List<String> ALLOWED_STATUES = Arrays.asList("TODO", "IN_PROGRESS", "DONE");

    public List<Tasks> getAllTasks() {
        return tasksRepository.findAll();
    }

    public Tasks createTask(Tasks task) {
        validateTask(task);
        return tasksRepository.save(task);
    }

    public Tasks updateTask(Long id, Tasks taskDetails) {
        Tasks task = tasksRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task dengan id " + id + " tidak ditemukan"));
        
        validateTask(taskDetails);

        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());

        return tasksRepository.save(task);
    }

    public void deleteTask(Long id) {
        tasksRepository.deleteById(id);
    }

    private void validateTask(Tasks task) {
        if(task.getDescription() != null && task.getDescription().length() > 100) {
            throw new RuntimeException("Operasi gagal. Deskripsi terlalu panjang. Maksimal 100 karakter");
        }

        if(!ALLOWED_STATUES.contains(task.getStatus())) {
            throw new RuntimeException("Operasi gagal. Status '" + task.getStatus() + "' tidak valid. Gunakan antara TODO, IN_PROGRESS, atau DONE!");
        }
    }

}
