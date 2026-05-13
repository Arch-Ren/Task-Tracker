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
import task_tracker.service.TaskService;

import java.util.List;

@RestController // penanda class yang menangani API
@RequestMapping("/api/tasks") // alamat URL
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/user/{userId}")
    public List<Tasks> getTasksByUser(@PathVariable Long userId) {
        return taskService.getTasksByUserId(userId);
    }

    @PostMapping
    public Tasks createTasks(@RequestBody Tasks task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{tasksId}/user/{userId}")
    public Tasks updateTasks(@PathVariable Long tasksId, @PathVariable Long userId, @RequestBody Tasks task) {
        return taskService.updateTask(tasksId, userId, task);
    }

    @DeleteMapping("/{tasksId}/user/{userId}")
    public String DeleteTask(@PathVariable Long tasksId, @PathVariable Long userId) {
        taskService.deleteTask(tasksId, userId);
        return "task berhasil dihapus";
    }
}
