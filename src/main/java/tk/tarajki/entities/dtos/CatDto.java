package tk.tarajki.entities.dtos;

import tk.tarajki.entities.models.Cat;

import java.time.Instant;

public class CatDto {
    private Long id;

    private Instant createdAt;

    private String name;

    private Integer age;

    public CatDto(Cat cat) {
        this.id = cat.getId();
        this.createdAt = cat.getCreatedAt();
        this.name = cat.getName();
        this.age = cat.getAge();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
