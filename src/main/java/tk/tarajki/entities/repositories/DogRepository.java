package tk.tarajki.entities.repositories;

import tk.tarajki.entities.models.Dog;
import tk.tarajki.entities.repositories.base.DeletableBaseRepository;

public interface DogRepository extends DeletableBaseRepository<Dog, Long> {
}
