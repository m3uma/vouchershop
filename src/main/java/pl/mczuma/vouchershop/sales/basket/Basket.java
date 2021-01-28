package pl.mczuma.vouchershop.sales.basket;

import pl.mczuma.vouchershop.catalog.Product;

import java.util.*;
import java.util.stream.Collectors;

public class Basket {
    private final Map<String, Product> products;
    private final Map<String, Integer> productsQuantities;

    public Basket() {
        this.products = new HashMap<>();
        this.productsQuantities = new HashMap<>();
    }

    public static Basket empty() {
        return new Basket();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void add(Product product) {
        products.put(product.getId(), product);
        if(isProductInBasket(product)){
            increaseQuantity(product);
        } else{
            putIntoBasket(product);
        }

    }

    private void putIntoBasket(Product product) {
        productsQuantities.put(product.getId(), 1);
        products.put(product.getId(), product);
    }

    private void increaseQuantity(Product product) {
        productsQuantities.put(
                product.getId(),
                productsQuantities.get(product.getId()) +1);
    }

    private boolean isProductInBasket(Product product) {
        return productsQuantities.containsKey(product.getId());
    }

    public Integer getProductsCount() {
        return products.size();
    }

    public List<BasketItem> getBasketItems() {
        return productsQuantities
                .entrySet()
                .stream()
                .map(es -> new BasketItem(es.getKey(), es.getValue()))
                .collect(Collectors.toList());
    }

    public void remove(String id) {
        productsQuantities.remove(id);
    }
}
