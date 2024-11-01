package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ContratServiceMockitoTest {

    @InjectMocks
    private ContratServiceImpl contratService;

    @Mock
    private ContratRepository contratRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddContrat() {
        // Given
        Contrat contrat = new Contrat(new Date(), new Date(), null, false, 1000);
        when(contratRepository.save(any(Contrat.class))).thenReturn(contrat);

        // When
        Contrat createdContrat = contratService.addContrat(contrat);

        // Then
        assertNotNull(createdContrat);
        assertEquals(1000, createdContrat.getMontantContrat());
        verify(contratRepository, times(1)).save(contrat);
    }

    @Test
    void testRetrieveContrat() {
        // Given
        Contrat contrat = new Contrat(1, new Date(), new Date(), null, false, 1000);
        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));

        // When
        Contrat retrievedContrat = contratService.retrieveContrat(1);

        // Then
        assertNotNull(retrievedContrat);
        assertEquals(1, retrievedContrat.getIdContrat());
        verify(contratRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateContrat() {
        // Given
        Contrat contrat = new Contrat(1, new Date(), new Date(), null, false, 1000);
        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));
        when(contratRepository.save(contrat)).thenReturn(contrat);

        // When
        contrat.setMontantContrat(1500);
        Contrat updatedContrat = contratService.updateContrat(contrat);

        // Then
        assertEquals(1500, updatedContrat.getMontantContrat());
        verify(contratRepository, times(1)).save(contrat);
    }

    @Test
    void testRemoveContrat() {
        // Given
        Contrat contrat = new Contrat(1, new Date(), new Date(), null, false, 1000);
        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));

        // When
        contratService.removeContrat(1);

        // Then
        verify(contratRepository, times(1)).delete(contrat);
    }
}
