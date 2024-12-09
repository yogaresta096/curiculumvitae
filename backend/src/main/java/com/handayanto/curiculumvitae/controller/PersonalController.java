package com.handayanto.curiculumvitae.controller;

import com.handayanto.curiculumvitae.model.ErrorResponse;
import com.handayanto.curiculumvitae.model.Personal;
import com.handayanto.curiculumvitae.service.PersonalService;
import io.vertx.core.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/cv/personal")
public class PersonalController {
    private final PersonalService personalService;
    private static final Logger logger = LoggerFactory.getLogger(PersonalController.class);

    @Autowired
    public PersonalController(PersonalService personalService){
        this.personalService = personalService;
    }

    @GetMapping
    public ResponseEntity<List<Personal>> getAllPersonal(){
        logger.info("===Personal Service====");
        return ResponseEntity.ok(personalService.getAllPersonal());
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getPersonalById(@PathVariable Long id) {
        logger.info("===Personal Service====");
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
    @PostMapping("/create")
    public ResponseEntity<?> createPersonal(@Valid @RequestBody Personal personal, BindingResult bindingResult){
        logger.info("====Create Personal Service====");
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        try {
            Personal newPersonal = personalService.createPersonal(personal);
            return new ResponseEntity<>(newPersonal, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePersonal(@PathVariable Long id) {
        personalService.deletePersonal(id);
        return ResponseEntity.noContent().build();
    }
}
