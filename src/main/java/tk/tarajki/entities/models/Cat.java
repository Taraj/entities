package tk.tarajki.entities.models;

import tk.tarajki.entities.models.base.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "cats")
public class Cat extends BaseModel {

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Cat(String name, Integer age, Owner owner) {
        this.name = name;
        this.age = age;
        this.owner = owner;
    }

    public Cat() {
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
