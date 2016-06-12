package com.example.entities;

import javax.persistence.*;

/**
 * Created by mateusz on 06.03.16.
 */
@Entity
@Table(name="problem_params_values")
public class ProblemParamValue {


    public ProblemParamValue(Long param_id, Long configuration_id, String value) {
        this.problemParamId = param_id;
        this.problemConfigurationId = configuration_id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String value;
    @Column(name="problem_configurations_id",updatable = false)
    private Long problemConfigurationId;

    public Long getProblemParamId() {
        return problemParamId;
    }

    public void setProblemParamId(Long problemParamId) {
        this.problemParamId = problemParamId;
    }

    @Column(name="problem_param_id",updatable = false)
    private Long problemParamId;
//    @ManyToOne
//    @JoinColumn(name="problem_configurations_id",referencedColumnName = "id",nullable = false,updatable = false,insertable = false)
//    public ProblemConfiguration problem_configuration;

    @ManyToOne
    @JoinColumn(name="problem_param_id",referencedColumnName = "id",nullable = false,updatable = false,insertable = false)
    public ProblemParam problem_param;

    public ProblemParamValue() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
