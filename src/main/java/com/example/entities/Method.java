package com.example.entities;

import javax.persistence.*;

/**
 * Created by mateusz on 05.03.16.
 */
@Entity
@Table(name="methods")
public class Method {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long user_id;

    public Long getProblem_id() {
        return problem_id;
    }

    public void setProblem_id(Long problem_id) {
        this.problem_id = problem_id;
    }

    private Long problem_id;

    private String name;

    private String description;

    private boolean ispublic;

    private String code;

    private String globals;

    private String imports;

    private String constructorcode;

    private String finishgame;


    public Method() {
    }

    public String getFinishgame() {
        return finishgame;
    }

    public void setFinishgame(String finishgame) {
        this.finishgame = finishgame;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGlobals() {
        return globals;
    }

    public void setGlobals(String globals) {
        this.globals = globals;
    }

    public String getImports() {
        return imports;
    }

    public void setImports(String imports) {
        this.imports = imports;
    }

    public String getConstructorcode() {
        return constructorcode;
    }

    public void setConstructorcode(String constructorcode) {
        this.constructorcode = constructorcode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public boolean ispublic() {
        return ispublic;
    }

    public void setIspublic(boolean ispublic) {
        this.ispublic = ispublic;
    }


    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    @ManyToOne(optional=false)
    @JoinColumn(name="problem_id",referencedColumnName="id" ,insertable = false, updatable = false)
    private Problem problem;
}
