package pl.mczuma.vouchershop.catalog;

import java.util.*;

public class ListProductStorage implements ProductStorage {
    private final List <Product> products;

    public ListProductStorage() {
        this.products = new ArrayList<>();
    }

    @Override
    public void save(Product newProduct) {
        products.add(newProduct);
    }

    @Override
    public boolean isExists(String productId) {
        return products
                .stream()
                .anyMatch(product -> product.getId().equals(productId));
        /*boolean ex = false;
        for (Product p:products){
            if(p.getId().equals(productId)){
                ex=true;
            }
        }
        return ex; */
    }

    @Override
    public Optional<Product> load(String productId) {
        return products
                .stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst();

        /*Optional<Product> pr = products.stream().filter(product -> product.getId().equals(productId)).findFirst();
        return pr.get(); */
    }

    @Override
    public List<Product> allProducts() {
        return Collections.unmodifiableList(products);
        /*return products
                .stream()
                .collect(Collectors.toList()); */
    }

    @Override
    public void clean() {
        products.clear();
    }
}
