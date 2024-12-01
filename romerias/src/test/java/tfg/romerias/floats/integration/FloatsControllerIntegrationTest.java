package tfg.romerias.floats.integration;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tfg.romerias.RomeriasApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RomeriasApplication.class})
@WebAppConfiguration
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FloatsControllerIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private static Integer idAuxFloats;

    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

    }
    @Test
    @Order(1)
    void when_you_call_floats_with_post_then_create() throws Exception{
        String jsonRequest = "{ \"name\": \"Nueva carroza\", \"username\": \"floats\", \"price\": 30.0, \"maxPeople\": 70 }";

        MvcResult mvcResult=mockMvc.perform(post("/floats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Nueva carroza"))
                .andExpect(jsonPath("$.username").value("floats"))
                .andExpect(jsonPath("$.description").doesNotExist())
                .andExpect(jsonPath("$.price").value(30.0))
                .andExpect(jsonPath("$.maxPeople").value(70))
                .andExpect(jsonPath("$.image").doesNotExist())
                .andReturn();
        String jsonResponse = mvcResult.getResponse().getContentAsString();
        idAuxFloats = Integer.parseInt(jsonResponse.substring(jsonResponse.indexOf("\"id\":")+5, jsonResponse.indexOf(",\"name\"")));
        System.out.println(idAuxFloats);
    }
    @Test
    @Order(2)
    void when_you_call_floats_with_get_id_then_show() throws Exception {

        mockMvc.perform(get("/floats/{id}", idAuxFloats))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(idAuxFloats))
                .andExpect(jsonPath("$.name").value("Nueva carroza"))
                .andExpect(jsonPath("$.username").value("floats"))
                .andExpect(jsonPath("$.description").doesNotExist())
                .andExpect(jsonPath("$.price").value(30.0))
                .andExpect(jsonPath("$.maxPeople").value(70))
                .andExpect(jsonPath("$.image").doesNotExist());
    }
    @Test
    @Order(3)
    void when_you_call_floats_with_put_id_then_update() throws Exception{
        String jsonRequest = "{\"id\":"+idAuxFloats+",\"name\":\"Carroza Modificada\",\"username\":\"floats2\",\"price\":40.0," +
                "\"maxPeople\":200,\"description\":\"Descripcion modificada\"}";

        mockMvc.perform(put("/floats/{id}", idAuxFloats).contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Carroza Modificada"))
                .andExpect(jsonPath("$.username").value("floats2"))
                .andExpect(jsonPath("$.description").value("Descripcion modificada"))
                .andExpect(jsonPath("$.price").value("40.0"))
                .andExpect(jsonPath("$.maxPeople").value("200"))
                .andExpect(jsonPath("$.image").doesNotExist());

    }
    @Test
    @Order(5)
    void when_you_call_add_Floats_then_add() throws Exception{
        mockMvc.perform(get("/pilgrimages/{pilgrimageId}/addFloat/{floatsId}",1,idAuxFloats))
                .andExpect(status().isOk());
    }
    @Test
    @Order(6)
    void when_you_call_float_with_delete_id_then_delete() throws Exception{

        mockMvc.perform(delete("/floats/{id}", idAuxFloats))
                .andExpect(status().isOk());
    }
}
