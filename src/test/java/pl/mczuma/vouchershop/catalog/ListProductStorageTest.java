package pl.mczuma.vouchershop.catalog;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class ListProductStorageTest {
    @Test
    public void itAllowAddProduct(){
        //Arrange
        ListProductStorage productStorage = new ListProductStorage();
        Product product = thereIsProduct();
        //A
        productStorage.save(product);
        //A
        Assert.assertTrue(productStorage.isExists(product.getId()));
    }

    private Product thereIsProduct() {
        return new Product(UUID.randomUUID());
    }

    @Test
    public void itAllowLoadAllProducts(){
        ListProductStorage productStorage = new ListProductStorage();
        Product product = thereIsProduct();

        productStorage.save(product);

        List<Product> all = productStorage.allProducts();
        Assert.assertEquals(1, all.size());
    }

    @Test
    public void itAllowCheckIfProductExists(){
        ListProductStorage productStorage = new ListProductStorage();
        Product product = thereIsProduct();

        productStorage.save(product);

        Assert.assertTrue(productStorage.isExists(product.getId()));
    }

    @Test
    public void testIt() {
        assertThat("Ala ma kota").containsIgnoringCase("ala");
        assertThat(Arrays.asList("kuba", "michal", "artur"))
                .hasSize(3)
                .contains("kuba", "michal")
                .doesNotContain("pawel");
    }
}
