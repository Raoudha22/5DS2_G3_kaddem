package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartementMockitoTest {

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementServiceImpl departementService;

    private Departement departement;

    @BeforeEach
    void setUp() {
        // Initialize a sample Departement object before each test
        departement = new Departement(1,"IT");

    }

    @Test
    public void testRetrieveAllDepartements() {
        // Arrange
        List<Departement> departementList = Arrays.asList(departement);
        when(departementRepository.findAll()).thenReturn(departementList);

        // Act
        List<Departement> result = departementService.retrieveAllDepartements();

        // Assert
        assertEquals(1, result.size());
        assertEquals(departement, result.get(0));
        verify(departementRepository, times(1)).findAll();
    }

    @Test
    public void testAddDepartement() {
        // Arrange
        when(departementRepository.save(departement)).thenReturn(departement);

        // Act
        Departement result = departementService.addDepartement(departement);

        // Assert
        assertEquals(departement, result);
        verify(departementRepository, times(1)).save(departement);
    }

    @Test
    public void testUpdateDepartement() {
        // Arrange
        when(departementRepository.save(departement)).thenReturn(departement);

        // Act
        Departement result = departementService.updateDepartement(departement);

        // Assert
        assertEquals(departement, result);
        verify(departementRepository, times(1)).save(departement);
    }

    @Test
    public void testRetrieveDepartement() {
        // Arrange
        int idDepart = 1;
        when(departementRepository.findById(idDepart)).thenReturn(Optional.of(departement));

        // Act
        Departement result = departementService.retrieveDepartement(idDepart);

        // Assert
        assertEquals(departement, result);
        verify(departementRepository, times(1)).findById(idDepart);
    }

    @Test
    public void testDeleteDepartement() {
        // Arrange
        int idDepart = 1;
        when(departementRepository.findById(idDepart)).thenReturn(Optional.of(departement));

        // Act
        departementService.deleteDepartement(idDepart);

        // Assert
        verify(departementRepository, times(1)).delete(departement);
    }
}

