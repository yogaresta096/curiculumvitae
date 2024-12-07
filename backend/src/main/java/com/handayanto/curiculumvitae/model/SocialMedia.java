package com.handayanto.curiculumvitae.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SocialMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "personal_id", insertable = false, updatable = false)
    private Long personalId;

    @Column(name = "name_of_media")
    private String nameOfMedia;

    @Column(name = "link_of_media")
    private String linkOfMedia;

    @ManyToOne
    @JoinColumn(name = "personal_id", nullable = false)
    protected Personal personal;

}
