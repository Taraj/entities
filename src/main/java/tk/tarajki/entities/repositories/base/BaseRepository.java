package tk.tarajki.entities.repositories.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import tk.tarajki.entities.models.base.BaseModel;

@NoRepositoryBean
public interface BaseRepository<T extends BaseModel, ID> extends JpaRepository<T, ID> {
    default T findByIdRequired(ID id) {
        return findById(id).orElseThrow(RuntimeException::new);
    }
}
