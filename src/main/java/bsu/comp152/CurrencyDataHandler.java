package bsu.comp152;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class CurrencyDataHandler {
    private HttpClient dataGrabber;
    private String webLocation;

    public CurrencyDataHandler(String webLocation) {
        dataGrabber = HttpClient.newHttpClient();
        this.webLocation = webLocation;
    }



    public ArrayList<currencyDataType> getData(){
        var requestBuilder = HttpRequest.newBuilder();
        var dataRequest = requestBuilder.uri(URI.create(webLocation)).build();
        HttpResponse<String> response = null;
        try {
            response = dataGrabber.send(dataRequest, HttpResponse.BodyHandlers.ofString());
        }catch(IOException e){
            System.out.println("Could not connect to the network website");
        }
        catch (InterruptedException e){
            System.out.println("Connection to the queried site has ended");
        }
        if (response == null ){
            System.out.println("Something else went wrong, ending the program now");
            System.exit(-1);
        }
        var usefulData = response.body();
        var jsonInterpreter = new Gson();
        var currencyData = jsonInterpreter.fromJson(usefulData, responseDataType.class);
        return currencyData.results;

    }

    class responseDataType {
        String title;
        float version;
        String href;
        ArrayList<currencyDataType> results;
    }

    class currencyDataType {
        String title;
        String href;
        float aFormCurrency;
        String thumbnail;

        @Override
        public String toString() {
            return "Currency Title: " + title ;
        }
    }
}
