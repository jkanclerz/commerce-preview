package pl.jkan.ecommerce.sales;

import org.junit.Assert;
import org.junit.Test;
import pl.jkan.ecommerce.sales.domain.basket.NotEnoughQuantityOnStock;
import pl.jkan.ecommerce.sales.domain.basket.Product;
import pl.jkan.ecommerce.sales.domain.basket.Basket;

public class BasketTest {

    @Test
    public void itIsEmptyWhenNew() {
        Basket basket = new Basket();

        Assert.assertTrue(basket.isEmpty());
    }

    @Test
    public void itAllowAddProductToBasket() {
        //3xA
        //arrange
        Basket basket = new Basket();
        Product p1 = new Product("lego-9398", 10);
        //act
        basket.add(p1);
        //assert
        Assert.assertFalse(basket.isEmpty());
    }

    @Test
    public void itIncreaseQuantityWhenSameProduct() {
        Basket basket = new Basket();
        Product p1 = new Product("lego-1234", 10);
        Product p2 = new Product("lego-9398", 10);

        basket.add(p1);
        basket.add(p2);
        basket.add(p2);

        Assert.assertTrue(
                "Should consider product qty-ies",
                2 == basket.productCount()
        );

        Assert.assertTrue(
                2 == basket.countOfProducts("lego-9398")
        );

        Assert.assertTrue(
                1 == basket.countOfProducts("lego-1234")
        );
    }

    @Test
    public void cantAddProductWithStockEquals0() {
        Basket basket = new Basket();
        Product p1 = new Product("lego-9398", 0);

        try {
            basket.add(p1);
            Assert.fail("Should throw exception: NoEnoughQuantity");
        } catch (NotEnoughQuantityOnStock e) {
            Assert.assertTrue(true);
        }
    }
}
