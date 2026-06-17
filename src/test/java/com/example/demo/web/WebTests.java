package com.example.demo.web;

import com.example.demo.data.Voiture;
import com.example.demo.service.Echantillon;
import com.example.demo.service.Statistique; // Import de l'interface
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WebTests {

    // 1. On mock l'interface demandée par le contrôleur
    @MockBean
    Statistique statistique;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetVoitures() throws Exception {
        // Préparation des données
        Echantillon echantillon = new Echantillon();
        echantillon.setNombreDeVoitures(2);
        echantillon.setPrixMoyen(2000);

        when(statistique.prixMoyen()).thenReturn(echantillon);

        mockMvc.perform(get("/statistique")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreDeVoitures").value(2))
                .andExpect(jsonPath("$.prixMoyen").value(2000));
    }

    @Test
    void testCreerVoiture() throws Exception {
        String voitureJson = "{\"marque\":\"f\",\"prix\":100}";

        mockMvc.perform(post("/voiture")
                .contentType(MediaType.APPLICATION_JSON)
                .content(voitureJson))
                .andExpect(status().isOk());

        verify(statistique, times(1)).ajouter(any(Voiture.class));
    }
}