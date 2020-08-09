package tk.tarajki.entities.models;

import tk.tarajki.entities.models.base.DeletableBaseModel;

import javax.persistence.*;

@Entity
@Table(name = "dogs")
public class Dog extends DeletableBaseModel {

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Dog(String name, Integer age, Owner owner) {
        this.name = name;
        this.age = age;
        this.owner = owner;
    }

    public Dog() {
        // JPA
    }

    public String getName() {
        return name;
    }


    public Integer getAge() {
        return age;
    }

    public Owner getOwner() {
        return owner;
    }

}