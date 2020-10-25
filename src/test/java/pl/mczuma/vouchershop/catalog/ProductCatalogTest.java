package pl.mczuma.vouchershop.catalog;

import org.junit.Assert;
import org.junit.Test;

public class ProductCatalogTest {
    public static final String NOT_EXISTS_ID = "notExistsId";

    @Test
    public void itAllowsToRegisterNewProduct(){
        //Arrange
        ProductCatalog catalog = thereIsProductCatalog();
        //Act
        String productId = catalog.registerProduct();
        //Assert
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
    public void itAllowsFillADtais(){
        //Arrange
        ProductCatalog catalog = thereIsProductCatalog();
        //Act
        String productId = catalog.registerProduct();
        catalog.updateDetails(productID, "My nice product", "http://nice_picture");
        Product loaded = catalog.load(productId);
        //Assert
        Assert.assertEquals(loaded.getPicture(), "http://nice_picture");
        Assert.assertEquals(loaded.getDescription(), "http://nice_picture");
    }

    private static ProductCatalog thereIsProductCatalog() {
        return new ProductCatalog();
    }
}
