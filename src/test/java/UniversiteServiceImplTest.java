package tn.esprit.spring.kaddem.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.*;

public class UniversiteServiceImplTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllUniversites() {
        // Given
        List<Universite> universites = Arrays.asList(new Universite(), new Universite());
        when(universiteRepository.findAll()).thenReturn(universites);

        // When
        List<Universite> result = universiteService.retrieveAllUniversites();

        // Then
        assertEquals(2, result.size(), "Should return all universities");
        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    public void testAddUniversite() {
        // Given
        Universite universite = new Universite();
        when(universiteRepository.save(universite)).thenReturn(universite);

        // When
        Universite result = universiteService.addUniversite(universite);

        // Then
        assertNotNull(result, "The saved university should not be null");
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    public void testAffectUniversiteToDepartement() {
        // Given
        Integer universiteId = 1;
        Integer departementId = 1;

        Universite universite = new Universite();
        universite.setIdUniversite(universiteId);
        universite.setDepartements(new HashSet<>());

        // Assuming departement repository and functionality are mocked and available
        Departement departement = new Departement();
        departement.setIdDepartement(departementId);

        when(universiteRepository.findById(universiteId)).thenReturn(Optional.of(universite));
        when(departementRepository.findById(departementId)).thenReturn(Optional.of(departement));
        when(universiteRepository.save(universite)).thenReturn(universite);

        // When
        Universite result = universiteService.affectUniversiteToDepartement(universiteId, departementId);

        // Then
        assertTrue(result.getDepartements().contains(departement), "The university should include the assigned department");
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    public void testCalculateStatisticsBetweenTwoDates() {
        // Given
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 30L * 24 * 60 * 60 * 1000); // 1 month later

        Universite universite1 = new Universite();
        Universite universite2 = new Universite();

        // Mocking some hypothetical statistics calculation for universities in the date range
        when(universiteRepository.findUniversitesByDateRange(startDate, endDate)).thenReturn(Arrays.asList(universite1, universite2));

        // When
        float result = universiteService.calculateStatisticsBetweenTwoDates(startDate, endDate);

        // Then
        float expected = 500.0f; // Example expected result based on your calculation logic
        assertEquals(expected, result, 0.1, "The calculated statistic should match the expected value");
        verify(universiteRepository, times(1)).findUniversitesByDateRange(startDate, endDate);
    }
}

