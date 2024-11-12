package com.handayanto.curiculumvitae.controller;

import com.handayanto.curiculumvitae.model.SocialMedia;
import com.handayanto.curiculumvitae.service.SocialMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/cv/socialmedia")
public class SocialMediaController {
    private final SocialMediaService socialMediaService;

    @Autowired
    public SocialMediaController(SocialMediaService socialMediaService){
        this.socialMediaService = socialMediaService;
    }

    @GetMapping
    public ResponseEntity<List<SocialMedia>> getAllSocialMedia(){
        return ResponseEntity.ok(socialMediaService.getAllSocialMedia());
    }
}
