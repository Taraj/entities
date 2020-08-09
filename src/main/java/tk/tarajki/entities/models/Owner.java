package tk.tarajki.entities.models;

import org.hibernate.annotations.Where;
import tk.tarajki.entities.models.base.BaseModel;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "owners")
public class Owner extends BaseModel {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "owner")
    Set<Cat> allCats;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    Set<Dog> allDogs;

    @Where(clause = "deleted = false")
    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "owner")
    Set<Dog> currentDogs;

    public Set<Dog> getAllDogs() {
        return allDogs;
    }

    public Set<Dog> getCurrentDogs() {
        return currentDogs;
    }

    public Set<Cat> getAllCats() {
        return allCats;
    }
}
