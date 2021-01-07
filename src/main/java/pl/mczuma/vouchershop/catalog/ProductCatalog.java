package pl.mczuma.vouchershop.catalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProductCatalog {
    private final ProductStorage products;

    public ProductCatalog(ProductStorage productStorage) {
        this.products = productStorage;
    }

    public String registerProduct() {
        Product newProduct = new Product(UUID.randomUUID());
        products.save(newProduct);
        return newProduct.getId();
    }

    public boolean isExists(String productId) {
        return products.isExists(productId);
    }

    public Product load(String productId) {
        return products.load(productId);
    }

    public void updateDetails(String productId, String productDesc, String productPicture) {
        Product loaded = products.load(productId);
        loaded.setDescription(productDesc);
        loaded.setPicture(productPicture);
    }

    public void applyPrice(String productId, BigDecimal price) {
        Product loaded = products.load(productId);
        loaded.setPrice(price);
    }

    public List<Product> allPublished() {
        return products.allProducts()
                .stream()
                .filter(p -> p.getDescription() != null)
                .filter(p -> p.getPicture() != null)
                .collect(Collectors.toList());
    }
}
