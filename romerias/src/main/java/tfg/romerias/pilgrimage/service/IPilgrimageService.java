package tfg.romerias.pilgrimage.service;

import tfg.romerias.pilgrimage.model.Pilgrimage;

import java.util.List;

public interface IPilgrimageService {

    List<Pilgrimage> getPilgrimages();

    Pilgrimage getPilgrimageById(Integer id);

    Pilgrimage savePilgrimage(Pilgrimage pilgrimage);

    void deletePilgrimage(Pilgrimage pilgrimage);
}
