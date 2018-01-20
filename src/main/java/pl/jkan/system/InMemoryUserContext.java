package pl.jkan.system;

public class InMemoryUserContext implements UserContext {

    private CurrentUser authorizedUser;

    public void authorize(String userId) {
        this.authorizedUser = new CurrentUser(userId);
    }

    public CurrentUser getCurrentUser() {
        return authorizedUser;
    }
}
