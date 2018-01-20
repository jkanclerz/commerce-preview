package pl.jkan.webcommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.jkan.webcommerce.controllers.ProductController;

@SpringBootApplication
public class Web {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Web.class, args);
    }
}
