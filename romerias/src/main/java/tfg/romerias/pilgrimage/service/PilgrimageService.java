package tfg.romerias.pilgrimage.service;

import org.springframework.stereotype.Service;
import tfg.romerias.common.ValidationUtils;
import tfg.romerias.exceptions.BadRequestException;
import tfg.romerias.floats.model.Floats;
import tfg.romerias.pilgrimage.model.Pilgrimage;
import tfg.romerias.pilgrimage.repository.PilgrimageRepository;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class PilgrimageService implements IPilgrimageService{

    private final PilgrimageRepository pilgrimageRepository;

    public PilgrimageService(PilgrimageRepository pilgrimageRepository) {
        this.pilgrimageRepository = pilgrimageRepository;

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
    public void deletePilgrimage(Pilgrimage pilgrimage) {
        pilgrimageRepository.delete(pilgrimage);

    }

    @Override
    public Set<Floats> getFloats(Integer id) {
        return Objects.requireNonNull(pilgrimageRepository.findById(id).orElse(null)).getFloats();


    }
}
