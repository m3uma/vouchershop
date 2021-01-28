package pl.mczuma.vouchershop.sales;

import pl.mczuma.vouchershop.catalog.ProductCatalog;
import pl.mczuma.vouchershop.catalog.ProductCatalogConfiguration;
import pl.mczuma.vouchershop.sales.basket.InMemoryBasketStorage;
import pl.mczuma.vouchershop.sales.offering.OfferMaker;

import java.math.BigDecimal;
import java.util.UUID;

public class SalesTestCase {
    protected InMemoryBasketStorage basketStorage;
    protected CurrentCustomerContext userContext;
    protected ProductCatalog productCatalog;
    protected OfferMaker offerMaker;
    protected String customerId;
    protected PaymentGateway paymentGateway;

    protected static ProductCatalog thereIsProductCatalog() {
        return new ProductCatalogConfiguration().myProductCatalog();
    }

    protected String thereIsCustomerWhoIsDoingSomeShoping() {
        var id = UUID.randomUUID().toString();
        this.customerId = id;
        return id;
    }

    protected String thereIsProductAvailable() {
        String productId = productCatalog.registerProduct();
        productCatalog.applyPrice(productId, BigDecimal.valueOf(20.20));
        productCatalog.updateDetails(productId, "info", "pic");

        return productId;
    }

    protected SalesFacade thereIsSalesModule() {
        return new SalesFacade(userContext, basketStorage, productCatalog, offerMaker, paymentGateway);
    }
}
