package com.handayanto.curiculumvitae.controller;

import com.handayanto.curiculumvitae.model.Personal;
import com.handayanto.curiculumvitae.service.PersonalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PersonalControllerTest {
    @InjectMocks
    private PersonalController personalController;

    @Mock
    private PersonalService personalService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPersonal() {
        when(personalService.getAllPersonal()).thenReturn(Collections.emptyList());
        ResponseEntity<List<Personal>> response = personalController.getAllPersonal();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Collections.emptyList(), response.getBody());
        verify(personalService, times(1)).getAllPersonal();
    }
}
