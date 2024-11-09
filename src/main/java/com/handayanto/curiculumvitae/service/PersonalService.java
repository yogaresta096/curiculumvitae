package com.handayanto.curiculumvitae.service;

import com.handayanto.curiculumvitae.model.Personal;
import com.handayanto.curiculumvitae.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalService {
    private final PersonalRepository personalRepository;
    @Autowired
    public PersonalService(PersonalRepository personalRepository){
        this.personalRepository = personalRepository;
    }

    public List<Personal> getAllPersonal(){
        return personalRepository.findAll();
    }

    public Optional<Personal> getPersonalById(Long id) {
        return personalRepository.findById(id);
    }

    public Personal updatePersonal(Long id, Personal personal){
        if (personalRepository.existsById(id)){
            personal.setId(id);
            return personalRepository.save(personal);
        }
        return null;
    }

    public Personal createPersonal(Personal personal) {
        return personalRepository.save(personal);
    }

    public void deletePersonal(Long id){
        personalRepository.deleteById(id);
    }

}
