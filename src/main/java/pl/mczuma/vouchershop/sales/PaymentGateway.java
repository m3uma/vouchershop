package pl.mczuma.vouchershop.sales;

public interface PaymentGateway {
    PaymentDetails registerFor(Reservation reservation, ClientData clientData);
}
