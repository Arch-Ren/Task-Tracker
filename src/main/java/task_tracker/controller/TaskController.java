package task_tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}
