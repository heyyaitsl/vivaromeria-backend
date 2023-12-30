package tfg.romerias.pilgrimage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfg.romerias.exceptions.ResourceNotFoundException;
import tfg.romerias.pilgrimage.model.Pilgrimage;
import tfg.romerias.pilgrimage.service.IPilgrimageService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PilgrimageController {

    private static final Logger logger = LoggerFactory.getLogger(PilgrimageController.class);

    @Autowired
    private IPilgrimageService pilgrimageService;

    @GetMapping("/pilgrimages")
    public List<Pilgrimage> getPilgrimages(){
        return pilgrimageService.showPilgrimages();
    }
    @PostMapping("/pilgrimages")
    public Pilgrimage addPilgrimage(@RequestBody Pilgrimage pilgrimage){
        return pilgrimageService.savePilgrimage(pilgrimage);
    }

    @GetMapping("/pilgrimages/{id}")
    public ResponseEntity<Pilgrimage> getPilgrimageById(@PathVariable Integer id){
        Pilgrimage pilgrimage = pilgrimageService.searchPilgrimageById(id);
        if (pilgrimage==null)
            throw new ResourceNotFoundException("Pilgrimage id not found: "+ id);
        return ResponseEntity.ok(pilgrimage);
    }

    @PutMapping("/pilgrimages/{id}")
    public  ResponseEntity<Pilgrimage> updatePilgrimage(@PathVariable Integer id, @RequestBody Pilgrimage pilgrimageReceived){
        Pilgrimage pilgrimage = pilgrimageService.searchPilgrimageById(id);
        if (pilgrimage==null)
            throw new ResourceNotFoundException("Pilgrimage id not found: " + id);
        pilgrimage.setName(pilgrimageReceived.getName());
        pilgrimage.setDate(pilgrimageReceived.getDate());
        pilgrimage.setDescription(pilgrimageReceived.getDescription());
        pilgrimage.setPlace(pilgrimageReceived.getPlace());
        pilgrimage.setUrl(pilgrimageReceived.getUrl());
        pilgrimage.setImage(pilgrimageReceived.getImage());
        pilgrimage.setRoute(pilgrimageReceived.getRoute());
        pilgrimageService.savePilgrimage(pilgrimage);
        return ResponseEntity.ok(pilgrimage);
    }

    @DeleteMapping("/pilgrimages/{id}")
    public ResponseEntity<Map<String,Boolean>> deletePilgrimage(@PathVariable Integer id){
        Pilgrimage pilgrimage = pilgrimageService.searchPilgrimageById(id);
        if (pilgrimage==null)
            throw new ResourceNotFoundException("Pilgrimage id not found: " + id);
        pilgrimageService.deletePilgrimage(pilgrimage);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
