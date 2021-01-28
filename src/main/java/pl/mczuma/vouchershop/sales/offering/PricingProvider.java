package pl.mczuma.vouchershop.sales.offering;

public interface PricingProvider {
    ProductPricing getForProduct(String productId);
}
