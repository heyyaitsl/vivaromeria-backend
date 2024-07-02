package tfg.romerias.pilgrimage.integration;

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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RomeriasApplication.class})
@WebAppConfiguration
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PilgrimageControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private static Integer idAuxPilgrimage;

    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

    }


    @Test
    @Order(2)
    void when_you_call_pilgrimage_with_get_then_return_a_list() throws Exception {
        this.mockMvc.perform(get("/pilgrimages"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Nueva Romería"))
                .andExpect(jsonPath("$[0].place").value("Ubicación"))
                .andExpect(jsonPath("$[0].description").doesNotExist())
                .andExpect(jsonPath("$[0].url").doesNotExist())
                .andExpect(jsonPath("$[0].date").value("2023-02-28T19:00:00"))
                .andExpect(jsonPath("$[0].route").doesNotExist())
                .andExpect(jsonPath("$[0].image").doesNotExist())
                .andExpect(jsonPath("$[0].status").value(1));

    }




    @Test
    @Order(3)
    void when_you_call_pilgrimage_with_get_id_then_show() throws Exception{

        mockMvc.perform(get("/pilgrimages/{id}", idAuxPilgrimage))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(idAuxPilgrimage))
                .andExpect(jsonPath("$.name").value("Nueva Romería"))
                .andExpect(jsonPath("$.place").value("Ubicación"))
                .andExpect(jsonPath("$.description").doesNotExist())
                .andExpect(jsonPath("$.url").doesNotExist())
                .andExpect(jsonPath("$.date").value("2023-02-28T19:00:00"))
                .andExpect(jsonPath("$.route").doesNotExist())
                .andExpect(jsonPath("$.image").doesNotExist())
                .andExpect(jsonPath("$.status").value(1));
    }

    @Test
    @Order(1)
    void when_you_call_pilgrimage_with_post_then_create() throws Exception{
        String jsonRequest = "{ \"name\": \"Nueva Romería\", \"place\": \"Ubicación\", \"date\": \"2023-02-28T19:00:00\", \"status\": \"1\" }";

        MvcResult mvcResult=mockMvc.perform(post("/pilgrimages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Nueva Romería"))
                .andExpect(jsonPath("$.place").value("Ubicación"))
                .andExpect(jsonPath("$.description").doesNotExist())
                .andExpect(jsonPath("$.url").doesNotExist())
                .andExpect(jsonPath("$.date").value("2023-02-28T19:00:00"))
                .andExpect(jsonPath("$.route").doesNotExist())
                .andExpect(jsonPath("$.image").doesNotExist())
                .andExpect(jsonPath("$.status").value(1))
                .andReturn();
        String jsonResponse = mvcResult.getResponse().getContentAsString();
        idAuxPilgrimage = Integer.parseInt(jsonResponse.substring(jsonResponse.indexOf("\"id\":")+5, jsonResponse.indexOf(",\"name\"")));
        System.out.println(idAuxPilgrimage);
    }


    @Test
    @Order(4)
    void when_you_call_pilgrimage_with_put_id_then_update() throws Exception{
        String jsonRequest = "{ \"id\": "+idAuxPilgrimage+", \"name\": \"Modificada Romería\", \"place\": \"Ubicación modificada\", \"description\": \"Descripcion modificada\", \"date\": \"2023-02-28T19:00:00\", \"status\": \"2\"}";

        mockMvc.perform(put("/pilgrimages/{id}", idAuxPilgrimage).contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Modificada Romería"))
                .andExpect(jsonPath("$.place").value("Ubicación modificada"))
                .andExpect(jsonPath("$.description").value("Descripcion modificada"))
                .andExpect(jsonPath("$.url").doesNotExist())
                .andExpect(jsonPath("$.date").value("2023-02-28T19:00:00"))
                .andExpect(jsonPath("$.route").doesNotExist())
                .andExpect(jsonPath("$.image").doesNotExist())
                .andExpect(jsonPath("$.status").value(2));

    }

    @Test
    @Order(5)
    void when_you_call_pilgrimage_with_delete_id_then_delete() throws Exception{

        mockMvc.perform(delete("/pilgrimages/{id}", idAuxPilgrimage))
                .andExpect(status().isOk());
    }
}
