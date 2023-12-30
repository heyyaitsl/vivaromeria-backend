package tfg.romerias.pilgrimage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tfg.romerias.pilgrimage.model.Pilgrimage;

public interface PilgrimageRepository extends JpaRepository<Pilgrimage,Integer> {
}
