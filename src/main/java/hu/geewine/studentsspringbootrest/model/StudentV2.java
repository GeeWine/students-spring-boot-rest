package hu.geewine.studentsspringbootrest.model;

import javax.persistence.*;

@Entity
public class StudentV2 extends Student {

    private String firstName;
    private String lastName;

    public StudentV2() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
