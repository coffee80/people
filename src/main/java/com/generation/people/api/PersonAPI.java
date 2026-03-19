package com.generation.people.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.people.dto.PersonDTO;
import com.generation.people.service.PersonService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/people")
public class PersonAPI {

    @Autowired
    PersonService service;

    @GetMapping("/test")
    public PersonDTO load2(){
        return service.findById(2);
    }

    @GetMapping("/test2")
    public List<PersonDTO> getMatthewBrothers() {
        return service.findBrothers(2);
    }

    @GetMapping("/test3")
    public List<PersonDTO> getFerdinandosChildren() {
        return service.findSonsOf("Ferdinando");
    }

    @GetMapping("/test4")
    public List<PersonDTO> getMiopi(){
        return service.findByTrait("Miopia");
    }
    

}
