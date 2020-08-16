package tk.tarajki.entities.dtos;

import tk.tarajki.entities.models.Owner;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class OwnerDto {
    private Long id;

    private Instant createdAt;

    private List<CatDto> cats;

    private List<DogDto> allDogs;

    private List<DogDto> currentDogs;

    public OwnerDto(Owner owner) {
        this.id = owner.getId();
        this.createdAt = owner.getCreatedAt();
        this.cats = owner.getAllCats().stream().map(CatDto::new).collect(Collectors.toList());
        this.allDogs = owner.getAllDogs().stream().map(DogDto::new).collect(Collectors.toList());
        this.currentDogs =  owner.getCurrentDogs().stream().map(DogDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public List<CatDto> getCats() {
        return cats;
    }

    public void setCats(List<CatDto> cats) {
        this.cats = cats;
    }

    public List<DogDto> getAllDogs() {
        return allDogs;
    }

    public void setAllDogs(List<DogDto> allDogs) {
        this.allDogs = allDogs;
    }

    public List<DogDto> getCurrentDogs() {
        return currentDogs;
    }

    public void setCurrentDogs(List<DogDto> currentDogs) {
        this.currentDogs = currentDogs;
    }
}
