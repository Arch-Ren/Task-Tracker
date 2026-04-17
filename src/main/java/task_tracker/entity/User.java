package task_tracker.entity;

import jakarta.persistence.*; //import library JPA

@Entity // Memberitahu spring bahwa class ini adalah tabel database
@Table(name = "users") // menghubungkan ke tabel users
public class User {

    @Id // menandai bahwa field ini adalah primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // field ini akan diisi otomatis
    private long id;

    @Column(name = "username", nullable = false) // menghubungkan ke kolom username
    private String username;

    @Column(name = "email", nullable = false) // menghubungkan ke kolom email
    private String email;

    public User() {
    }

    public User(long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
