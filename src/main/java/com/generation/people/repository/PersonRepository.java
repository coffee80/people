package com.generation.people.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.generation.people.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

    @Query("SELECT p FROM Person p WHERE p.father.id = :fatherId AND p.mother.id = :motherId")
    List<Person> findByMotherIdAndFatherId(int fatherId, int motherId);

    // 1 
    // abbiamo stabilito che la miopia può essere editaria 
    // e ci interessa tracciare fino a due generazioni indietro (padri e nonni)
    // trovate tutte le persone che abbiano almeno un antenato miope


    // 2 
    // dato il nome di un tratto, trovare tutti gli individui che lo posseggono o i cui genitori lo posseggono

    // 3
    // trovare l'età media a cui una donna nata dopo il 1980 ha avuto il primo figlio
    // sviluppare questo metodo lavorando sia su repository (ad esempio, findByGenderAndBirthYear) che sulla entità
    // ad esempio, in Person, un metodo getAgeAtFirstChild(), età della persona al primo figlio, se c'è stato!
    // e poi in service, oppure sviluppare tutto in repository se si preferisce

    // 4
    // trovare tutti i cugini primi di una persona data 

    // 5
    // trovare tutte le donne che abbiano avuto figli da uomini diversi

    // 6
    // trovare tutte le persone i cui genitori abbiano una differenza di età superiore ad n anni
    // con n parametro


    default List<Person> getBrothers(int id){
        Optional<Person> person = findById(id);
        if(person.isEmpty())
            return new ArrayList<Person>();
        Person p = person.get();
        int fatherId = p.getFather().getId();
        int motherId = p.getMother().getId();
        List<Person> res = findByMotherIdAndFatherId(fatherId, motherId);
        res.remove(p);
        return res;
    }


}
