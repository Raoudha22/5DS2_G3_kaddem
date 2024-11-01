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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

 class ContratServiceImplTest {

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
        assertThat(createdContrat).isNotNull();
        assertThat(createdContrat.getMontantContrat()).isEqualTo(1000);
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
        assertThat(retrievedContrat).isNotNull();
        assertThat(retrievedContrat.getIdContrat()).isEqualTo(1);
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
        assertThat(updatedContrat.getMontantContrat()).isEqualTo(1500);
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
