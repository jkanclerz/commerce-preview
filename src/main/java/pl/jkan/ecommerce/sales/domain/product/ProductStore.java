package pl.jkan.ecommerce.sales.domain.product;

import pl.jkan.ecommerce.sales.domain.basket.Product;

import java.util.List;

public interface ProductStore {
    public void addProduct(Product product);
    public Product get(String productId);

    public List<Product> findAll();
}
