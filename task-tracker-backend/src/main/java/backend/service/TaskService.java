package backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.entity.Tasks;
import backend.repository.TasksRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TasksRepository tasksRepository;

    private final List<String> ALLOWED_STATUES = Arrays.asList("TODO", "IN_PROGRESS", "DONE");

    public List<Tasks> getTasksByUserId(Long userId) {
        return tasksRepository.findByUserId(userId);
    }

    public Tasks createTask(Tasks task) {
        validateTask(task);
        return tasksRepository.save(task);
    }

    public Tasks updateTask(Long tasksId, Long userId, Tasks taskDetails) {
        Tasks task = tasksRepository.findById(tasksId)
                .orElseThrow(() -> new RuntimeException("Task tidak ditemukan"));

        if (!task.getUser().getId().equals(userId)) {
            throw new RuntimeException("Anda tidak memiliki izin untuk mengubah tasks ini");
        }

        validateTask(taskDetails);
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());

        return tasksRepository.save(task);
    }

    public void deleteTask(Long tasksId, Long userId) {
        Tasks task = tasksRepository.findById(tasksId)
                .orElseThrow(() -> new RuntimeException("Task tidak ditemukan"));

        if (!task.getUser().getId().equals(userId)) {
            throw new RuntimeException("Anda tidak memiliki izin untuk menghapus tasks ini");
        }

        tasksRepository.delete(task);
    }

    private void validateTask(Tasks task) {
        if (task.getDescription() != null && task.getDescription().length() > 100) {
            throw new RuntimeException("Operasi gagal. Deskripsi terlalu panjang. Maksimal 100 karakter");
        }

        if (!ALLOWED_STATUES.contains(task.getStatus())) {
            throw new RuntimeException("Operasi gagal. Status '" + task.getStatus()
                    + "' tidak valid. Gunakan antara TODO, IN_PROGRESS, atau DONE!");
        }
    }

}
