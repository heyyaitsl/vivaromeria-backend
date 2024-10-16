package tfg.romerias.user.converter;

import org.springframework.stereotype.Component;
import tfg.romerias.floats.model.Floats;
import tfg.romerias.floats.service.IFloatsService;
import tfg.romerias.user.model.User;
import tfg.romerias.user.model.UserRequest;
import tfg.romerias.user.model.UserResponse;

import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserConverter {
    private final IFloatsService floatsService;

    public UserConverter(IFloatsService floatsService) {
        this.floatsService = floatsService;
    }

    public UserResponse convertToResponse(User user){
        return new UserResponse(user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
                user.getPhoneNumber(), user.getRole(), getImage(user), getFloatsId(user.getFloats()));
    }
    public User convertFromRequest(UserRequest user){
        return new User(user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
                user.getPhoneNumber(), user.getRole(), getImage(user), getFloats(user.getFloats()));
    }

    private static byte[] getImage(UserRequest userRequest) {
        final String image = userRequest.getPhoto();
        return decode(image);
    }

    private static String getImage(User user) {
        final byte[] image = user.getPhoto();
        return encode(image);
    }

    private static byte[] decode(String image) {
        return image != null ? Base64.getDecoder().decode(image) : null;
    }

    private static String encode(byte[] image) {
        return image != null ? Base64.getEncoder().encodeToString(image) : null;
    }

    private static Set<Integer> getFloatsId(Set<Floats> floats){
        if(floats==null)return null;
        return floats.stream().map(Floats::getId).collect(Collectors.toSet());
    }
    private Set<Floats> getFloats(Set<Integer> floatsId){
        if(floatsId==null)return new HashSet<>();
        return floatsId.stream().map(floatsService::getFloatById).collect(Collectors.toSet());
    }

}
