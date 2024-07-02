package tfg.romerias.user.service;

import org.springframework.stereotype.Service;
import tfg.romerias.exceptions.BadRequestException;
import tfg.romerias.user.model.User;
import tfg.romerias.user.repository.UserRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;
    //private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository /*BCryptPasswordEncoder passwordEncoder*/) {
        this.userRepository = userRepository;
        //this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User saveUser(User user) {
        user.setPassword(/*passwordEncoder.encode(user.getPassword())*/"");
        return userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()-> new BadRequestException("Username no válido"));
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);

    }
}
