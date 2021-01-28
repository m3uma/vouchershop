package pl.mczuma.vouchershop.catalog;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductFixtures {
    public static Product randomProduct(){
        return new Product(UUID.randomUUID());
    }

    public static Product readyToSellProduct(String name, Double price) {
        Product product = new Product(UUID.randomUUID());
        product.setDescription(name);
        product.setPrice(BigDecimal.valueOf(price));
        product.setPicture("http://nicePiccture");

        return product;
    }
}
