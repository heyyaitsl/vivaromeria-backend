package tfg.romerias.pilgrimage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tfg.romerias.pilgrimage.model.Pilgrimage;
import tfg.romerias.pilgrimage.repository.PilgrimageRepository;

import java.util.List;

@Service
public class PilgrimageService implements IPilgrimageService{

    @Autowired
    PilgrimageRepository pilgrimageRepository;

    @Override
    public List<Pilgrimage> showPilgrimages() {
        return pilgrimageRepository.findAll();
    }

    @Override
    public Pilgrimage searchPilgrimageById(Integer id) {
        return pilgrimageRepository.findById(id).orElse(null);
    }

    @Override
    public Pilgrimage savePilgrimage(Pilgrimage pilgrimage) {
        return pilgrimageRepository.save(pilgrimage);
    }

    @Override
    public void deletePilgrimage(Pilgrimage pilgrimage) {
        pilgrimageRepository.delete(pilgrimage);

    }
}
