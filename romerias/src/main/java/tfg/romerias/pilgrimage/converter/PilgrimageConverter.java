package tfg.romerias.pilgrimage.converter;

import org.springframework.stereotype.Component;
import tfg.romerias.pilgrimage.model.Pilgrimage;
import tfg.romerias.pilgrimage.model.PilgrimageRequest;
import tfg.romerias.pilgrimage.model.PilgrimageResponse;

import java.util.Base64;

@Component
public class PilgrimageConverter {

    public PilgrimageResponse convertToResponse(final Pilgrimage pilgrimage){
        return new PilgrimageResponse(pilgrimage.getId(),
                pilgrimage.getName(), pilgrimage.getPlace(), pilgrimage.getDescription(),
                pilgrimage.getUrl(), pilgrimage.getDate(), pilgrimage.getRoute(), getImage(pilgrimage));
    }

    public Pilgrimage convertFromRequest(final PilgrimageRequest pilgrimageRequest){
        return new Pilgrimage(pilgrimageRequest.getId(), pilgrimageRequest.getName(),
                pilgrimageRequest.getPlace(), pilgrimageRequest.getDescription(),
                pilgrimageRequest.getUrl(), pilgrimageRequest.getDate(),
                pilgrimageRequest.getRoute(), getImage(pilgrimageRequest));

    }

    private static byte[] getImage(PilgrimageRequest pilgrimageRequest) {
        final String image = pilgrimageRequest.getImage();
        return decode(image);
    }

    private static String getImage(Pilgrimage pilgrimage) {
        final byte[] image = pilgrimage.getImage();
        return encode(image);
    }

    private static byte[] decode(String image) {
        return image != null ? Base64.getDecoder().decode(image) : null;
    }

    private static String encode(byte[] image) {
        return image != null ? Base64.getEncoder().encodeToString(image) : null;
    }
}
