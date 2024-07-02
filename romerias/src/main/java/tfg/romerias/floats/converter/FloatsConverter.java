package tfg.romerias.floats.converter;

import org.springframework.stereotype.Component;
import tfg.romerias.floats.model.Floats;
import tfg.romerias.floats.model.FloatsRequest;
import tfg.romerias.floats.model.FloatsResponse;


import java.util.Base64;

@Component
public class FloatsConverter {

    public FloatsResponse convertToResponse(final Floats floats){
        return new FloatsResponse(floats.getId(),floats.getName(),floats.getUsername(),floats.getPrice(),
                floats.getMaxPeople(),floats.getDescription(),getImage(floats),floats.getPilgrimages(), floats.getAvailableTickets());
    }

    public Floats convertFromRequest(final FloatsRequest floatsRequest){
        return new Floats(floatsRequest.getId(),floatsRequest.getName(),floatsRequest.getUsername(),
                floatsRequest.getPrice(),floatsRequest.getMaxPeople(),floatsRequest.getDescription(),
                getImage(floatsRequest), floatsRequest.getPilgrimages(), floatsRequest.getAvailableTickets());

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
}
