package pl.jkan.ecommerc.sales.application;

import pl.jkan.ecommerce.sales.domain.basket.Basket;
import pl.jkan.ecommerce.sales.domain.basket.BasketRepository;
import pl.jkan.ecommerce.sales.domain.basket.Product;
import pl.jkan.ecommerce.sales.domain.product.ProductStore;
import pl.jkan.system.CurrentUser;
import pl.jkan.system.UserContext;

public class AddProductHandler {
    private BasketRepository basketRepository;
    private ProductStore productStore;
    private UserContext userContext;

    public AddProductHandler(BasketRepository basketRepository, ProductStore productStore, UserContext userContext) {
        this.basketRepository = basketRepository;
        this.productStore = productStore;
        this.userContext = userContext;
    }

    public void handle(String productId) {
        CurrentUser user = userContext.getCurrentUser();
        Basket basket = basketRepository.getCustomerBasket(user.getId());

        Product product = productStore.get(productId);
        basket.add(product);
    }
}
