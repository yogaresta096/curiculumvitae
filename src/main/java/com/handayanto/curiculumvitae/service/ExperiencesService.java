package com.handayanto.curiculumvitae.service;

import com.handayanto.curiculumvitae.model.Experiences;
import com.handayanto.curiculumvitae.repository.ExperiencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ExperiencesService {
    private final ExperiencesRepository experiencesRepository;

    @Autowired
    public ExperiencesService(ExperiencesRepository experiencesRepository){
        this.experiencesRepository = experiencesRepository;
    }

    public List<Experiences> getAllExperience(){
        return experiencesRepository.findAll();
    }

    public Optional<Experiences> getExperienceById(Long idExperience){
        return experiencesRepository.findById(idExperience);
    }

    public Experiences updateExperience(Long idExperience, Experiences experiences){
        if (!experiencesRepository.existsById(idExperience)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Experience Cannot be null");
        }
            experiences.setIdExperience(idExperience);
            return experiencesRepository.save(experiences);
    }

    public Experiences createExperience(Experiences experiences){
        return experiencesRepository.save(experiences);
    }

    public void deleteExperience(Long idExperience){
        experiencesRepository.deleteById(idExperience);
    }
}
