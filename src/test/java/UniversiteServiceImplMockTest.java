import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
//@SpringBootTest
//@AutoConfigureMockMvc
//@ExtendWith(MockitoExtension.class)
public class UniversiteServiceImplMockTest {

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    private Universite universite;

    @BeforeEach
    public void setUp() {
        universite = new Universite(1, "Test University");
    }

    @Test
    public void testAddUniversite() {
        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);
        Universite savedUniversite = universiteService.addUniversite(universite);
        assertNotNull(savedUniversite);
        assertEquals("Test University", savedUniversite.getNomUniv());
    }

    @Test
    public void testRetrieveUniversite() {
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));
        Universite foundUniversite = universiteService.retrieveUniversite(1);
        assertNotNull(foundUniversite);
        assertEquals("Test University", foundUniversite.getNomUniv());
    }

    @Test
    public void testRetrieveUniversite_NotFound() {
        when(universiteRepository.findById(1)).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            universiteService.retrieveUniversite(1);
        });
        assertEquals("Universite not found with id: 1", exception.getMessage());
    }

    // Add more tests for other methods as needed
}

