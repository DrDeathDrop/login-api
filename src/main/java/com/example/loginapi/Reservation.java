package com.example.loginapi;
import jakarta.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    private String username;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

}