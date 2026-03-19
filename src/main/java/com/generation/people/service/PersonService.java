package com.generation.people.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.people.dto.PersonDTO;
import com.generation.people.mapper.PersonMapper;
import com.generation.people.repository.PersonRepository;

@Service
public class PersonService {

    @Autowired    
    PersonRepository repo;

    @Autowired
    PersonMapper mapper;

    public PersonDTO findById(int id) {
        return repo.findById(id).map(mapper::toDTO).orElse(null);
    }

    public List<PersonDTO> findAll() {
        return repo.findAll().stream().map(mapper::toDTO).toList();
    }

    public List<PersonDTO> findBrothers(int id){
        return repo.getBrothers(id).stream().map(mapper::toDTO).toList();
    }

    public List<PersonDTO> findSonsOf(String fatherName){
        return repo.findSonsOfFather(fatherName).stream().map(mapper::toDTO).toList();
    }

    public List<PersonDTO> findByTrait(String trait){
        return repo.findByTrait(trait).stream().map(mapper::toDTO).toList();
    }

}
