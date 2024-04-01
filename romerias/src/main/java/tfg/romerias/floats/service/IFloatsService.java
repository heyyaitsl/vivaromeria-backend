package tfg.romerias.floats.service;

import tfg.romerias.floats.model.Floats;

import java.util.List;

public interface IFloatsService {

    List<Floats> getFloats();

    Floats getFloatById(Integer id);

    Floats saveFloat(Floats floats);

    void deleteFloat(Floats floats);
}
