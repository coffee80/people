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

    // abbiamo stabilito che la miopia può essere editaria 
    // e ci interessa tracciare fino a due generazioni indietro (padri e nonni)
    // trovate tutte le persone che abbiano almeno un antenato miope

    

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
