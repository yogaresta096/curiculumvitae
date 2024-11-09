package com.handayanto.curiculumvitae.controller;

import com.handayanto.curiculumvitae.model.ErrorResponse;
import com.handayanto.curiculumvitae.model.Personal;
import com.handayanto.curiculumvitae.service.PersonalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/cv/personal")
public class CuriculumVitaeController {
    private final PersonalService personalService;
    private static final Logger logger = LoggerFactory.getLogger(CuriculumVitaeController.class);

    @Autowired
    public CuriculumVitaeController(PersonalService personalService){
        this.personalService = personalService;
    }

    @GetMapping
    public ResponseEntity<List<Personal>> getAllPersonal(){
        logger.info("===personalService====");
        return ResponseEntity.ok(personalService.getAllPersonal());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPersonalById(@PathVariable Long id) {
        return personalService.getPersonalById(id)
                .map(personal -> ResponseEntity.ok(personal))
                .orElseGet(() -> ResponseEntity.status(404).body(new ErrorResponse(
                        LocalDateTime.now(),
                        404,
                        "Not Found",
                        "ID " + id + " tidak ditemukan",
                        "/api/cv/personal/" + id
                )));
    }
    @PostMapping
    public ResponseEntity<Personal> createPersonal(@RequestBody Personal personal){
        return ResponseEntity.ok(personalService.createPersonal(personal));
    }
}
