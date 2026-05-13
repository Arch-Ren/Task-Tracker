package task_tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task_tracker.entity.Tasks;
import task_tracker.repository.TasksRepository;

import java.util.List;

@RestController     //penanda class yang menangani API
@RequestMapping("/api/tasks")       //alamat URL
public class TaskController {
    
    @Autowired
    private TasksRepository taskRepository;

    @GetMapping
    public List<Tasks> getAllTasks() {
        return taskRepository.findAll();
    }

    @PostMapping
    public Tasks createTasks(@RequestBody Tasks task) {
        return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    public Tasks updateTasks(@PathVariable Long id, @RequestBody Tasks taskDetails) {
        Tasks task = taskRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task tidak ditemukan dengan id: " + id));

            task.setDescription(taskDetails.getDescription());
            task.setStatus(taskDetails.getStatus());

            return taskRepository.save(task);
    }
}
