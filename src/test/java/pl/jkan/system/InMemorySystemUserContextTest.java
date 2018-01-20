package pl.jkan.system;

import org.junit.Assert;
import org.junit.Test;

public class InMemorySystemUserContextTest {
    @Test
    public void itAllowObtainCurrentUserWhenAuthenticated() {
        UserContext userContext = new InMemoryUserContext();

        userContext.authorize("user_1");
        CurrentUser currentUser = userContext.getCurrentUser();

        Assert.assertNotNull(currentUser);
        Assert.assertTrue(currentUser.getId().equals("user_1"));
    }
}
