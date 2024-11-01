package tn.esprit.spring.kaddem;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.services.IDetailEquipeService;
import java.util.Optional;
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
class DetailEquipeImplTest {
    @Autowired
    private IDetailEquipeService detailEquipeService;

    // 1. Test for adding a DetailEquipe
    @Test
     void testAddDetailEquipe() {
        DetailEquipe detailEquipe = new DetailEquipe(101, "AI Research");
        DetailEquipe savedDetailEquipe = detailEquipeService.addDetailEquipe(detailEquipe);

        assertNotNull(savedDetailEquipe);
        assertNotNull(savedDetailEquipe.getIdDetailEquipe());
        assertEquals(101, savedDetailEquipe.getSalle());
        assertEquals("AI Research", savedDetailEquipe.getThematique());

        // Cleanup
        detailEquipeService.deleteDetailEquipe(savedDetailEquipe.getIdDetailEquipe());
    }

    // 2. Test for retrieving a DetailEquipe by ID
    @Test
     void testGetDetailEquipeById() {
        DetailEquipe detailEquipe = new DetailEquipe(102, "Machine Learning Lab");
        DetailEquipe savedDetailEquipe = detailEquipeService.addDetailEquipe(detailEquipe);

        Optional<DetailEquipe> retrievedDetailEquipe = detailEquipeService.getDetailEquipeById(savedDetailEquipe.getIdDetailEquipe());
        assertTrue(retrievedDetailEquipe.isPresent());
        assertEquals(102, retrievedDetailEquipe.get().getSalle());
        assertEquals("Machine Learning Lab", retrievedDetailEquipe.get().getThematique());

        // Cleanup
        detailEquipeService.deleteDetailEquipe(savedDetailEquipe.getIdDetailEquipe());
    }

    // 3. Test for updating a DetailEquipe
    @Test
     void testUpdateDetailEquipe() {
        DetailEquipe detailEquipe = new DetailEquipe(103, "Computer Vision");
        DetailEquipe savedDetailEquipe = detailEquipeService.addDetailEquipe(detailEquipe);

        // Update details
        savedDetailEquipe.setSalle(104);
        savedDetailEquipe.setThematique("Data Science");
        DetailEquipe updatedDetailEquipe = detailEquipeService.updateDetailEquipe(savedDetailEquipe);

        assertNotNull(updatedDetailEquipe);
        assertEquals(104, updatedDetailEquipe.getSalle());
        assertEquals("Data Science", updatedDetailEquipe.getThematique());

        // Cleanup
        detailEquipeService.deleteDetailEquipe(updatedDetailEquipe.getIdDetailEquipe());
    }

    // 4. Test for deleting a DetailEquipe
    @Test
     void testDeleteDetailEquipe() {
        DetailEquipe detailEquipe = new DetailEquipe(105, "Deep Learning Lab");
        DetailEquipe savedDetailEquipe = detailEquipeService.addDetailEquipe(detailEquipe);

        Integer id = savedDetailEquipe.getIdDetailEquipe();
        detailEquipeService.deleteDetailEquipe(id);

        // Verify the entity has been deleted
        Optional<DetailEquipe> deletedDetailEquipe = detailEquipeService.getDetailEquipeById(id);
        assertFalse(deletedDetailEquipe.isPresent());
    }
}
