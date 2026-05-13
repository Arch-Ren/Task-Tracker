package task_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import task_tracker.entity.User;
import task_tracker.entity.Tasks;
import task_tracker.repository.UserRepository;
import task_tracker.repository.TasksRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserRepository userRepo, TasksRepository taskRepo) {
		return args -> {
			//Buat User Baru
			User user = new User();
			user.setUsername("Renth Demo");
			user.setEmail("renth@gmail.com");
			userRepo.save(user);

			//buat tasks untuk user
			Tasks task = new Tasks();
			task.setDescription("Projek mandiri Task Tracker");
			task.setStatus("IN_PROGRESS");
			task.setUser(user);
			taskRepo.save(task);

			//tester
			System.out.println("Data berhasil disimpan ke database");
		};
	}
}
