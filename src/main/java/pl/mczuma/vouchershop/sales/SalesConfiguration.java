package pl.mczuma.vouchershop.sales;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mczuma.vouchershop.catalog.ProductCatalog;
import pl.mczuma.vouchershop.sales.basket.InMemoryBasketStorage;
import pl.mczuma.vouchershop.sales.offering.OfferMaker;
import pl.mczuma.vouchershop.sales.offering.ProductCatalogPricingProvider;

@Configuration
public class SalesConfiguration {
    @Bean
    SalesFacade salesFacade(CurrentCustomerContext customerContext, ProductCatalog productCatalog, OfferMaker offerMaker, PaymentGateway paymentGateway) {
        return new SalesFacade(customerContext, new InMemoryBasketStorage(), productCatalog, offerMaker, paymentGateway);
    }

    @Bean
    PaymentGateway payUPaymentGateway() {
        return new PayUPaymentGateway();
    }

    @Bean
    CurrentCustomerContext customerContext() {
        return new AlwaysTheSameCustomerContext();
    }

    @Bean
    OfferMaker offerMaker(ProductCatalog productCatalog) {
        return new OfferMaker(new ProductCatalogPricingProvider(productCatalog));
    }
}
