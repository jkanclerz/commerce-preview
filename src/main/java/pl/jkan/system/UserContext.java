package pl.jkan.system;

public interface UserContext {
    public void authorize(String id);

    public CurrentUser getCurrentUser();
}
