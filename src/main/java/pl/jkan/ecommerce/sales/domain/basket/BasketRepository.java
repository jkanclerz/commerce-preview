package pl.jkan.ecommerce.sales.domain.basket;

public interface BasketRepository {
    public Basket getCustomerBasket(String customerId);
}
