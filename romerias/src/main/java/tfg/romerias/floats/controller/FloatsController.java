package tfg.romerias.floats.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tfg.romerias.floats.converter.FloatsConverter;
import tfg.romerias.floats.model.Floats;
import tfg.romerias.floats.model.FloatsRequest;
import tfg.romerias.floats.model.FloatsResponse;
import tfg.romerias.floats.service.IFloatsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/floats")
public class FloatsController {
    private final IFloatsService floatsService;
    private final FloatsConverter converter;

    public FloatsController(IFloatsService floatsService, FloatsConverter floatsConverter) {
        this.floatsService = floatsService;
        this.converter = floatsConverter;
    }


    @GetMapping()
    public List<FloatsResponse> getFloats(){
        List<Floats> floats = floatsService.getFloats();
        return floats.stream().map(converter::convertToResponse).collect(Collectors.toList());
    }
    @PostMapping()
    public FloatsResponse addFloat(@RequestBody FloatsRequest floatsRequest){
        Floats floats = converter.convertFromRequest(floatsRequest);
        return converter.convertToResponse(floatsService.saveFloat(floats));
    }

    @GetMapping("{id}")
    public ResponseEntity<FloatsResponse> getFloatById(@PathVariable Integer id){
        return ResponseEntity.ok(converter.convertToResponse(floatsService.getFloatById(id)));
    }

    @PutMapping("{id}")
    public  ResponseEntity<FloatsResponse> updateFloat(@RequestBody FloatsRequest floatsRequest){
        Floats floats = floatsService.saveFloat(converter.convertFromRequest(floatsRequest));
        return ResponseEntity.ok(converter.convertToResponse(floats));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String,Boolean>> deleteFloat(@PathVariable Integer id){
        Floats floats = floatsService.getFloatById(id);
        floatsService.deleteFloat(floats);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
