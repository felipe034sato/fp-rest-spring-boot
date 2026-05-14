package br.com.felipe034sato.controllers;

import br.com.felipe034sato.data.DTO.v1.PersonDTO;
import br.com.felipe034sato.data.DTO.v2.PersonDTOV2;
import br.com.felipe034sato.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/person/v1")
public class PersonController {

    @Autowired //instanciar o services quando precisar
    private PersonServices services; // because of dados injection
    //private PersonServices services = new PersonController();

//    //Se nao tiver parametro vai no default
//    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<Person> findAll(){
//        return services.findAll();
//    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) //Melhor forma de fazer pq é um Alias, isso faz com que ele retorne o Status correto ao inves de so 200 OK
    public List<PersonDTO> findAll(){
        return services.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO findById(@PathVariable("id") Long id){
        var person = services.findById(id);
        person.setBirthday(new Date());
        return person;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)//Swagger to not getting lost
    public PersonDTO create(@RequestBody PersonDTO person){ // to get data from body
        return services.create(person);
    }

    @PostMapping(value ="/v2", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)//Swagger to not getting lost
    public PersonDTOV2 create(@RequestBody PersonDTOV2 person){
        return services.createV2(person);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO update(@RequestBody PersonDTO person){
        return services.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){

        services.delete(id);
        return ResponseEntity.noContent().build();
    }
}

