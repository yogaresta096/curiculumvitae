package com.handayanto.curiculumvitae.controller;

import com.handayanto.curiculumvitae.model.Experiences;
import com.handayanto.curiculumvitae.service.ExperiencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cv/experiences")
public class ExperienceController {
    private final ExperiencesService experiencesService;

    @Autowired
    public ExperienceController(ExperiencesService experiencesService) {
        this.experiencesService = experiencesService;
    }

    @GetMapping
    public ResponseEntity<List<Experiences>> getAllExperience() {
        return ResponseEntity.ok(experiencesService.getAllExperience());
    }

    @GetMapping("{idExperience}")
    public ResponseEntity<Experiences> getExperienceById(@PathVariable Long idExperience) {
        return experiencesService.getExperienceById(idExperience)
                .map(experience -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(experience))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Experiences> createExperience(@RequestBody Experiences experiences) {
        if (experiences == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Experiences createdExperience = experiencesService.createExperience(experiences);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExperience);
    }

    @PutMapping("{idExperience}")
    public ResponseEntity<Experiences> updateExperience(@PathVariable Long idExperience, @RequestBody Experiences experiences) {
        Experiences updatedExperience = experiencesService.updateExperience(idExperience, experiences);
        if (updatedExperience == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedExperience);
    }

    @DeleteMapping("{idExperience}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long idExperience) {
        experiencesService.deleteExperience(idExperience);
        return ResponseEntity.noContent().build(); // Mengembalikan 204 No Content setelah penghapusan
    }
}