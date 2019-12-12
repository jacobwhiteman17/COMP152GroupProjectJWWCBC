package bsu.comp152;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CurrencyController implements Initializable {
    @FXML
    private ListView<CurrencyDataHandler.currencyDataType> ListControl;
    private CurrencyDataHandler Model;
    private String currencyType;


    public void loadData() throws IOException {
        String url_str = "https://api.exchangerate-api.com/v4/latest/USD";
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();
        String req_result = jsonobj.get("result").getAsString();
    }

    private String getCurrencyType(){
        return currencyType;
    }

    private String getCurrency(){
        TextInputDialog answer = new TextInputDialog(" ");
        answer.setHeaderText("Input value for the currency you selected");
        answer.setContentText("Value:");
        Optional<String> result = answer.showAndWait();
        if (result.isPresent())
            return result.get();
        else
            return "";
    }

    @FXML
    public void selectMenuItem(javafx.event.ActionEvent actionEvent) {
        var item =(MenuItem)actionEvent.getSource();
        currencyType = item.getText();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //loadData();
        currencyType = "";
        ListControl.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<CurrencyDataHandler.currencyDataType>() {
                    @Override
                    public void changed(ObservableValue<? extends CurrencyDataHandler.currencyDataType> observable,
                                        CurrencyDataHandler.currencyDataType oldValue, CurrencyDataHandler.currencyDataType newValue) {
                        var newCurrency = ListControl.getSelectionModel().getSelectedItem();
                        Alert currencyInfo = new Alert(Alert.AlertType.INFORMATION);
                        currencyInfo.setTitle("Information  for "+newCurrency.title);
                        currencyInfo.setHeaderText("Currency: "+ newCurrency.aFormCurrency);
                        currencyInfo.setContentText("Here: "+newCurrency.href);
                        currencyInfo.showAndWait();
                    }
                }
        );
    }
}