package com.klef.fsad.exam.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String landDetails;
    private String location;
    private String cropsGrown;

    // 🔥 NEW FIELD (TASKS STORED AS TEXT LIST)
    @ElementCollection
    private List<String> tasks = new ArrayList<>();

    // Link with User
    @OneToOne
    private User user;

    // GETTERS & SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLandDetails() { return landDetails; }
    public void setLandDetails(String landDetails) { this.landDetails = landDetails; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getCropsGrown() { return cropsGrown; }
    public void setCropsGrown(String cropsGrown) { this.cropsGrown = cropsGrown; }

    public List<String> getTasks() { return tasks; }
    public void setTasks(List<String> tasks) { this.tasks = tasks; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}