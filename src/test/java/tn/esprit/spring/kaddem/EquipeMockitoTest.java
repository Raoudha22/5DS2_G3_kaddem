package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipeMockitoTest {

    @Mock
    private EquipeRepository equipeRepository;

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllEquipes() {
        List<Equipe> equipes = new ArrayList<>();
        when(equipeRepository.findAll()).thenReturn(equipes);

        List<Equipe> result = equipeService.retrieveAllEquipes();

        assertNotNull(result);
        assertEquals(equipes, result);
        verify(equipeRepository, times(1)).findAll();
    }

    @Test
    void testAddEquipe() {
        Equipe equipe = new Equipe();
        when(equipeRepository.save(equipe)).thenReturn(equipe);

        Equipe result = equipeService.addEquipe(equipe);

        assertNotNull(result);
        assertEquals(equipe, result);
        verify(equipeRepository, times(1)).save(equipe);
    }

    @Test
    void testDeleteEquipe() {
        Integer equipeId = 1;
        Equipe equipe = new Equipe();
        when(equipeRepository.findById(equipeId)).thenReturn(Optional.of(equipe));

        equipeService.deleteEquipe(equipeId);

        verify(equipeRepository, times(1)).delete(equipe);
    }

    @Test
    void testRetrieveEquipe() {
        Integer equipeId = 1;
        Equipe equipe = new Equipe();
        when(equipeRepository.findById(equipeId)).thenReturn(Optional.of(equipe));

        Equipe result = equipeService.retrieveEquipe(equipeId);

        assertNotNull(result);
        assertEquals(equipe, result);
        verify(equipeRepository, times(1)).findById(equipeId);
    }
}
