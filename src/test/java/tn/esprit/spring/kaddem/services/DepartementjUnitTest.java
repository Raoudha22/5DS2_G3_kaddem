package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DepartementjUnitTest {

    @InjectMocks
    private DepartementServiceImpl DepartementService;

    @Mock
    private DepartementRepository DepartementRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllDepartements() {
        // Given
        Departement departement1 = new Departement(/* initialize with appropriate parameters */);
        Departement departement2 = new Departement(/* initialize with appropriate parameters */);
        when(DepartementRepository.findAll()).thenReturn(Arrays.asList(departement1, departement2));

        // When
        List<Departement> departements = DepartementService.retrieveAllDepartements();

        // Then
        assertThat(departements).isNotEmpty();
        assertThat(departements.size()).isEqualTo(2);
        verify(DepartementRepository, times(1)).findAll();
    }

    @Test
    void testAddDepartement() {
        // Given
        Departement departement = new Departement();
        when(DepartementRepository.save(any(Departement.class))).thenReturn(departement);

        // When
        Departement createdDepartement = DepartementService.addDepartement(departement);

        // Then
        assertThat(createdDepartement).isNotNull();
        verify(DepartementRepository, times(1)).save(departement);
    }

    @Test
    void testUpdateDepartement() {
        // Given
        Departement departement = new Departement();
        when(DepartementRepository.save(departement)).thenReturn(departement);

        // When
        Departement updatedDepartement = DepartementService.updateDepartement(departement);

        // Then
        assertThat(updatedDepartement).isNotNull();
        verify(DepartementRepository, times(1)).save(departement);
    }

    @Test
    void testRetrieveDepartement() {
        // Given
        Departement departement = new Departement(1,"Science");
        when(DepartementRepository.findById(1)).thenReturn(Optional.of(departement));

        // When
        Departement retrievedDepartement = DepartementService.retrieveDepartement(1);

        // Then
        assertThat(retrievedDepartement).isNotNull();
        assertThat(retrievedDepartement.getIdDepart()).isEqualTo(1);  // Adjust as necessary
        verify(DepartementRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteDepartement() {
        // Given

        Departement departement = new Departement();
        when(DepartementRepository.findById(1)).thenReturn(Optional.of(departement));

        // When
        DepartementService.deleteDepartement(1);

        // Then
        verify(DepartementRepository, times(1)).delete(departement);
    }
}
