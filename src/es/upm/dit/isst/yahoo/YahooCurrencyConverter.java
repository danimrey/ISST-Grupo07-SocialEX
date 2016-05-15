package es.upm.dit.isst.yahoo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class YahooCurrencyConverter {
	private Map<String, Double> cambio = new HashMap<String, Double>();
	
	public YahooCurrencyConverter(){
		//En GAE no funciona la API de Yahoo. Se obtienen los cambios de divisa de forma manual
		//Cambios 15/5/2016 1:20
		cambio.put("EUR/EUR", 1.0);
		cambio.put("USD/USD", 1.0);
		cambio.put("GBP/GBP", 1.0);
		cambio.put("JPY/JPY", 1.0);
		cambio.put("EUR/USD", 1.1308);
		cambio.put("EUR/GBP", 0.7875);
		cambio.put("EUR/JPY", 122.8501);
		cambio.put("USD/EUR", 0.8843);
		cambio.put("GBP/EUR", 1.2698);
		cambio.put("JPY/EUR", 0.0081);
		cambio.put("USD/GBP", 0.696306);
		cambio.put("USD/JPY", 108.683);
		cambio.put("GPB/USD", 1.43615);
		cambio.put("GPB/JPY", 156.085);
		cambio.put("JPY/USD", 0.00920107);
		cambio.put("JPY/GBP", 0.00640676 );
	}
    public Double convert(String currencyFrom, String currencyTo) {
    	
        /*HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("http://quote.yahoo.com/d/quotes.csv?s=" + currencyFrom + currencyTo + "=X&f=l1&e=.csv");
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody = httpclient.execute(httpGet, responseHandler);
        httpclient.getConnectionManager().shutdown();
       return Double.parseDouble(responseBody);
       */
        
        return cambio.get(currencyFrom+"/"+currencyTo);
    }
}

