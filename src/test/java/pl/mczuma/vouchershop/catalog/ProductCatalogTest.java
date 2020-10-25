package pl.mczuma.vouchershop.catalog;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class ProductCatalogTest {
    public static final String NOT_EXISTS_ID = "notExistsId";
    public static final String PRODUCT_DESC = "My nice product";
    public static final String PRODUCT_PICTURE = "http://nice_picture";

    @Test
    public void itAllowsToRegisterNewProduct(){
        //Arrange //Given
        ProductCatalog catalog = thereIsProductCatalog();
        //Act //When //Act
        String productId = catalog.registerProduct();
        //Assert //Then //Expected
        Assert.assertTrue(catalog.isExists(productId));
        Assert.assertFalse(catalog.isExists(NOT_EXISTS_ID));
    }

    @Test
    public void itAllowsLoadCreatedProduct(){
        //Arrange
        ProductCatalog catalog = thereIsProductCatalog();
        //Act
        String productId = catalog.registerProduct();
        Product loaded = catalog.load(productId);
        //Assert
        Assert.assertEquals(loaded.getId(), productId);

    }

    @Test
    public void itAllowsFillADetais(){
        //Arrange
        ProductCatalog catalog = thereIsProductCatalog();
        //Act
        String productId = catalog.registerProduct();
        catalog.updateDetails(productId, PRODUCT_DESC, PRODUCT_PICTURE);
        Product loaded = catalog.load(productId);
        //Assert
        Assert.assertEquals(loaded.getPicture(), PRODUCT_PICTURE);
        Assert.assertEquals(loaded.getDescription(), PRODUCT_DESC);
    }

    @Test
    public void itAllowsApplyPrice(){
        //Arrange
        ProductCatalog catalog = thereIsProductCatalog();
        //Act
        String productId = catalog.registerProduct();
        catalog.applyPrice(productId, BigDecimal.TEN);
        Product loaded = catalog.load(productId);
        //Assert
        Assert.assertEquals(loaded.getPrice(), BigDecimal.TEN);
    }

    @Test
    public void itAllowsToListAllProducts(){
        //Arrange
        ProductCatalog catalog = thereIsProductCatalog();
        //Act
        String productId = catalog.registerProduct();
        catalog.updateDetails(productId, PRODUCT_DESC, PRODUCT_PICTURE);
        catalog.applyPrice(productId, BigDecimal.TEN);

        //Assert
        String draftProductId = catalog.registerProduct();
        List<Product> all = catalog.allPublished();

        Assert.assertEquals(1, all.size());
    }

    private static ProductCatalog thereIsProductCatalog() {
        return new ProductCatalog();
    }
}
