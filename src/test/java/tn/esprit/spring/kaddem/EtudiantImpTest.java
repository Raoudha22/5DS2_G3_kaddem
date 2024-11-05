package tn.esprit.spring.kaddem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

class EtudiantImpTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllEtudiants() {
        Etudiant etudiant1 = new Etudiant();
        Etudiant etudiant2 = new Etudiant();
        when(etudiantRepository.findAll()).thenReturn(Arrays.asList(etudiant1, etudiant2));

        var etudiants = etudiantService.retrieveAllEtudiants();

        assertEquals(2, etudiants.size());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    void testAddEtudiant() {
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant savedEtudiant = etudiantService.addEtudiant(etudiant);

        assertNotNull(savedEtudiant);
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testUpdateEtudiant() {
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant updatedEtudiant = etudiantService.updateEtudiant(etudiant);

        assertNotNull(updatedEtudiant);
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    void testRetrieveEtudiant() {
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

        Etudiant retrievedEtudiant = etudiantService.retrieveEtudiant(1);

        assertNotNull(retrievedEtudiant);
        verify(etudiantRepository, times(1)).findById(1);
    }

    @Test
    void testRemoveEtudiant() {
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

        etudiantService.removeEtudiant(1);

        verify(etudiantRepository, times(1)).delete(etudiant);
    }

    @Test
    void testAssignEtudiantToDepartement() {
        Etudiant etudiant = new Etudiant();
        Departement departement = new Departement();
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        etudiantService.assignEtudiantToDepartement(1, 1);

        assertEquals(departement, etudiant.getDepartement());
        verify(etudiantRepository, times(1)).save(etudiant);
    }


    @Test
    void testGetEtudiantsByDepartement() {
        Etudiant etudiant = new Etudiant();
        when(etudiantRepository.findEtudiantsByDepartement_IdDepart(1))
                .thenReturn(Arrays.asList(etudiant));

        var etudiants = etudiantService.getEtudiantsByDepartement(1);

        assertEquals(1, etudiants.size());
        verify(etudiantRepository, times(1)).findEtudiantsByDepartement_IdDepart(1);
    }
}
