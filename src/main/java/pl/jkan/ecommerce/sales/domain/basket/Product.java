package pl.jkan.ecommerce.sales.domain.basket;

public class Product {
    private String symbol;
    private Integer stockQuantity;
    private Double price;

    public Product(String symbol, Integer stockQuantity) {
        this.symbol = symbol;
        this.stockQuantity = stockQuantity;
        this.price = 0.0;
    }

    public Product(String symbol, Integer stockQuantity, Double price) {
        this.stockQuantity = stockQuantity;
        this.symbol = symbol;
        this.price = price;
    }

    public String getId() {
        return symbol;
    }

    public Boolean isInStock() {
        return stockQuantity > 0;
    }
}
