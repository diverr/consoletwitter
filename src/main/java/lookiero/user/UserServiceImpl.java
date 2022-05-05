package lookiero.user;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void followUser(String userName, String followUserName) {
        User user = userRepository.findByName(userName).orElseThrow(() -> new RuntimeException(String.format("User %s not found", userName)));

        userRepository.findByName(followUserName).orElseThrow(() -> new RuntimeException(String.format("User %s not found", followUserName)));

        if (user.getSubscriptions().contains(followUserName)) {
            throw new RuntimeException(String.format("User %s already follows %s", userName, followUserName));
        } else {
            user.getSubscriptions().add(followUserName);
        }
    }
}
