package pl.jkan.ecommerce.sales.domain.basket;

import java.util.HashMap;
import java.util.Map;

public class Basket {

    private Map<String, Integer> products;

    public Basket() {
        this.products = new HashMap<String, Integer>();
    }

    public void add(Product product) {

        if (!product.isInStock()) {
            throw new NotEnoughQuantityOnStock();
        }

        if (isInBasket(product)) {
            incrementQty(product);
        } else {
            createBasketRow(product);
        }
    }

    private void createBasketRow(Product product) {
        products.put(product.getId(), 1);
    }

    private void incrementQty(Product product) {
        products.put(product.getId(), products.get(product.getId()) + 1);
    }

    private boolean isInBasket(Product product) {
        return products.containsKey(product.getId());
    }

    public Boolean isEmpty() {
        return products.isEmpty();
    }

    public Integer productCount() {
        return products.size();
    }

    public Integer countOfProducts(String id) {
        return products.get(id);
    }
}
