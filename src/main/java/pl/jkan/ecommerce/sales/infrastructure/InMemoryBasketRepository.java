package pl.jkan.ecommerce.sales.infrastructure;

import pl.jkan.ecommerce.sales.domain.basket.Basket;
import pl.jkan.ecommerce.sales.domain.basket.BasketRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryBasketRepository implements BasketRepository {
    private Map<String, Basket> baskets;

    public InMemoryBasketRepository() {
        this.baskets = new ConcurrentHashMap<String, Basket>();
    }

    public Basket getCustomerBasket(String customerId) {
        if (!baskets.containsKey(customerId)) {
            Basket basket = new Basket();
            baskets.put(customerId, basket);

            return basket;
        }

        return baskets.get(customerId);
    }
}
