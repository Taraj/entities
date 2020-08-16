package tk.tarajki.entities;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tk.tarajki.entities.dtos.DogDto;
import tk.tarajki.entities.dtos.OwnerDto;
import tk.tarajki.entities.dtos.TimeDto;
import tk.tarajki.entities.repositories.DogRepository;
import tk.tarajki.entities.repositories.OwnerRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Transactional
public class ExampleController {

    private final OwnerRepository ownerRepository;

    private final DogRepository dogsRepository;

    public ExampleController(OwnerRepository ownerRepository, DogRepository dogsRepository) {
        this.ownerRepository = ownerRepository;
        this.dogsRepository = dogsRepository;
    }


    @GetMapping("/owners")
    public List<OwnerDto> getAllOwners(){
        return ownerRepository.findAll().stream().map(OwnerDto::new).collect(Collectors.toList());
    }

    @GetMapping("/dogs")
    public List<DogDto> getAllDogs(){
        return dogsRepository.findAllNotDeleted().stream().map(DogDto::new).collect(Collectors.toList());
    }

    @PostMapping("/time")
    public void postTime(@RequestBody TimeDto dto){
       System.out.println(dto.getInstant());
    }

}
