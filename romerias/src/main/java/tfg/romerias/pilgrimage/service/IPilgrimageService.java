package tfg.romerias.pilgrimage.service;

import tfg.romerias.pilgrimage.model.Pilgrimage;

import java.util.List;

public interface IPilgrimageService {

    public List<Pilgrimage> showPilgrimages();

    public Pilgrimage searchPilgrimageById(Integer id);

    public void savePilgrimage(Pilgrimage pilgrimage);

    public void deletePilgrimage(Pilgrimage pilgrimage);
}
