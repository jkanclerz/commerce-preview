package pl.jkan.ecommerce.sales.domain.product;

import pl.jkan.ecommerce.sales.domain.basket.Product;

public interface ProductStore {
    public void addProduct(Product product);
    public Product get(String productId);
}
