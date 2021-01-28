package pl.mczuma.vouchershop.sales;

import pl.mczuma.vouchershop.catalog.Product;
import pl.mczuma.vouchershop.catalog.ProductCatalog;
import pl.mczuma.vouchershop.sales.basket.Basket;
import pl.mczuma.vouchershop.sales.basket.InMemoryBasketStorage;
import pl.mczuma.vouchershop.sales.offering.Offer;
import pl.mczuma.vouchershop.sales.offering.OfferMaker;

public class SalesFacade {

    private final CurrentCustomerContext currentSystemUserContext;
    private final InMemoryBasketStorage basketStorage;
    private final ProductCatalog productCatalog;
    private final OfferMaker offerMaker;
    private final PaymentGateway paymentGateway;

    public SalesFacade(CurrentCustomerContext currentSystemUserContext, InMemoryBasketStorage basketStorage, ProductCatalog productCatalog, OfferMaker offerMaker, PaymentGateway paymentGateway) {
        this.currentSystemUserContext = currentSystemUserContext;
        this.basketStorage = basketStorage;
        this.productCatalog = productCatalog;
        this.offerMaker = offerMaker;
        this.paymentGateway = paymentGateway;
    }

    public void addToBasket(String productId) {
        Basket basket = basketStorage.getBasket(getCurrentCustomerId())
                .orElse(Basket.empty());

        Product product = productCatalog.load(productId);

        basket.add(product);

        basketStorage.addForCustomer(getCurrentCustomerId(), basket);
    }

    private String getCurrentCustomerId() {
        return currentSystemUserContext.getCustomerId();
    }

    public Offer getCurrentOffer() {
        Basket basket = basketStorage.getBasket(getCurrentCustomerId())
                .orElse(Basket.empty());

        return offerMaker.calculate(basket.getBasketItems());
    }

    public PaymentDetails acceptOffer(ClientData clientData) {
        Basket basket = basketStorage.getBasket(getCurrentCustomerId())
                .orElse(Basket.empty());

        Offer offer = offerMaker.calculate(basket.getBasketItems());

        Reservation reservation = Reservation.of(offer, clientData);

        PaymentDetails paymentDetails = paymentGateway.registerFor(reservation, clientData);

        return paymentDetails;
    }
}
