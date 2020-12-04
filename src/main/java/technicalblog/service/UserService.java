package technicalblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technicalblog.model.User;
import technicalblog.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User login(User user) {
        User exixtingUser = repository.checkUser(user.getUsername(), user.getPassword());
        if( exixtingUser != null) {
            return exixtingUser;
        } else {
            return null;
        }
    }

    public void registerUser(User user) {
        repository.registerUser(user);
    }
}
