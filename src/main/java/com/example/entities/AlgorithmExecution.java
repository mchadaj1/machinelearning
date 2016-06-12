package com.example.entities;

import javax.persistence.*;

/**
 * Created by mateusz on 09.05.16.
 */
@Entity
@Table(name="algorithm_executions")
public class AlgorithmExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean pending;

    private boolean completed;

    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "problem_configuration_id",referencedColumnName = "id",nullable = false,updatable = false,insertable = false)
    public ProblemConfiguration problemConfiguration;

    @ManyToOne
    @JoinColumn(name = "method_configuration_id",referencedColumnName = "id",nullable = false,updatable = false,insertable = false)
    public MethodConfiguration methodConfiguration;

    @Column(name="method_configuration_id",updatable = false)
    private Long methodConfigurationId;

    @Column(name="problem_configuration_id",updatable = false)
    private Long problemConfigurationId;

    @Override
    public String toString() {
        return "AlgorithmExecution{" +
                "id=" + id +
                ", pending=" + pending +
                ", completed=" + completed +
                ", problemConfiguration=" + problemConfiguration +
                ", methodConfiguration=" + methodConfiguration +
                '}';
    }

    public AlgorithmExecution(Long methodConfigurationId, Long problemConfigurationId) {
        this.methodConfigurationId = methodConfigurationId;
        this.problemConfigurationId = problemConfigurationId;
    }

    public AlgorithmExecution() {
    }

    public void setMethodConfigurationId(Long methodConfigurationId) {
        this.methodConfigurationId = methodConfigurationId;
    }

    public void setProblemConfigurationId(Long problemConfigurationId) {
        this.problemConfigurationId = problemConfigurationId;
    }

    public Long getMethodConfigurationId() {
        return methodConfigurationId;
    }

    public Long getProblemConfigurationId() {
        return problemConfigurationId;
    }
}
