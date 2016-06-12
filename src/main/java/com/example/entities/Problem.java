package com.example.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Mateusz on 2016-03-03.
 */
@Entity
@Table(name="problems")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;
    @OneToMany(mappedBy = "problem")
    private List<ProblemConfiguration> problem_configurations;

    @OneToMany(mappedBy = "problem")
    private List<Method> methods;

    public Problem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
