package fr.istic.taa.jaxrs.domain;


import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
public class Admin extends User{

    public Admin(String name, String email) {
        super(name, email);
    }

    public Admin(){};
}
