package pl.jkan.ecommerce.sales.infrastructure;

import pl.jkan.ecommerce.sales.domain.basket.Product;
import pl.jkan.ecommerce.sales.domain.product.ProductStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryProductStore implements ProductStore {

    private Map<String, Product> products;

    public InMemoryProductStore() {
        this.products = new HashMap<String, Product>();
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public Product get(String productId) {
        return products.get(productId);
    }

    public List<Product> findAll() {

        return new ArrayList<Product>(products.values());
    }
}
