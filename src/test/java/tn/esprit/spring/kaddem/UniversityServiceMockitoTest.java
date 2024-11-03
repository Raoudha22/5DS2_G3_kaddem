package tn.esprit.spring.kaddem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UniversiteServiceMockitoTest {

    @InjectMocks
    private UniversiteServiceImpl universiteService;
    private DepartementRepository departementRepository;
    @Mock
    private UniversiteRepository universiteRepository;

    @BeforeEach
    public void setUp() {
        // Initializes mocks before each test
    }

    @Test
    void testRetrieveAllUniversites() {
        // Given
        Universite universite = new Universite("University Name");
        when(universiteRepository.findAll()).thenReturn(Collections.singletonList(universite));

        // When
        List<Universite> universites = universiteService.retrieveAllUniversites();

        // Then
        assertThat(universites).isNotEmpty();
        assertThat(universites.get(0).getNomUniv()).isEqualTo("University Name");
        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    void testAddUniversite() {
        // Given
        Universite universite = new Universite("University Name");
        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);

        // When
        Universite createdUniversite = universiteService.addUniversite(universite);

        // Then
        assertThat(createdUniversite).isNotNull();
        assertThat(createdUniversite.getNomUniv()).isEqualTo("University Name");
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testRetrieveUniversite() {
        // Given
        Universite universite = new Universite(1, "University Name");
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        // When
        Universite retrievedUniversite = universiteService.retrieveUniversite(1);

        // Then
        assertThat(retrievedUniversite).isNotNull();
        assertThat(retrievedUniversite.getIdUniv()).isEqualTo(1);
        verify(universiteRepository, times(1)).findById(1);
    }

    @Test
    void testRetrieveUniversite_NotFound() {
        // Given
        when(universiteRepository.findById(1)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> {
            universiteService.retrieveUniversite(1);
        });
        verify(universiteRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateUniversite() {
        // Given
        Universite universite = new Universite(1, "Old University Name");
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));
        when(universiteRepository.save(universite)).thenReturn(universite);

        // When
        universite.setNomUniv("Updated University Name");
        Universite updatedUniversite = universiteService.updateUniversite(universite);

        // Then
        assertThat(updatedUniversite.getNomUniv()).isEqualTo("Updated University Name");
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testRemoveUniversite() {
        // Given
        Universite universite = new Universite(1, "University Name");
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        // When
        universiteService.deleteUniversite(1);

        // Then
        verify(universiteRepository, times(1)).delete(universite);
    }

    @Test
    void testAssignUniversiteToDepartement() {
        // Given
        Universite universite = new Universite(1, "University Name");
        Departement departement = new Departement(1, "Department Name");
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));
        when(universiteRepository.save(universite)).thenReturn(universite);

        // When
        universiteService.assignUniversiteToDepartement(1, 1);

        // Then
        assertThat(universite.getDepartements()).contains(departement);
        verify(universiteRepository, times(1)).save(universite);
    }
}

