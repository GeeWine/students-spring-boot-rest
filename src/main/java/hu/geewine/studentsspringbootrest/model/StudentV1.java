package hu.geewine.studentsspringbootrest.model;

import javax.persistence.*;

@Entity
public class StudentV1 extends Student {

    private String name;

    public StudentV1() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
