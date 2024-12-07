package com.handayanto.curiculumvitae.repository;

import com.handayanto.curiculumvitae.model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {
}
