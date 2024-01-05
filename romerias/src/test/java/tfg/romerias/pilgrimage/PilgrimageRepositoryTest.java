package tfg.romerias.pilgrimage;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import tfg.romerias.TestDatabaseConfig;
import tfg.romerias.pilgrimage.model.Pilgrimage;
import tfg.romerias.pilgrimage.repository.PilgrimageRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


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

    private Pilgrimage pilgrimageInit;
    @BeforeEach
    void setup(){
        pilgrimageInit = Pilgrimage.builder().name("Pilgrimage init").date(LocalDateTime.now()).place("place").build();
    }

    @Test
    void savePilgrimageTest(){
        Pilgrimage savedPilgrimage = pilgrimageRepository.save(pilgrimageInit);
        assertThat(savedPilgrimage).isNotNull();
        assertThat(savedPilgrimage.getId()).isGreaterThan(0);

    }

    @Test
    void listPilgrimagesTest(){
        Pilgrimage pilgrimage = Pilgrimage.builder()
                .name("Romería Test").place("Test").date(LocalDateTime.now()).build();
        //when
        pilgrimageRepository.save(pilgrimageInit);
        pilgrimageRepository.save(pilgrimage);

        List<Pilgrimage> pilgrimagesList = pilgrimageRepository.findAll();

        assertThat(pilgrimagesList).isNotNull();
        assertThat(pilgrimagesList.size()).isEqualTo(2);
    }

    @Test
    void getPilgrimageByIdTest(){
        Pilgrimage pilgrimageFound = pilgrimageRepository.findById(pilgrimageRepository.save(pilgrimageInit).getId()).orElse(null);
        assertThat(pilgrimageFound).isNotNull();
    }

    @Test
    void updatePilgrimageTest(){
        Pilgrimage pilgrimage = pilgrimageRepository.save(pilgrimageInit);
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
        Pilgrimage pilgrimage = pilgrimageRepository.save(pilgrimageInit);
        pilgrimageRepository.delete(pilgrimage);
        Pilgrimage pilgrimageDeleted = pilgrimageRepository.findById(pilgrimage.getId()).orElse(null);
        assertThat(pilgrimageDeleted).isNull();
    }

}
