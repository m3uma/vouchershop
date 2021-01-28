package pl.mczuma.vouchershop.sales;

public class AlwaysTheSameCustomerContext implements CurrentCustomerContext {
    @Override
    public String getCustomerId() {
        return "customer_1";
    }
}
