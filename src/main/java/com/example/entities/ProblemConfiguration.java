package com.example.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mateusz on 06.03.16.
 */
@Entity
@Table(name="problem_configurations")
public class ProblemConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int problems_id;

    @ManyToOne(optional=false)
    @JoinColumn(name="problems_id",referencedColumnName="id" ,insertable = false, updatable = false)
    private Problem problem;

    @OneToMany(mappedBy = "problemConfigurationId")
    public List<ProblemParamValue> problemParamValues;

    public ProblemConfiguration() {
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

    public int getProblems_id() {
        return problems_id;
    }

    public void setProblems_id(int problems_id) {
        this.problems_id = problems_id;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }
}
