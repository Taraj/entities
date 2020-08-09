package tk.tarajki.entities.dtos;

import tk.tarajki.entities.models.Dog;

public class DogDto {
    private Long id;

    private Long createdAt;

    private String name;

    private Integer age;

    public DogDto(Dog dog) {
        this.id = dog.getId();
        this.createdAt = dog.getCreatedAt().toEpochMilli();
        this.name = dog.getName();
        this.age = dog.getAge();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
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
