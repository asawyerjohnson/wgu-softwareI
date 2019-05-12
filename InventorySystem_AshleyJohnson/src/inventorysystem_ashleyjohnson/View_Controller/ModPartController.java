package inventorysystem_ashleyjohnson.View_Controller;

import inventorysystem_ashleyjohnson.Model.Inhouse;
import inventorysystem_ashleyjohnson.Model.Outsourced;
import inventorysystem_ashleyjohnson.Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ashley Johnson C482 Software I
 */
public class ModPartController implements Initializable {

   @FXML
    private TextField partIDField;

    @FXML
    private TextField partNameField;

    @FXML
    private TextField partInStockField;

    @FXML
    private TextField partMaxField;

    @FXML
    private TextField partMinField;

    @FXML
    private TextField partSourceField;

    @FXML
    private TextField partPriceField;

    @FXML
    private Label labelMod;

    @FXML
    private Button saveMod;

    @FXML
    private Button cancelMod;

    @FXML
    private RadioButton inhouseMod;

    @FXML
    private RadioButton outsourcedMod;
    private Part part;
    private Part changedPart;
    
       
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }    

     @FXML
    void handleCancelMod(Event event) throws IOException {
        Stage stage; 
        Parent root;       
        stage=(Stage) cancelMod.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader=new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        root =loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void setPart(Part part) {
        System.out.println("Modify part screen opened.");
        this.part = part;
        partIDField.setText(Integer.toString(part.getPartID()));
        partNameField.setText(part.getPartName());
        partInStockField.setText(Integer.toString(part.getPartInStock()));
        partPriceField.setText(Double.toString(part.getPartPrice()));
        partMaxField.setText(Integer.toString(part.getPartMax()));
        partMinField.setText(Integer.toString(part.getPartMin()));
        if(part instanceof Inhouse){
            Inhouse inHouse = (Inhouse)part;
            partSourceField.setText(Integer.toString(inHouse.getMachineID()));
            labelMod.setText("Machine ID");
            inhouseMod.selectedProperty().set(true);
        } else{
            Outsourced outSource = (Outsourced)part;
            partSourceField.setText(outSource.getCompanyName());
            labelMod.setText("Company Name");
            outsourcedMod.selectedProperty().set(true);            
        }
    }

    @FXML
    void handleInhouseMod(ActionEvent event) {
        labelMod.setText("Machine ID");
        partSourceField.setPromptText("Mach ID");
    }

    @FXML
    void handleOutsourcedMod(ActionEvent event) {
        labelMod.setText("Company Name");
        partSourceField.setPromptText("Company Name");       
    }

    @FXML
    void handleSaveMod(ActionEvent event) throws IOException {
        this.changedPart = part;
            part.setPartID(Integer.parseInt(partIDField.getText()));
            part.setPartName(partNameField.getText());
            part.setPartInStock(Integer.parseInt(partInStockField.getText()));
            part.setPartPrice(Double.parseDouble(partPriceField.getText()));
            part.setPartMax(Integer.parseInt(partMaxField.getText()));
            part.setPartMin(Integer.parseInt(partMinField.getText()));
            System.out.println("Modification saved.");
        //Back to Main Screen
        Stage stage; 
        Parent root;       
        stage=(Stage) saveMod.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader=new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        root =loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    } 
}
