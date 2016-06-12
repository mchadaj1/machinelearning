package com.example.entities;

import javax.persistence.*;

/**
 * Created by mateusz on 11.05.16.
 */
@Entity
@Table(name = "statistics")
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int eaten_agent_number;

    private int experiment_number;

    private int simulation_number;

    private int step_number;

    private String eaten_agent_type;

    @Column(name = "execution_id")
    private Long executionId;

    public Statistic(int eaten_agent_number, int step_number, String eaten_agent_type) {
        this.eaten_agent_number = eaten_agent_number;
        this.step_number = step_number;
        this.eaten_agent_type = eaten_agent_type;
    }

    public String getEaten_agent_type() {
        return eaten_agent_type;
    }

    public void setSimulation_number(int simulation_number) {
        this.simulation_number = simulation_number;
    }
    public void setExecutionId(Long executionId) {
        this.executionId = executionId;
    }

    public void setExperiment_number(int experiment_number) {
        this.experiment_number = experiment_number;
    }

    public Statistic() {
    }

    public Statistic(int eaten_agent_number, int step_number) {
        this.eaten_agent_number = eaten_agent_number;
        this.step_number = step_number;
    }

    public Long getId() {
        return id;
    }



    public int getEaten_agent_number() {
        return eaten_agent_number;
    }

    public int getExperiment_number() {
        return experiment_number;
    }

    public int getSimulation_number() {
        return simulation_number;
    }

    public int getStep_number() {
        return step_number;
    }

    public Long getExecutionId() {
        return executionId;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", eaten_agent_number=" + eaten_agent_number +
                ", experiment_number=" + experiment_number +
                ", simulation_number=" + simulation_number +
                ", step_number=" + step_number +
                ", eaten_agent_type='" + eaten_agent_type + '\'' +
                ", executionId=" + executionId +
                '}';
    }
}
