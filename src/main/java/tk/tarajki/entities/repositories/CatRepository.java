package tk.tarajki.entities.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import tk.tarajki.entities.models.Cat;
import tk.tarajki.entities.repositories.base.BaseRepository;

public interface CatRepository extends BaseRepository<Cat, Long> {

    Page<Cat> findAll(Specification<Cat> specification, Pageable pageable);
}
