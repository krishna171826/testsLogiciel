package com.example.demo.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class VoitureTest {

    private Voiture v;

    @BeforeEach
    void setUp() {
        // Utilise le constructeur existant : Voiture(String marque, int prix)
        v = new Voiture("Peugeot", 15000);
    }

    @Test
    @DisplayName("Le constructeur et les getters doivent retourner les valeurs attendues")
    void creerVoiture() { // Renommé ici pour correspondre au test qui échouait
        assertNotNull(v);
        assertEquals("Peugeot", v.getMarque());
        assertEquals(15000, v.getPrix());
        assertEquals(0, v.getId()); // Par défaut, l'id n'est pas initialisé dans le constructeur (donc 0)
    }

    @Test
    @DisplayName("Les setters doivent correctement modifier les attributs")
    void setters() {
        v.setMarque("Renault");
        v.setPrix(16000);
        v.setId(123);

        assertEquals("Renault", v.getMarque());
        assertEquals(16000, v.getPrix());
        assertEquals(123, v.getId());
    }

    @Test
    @DisplayName("Le constructeur par défaut doit instancier un objet vide")
    void constructeurParDefaut() {
        Voiture voitureVide = new Voiture();
        assertNull(voitureVide.getMarque());
        assertEquals(0, voitureVide.getPrix());
        assertEquals(0, voitureVide.getId());
    }

    @Test
    @DisplayName("La méthode toString doit contenir les données clés")
    void toStringContainsData() {
        v.setId(42);
        String s = v.toString();

        // Vérification par rapport au format exact : Car{marque='Peugeot', prix=15000,
        // id=42}
        assertTrue(s.contains("Car"));
        assertTrue(s.contains("Peugeot"));
        assertTrue(s.contains("15000"));
        assertTrue(s.contains("42"));
    }
}