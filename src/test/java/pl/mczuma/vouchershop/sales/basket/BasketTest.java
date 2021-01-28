package pl.mczuma.vouchershop.sales.basket;

import org.junit.Test;
import pl.mczuma.vouchershop.catalog.Product;
import pl.mczuma.vouchershop.sales.basket.Basket;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class BasketTest {

    @Test
    public void newlyCreatedBasketIsEmpty(){
        Basket basket = new Basket();

        assertThat(basket.isEmpty())
                .isTrue();
    }

    @Test
    public void basketWithProductIsNotEmpty(){
        Basket basket = new Basket();
        Product product = thereIsProduct("lego-1234");

        basket.add(product);
        assertThat(basket.isEmpty())
                .isFalse();
    }

    @Test
    public void itShowsProductsCount(){
        Basket basket = new Basket();
        Product product1 = thereIsProduct("lego-1234");
        Product product2 = thereIsProduct("lego-5678");

        basket.add(product1);
        basket.add(product2);

        assertThat(basket.getProductsCount())
                .isEqualTo(2);
    }

    @Test
    public void itShowSingleLineForSameProductAddedTwice(){
        Basket basket = new Basket();
        Product product1 = thereIsProduct("lego-1234");

        basket.add(product1);
        basket.add(product1);
        basket.add(product1);

        assertThat(basket.getProductsCount())
                .isEqualTo(1);
    }

    @Test
    public void itContainsBasketLineQuantity(){
        Basket basket = new Basket();
        Product product1 = thereIsProduct("lego-1234");
        Product product2 = thereIsProduct("lego-5678");

        basket.add(product1);
        basket.add(product1);
        basket.add(product1);
        basket.add(product2);

        basketContainsProductWithQuantity(basket, product1, 3);
        basketContainsProductWithQuantity(basket, product2, 1);
    }


    private void basketContainsProductWithQuantity(Basket basket, Product product1, int expectedQuantity) {
        assertThat(basket.getBasketItems())
                .filteredOn(basketItem -> basketItem.getProductId().equals(product1.getId()))
                .extracting(basketItem -> basketItem.getQuantity())
                .first()
                .isEqualTo(expectedQuantity);
    }

    private Product thereIsProduct(String name) {
        Product product = new Product(UUID.randomUUID());
        product.setDescription(name);
        return product;
    }
}
