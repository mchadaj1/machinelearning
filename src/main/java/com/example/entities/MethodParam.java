package com.example.entities;

import javax.persistence.*;

/**
 * Created by mateusz on 04.03.16.
 */
@Entity
@Table(name="method_params")
public class MethodParam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(updatable = false,name="methods_id")
    private Long methodsid;

    public enum Type
    {
        INT,DOUBLE,STRING,BOOLEAN;
    }

    public Type getType() {
        return type;
    }



    public void setType(Type type) {
        this.type = type;

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


    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('INT', 'DOUBLE', 'STRING','BOOLEAN')")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "methods_id",referencedColumnName = "id",nullable = false,updatable = false,insertable = false)
    public Method method;

    public Long getMethodsid() {
        return methodsid;
    }

    public void setMethodsid(Long methodsid) {
        this.methodsid = methodsid;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "MethodParam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", methodsid=" + methodsid +
                ", type=" + type +
                ", method=" + method +
                '}';
    }
}







