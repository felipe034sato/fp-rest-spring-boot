package br.com.felipe034sato.services;

import br.com.felipe034sato.data.DTO.v1.PersonDTO;
import br.com.felipe034sato.data.DTO.v2.PersonDTOV2;
import br.com.felipe034sato.exception.ResourceNotFoundException;
import static br.com.felipe034sato.mapper.ObjectMapper.parseListObject;
import static br.com.felipe034sato.mapper.ObjectMapper.parseObject;

import br.com.felipe034sato.mapper.custom.PersonMapper;
import br.com.felipe034sato.model.Person;
import br.com.felipe034sato.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
//allow it to be injected other classes
public class PersonServices {
    // it will have the operations to register someone

    @Autowired
    private PersonMapper personMapper;

    private final AtomicLong counter = new AtomicLong();

    @Autowired //Annotation to inject
    PersonRepository repository;

    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    //Retorna uma lista
    public List<PersonDTO> findAll(){
        logger.info("Find All People");

         return parseListObject(repository.findAll(),  PersonDTO.class); //Para nao precisar fazer isso toda vez, tendo em vista que é estatico voce pode somente importar o metodo.
//        List<Person> people = new ArrayList<Person>();
//        for(int i = 0; i < 4; i++) {
//            Person person = mockPerson(i);
//            people.add(person);
//        }
//        return people;
    }


    //“This method will return a Person object”
    public PersonDTO findById(Long id){
        logger.info("Finding one person");

        var entity = repository.findById(id) .orElseThrow(() -> new ResourceNotFoundException("Not records found with this id"));
//        Person p = new  Person();
//        p.setId(counter.incrementAndGet());
//        p.setFirstName("Felipe");
//        p.setLastName("Sato");
//        p.setAddress("Rua Gememl");
//        p.setGender("Male");
//
//        return p;
        return parseObject(entity, PersonDTO.class);
    }

    //POST
    public PersonDTO create(PersonDTO person){
        logger.info("Creating Person");

        //O bd so conversa com entidade, entao voce precisa converter tudo para entidade
        var entity = parseObject(person, Person.class);

        return parseObject(repository.save(entity), PersonDTO.class);

//        return repository.save(person); // Ja salva no bd
    }

    public PersonDTOV2 createV2(PersonDTOV2 person){
        logger.info("Creating Person V2");

        var entity =  personMapper.convertDtoToentity(person);

        return personMapper.convertEntityToDto(repository.save(entity));

//        return repository.save(person); // Ja salva no bd
    }

    //UPDATE(PUT)
    public PersonDTO update(PersonDTO person){
        logger.info("Updating person");

        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Not records found with this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());


         return parseObject(repository.save(entity), PersonDTO.class);

    }

    //DELETE
    public void delete(Long id){ //Nao retorna nada
        logger.info("Deleting person");

        Person entity = repository.findById(id) .orElseThrow(() -> new ResourceNotFoundException("Not records found with this id"));

        repository.delete(entity);

    }


//    private Person mockPerson(int i) {
//        Person p = new  Person();
//        p.setId(counter.incrementAndGet());
//        p.setFirstName("FistName: " + i);
//        p.setLastName("LastName: " + i);
//        p.setAddress("Address: " + i);
//        p.setGender("Gender: " + i);
//
//        return p;
//    }
}
