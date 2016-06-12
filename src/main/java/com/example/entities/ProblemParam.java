package com.example.entities;

import javax.persistence.*;

/**
 * Created by mateusz on 04.03.16.
 */
@Entity
@Table(name="problem_params")
public class ProblemParam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(updatable = false,name="problems_id")
    private Long problemsid;

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

    public Long getProblemsid() {
        return problemsid;
    }

    public void setProblemsid(Long problems_id) {
        this.problemsid = problems_id;
    }

    //    @EmbeddedId
//    PrimaryKey primaryKey;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('INT', 'DOUBLE', 'STRING','BOOLEAN')")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "problems_id",referencedColumnName = "id",nullable = false,updatable = false,insertable = false)
    public Problem problem;


    @Override
    public String toString() {
        return "ProblemParam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", problemsid=" + problemsid +
                ", type=" + type +
                ", problem=" + problem +
                '}';
    }
}







