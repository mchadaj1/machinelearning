package com.example.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mateusz on 06.03.16.
 */
@Entity
@Table(name="method_configurations")
public class MethodConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int method_id;




    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    @ManyToOne(optional=false)
    @JoinColumn(name="method_id",referencedColumnName="id" ,insertable = false, updatable = false)
    private Method method;

    public MethodConfiguration() {
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

    public int getMethod_id() {
        return method_id;
    }

    public void setMethod_id(int method_id) {
        this.method_id = method_id;
    }

    @OneToMany(mappedBy = "methodConfigurationId")
    public List<MethodParamValue> methodParamValues;



}
