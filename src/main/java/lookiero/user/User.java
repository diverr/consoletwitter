package lookiero.user;

import java.util.List;

public class User {
    private final String name;
    private final List<String> subscriptions;

    public User(String name, List<String> subscriptions) {
        this.name = name;
        this.subscriptions = subscriptions;
    }

    public String getName() {
        return name;
    }

    public List<String> getSubscriptions() {
        return subscriptions;
    }

    public void addSubscription(String subscription) {
        subscriptions.add(subscription);
    }
}

