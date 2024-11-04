import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.extension.ExtendWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.context.junit.jupiter.SpringExtension;
        import org.springframework.test.web.servlet.MockMvc;

        import com.fasterxml.jackson.databind.ObjectMapper;
import tn.esprit.spring.kaddem.entities.Universite;

//@SpringBootTest
//@AutoConfigureMockMvc
//@ExtendWith(SpringExtension.class)
class UniversiteServiceImplTest {

    @Autowired
    private MockMvc mockMvc;

    private Universite universite;

    @BeforeEach
    public void setUp() {
        universite = new Universite(1, "Test University");
    }

    @Test
     void testGetUniversites() throws Exception {
        mockMvc.perform(get("/universite/retrieve-all-universites"))
                .andExpect(status().isOk());
        // Add additional checks for response content if necessary
    }

    @Test
     void testAddUniversite() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String universiteJson = objectMapper.writeValueAsString(universite);

        mockMvc.perform(post("/universite/add-universite")
                        .contentType("application/json")
                        .content(universiteJson))
                .andExpect(status().isOk());
        // Add additional checks for response content if necessary
    }

    @Test
     void testRetrieveUniversite() throws Exception {
        mockMvc.perform(get("/universite/retrieve-universite/1"))
                .andExpect(status().isOk());
    }


}
