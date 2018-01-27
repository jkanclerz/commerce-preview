package pl.jkan.przelewy24;

import org.junit.Assert;
import org.junit.Test;
import pl.jkan.support.http.HttpClient;
import pl.jkan.support.http.Request;
import pl.jkan.support.http.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Przelewy24ApiTest {

    @Test
    public void itUseHtppClienetToComunicate() {
        HttpClientSpy client = new HttpClientSpy();

        Przelewy24APi api = new Przelewy24APi(client, "my_merchant_id", "my_crc_code");
        api.testConnection();

        Assert.assertNotNull(client.lastRequest);
    }

    @Test
    public void requestContainsParams() {
        HttpClientSpy client = new HttpClientSpy();

        Przelewy24APi api = new Przelewy24APi(client, "my_merchant_id", "my_crc_code");
        api.testConnection();

        Map<String, String> params = client.lastRequest.getParams();

        Assert.assertTrue(params.containsKey("p24_merchant_id"));
        Assert.assertTrue(params.containsKey("p24_pos_id"));
        Assert.assertTrue(params.containsKey("p24_sign"));
    }

    @Test
    public void requestWithValidParams() {
        HttpClientSpy client = new HttpClientSpy();

        Przelewy24APi api = new Przelewy24APi(client, "my_merchant_id", "my_crc_code");
        api.testConnection();

        Map<String, String> params = client.lastRequest.getParams();

        Assert.assertTrue(params.get("p24_merchant_id").equals("my_merchant_id"));
        Assert.assertTrue(params.get("p24_pos_id").equals("my_merchant_id"));
        Assert.assertEquals(params.get("p24_sign"), "80B9FD7D2F4132350813446C1A71CFCE");
    }

    @Test
    public void itClallValidUrl() {
        HttpClientSpy client = new HttpClientSpy();

        Przelewy24APi api = new Przelewy24APi(client, "my_merchant_id", "my_crc_code");
        api.testConnection();

        Assert.assertEquals("https://sandbox.przelewy24.pl/testConnection", client.lastRequest.getUrl());
    }

    @Test
    public void itAllowRegisterTransaction() {
        HttpClientSpy client = new HttpClientSpy();
        Przelewy24APi api = new Przelewy24APi(client, "my_merchant_id", "my_crc_code");

        api.registerTransaction(new RegisterTransactionData("id_platnosci", 2500));

        Map<String, String> params = client.lastRequest.getParams();
        Assert.assertEquals("https://sandbox.przelewy24.pl/trnRegister", client.lastRequest.getUrl());
        Assert.assertEquals(params.get("p24_merchant_id"), "my_merchant_id");
        Assert.assertEquals(params.get("p24_pos_id"), "my_merchant_id");
        Assert.assertEquals(params.get("p24_session_id"), "id_platnosci");
        Assert.assertEquals(params.get("p24_amount"), "2500");
        Assert.assertEquals(params.get("p24_currency"), "PLN");
        Assert.assertEquals(params.get("p24_description"), "");
        Assert.assertEquals(params.get("p24_email"), "");
        Assert.assertEquals(params.get("p24_country"), "");
        Assert.assertEquals(params.get("p24_url_return"), "");
        Assert.assertEquals(params.get("p24_url_status"), "");
        Assert.assertEquals(params.get("p24_api_version"), "");
        Assert.assertEquals(params.get("p24_sign"), "5FBBD233966974A25D531BAB3279BF87");

    }

    class HttpClientSpy implements HttpClient {
        public Request lastRequest;
        @Override
        public Response send(Request request) throws IOException {
            lastRequest = request;

            return new Response(200, new HashMap<String, String>(), "");
        }
    }
}
