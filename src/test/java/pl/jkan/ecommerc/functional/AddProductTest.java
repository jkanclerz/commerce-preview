package pl.jkan.ecommerc.functional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.jkan.ecommerce.sales.application.AddProductHandler;
import pl.jkan.ecommerce.sales.domain.basket.Basket;
import pl.jkan.ecommerce.sales.domain.basket.InMemoryBasketRepository;
import pl.jkan.ecommerce.sales.domain.basket.Product;
import pl.jkan.ecommerce.sales.domain.basket.BasketRepository;
import pl.jkan.ecommerce.sales.domain.product.InMemoryProductStore;
import pl.jkan.ecommerce.sales.domain.product.ProductStore;
import pl.jkan.system.InMemoryUserContext;
import pl.jkan.system.UserContext;

public class AddProductTest {

    BasketRepository basketRepository;
    ProductStore productStore;
    UserContext userContext;

    @Before
    public void setUp() {
        this.userContext = new InMemoryUserContext();
        this.productStore = new InMemoryProductStore();
        this.basketRepository = new InMemoryBasketRepository();
    }

    @Test
    public void addingProductByCurrentCustomer() {
        //arrange // given
        thereIsProduct("lego9298", 10.00, 7);
        thereIsCustomerIdentifiedWith("kanclerj@uek.krakow.pl");

        //act // when
        (new AddProductHandler(
                basketRepository,
                productStore,
                userContext
        )).handle("lego9298");

        //assert // then
        customersBasketContainsProducts("kanclerj@uek.krakow.pl", 1);
    }

    private void customersBasketContainsProducts(String userId, Integer productCount) {
        Basket basket = basketRepository.getCustomerBasket(userId);

        Assert.assertNotNull(basket);
        Assert.assertTrue(basket.productCount() == productCount);
    }

    private void thereIsCustomerIdentifiedWith(String userId) {
        userContext.authorize(userId);
    }

    private void thereIsProduct(String productId, double price, int stockQty) {
        productStore.addProduct(new Product(productId, stockQty, price));
    }
}
