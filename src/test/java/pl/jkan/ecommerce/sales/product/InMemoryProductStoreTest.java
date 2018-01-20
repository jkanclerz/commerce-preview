package pl.jkan.ecommerce.sales.product;

import org.junit.Assert;
import org.junit.Test;
import pl.jkan.ecommerce.sales.domain.basket.Product;
import pl.jkan.ecommerce.sales.domain.product.InMemoryProductStore;
import pl.jkan.ecommerce.sales.domain.product.ProductStore;

public class InMemoryProductStoreTest {
    @Test
    public void itAllowStoreProduct() {
        ProductStore productStore = new InMemoryProductStore();

        productStore.addProduct(new Product("lego-1", 1, 10.00));
        productStore.addProduct(new Product("lego-2", 1, 10.00));

        Product product = productStore.get("lego-2");

        Assert.assertNotNull(product);
        Assert.assertTrue(product.getId().equals("lego-2"));
    }
}
