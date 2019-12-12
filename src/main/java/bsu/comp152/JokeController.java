package bsu.comp152;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class JokeController implements Initializable {
    @FXML
    public JokeDataHandler Model;
    public Button misc, programming, any;
    public CheckBox nsfw, religious, political;
    public TextField joke, setup, punchline;
    public String site = "https://sv443.net/jokeapi/category/";
    public String punch = "";

    @Override
    public void initialize(URL location, ResourceBundle resources){
        Model = new JokeDataHandler();
    }

    public void loadData(responseData data){
        if(data.type.equals("single")){
            joke.setText(data.joke);
            setup.setText("");
            punchline.setText("");
            punch = "";
        }
        if(data.type.equals("twopart")){
            joke.setText("");
            setup.setText(data.setup);
            punch = data.delivery;
        }
    }

    @FXML
    public void getPunchButton(){
        punchline.setText(punch);
    }

    @FXML
    public String getCheckBoxes(){
        var result = "?blacklistFlags=";
        var filter = false;

        if(nsfw.isSelected()){
            result+="nsfw";
            filter = true;
        }
        if(religious.isSelected()){
            if(filter==true)
                result+=",";
            result+="religious";
            filter = true;
        }
        if(political.isSelected()){
            if(filter==true)
                result+=",";
            result+="political";
            filter = true;
        }
        if(filter == false)
            return "";
        return result;
    }
    @FXML
    public void anyButtonPressed(){
        var whole = site+"Any";
        var other = getCheckBoxes();
        whole+=other;
        var data = Model.getData(whole);
        loadData(data);
    }
    @FXML
    public void miscButtonPressed(){
        var whole = site+"Miscellaneous";
        var other = getCheckBoxes();
        whole+=other;
        var data = Model.getData(whole);
        loadData(data);
    }
    @FXML
    public void programmingButtonPressed(){
        var whole = site+"Programming";
        var other = getCheckBoxes();
        whole+=other;
        var data = Model.getData(whole);
        loadData(data);
    }

}
