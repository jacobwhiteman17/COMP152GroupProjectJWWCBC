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
        Model = new JokeDataHandler();//getting data
    }

    public void loadData(responseData data){
        if(data.type.equals("single")){
            joke.setText(data.joke);
            setup.setText("");
            punchline.setText("");
            punch = "";//if the joke is a single part joke, then put the joke in the joke textfield and make the others empty
        }
        if(data.type.equals("twopart")){
            joke.setText("");
            setup.setText(data.setup);
            punch = data.delivery;//if the joke is two part, add setup to setup field, store punchline
        }
    }

    @FXML
    public void getPunchButton(){
        punchline.setText(punch);//gets the punchline and sets it to the button
    }

    @FXML
    public String getCheckBoxes(){
        var result = "?blacklistFlags=";//adds blacklists to URL
        var filter = false;//this will be updated to true and used so when other checkboxes are selected, it keeps adding to the URL

        if(nsfw.isSelected()){
            result+="nsfw";//adds to URL
            filter = true;
        }
        if(religious.isSelected()){
            if(filter==true)
                result+=",";
            result+="religious";//adds to URL
            filter = true;
        }
        if(political.isSelected()){
            if(filter==true)
                result+=",";
            result+="political";//adds to URL
            filter = true;
        }
        if(filter == false)
            return "";//gets rid of text from URL
        return result;
    }
    @FXML
    public void anyButtonPressed(){//all buttons do the same thing. adds category to URL, then adds selected checkboxes
        var whole = site+"Any";
        var other = getCheckBoxes();
        whole+=other;
        var data = Model.getData(whole);//data from site
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
