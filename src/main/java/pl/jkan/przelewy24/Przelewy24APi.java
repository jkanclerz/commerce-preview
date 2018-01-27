package pl.jkan.przelewy24;

import pl.jkan.support.http.HttpClient;
import pl.jkan.support.http.Request;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Przelewy24APi {
    HttpClient http;
    private final String merchantId;
    private final String crcCode;
    private final String endpoint = "https://sandbox.przelewy24.pl";

    public Przelewy24APi(HttpClient http, String merchantId, String crcCode) {
        this.http = http;
        this.merchantId = merchantId;
        this.crcCode = crcCode;
    }

    public void registerTransaction(RegisterTransactionData data) {
        try {
            http.send(getRegisterTransactionRequest(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Request getRegisterTransactionRequest(RegisterTransactionData data) {
        Map<String, String> params = new HashMap<>();
        params.put("p24_merchant_id", merchantId);
        params.put("p24_pos_id", merchantId);
        params.put("p24_session_id", data.getPaymentId());
        params.put("p24_amount", data.getAmount().toString());
        params.put("p24_currency", "PLN");
        params.put("p24_description", "");
        params.put("p24_email", "");
        params.put("p24_country", "");
        params.put("p24_url_return", "");
        params.put("p24_url_status", "");
        params.put("p24_api_version", "");
        params.put("p24_sign", generateMd5(String.format("%s|%s|%s|%s|%s", data.getPaymentId(), merchantId, data.getAmount().toString(), "PLN", crcCode)));

        return Request.post(
                String.format("%s/%s", endpoint, "trnRegister"),
                params,
                new HashMap<String, String>()
        );
    }

    public void testConnection() {
        try {
            http.send(getTestConnectionRequest());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Request getTestConnectionRequest() {
        Map<String, String> params = new HashMap<>();
        params.put("p24_merchant_id", merchantId);
        params.put("p24_pos_id", merchantId);
        params.put("p24_sign", generateMd5(String.format("%s|%s", merchantId, crcCode)));

        return Request.post(
                String.format("%s/%s", endpoint, "testConnection"),
                params,
                new HashMap<String, String>()
        );
    }

    private String generateMd5(String plaintext) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(plaintext.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1,digest);
            String hashtext = bigInt.toString(16);
            while(hashtext.length() < 32 ){
                hashtext = "0"+hashtext;
            }

            return hashtext.toUpperCase();

        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
}
