package tk.tarajki.entities.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.UniqueElements;
import tk.tarajki.entities.models.Cat;


import org.hibernate.validator.constraints.Length;
import tk.tarajki.entities.validators.Password;

import javax.validation.constraints.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static io.swagger.annotations.ApiModelProperty.AccessMode;

@ApiModel("CatDto")
public class CatDto {
    @ApiModelProperty(accessMode = AccessMode.READ_ONLY)
    private Long id;

    @ApiModelProperty
@FutureOrPresent
    private Instant createdAt;

    @ApiModelProperty
    @Size(max = 12)
    private List<@Length(max = 3) String> createdAtt;


    @ApiModelProperty
    @NotEmpty
   // @Length(max = 2)
    private String name;

    @ApiModelProperty()
    @Positive
    private Integer age;

    @Password
    @NotEmpty
    @ApiModelProperty(required = true)
    private String password;

    public CatDto(Cat cat) {
        this.id = cat.getId();
        this.createdAt = cat.getCreatedAt();
        this.name = cat.getName();
        this.age = cat.getAge();
    }


    public CatDto() {

    }

    public Long getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public List<String> getCreatedAtt() {
        return createdAtt;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }
}
