package pl.mczuma.vouchershop.sales;

import pl.mczuma.vouchershop.catalog.Product;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

public class Basket {
    private final Map<String, Product> products;

    public Basket() {
        this.products = new HashMap<>();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void add(Product product) {
        products.put(product.getId(), product);
    }

    public Integer getProductsCount() {
        return products.size();
    }

    public List<BasketItem> getBasketItems() {
        return Collections.emptyList();
    }

    public void remove(String id) {

    }
}
