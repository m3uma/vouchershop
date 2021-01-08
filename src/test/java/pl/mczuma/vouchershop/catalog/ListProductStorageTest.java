package pl.mczuma.vouchershop.catalog;

import org.junit.Assert;
import org.junit.Test;
import pl.mczuma.vouchershop.catalog.exceptions.NoSuchProductException;

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
        return ProductFixtures.randomProduct();
    }

    @Test
    public void itAllowLoadAllProducts(){
        ListProductStorage productStorage = new ListProductStorage();
        var product1 = thereIsProduct();
        var product2 = thereIsProduct();

        productStorage.save(product1);
        productStorage.save(product2);

        List<Product> all = productStorage.allProducts();
        assertThat(all)
                .hasSize(2)
                .extracting(Product::getId)
                .contains(product1.getId())
                .contains(product2.getId());
    }

    @Test
    public void itAllowCheckIfProductExists(){
        ListProductStorage productStorage = new ListProductStorage();
        var product1 = thereIsProduct();

        productStorage.save(product1);

        assertThat(productStorage.isExists(product1.getId()))
                .isTrue();
        assertThat(productStorage.isExists(UUID.randomUUID().toString()))
                .isFalse();
    }

    @Test
    public void itAllowLoadSingleProduct(){
        ProductStorage productStorage = new ListProductStorage();
        var product1 = thereIsProduct();

        productStorage.save(product1);

        var loaded = productStorage.load(product1.getId())
                .get();

        assertThat(loaded.getId())
                .isEqualTo(product1.getId());

    }

    @Test(expected = NoSuchProductException.class)
    public void itShouldProtectFromDefenceProgramming(){
        ProductStorage productStorage = new ListProductStorage();

        var loaded = productStorage.load(UUID.randomUUID().toString())
                .orElseThrow(() -> new NoSuchProductException("no such product"));

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
