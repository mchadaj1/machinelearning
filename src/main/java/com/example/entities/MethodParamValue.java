package com.example.entities;

import javax.persistence.*;

/**
 * Created by mateusz on 06.03.16.
 */
@Entity
@Table(name="method_param_values")
public class MethodParamValue {


    public MethodParamValue(Long param_id, Long configuration_id, String value) {
        this.methodParamId = param_id;
        this.methodConfigurationId = configuration_id;
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

    private String param_name;

    private String value;
    @Column(name="method_configurations_id",updatable = false)
    private Long methodConfigurationId;


    @Column(name="method_param_id",updatable = false)
    private Long methodParamId;
//    @ManyToOne
//    @JoinColumn(name="method_configurations_id",referencedColumnName = "id",nullable = false,updatable = false,insertable = false)
//    public MethodConfiguration method_configuration;

    @ManyToOne
    @JoinColumn(name="method_param_id",referencedColumnName = "id",nullable = false,updatable = false,insertable = false)
    public MethodParam method_param;

    public MethodParamValue() {
        super();
    }

    public String getName() {
        return param_name;
    }

    public void setName(String name) {
        this.param_name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getMethodConfigurationId() {
        return methodConfigurationId;
    }

    public void setMethodConfigurationId(Long methodConfigurationId) {
        this.methodConfigurationId = methodConfigurationId;
    }

    public Long getMethodParamId() {
        return methodParamId;
    }

    public void setMethodParamId(Long methodParamId) {
        this.methodParamId = methodParamId;
    }
}
