package tfg.romerias.pilgrimage.unit;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import tfg.romerias.TestDatabaseConfig;
import tfg.romerias.pilgrimage.model.Pilgrimage;
import tfg.romerias.pilgrimage.repository.PilgrimageRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/*@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:mysql://localhost:3307/romerias_db",
        "spring.datasource.username=root",
        "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver",
        "spring.jpa.hibernate.ddl-auto=none"
})*/
@DataJpaTest
@ContextConfiguration(classes = TestDatabaseConfig.class)
public class PilgrimageRepositoryTest {

    @Autowired
    private PilgrimageRepository pilgrimageRepository;

    private Pilgrimage pilgrimage;
    @BeforeEach
    void setup(){
        pilgrimage = pilgrimageRepository.save(Pilgrimage.builder().name("Pilgrimage init").date(LocalDateTime.now()).place("place").build());
    }

    @Test
    void savePilgrimageTest(){
        assertThat(pilgrimage).isNotNull();
        assertThat(pilgrimage.getId()).isGreaterThan(0);

    }

    @Test
    void listPilgrimagesTest(){
        Pilgrimage pilgrimageList = Pilgrimage.builder()
                .name("Romería Test").place("Test").date(LocalDateTime.now()).build();
        //when
        pilgrimageRepository.save(pilgrimageList);

        List<Pilgrimage> pilgrimagesList = pilgrimageRepository.findAll();

        assertThat(pilgrimagesList).isNotNull();
        assertThat(pilgrimagesList.size()).isEqualTo(2);
    }

    @Test
    void getPilgrimageByIdTest(){
        Pilgrimage pilgrimageFound = pilgrimageRepository.findById(pilgrimage.getId()).orElse(null);
        assertThat(pilgrimageFound).isNotNull();
    }

    @Test
    void updatePilgrimageTest(){
        pilgrimage.setName("Pilgrimage update");
        pilgrimage.setPlace("Place update");
        pilgrimage.setDescription("Description update");
        Pilgrimage updatedPilgrimage = pilgrimageRepository.save(pilgrimage);

        assertThat(updatedPilgrimage.getId()).isEqualTo(pilgrimage.getId());
        assertThat(updatedPilgrimage.getName()).isEqualTo("Pilgrimage update");
        assertThat(updatedPilgrimage.getPlace()).isEqualTo("Place update");
        assertThat(updatedPilgrimage.getDescription()).isEqualTo("Description update");
    }

    @Test
    void detelePilgrimageTest(){
        pilgrimageRepository.delete(pilgrimage);
        Pilgrimage pilgrimageDeleted = pilgrimageRepository.findById(pilgrimage.getId()).orElse(null);
        assertThat(pilgrimageDeleted).isNull();
    }

}
