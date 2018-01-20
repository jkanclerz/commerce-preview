package pl.jkan.webcommerce.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.jkan.ecommerce.sales.application.AddProductHandler;
import pl.jkan.ecommerce.sales.domain.basket.BasketRepository;
import pl.jkan.ecommerce.sales.domain.basket.Product;
import pl.jkan.ecommerce.sales.domain.product.ProductStore;
import pl.jkan.ecommerce.sales.infrastructure.InMemoryBasketRepository;
import pl.jkan.ecommerce.sales.infrastructure.InMemoryProductStore;
import pl.jkan.system.UserContext;

@Component
public class EcommerceSalesComponent {

    @Autowired
    UserContext userContext;

    @Bean
    public ProductStore inMemoryProductStore() {
        ProductStore store = new InMemoryProductStore();

        store.addProduct(new Product("lego-1", 10, 10.10));
        store.addProduct(new Product("lego-2", 11, 10.20));
        store.addProduct(new Product("lego-3", 12, 10.30));
        store.addProduct(new Product("lego-4", 0, 10.40));

        return store;
    }

    @Bean
    public AddProductHandler addProductHandler() {
        return new AddProductHandler(
            basketRepository(),
            inMemoryProductStore(),
            userContext
        );
    }

    @Bean
    public BasketRepository basketRepository() {
        return new InMemoryBasketRepository();
    }
}
