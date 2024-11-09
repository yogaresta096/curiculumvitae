package com.handayanto.curiculumvitae.controller;

import com.handayanto.curiculumvitae.model.ErrorResponse;
import com.handayanto.curiculumvitae.model.Personal;
import com.handayanto.curiculumvitae.service.PersonalService;
import io.vertx.core.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
    public ResponseEntity<String> getPersonalById(@PathVariable Long id) {
        logger.info("===personalService====");
        return personalService.getPersonalById(id)
                .map(personal -> {
                    String responsePersonal = Json.encode(personal);
                    return ResponseEntity.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(responsePersonal);
                })
                .orElseGet(() -> {
                    long timestamp = new Date().getTime();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                    String formattedDate = formatter.format(new Date(timestamp));

                    String responseErrorPersonal = Json.encode(new ErrorResponse(
                            formattedDate,
                            "Not Found",
                            "Data Not Found Please input with valid data"
                    ));
                    return ResponseEntity.status(404)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(responseErrorPersonal);
                });
    }
    @PostMapping
    public ResponseEntity<Personal> createPersonal(@RequestBody Personal personal){
        return ResponseEntity.ok(personalService.createPersonal(personal));
    }
}
