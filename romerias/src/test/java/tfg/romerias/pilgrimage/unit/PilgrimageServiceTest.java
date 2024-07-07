package tfg.romerias.pilgrimage.unit;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import tfg.romerias.floats.model.Floats;
import tfg.romerias.floats.service.FloatsService;
import tfg.romerias.pilgrimage.model.Pilgrimage;
import tfg.romerias.pilgrimage.repository.PilgrimageRepository;
import tfg.romerias.pilgrimage.service.PilgrimageService;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PilgrimageServiceTest {

    private final PilgrimageRepository pilgrimageRepository = mock(PilgrimageRepository.class);
    private final FloatsService floatsService = mock(FloatsService.class);
    private final PilgrimageService pilgrimageService = new PilgrimageService(pilgrimageRepository, floatsService);

    private Pilgrimage pilgrimage;
    private Floats floats;
    private Set<Floats> floatsSet;
    private Set<Pilgrimage> pilgrimageList;
    private Map<Pilgrimage, Integer> availableTickets;
    @BeforeEach
    void setup(){
        availableTickets = new HashMap<>();
        pilgrimageList = new HashSet<>();
        floatsSet = new HashSet<>();

        floatsSet.add(Floats.builder().id(0).maxPeople(20).price(30).name("Floats previous init").username("user").build());
        floats = Floats.builder().id(1).maxPeople(30).price(20).name("Floats init").username("user").pilgrimages(pilgrimageList).availableTickets(availableTickets).build();
        pilgrimage = Pilgrimage.builder().id(1).name("Pilgrimage init").date(LocalDateTime.now()).place("place").status(0).floats(floatsSet).build();

    }

    @Test
    void shouldGetPilgrimagesTest(){
        when(pilgrimageRepository.findAll()).thenReturn(List.of(pilgrimage));

        pilgrimageService.getPilgrimages();

        verify(pilgrimageRepository).findAll();
    }

    @Test
    void shouldGetPilgrimageByIdTest(){
        when(pilgrimageRepository.findById(pilgrimage.getId())).thenReturn(Optional.ofNullable(pilgrimage));

        pilgrimageService.getPilgrimageById(pilgrimage.getId());

        verify(pilgrimageRepository).findById(pilgrimage.getId());
    }

    @Test
    void shouldSavePilgrimageTest(){

        when(pilgrimageRepository.save(pilgrimage)).thenReturn(pilgrimage);

        pilgrimageService.savePilgrimage(pilgrimage);

        verify(pilgrimageRepository).save(pilgrimage);
    }

    @Test
    void shouldDeletePilgrimageTest(){
        doNothing().when(pilgrimageRepository).delete(pilgrimage);

        pilgrimageService.deletePilgrimage(pilgrimage);

        verify(pilgrimageRepository).delete(pilgrimage);
    }

    @Test
    void shouldAddFloatToPilgrimageTest(){
        when(pilgrimageRepository.findById(pilgrimage.getId())).thenReturn(Optional.ofNullable(pilgrimage));
        when(floatsService.getFloatById(floats.getId())).thenReturn(floats);
        when(pilgrimageRepository.save(pilgrimage)).thenReturn(pilgrimage);
        when(floatsService.saveFloat(floats)).thenReturn(floats);
        pilgrimageService.addFloatToPilgrimage(pilgrimage.getId(),floats.getId());
        assertTrue(pilgrimage.getFloats().contains(floats));
        assertTrue(floats.getPilgrimages().contains(pilgrimage));
        assertEquals(floats.getMaxPeople(), floats.getAvailableTickets().get(pilgrimage));
        verify(pilgrimageRepository).save(pilgrimage);
        verify(floatsService).saveFloat(floats);
    }

    @Test
    void shouldGetFloatsTest(){
        when(pilgrimageRepository.findById(pilgrimage.getId())).thenReturn(Optional.ofNullable(pilgrimage));
        assertEquals(pilgrimage.getFloats(),pilgrimageService.getFloats(pilgrimage.getId()));
        verify(pilgrimageRepository).findById(pilgrimage.getId());

    }

}
