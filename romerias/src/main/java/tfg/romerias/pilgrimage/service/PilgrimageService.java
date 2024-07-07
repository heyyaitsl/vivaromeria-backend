package tfg.romerias.pilgrimage.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tfg.romerias.common.ValidationUtils;
import tfg.romerias.exceptions.BadRequestException;
import tfg.romerias.floats.model.Floats;
import tfg.romerias.floats.service.FloatsService;
import tfg.romerias.pilgrimage.model.Pilgrimage;
import tfg.romerias.pilgrimage.repository.PilgrimageRepository;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class PilgrimageService implements IPilgrimageService{

    private final PilgrimageRepository pilgrimageRepository;
    private final FloatsService floatsService;

    public PilgrimageService(PilgrimageRepository pilgrimageRepository, FloatsService floatsService) {
        this.pilgrimageRepository = pilgrimageRepository;

        this.floatsService = floatsService;
    }

    @Override
    public List<Pilgrimage> getPilgrimages() {
        return pilgrimageRepository.findAll();
    }

    @Override
    public Pilgrimage getPilgrimageById(Integer id) {
        return pilgrimageRepository.findById(id).orElseThrow(() -> new BadRequestException("Id no válido")); //TODO: Check this null
    }

    @Override
    public Pilgrimage savePilgrimage(Pilgrimage pilgrimage) {
        ValidationUtils.notNull(pilgrimage.getName(),pilgrimage.getPlace(),pilgrimage.getPlace());
        return pilgrimageRepository.save(pilgrimage);
    }


    @Override
    @Transactional
    public void deletePilgrimage(Pilgrimage pilgrimage) {
        pilgrimageRepository.delete(pilgrimage);

    }

    @Override
    public Set<Floats> getFloats(Integer id) {
        return getPilgrimageById(id).getFloats();


    }

    @Override
    @Transactional
    public void addFloatToPilgrimage(Integer pilgrimageId, Integer floatsId) {
        Pilgrimage pilgrimage = getPilgrimageById(pilgrimageId);
        Floats floats = floatsService.getFloatById(floatsId);
        //pilgrimage.getFloats().add(floats);
        //floats.getPilgrimages().add(pilgrimage);
        floats.getAvailableTickets().put(pilgrimage, floats.getMaxPeople());
        //savePilgrimage(pilgrimage);
        //floatsService.saveFloat(floats);
    }
}
