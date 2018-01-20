package pl.jkan.webcommerce.components;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.jkan.system.InMemoryUserContext;
import pl.jkan.system.UserContext;

@Component
public class SystemComponent {
    @Bean
    public UserContext userContext() {
        UserContext context = new InMemoryUserContext();
        context.authorize("my_user");

        return context;
    }
}
