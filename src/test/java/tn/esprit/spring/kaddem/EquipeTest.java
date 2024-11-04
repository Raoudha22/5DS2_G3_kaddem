package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.services.EquipeServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipeTest {

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
        // Arrange
        Equipe equipe1 = new Equipe();
        Equipe equipe2 = new Equipe();
        when(equipeRepository.findAll()).thenReturn(Arrays.asList(equipe1, equipe2));

        // Act
        List<Equipe> equipes = equipeService.retrieveAllEquipes();

        // Assert
        assertNotNull(equipes);
        assertEquals(2, equipes.size());
        verify(equipeRepository, times(1)).findAll();
    }

    @Test
    void testAddEquipe() {
        // Arrange
        Equipe equipe = new Equipe();
        when(equipeRepository.save(equipe)).thenReturn(equipe);

        // Act
        Equipe result = equipeService.addEquipe(equipe);

        // Assert
        assertNotNull(result);
        verify(equipeRepository, times(1)).save(equipe);
    }

    @Test
    void testDeleteEquipe() {
        // Arrange
        Integer equipeId = 1;
        Equipe equipe = new Equipe();
        when(equipeRepository.findById(equipeId)).thenReturn(Optional.of(equipe));

        // Act
        equipeService.deleteEquipe(equipeId);

        // Assert
        verify(equipeRepository, times(1)).findById(equipeId);
        verify(equipeRepository, times(1)).delete(equipe);
    }

    @Test
    void testRetrieveEquipe() {
        // Arrange
        Integer equipeId = 1;
        Equipe equipe = new Equipe();
        when(equipeRepository.findById(equipeId)).thenReturn(Optional.of(equipe));

        // Act
        Equipe result = equipeService.retrieveEquipe(equipeId);

        // Assert
        assertNotNull(result);
        assertEquals(equipe, result);
        verify(equipeRepository, times(1)).findById(equipeId);
    }

    @Test
    void testUpdateEquipe() {
        // Arrange
        Equipe equipe = new Equipe();

        when(equipeRepository.save(equipe)).thenReturn(equipe);

        // Act
        Equipe result = equipeService.updateEquipe(equipe);

        // Assert
        assertNotNull(result);
        assertEquals(equipe, result);
        verify(equipeRepository, times(1)).save(equipe);
    }
}
