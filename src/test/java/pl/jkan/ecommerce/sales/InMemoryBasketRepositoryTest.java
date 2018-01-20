package pl.jkan.ecommerce.sales;

import org.junit.Assert;
import org.junit.Test;
import pl.jkan.ecommerce.sales.domain.basket.Basket;
import pl.jkan.ecommerce.sales.domain.basket.BasketRepository;
import pl.jkan.ecommerce.sales.infrastructure.InMemoryBasketRepository;

public class InMemoryBasketRepositoryTest {
    @Test
    public void itCreateNewBasketForCustomerWhenNotExisits() {
        BasketRepository basketRepository = new InMemoryBasketRepository();

        Basket basket = basketRepository.getCustomerBasket("new_customer");

        Assert.assertNotNull(basket);
    }

    @Test
    public void itCreateUniqueBasketsForCustomers() {
        BasketRepository basketRepository = new InMemoryBasketRepository();

        Basket basket1 = basketRepository.getCustomerBasket("new_customer");
        Basket basket2 = basketRepository.getCustomerBasket("next_customer");

        Assert.assertNotEquals(basket1, basket2);
    }

    @Test
    public void itGivesSameBasketForSameCustomer() {
        BasketRepository basketRepository = new InMemoryBasketRepository();

        Basket basket1 = basketRepository.getCustomerBasket("same_customer");
        Basket basket2 = basketRepository.getCustomerBasket("same_customer");

        Assert.assertEquals(basket1, basket2);
    }

    @Test
    public void itGivesFreshBasketWhenNewCustomer() {
        BasketRepository basketRepository = new InMemoryBasketRepository();

        Basket basket1 = basketRepository.getCustomerBasket("new_customer");

        Assert.assertTrue(basket1.isEmpty());
    }
}
