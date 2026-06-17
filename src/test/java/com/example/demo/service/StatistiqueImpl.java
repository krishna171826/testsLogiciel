package com.example.demo.service;

import com.example.demo.data.Voiture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatistiqueImplTest {

    private StatistiqueImpl statistiqueService;

    @BeforeEach
    void setUp() {
        statistiqueService = new StatistiqueImpl();
    }

    @Test
    @DisplayName("ajouter() doit incrémenter correctement la liste interne de voitures")
    void testAjouterVoiture() {
        Voiture v = new Voiture("Peugeot", 15000);

        assertThrows(ArithmeticException.class, () -> statistiqueService.prixMoyen());

        statistiqueService.ajouter(v);

        Echantillon echantillon = statistiqueService.prixMoyen();
        assertNotNull(echantillon);
        assertEquals(1, echantillon.getNombreDeVoitures());
    }

    @Test
    @DisplayName("prixMoyen() doit calculer correctement la moyenne et le nombre de voitures (Cas Nominal)")
    void testPrixMoyenCasNominal() {
        statistiqueService.ajouter(new Voiture("Peugeot", 10000));
        statistiqueService.ajouter(new Voiture("Renault", 20000));
        statistiqueService.ajouter(new Voiture("Citroën", 30000));


        Echantillon echantillon = statistiqueService.prixMoyen();

        assertNotNull(echantillon, "L'échantillon retourné ne doit pas être null");
        assertEquals(3, echantillon.getNombreDeVoitures(), "Le nombre de voitures est incorrect");
        assertEquals(20000, echantillon.getPrixMoyen(), "Le prix moyen calculé est incorrect");
    }

    @Test
    @DisplayName("prixMoyen() doit lever une ArithmeticException si la liste est vide (Cas Limite)")
    void testPrixMoyenListeVide() {

        assertThrows(ArithmeticException.class, () -> {
            statistiqueService.prixMoyen();
        }, "Une ArithmeticException était attendue à cause de la division par zéro (0 voiture).");
    }
}
