package tfg.romerias.pilgrimage.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import tfg.romerias.floats.converter.FloatsConverter;
import tfg.romerias.pilgrimage.controller.PilgrimageController;
import tfg.romerias.pilgrimage.converter.PilgrimageConverter;
import tfg.romerias.pilgrimage.model.Pilgrimage;
import tfg.romerias.pilgrimage.model.PilgrimageRequest;
import tfg.romerias.pilgrimage.model.PilgrimageResponse;
import tfg.romerias.pilgrimage.service.IPilgrimageService;
import tfg.romerias.pilgrimage.service.PilgrimageService;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PilgrimageControllerTest {

    private final IPilgrimageService pilgrimageService = mock(PilgrimageService.class);
    private final PilgrimageConverter converter = mock(PilgrimageConverter.class);
    private final FloatsConverter converterFloat = mock(FloatsConverter.class);


    // SUT -> Subject Under Test
    private final PilgrimageController sut = new PilgrimageController(pilgrimageService, converter, converterFloat);

    private Pilgrimage pilgrimage;
    private PilgrimageResponse pilgrimageResponse;
    private PilgrimageRequest pilgrimageRequest;
    @BeforeEach
    void setup(){
        pilgrimage = Pilgrimage.builder().id(1).name("Pilgrimage init").date(LocalDateTime.now()).place("place").build();
        pilgrimageResponse = PilgrimageResponse.builder().id(1).name("Pilgrimage init").date(LocalDateTime.now()).place("place").build();
        pilgrimageRequest = PilgrimageRequest.builder().id(1).name("Pilgrimage init").date(LocalDateTime.now()).place("place").build();
    }
    @Test
    void shouldGetPilgrimagesTest(){
        when(pilgrimageService.getPilgrimages()).thenReturn(List.of(pilgrimage));
        when(converter.convertToResponse(pilgrimage)).thenReturn(pilgrimageResponse);
        sut.getPilgrimages();
        verify(pilgrimageService).getPilgrimages();
        verify(converter).convertToResponse(pilgrimage);
    }

    @Test
    void shouldAddPilgrimageTest(){
        when(converter.convertFromRequest(pilgrimageRequest)).thenReturn(pilgrimage);
        when(pilgrimageService.savePilgrimage(pilgrimage)).thenReturn(pilgrimage);
        when(converter.convertToResponse(pilgrimage)).thenReturn(pilgrimageResponse);

        sut.addPilgrimage(pilgrimageRequest);
        verify(converter).convertFromRequest(pilgrimageRequest);
        verify(pilgrimageService).savePilgrimage(pilgrimage);
        verify(converter).convertToResponse(pilgrimage);

    }

    @Test
    void shouldGetPilgrimageById(){
        when(pilgrimageService.getPilgrimageById(1)).thenReturn(pilgrimage);
        when(converter.convertToResponse(pilgrimage)).thenReturn(pilgrimageResponse);

        sut.getPilgrimageById(1);

        verify(pilgrimageService).getPilgrimageById(1);
        verify(converter).convertToResponse(pilgrimage);
    }

    @Test
    void shouldUpdatePilgrimage(){
        when(converter.convertFromRequest(pilgrimageRequest)).thenReturn(pilgrimage);
        when(pilgrimageService.savePilgrimage(pilgrimage)).thenReturn(pilgrimage);
        when(converter.convertToResponse(pilgrimage)).thenReturn(pilgrimageResponse);

        sut.updatePilgrimage(pilgrimageRequest);

        verify(converter).convertFromRequest(pilgrimageRequest);
        verify(pilgrimageService).savePilgrimage(pilgrimage);
        verify(converter).convertToResponse(pilgrimage);
    }

    @Test
    void shouldDeletePilgrimageTest(){
        when(pilgrimageService.getPilgrimageById(1)).thenReturn(pilgrimage);
        doNothing().when(pilgrimageService).deletePilgrimage(pilgrimage);

        sut.deletePilgrimage(1);

        verify(pilgrimageService).getPilgrimageById(1);
        verify(pilgrimageService).deletePilgrimage(pilgrimage);
    }
}
