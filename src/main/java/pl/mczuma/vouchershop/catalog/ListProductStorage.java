package pl.mczuma.vouchershop.catalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ListProductStorage implements ProductStorage {
    List <Product> products;

    public ListProductStorage() {
        this.products = new ArrayList<>();
    }

    @Override
    public void save(Product newProduct) {
        products.add(newProduct);
    }

    @Override
    public boolean isExists(String productId) {
        boolean ex = false;
        for (Product p:products){
            if(p.getId().equals(productId)){
                ex=true;
            }
        }
        return ex;
    }

    @Override
    public Product load(String productId) {
        Optional<Product> pr = products.stream().filter(product -> product.getId().equals(productId)).findFirst();
        return pr.get();
    }

    @Override
    public List<Product> allProducts() {
        return products
                .stream()
                .collect(Collectors.toList());
    }
}
