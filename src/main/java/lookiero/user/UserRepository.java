package lookiero.user;

import java.util.Optional;

public interface UserRepository {
    void addUser(User user);

    Optional<User> findByName(String userName);
}
