package tfg.romerias.pilgrimage.service;

import org.springframework.data.domain.Page;
import tfg.romerias.floats.model.Floats;
import tfg.romerias.pilgrimage.model.Pilgrimage;

import java.util.List;
import java.util.Set;

public interface IPilgrimageService {

    List<Pilgrimage> getPilgrimages();

    Pilgrimage getPilgrimageById(Integer id);

    Pilgrimage savePilgrimage(Pilgrimage pilgrimage);

    void deletePilgrimage(Pilgrimage pilgrimage);

    Set<Floats> getFloats(Integer id);

    void addFloatToPilgrimage(Integer pilgrimageId, Integer floatsId);
    Page<Pilgrimage> getPilgrimages(int pageNo, int pageSize);

}
