package tfg.romerias.floats.service;

import org.springframework.stereotype.Service;
import tfg.romerias.common.ValidationUtils;
import tfg.romerias.exceptions.BadRequestException;
import tfg.romerias.floats.model.Floats;
import tfg.romerias.floats.repository.FloatsRepository;

import java.util.List;

@Service
public class FloatsService implements IFloatsService {

    private final FloatsRepository floatsRepository;

    public FloatsService(FloatsRepository floatsRepository) {
        this.floatsRepository = floatsRepository;
    }

    @Override
    public List<Floats> getFloats() {
        return floatsRepository.findAll();
    }

    @Override
    public Floats getFloatById(Integer id) {
        return floatsRepository.findById(id).orElseThrow(()-> new BadRequestException("Id no válido"));
    }

    @Override
    public Floats saveFloat(Floats floats) {
        ValidationUtils.notNull(floats.getUsername(),floats.getName(),floats.getPrice());
        return floatsRepository.save(floats);
    }

    @Override
    public void deleteFloat(Floats floats) {
        floatsRepository.delete(floats);
    }
}
