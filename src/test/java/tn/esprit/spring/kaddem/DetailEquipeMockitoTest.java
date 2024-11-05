package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.repositories.DetailEquipeRepository;
import tn.esprit.spring.kaddem.services.DetailEquipeServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DetailEquipeMockitoTest {

    @InjectMocks
    private DetailEquipeServiceImpl detailEquipeService;

    @Mock
    private DetailEquipeRepository detailEquipeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddDetailEquipe() {
        // Given
        DetailEquipe detailEquipe = new DetailEquipe(101, "Research");
        when(detailEquipeRepository.save(any(DetailEquipe.class))).thenReturn(detailEquipe);

        // When
        DetailEquipe createdDetailEquipe = detailEquipeService.addDetailEquipe(detailEquipe);

        // Then
        assertNotNull(createdDetailEquipe);
        assertEquals(101, createdDetailEquipe.getSalle());
        assertEquals("Research", createdDetailEquipe.getThematique());
        verify(detailEquipeRepository, times(1)).save(detailEquipe);
    }

    @Test
    void testRetrieveDetailEquipe() {
        // Given
        DetailEquipe detailEquipe = new DetailEquipe(1, 101, "Research");
        when(detailEquipeRepository.findById(1)).thenReturn(Optional.of(detailEquipe));

        // When
        DetailEquipe retrievedDetailEquipe = detailEquipeService.retrieveDetailEquipe(1);

        // Then
        assertNotNull(retrievedDetailEquipe);
        assertEquals(1, retrievedDetailEquipe.getIdDetailEquipe());
        verify(detailEquipeRepository, times(1)).findById(1);
    }

    @Test
    void testRemoveDetailEquipe() {
        // Given
        DetailEquipe detailEquipe = new DetailEquipe(1, 101, "Research");
        when(detailEquipeRepository.findById(1)).thenReturn(Optional.of(detailEquipe));

        // When
        detailEquipeService.removeDetailEquipe(1);

        // Then
        verify(detailEquipeRepository, times(1)).delete(detailEquipe);
    }
}
