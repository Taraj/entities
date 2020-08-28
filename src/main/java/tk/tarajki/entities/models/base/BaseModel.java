package tk.tarajki.entities.models.base;

import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@MappedSuperclass
public abstract class BaseModel implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Version
    @Column(name = "version")
    private Long version;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    public Long getVersion() {
        return version;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseModel baseModel = (BaseModel) o;

        return Objects.equals(id, baseModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final var thisClass = Hibernate.getClass(this);
        return thisClass.getSimpleName() + " extends BaseModel {\n" +
                getAllFields(new ArrayList<>(), thisClass).stream()
                        .filter(field -> field.isAnnotationPresent(Column.class))
                        .map(field -> {
                                    field.setAccessible(true);
                                    try {
                                        return "\t" + field.getName() + "=" + field.get(this).toString();
                                    } catch (IllegalAccessException e) {
                                        return "\t" + field.getName() + "=ERR";
                                    }
                                }
                        )
                        .collect(Collectors.joining("\n")) +
                "\n}";
    }

    private static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(0, Arrays.asList(type.getDeclaredFields()));
        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }
        return fields;
    }

}
