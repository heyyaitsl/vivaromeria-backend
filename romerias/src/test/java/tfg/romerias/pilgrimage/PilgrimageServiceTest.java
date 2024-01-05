package tfg.romerias.pilgrimage;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tfg.romerias.pilgrimage.model.Pilgrimage;
import tfg.romerias.pilgrimage.repository.PilgrimageRepository;
import tfg.romerias.pilgrimage.service.IPilgrimageService;
import tfg.romerias.pilgrimage.service.PilgrimageService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PilgrimageServiceTest {

    private final PilgrimageRepository pilgrimageRepository = mock(PilgrimageRepository.class);

    private final PilgrimageService pilgrimageService = new PilgrimageService(pilgrimageRepository);

    private Pilgrimage pilgrimage;
    @BeforeEach
    void setup(){
        pilgrimage = Pilgrimage.builder().id(1).name("Pilgrimage init").date(LocalDateTime.now()).place("place").build();
    }

    @Test
    void getPilgrimagesTest(){
        when(pilgrimageRepository.findAll()).thenReturn(List.of(pilgrimage));

        pilgrimageService.getPilgrimages();

        verify(pilgrimageRepository).findAll();
    }

    @Test
    void getPilgrimageByIdTest(){
        when(pilgrimageRepository.findById(pilgrimage.getId())).thenReturn(Optional.ofNullable(pilgrimage));

        pilgrimageService.getPilgrimageById(pilgrimage.getId());

        verify(pilgrimageRepository).findById(pilgrimage.getId());
    }

    @Test
    void savePilgrimageTest(){
        when(pilgrimageRepository.save(pilgrimage)).thenReturn(pilgrimage);

        pilgrimageService.savePilgrimage(pilgrimage);

        verify(pilgrimageRepository).save(pilgrimage);
    }

    @Test
    void deletePilgrimageTest(){
        willDoNothing().given(pilgrimageRepository).delete(pilgrimage);

        pilgrimageService.deletePilgrimage(pilgrimage);

        verify(pilgrimageRepository).delete(pilgrimage);
    }
}
