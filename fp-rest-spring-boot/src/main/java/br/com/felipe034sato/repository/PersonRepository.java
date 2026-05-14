package br.com.felipe034sato.repository;

import br.com.felipe034sato.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    //Passa o Tipo(Class) e Id(tipo do id)
}
