package tn.esprit.spring.kaddem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

class UniversiteServiceImplTest {

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    @BeforeEach
    public void setUp() {
        // Initializes mocks before each test
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

    // Other test methods...
}
