package com.handayanto.curiculumvitae.controller;

import com.handayanto.curiculumvitae.model.Experiences;
import com.handayanto.curiculumvitae.service.ExperiencesService;
import io.vertx.core.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cv/experience")
public class ExperienceController {
    private final ExperiencesService experiencesService;

    @Autowired
    public ExperienceController(ExperiencesService experiencesService){
        this.experiencesService = experiencesService;
    }

    @GetMapping
    public ResponseEntity<List<Experiences>> getAllExperience(){
        return ResponseEntity.ok(experiencesService.getAllExperience());
    }

    @GetMapping("{idExperience}")
    public Optional<ResponseEntity<String>> getExperienceById(@PathVariable Long idExperience){
        return experiencesService.getExperienceById(idExperience)
                .map(experiences -> {
                    String responseExperience = Json.encode(experiences);
                    return ResponseEntity.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(responseExperience);
                });
    }

}
