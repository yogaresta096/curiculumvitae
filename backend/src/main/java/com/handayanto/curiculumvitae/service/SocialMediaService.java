package com.handayanto.curiculumvitae.service;

import com.handayanto.curiculumvitae.model.SocialMedia;
import com.handayanto.curiculumvitae.repository.SocialMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialMediaService {
    private final SocialMediaRepository socialMediaRepository;

    @Autowired
    public SocialMediaService(SocialMediaRepository socialMediaRepository){
        this.socialMediaRepository = socialMediaRepository;
    }

    public List<SocialMedia> getAllSocialMedia(){
        return  socialMediaRepository.findAll();
    }
}
