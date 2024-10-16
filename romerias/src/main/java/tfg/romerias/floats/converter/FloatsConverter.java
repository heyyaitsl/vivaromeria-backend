package tfg.romerias.floats.converter;

import org.springframework.stereotype.Component;
import tfg.romerias.floats.model.Floats;
import tfg.romerias.floats.model.FloatsRequest;
import tfg.romerias.floats.model.FloatsResponse;
import tfg.romerias.pilgrimage.model.Pilgrimage;
import tfg.romerias.pilgrimage.service.PilgrimageService;
import tfg.romerias.user.model.User;
import tfg.romerias.user.service.UserService;


import java.util.*;
import java.util.stream.Collectors;

@Component
public class FloatsConverter {

    private final PilgrimageService pilgrimageService;
    private final UserService userService;

    public FloatsConverter(PilgrimageService pilgrimageService, UserService userService) {
        this.pilgrimageService = pilgrimageService;
        this.userService = userService;
    }

    public FloatsResponse convertToResponse(final Floats floats){
        return new FloatsResponse(floats.getId(),floats.getName(),floats.getUser().getUsername(),floats.getPrice(),
                floats.getMaxPeople(),floats.getDescription(),getImage(floats), getPilgrimageId(floats.getAvailableTickets()));
    }

    public Floats convertFromRequest(final FloatsRequest floatsRequest){
        return new Floats(floatsRequest.getId(),floatsRequest.getName(),getUser(floatsRequest.getUsername()),
                floatsRequest.getPrice(),floatsRequest.getMaxPeople(),floatsRequest.getDescription(),
                getImage(floatsRequest), getPilgrimage(floatsRequest.getPilgrimages()), getAvailableTickets(floatsRequest.getPilgrimages()));

    }
    private User getUser(String username){
        return userService.getUserByUsername(username);
    }
    private static byte[] getImage(FloatsRequest floatsRequest) {
        final String image = floatsRequest.getImage();
        return decode(image);
    }

    private static String getImage(Floats floats) {
        final byte[] image = floats.getImage();
        return encode(image);
    }

    private static byte[] decode(String image) {
        return image != null ? Base64.getDecoder().decode(image) : null;
    }

    private static String encode(byte[] image) {
        return image != null ? Base64.getEncoder().encodeToString(image) : null;
    }
    private static Map<Integer, Integer> getPilgrimageId(Map<Pilgrimage,Integer> pilgrimages){
        if(pilgrimages==null)return null;
        return pilgrimages.entrySet().stream().collect(Collectors.toMap(entry-> entry.getKey().getId(), Map.Entry::getValue));
    }
    private Set<Pilgrimage> getPilgrimage(Map<Integer,Integer> pilgrimagesId){
        if(pilgrimagesId==null)return new HashSet<>();
        return pilgrimagesId.keySet().stream().map(pilgrimageService::getPilgrimageById).collect(Collectors.toSet());
    }
    private Map<Pilgrimage, Integer> getAvailableTickets(Map<Integer,Integer> pilgrimages){
        if(pilgrimages==null)return new HashMap<>();
        return pilgrimages.entrySet().stream().collect(Collectors.toMap(entry->pilgrimageService.getPilgrimageById(entry.getKey()), Map.Entry::getValue));
    }

}
