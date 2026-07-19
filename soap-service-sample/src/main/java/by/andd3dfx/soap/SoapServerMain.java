package by.andd3dfx.soap;

import jakarta.xml.ws.Endpoint;

public final class SoapServerMain {

    private static final String ADDRESS = "http://localhost:8080/services/exrates";

    private SoapServerMain() {
    }

    public static void main(String[] args) {
        Endpoint.publish(ADDRESS, new ExRatesSoapImpl());
        System.out.println("SOAP ExRates published at " + ADDRESS);
        System.out.println("WSDL: " + ADDRESS + "?wsdl");
    }
}
