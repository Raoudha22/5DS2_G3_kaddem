package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Specialite;

import java.util.Date;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class ContratTest {

    private Contrat contrat;
    private Date startDate;
    private Date endDate;
    private Etudiant etudiant;

    @BeforeEach
    public void setUp() {
        startDate = new Date();
        endDate = new Date(startDate.getTime() + (1000 * 60 * 60 * 24 * 30)); // End date 30 days after start date
        etudiant = new Etudiant("John", "Doe");
        contrat = new Contrat(startDate, endDate, Specialite.RESEAUX, false, 1000);
    }

    @Test
    public void testDefaultConstructor() {
        Contrat emptyContrat = new Contrat();
        assertNotNull(emptyContrat);
    }

    @Test
    public void testParameterizedConstructor() {
        Contrat parameterizedContrat = new Contrat(startDate, endDate, Specialite.SECURITE, true, 1500);
        assertEquals(startDate, parameterizedContrat.getDateDebutContrat());
        assertEquals(endDate, parameterizedContrat.getDateFinContrat());
        assertEquals(Specialite.SECURITE, parameterizedContrat.getSpecialite());
        assertTrue(parameterizedContrat.getArchive());
        assertEquals(1500, parameterizedContrat.getMontantContrat());
    }

    @Test
    public void testGettersAndSetters() {
        contrat.setIdContrat(1);
        contrat.setDateDebutContrat(startDate);
        contrat.setDateFinContrat(endDate);
        contrat.setSpecialite(Specialite.SECURITE);
        contrat.setArchive(true);
        contrat.setMontantContrat(2000);
        contrat.setEtudiant(etudiant);

        assertEquals(1, contrat.getIdContrat());
        assertEquals(startDate, contrat.getDateDebutContrat());
        assertEquals(endDate, contrat.getDateFinContrat());
        assertEquals(Specialite.SECURITE, contrat.getSpecialite());
        assertTrue(contrat.getArchive());
        assertEquals(2000, contrat.getMontantContrat());
        assertEquals(etudiant, contrat.getEtudiant());
    }

    @Test
    void testLinkWithEtudiant() {
        // Initialize the Contrats set in the Etudiant instance
        etudiant.setContrats(new HashSet<>()); // Create a new HashSet if it's null

        // Now you can add the contrat
        contrat.setEtudiant(etudiant); // Set the Etudiant for the Contrat
        etudiant.getContrats().add(contrat); // Add the Contrat to Etudiant's Contrats set

        // Verify that the Contrat was added correctly
        assertTrue(etudiant.getContrats().contains(contrat));
        assertEquals(etudiant, contrat.getEtudiant()); // Ensure the link is established correctly
    }

    @Test
    public void testToString() {
        String toStringOutput = contrat.toString();
        assertNotNull(toStringOutput);
        assertTrue(toStringOutput.contains("dateDebutContrat=" + startDate));
        assertTrue(toStringOutput.contains("dateFinContrat=" + endDate));
    }
}
