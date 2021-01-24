package pl.mczuma.vouchershop.catalog;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JdbcProductStorageTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate.execute("DROP TABLE `products_catalog__products` IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE `products_catalog__products` (" +
                "`id` varchar(100) NOT NULL," +
                "`description` varchar(255)," +
                "`picture` varchar(255)," +
                "`price` DECIMAL(12,2)," +
                "PRIMARY KEY(id)" +
                ");");
    }

    @Test
    public void itAllowAddProduct(){
        JdbcProductStorage productStorage = new JdbcProductStorage(jdbcTemplate);
        Product product = ProductFixtures.randomProduct();

        productStorage.save(product);

        assertThat(productStorage.isExists(product.getId()))
                .isTrue();

        assertThat(productStorage.isExists(UUID.randomUUID().toString()))
                .isFalse();
    }

    @Test
    public void itAllowLoadAllProducts(){
        var p1 = ProductFixtures.readyToSellProduct("p1", 20.20);
        var p2 = ProductFixtures.randomProduct();

        ProductStorage jdbcStorage = thereIsJDBCProductStorage();

        jdbcStorage.save(p1);
        jdbcStorage.save(p2);

        List<Product> products = jdbcStorage.allProducts();

        assertThat(products)
                .hasSize(1)
                .extracting(Product::getId)
                .contains(p1.getId())
                .doesNotContain(p2.getId());
    }

    private ProductStorage thereIsJDBCProductStorage() {
        return new JdbcProductStorage(jdbcTemplate);
    }
    @Test
    public void itAllowCheckIfProductExists(){
        JdbcProductStorage productStorage = new JdbcProductStorage(jdbcTemplate);
        Product product = ProductFixtures.randomProduct();

        productStorage.save(product);

        assertThat(productStorage.isExists(product.getId()))
                .isTrue();
        assertThat(productStorage.isExists(UUID.randomUUID().toString()))
                .isFalse();
    }

}
