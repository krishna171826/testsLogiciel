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
        // Initialisation d'une nouvelle instance avant chaque test
        statistiqueService = new StatistiqueImpl();
    }

    @Test
    @DisplayName("ajouter() doit incrémenter correctement la liste interne de voitures")
    void testAjouterVoiture() {
        Voiture v = new Voiture("Peugeot", 15000);

        // On vérifie qu'au départ le calcul lève une exception (liste vide)
        assertThrows(ArithmeticException.class, () -> statistiqueService.prixMoyen());

        // On ajoute une voiture
        statistiqueService.ajouter(v);

        // Désormais, prixMoyen() ne doit plus planter et doit contenir 1 voiture
        Echantillon echantillon = statistiqueService.prixMoyen();
        assertNotNull(echantillon);
        assertEquals(1, echantillon.getNombreDeVoitures());
    }

    @Test
    @DisplayName("prixMoyen() doit calculer correctement la moyenne et le nombre de voitures (Cas Nominal)")
    void testPrixMoyenCasNominal() {
        // Ajout de plusieurs voitures avec des prix différents
        statistiqueService.ajouter(new Voiture("Peugeot", 10000));
        statistiqueService.ajouter(new Voiture("Renault", 20000));
        statistiqueService.ajouter(new Voiture("Citroën", 30000));

        // Calcul attendu :
        // Nombre = 3
        // Prix total = 10000 + 20000 + 30000 = 60000
        // Moyenne = 60000 / 3 = 20000
        Echantillon echantillon = statistiqueService.prixMoyen();

        assertNotNull(echantillon, "L'échantillon retourné ne doit pas être null");
        assertEquals(3, echantillon.getNombreDeVoitures(), "Le nombre de voitures est incorrect");
        assertEquals(20000, echantillon.getPrixMoyen(), "Le prix moyen calculé est incorrect");
    }

    @Test
    @DisplayName("prixMoyen() doit lever une ArithmeticException si la liste est vide (Cas Limite)")
    void testPrixMoyenListeVide() {
        // La liste interne de 'statistiqueService' est vide à ce stade (0 voiture)
        // L'opération 'prixTotal / nombreDeVoitures' va provoquer une division par zéro
        // (X / 0)

        assertThrows(ArithmeticException.class, () -> {
            statistiqueService.prixMoyen();
        }, "Une ArithmeticException était attendue à cause de la division par zéro (0 voiture).");
    }
}