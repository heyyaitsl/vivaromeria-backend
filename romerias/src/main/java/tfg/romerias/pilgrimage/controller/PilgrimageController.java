package tfg.romerias.pilgrimage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfg.romerias.floats.converter.FloatsConverter;
import tfg.romerias.floats.model.Floats;
import tfg.romerias.floats.model.FloatsResponse;
import tfg.romerias.pilgrimage.converter.PilgrimageConverter;
import tfg.romerias.pilgrimage.model.Pilgrimage;
import tfg.romerias.pilgrimage.model.PilgrimageRequest;
import tfg.romerias.pilgrimage.model.PilgrimageResponse;
import tfg.romerias.pilgrimage.service.IPilgrimageService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/pilgrimages")
@CrossOrigin(value = "http://localhost:5173")
public class PilgrimageController {

    private final IPilgrimageService pilgrimageService;
    private final PilgrimageConverter converter;
    private final FloatsConverter converterFloats;

    public PilgrimageController(final IPilgrimageService pilgrimageService, PilgrimageConverter converter, FloatsConverter converterFloats) {
        this.pilgrimageService = pilgrimageService;
        this.converter = converter;
        this.converterFloats = converterFloats;
    }

    @GetMapping()
    public List<PilgrimageResponse> getPilgrimages(){
        List<Pilgrimage> pilgrimages = pilgrimageService.getPilgrimages();
        return pilgrimages.stream().map(converter::convertToResponse).collect(Collectors.toList());
    }
    @PostMapping()
    public PilgrimageResponse addPilgrimage(@RequestBody PilgrimageRequest pilgrimageRequest){
        Pilgrimage pilgrimage = converter.convertFromRequest(pilgrimageRequest);
        return converter.convertToResponse(pilgrimageService.savePilgrimage(pilgrimage));
    }

    @GetMapping("{id}")
    public ResponseEntity<PilgrimageResponse> getPilgrimageById(@PathVariable Integer id){
        return ResponseEntity.ok(converter.convertToResponse(pilgrimageService.getPilgrimageById(id)));
    }

    @PutMapping("{id}")
    public  ResponseEntity<PilgrimageResponse> updatePilgrimage(@RequestBody PilgrimageRequest pilgrimageRequest){
        Pilgrimage pilgrimage = pilgrimageService.savePilgrimage(converter.convertFromRequest(pilgrimageRequest));
        return ResponseEntity.ok(converter.convertToResponse(pilgrimage));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String,Boolean>> deletePilgrimage(@PathVariable Integer id){
        Pilgrimage pilgrimage = pilgrimageService.getPilgrimageById(id);
        pilgrimageService.deletePilgrimage(pilgrimage);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}/floats")
    public Set<FloatsResponse> getFloatsByPilgrimageId(@PathVariable Integer id){
        Set<Floats> floats = pilgrimageService.getFloats(id);
        return floats.stream().map(converterFloats::convertToResponse).collect(Collectors.toSet());
    }

    @PutMapping ("{pilgrimageId}/addFloat/{floatId}")
    public ResponseEntity<String> addFloatToPilgrimage(@PathVariable Integer pilgrimageId,
                                                       @PathVariable Integer floatId){
        pilgrimageService.addFloatToPilgrimage(pilgrimageId,floatId);

        return ResponseEntity.ok("Añadida");
    }


}
