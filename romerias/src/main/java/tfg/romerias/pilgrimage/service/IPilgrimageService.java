package tfg.romerias.pilgrimage.service;

import tfg.romerias.pilgrimage.model.Pilgrimage;

import java.util.List;

public interface IPilgrimageService {

    public List<Pilgrimage> getPilgrimages();

    public Pilgrimage getPilgrimageById(Integer id);

    public Pilgrimage savePilgrimage(Pilgrimage pilgrimage);

    public void deletePilgrimage(Pilgrimage pilgrimage);
}
