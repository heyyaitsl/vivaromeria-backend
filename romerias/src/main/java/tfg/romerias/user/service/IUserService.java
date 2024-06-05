package tfg.romerias.user.service;

import tfg.romerias.user.model.User;

public interface IUserService {
    User saveUser(User user);
    User getUserByUsername(String username);
    void deleteUser(User user);
}
