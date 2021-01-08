package pl.mczuma.vouchershop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.mczuma.vouchershop.catalog.HashMapProductStorage;
import pl.mczuma.vouchershop.catalog.JdbcProductStorage;
import pl.mczuma.vouchershop.catalog.ProductCatalog;

@SpringBootApplication
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class, args);

        /*ProductCatalog catalog;
        if (System.getenv( "ENV").equals("dev")){
            catalog = new ProductCatalog(new HashMapProductStorage());
        } else{
            catalog = new ProductCatalog(new JdbcProductStorage());
        }*/
    }
}
