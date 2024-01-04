package tfg.romerias.pilgrimage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tfg.romerias.common.ValidationUtils;
import tfg.romerias.pilgrimage.converter.PilgrimageConverter;
import tfg.romerias.pilgrimage.model.Pilgrimage;
import tfg.romerias.pilgrimage.model.PilgrimageRequest;
import tfg.romerias.pilgrimage.model.PilgrimageResponse;
import tfg.romerias.pilgrimage.service.IPilgrimageService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/pilgrimages")
public class PilgrimageController {

    private final IPilgrimageService pilgrimageService;
    private final PilgrimageConverter converter;

    public PilgrimageController(final IPilgrimageService pilgrimageService, PilgrimageConverter converter) {
        this.pilgrimageService = pilgrimageService;
        this.converter = converter;
    }

    @GetMapping()
    public List<PilgrimageResponse> getPilgrimages(){
        List<Pilgrimage> pilgrimages = pilgrimageService.getPilgrimages();
        return pilgrimages.stream().map(converter::convertToResponse).collect(Collectors.toList());
    }
    @PostMapping()
    public PilgrimageResponse addPilgrimage(@RequestBody PilgrimageRequest pilgrimageRequest){
        ValidationUtils.notNull(pilgrimageRequest.getName(),pilgrimageRequest.getPlace(), pilgrimageRequest.getDate());
        ValidationUtils.notEmpty(pilgrimageRequest.getName(), pilgrimageRequest.getPlace());
        Pilgrimage pilgrimage = converter.convertFromRequest(pilgrimageRequest);
        return converter.convertToResponse(pilgrimageService.savePilgrimage(pilgrimage));
    }

    @GetMapping("{id}")
    public ResponseEntity<PilgrimageResponse> getPilgrimageById(@PathVariable Integer id){
        return ResponseEntity.ok(converter.convertToResponse(pilgrimageService.getPilgrimageById(id)));
    }

    @PutMapping("{id}")
    public  ResponseEntity<PilgrimageResponse> updatePilgrimage(@RequestBody PilgrimageRequest pilgrimageRequest){
        ValidationUtils.notNull(pilgrimageRequest.getName(),pilgrimageRequest.getPlace(), pilgrimageRequest.getDate());
        ValidationUtils.notEmpty(pilgrimageRequest.getName(), pilgrimageRequest.getPlace());
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


}
