package tk.tarajki.entities.repositories.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import tk.tarajki.entities.models.base.DeletableBaseModel;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface DeletableBaseRepository<T extends DeletableBaseModel, ID> extends BaseRepository<T, ID> {

    @Query("SELECT e FROM #{#entityName} e WHERE e.deleted = false AND e.id = :id")
    Optional<T> findByIdAndNotDeleted(@Param("id") ID id);

    default T findByIdAndNotDeletedRequired(ID id) {
        return findByIdAndNotDeleted(id).orElseThrow(RuntimeException::new);
    }


    @Query("SELECT COUNT(e) > 0 FROM #{#entityName} e WHERE e.deleted = false AND e.id = :id")
    boolean existsByIdNotDeleted(@Param("id") ID id);



    @Query("SELECT e FROM #{#entityName} e WHERE e.deleted = false")
    List<T> findAllNotDeleted();

    @Query(
        value = "SELECT e FROM #{#entityName} e WHERE e.deleted = false",
        countQuery = "SELECT COUNT(e) FROM #{#entityName} e WHERE e.deleted = false"
    )
    Page<T> findAllNotDeleted(Pageable pageable);



    @Modifying
    @Query("UPDATE #{#entityName} e SET e.deleted = true WHERE e.id = :id")
    void softDelete(@Param("id") ID id);

    @Modifying
    @Query("UPDATE #{#entityName} e SET e.deleted = true WHERE e = :entity")
    void softDelete(@Param("entity") T entity);

    @Modifying
    @Query("UPDATE #{#entityName} e SET e.deleted = true WHERE e IN :entities")
    void  softDelete(@Param("entities") Iterable<? extends T> entities);



    @Query("SELECT COUNT(e) FROM #{#entityName} e WHERE e.deleted = false")
    long countNotDeleted();
}
