package tn.esprit.spring.kaddem.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class UniversityTest {

    private Universite university;

    @BeforeEach
    public void setup() {
        university = new Universite();
    }

    @Test
    public void testConstructorWithName() {
        Universite namedUniversity = new Universite("Tech Institute");
        assertEquals("Tech Institute", namedUniversity.getNomUniv(), "University name should match the input");
    }

    @Test
    public void testSetAndGetUniversityName() {
        university.setNomUniv("Innovative University");
        assertEquals("Innovative University", university.getNomUniv(), "University name should be updated correctly");
    }

    @Test
    public void testSetDepartments() {
        Set<Departement> depts = new HashSet<>();
        depts.add(new Departement("Engineering"));
        university.setDepartements(depts);

        assertEquals(1, university.getDepartements().size(), "University should have one department");
        assertTrue(university.getDepartements().stream().anyMatch(d -> d.getNomDepart().equals("Engineering")), "Should contain the department 'Engineering'");
    }
}
