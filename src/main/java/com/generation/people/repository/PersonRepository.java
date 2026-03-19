package com.generation.people.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.generation.people.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

    // 1 - SQL legge RIGHE, JPQL legge oggetti
    // 2 la sintassi non è identica. Io posso usare le RELAZIONI in JPQL e lo sto facendo ora
    // lui tradurrà questa roba in una query SQL del DBMS sottostante
    @Query("SELECT p FROM Person p WHERE p.father.id = :fatherId AND p.mother.id = :motherId")
    List<Person> findByMotherIdAndFatherId(int fatherId, int motherId);

    /*
        select 
                child.*
        from
                person as child inner join 
                person as father
        where
                father.name = :fatherName
    
    */
    @Query("SELECT p FROM Person p WHERE p.father.firstName = :fatherName")
    List<Person> findSonsOfFather(String fatherName);

    @Query("SELECT p FROM Person p where :traitName in (select t.name from Trait t where t.person.id = p.id)")
    List<Person> findByTrait(String traitName);

    // 1 trovare tutte le persone che abbiano uno fra due tratti specifici 
    // esempio: miopia o presbiopia

    // 2
    // trovare l'età media a cui una donna nata dopo il 1980 ha avuto il primo figlio
    // sviluppare questo metodo lavorando sia su repository (ad esempio, findByGenderAndBirthYear) che sulla entità
    // ad esempio, in Person, un metodo getAgeAtFirstChild(), età della persona al primo figlio, se c'è stato!
    // e poi in service, oppure sviluppare tutto in repository se si preferisce

    // 3
    // trovare tutti i cugini primi di una persona data 

    // 4
    // trovare tutte le donne che abbiano avuto figli da uomini diversi

    // 5
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
