package inventorysystem_ashleyjohnson.View_Controller;

import inventorysystem_ashleyjohnson.Model.Inhouse;
import inventorysystem_ashleyjohnson.Model.Inventory;
import inventorysystem_ashleyjohnson.Model.Outsourced;
import inventorysystem_ashleyjohnson.Model.Part;
import static inventorysystem_ashleyjohnson.View_Controller.MainScreenController.selectedIndex;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ashley Johnson C482 Software I
 */
public class AddPartController implements Initializable {

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
    private Label labelAdd;
    
    @FXML
    private Button saveAdd;
    
    @FXML
    private Button cancelAdd;
    
    @FXML
    private RadioButton inhouseAdd;
    
    @FXML
    private RadioButton outsourcedAdd;
    
     @FXML
    private ToggleGroup partSource;
    
    private Part part;
    private Part changedPart;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

      @FXML
    void handleCancelAdd(ActionEvent event) throws IOException {
        Stage stage; 
        Parent root;       
        stage=(Stage) cancelAdd.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader=new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        root =loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    void handleInhouseAdd(ActionEvent event) {
        labelAdd.setText("Machine ID");
        partSourceField.setPromptText("Mach ID");
        
    }

    @FXML
    void handleOutsourcedAdd(ActionEvent event) {
        labelAdd.setText("Company Name");
        partSourceField.setPromptText("Company Name");
        
    }

    @FXML
    void handleSaveAdd(ActionEvent event) throws IOException {
        System.out.println("save button clicked");
        if(isPartValid(
            this.partIDField.getText(),
            this.partNameField.getText(),
            this.partInStockField.getText(),
            this.partPriceField.getText(),
            this.partMaxField.getText(),
            this.partMinField.getText(),
            this.partSourceField.getText()
        )){
           if (this.partSource.getSelectedToggle().equals(this.inhouseAdd)){
                Inhouse tomato = new Inhouse();
                tomato.setMachineID(Integer.parseInt(this.partSourceField.getText()));
                tomato.setPartName(this.partNameField.getText());
                tomato.setPartInStock(Integer.parseInt(this.partInStockField.getText()));
                tomato.setPartMin(Integer.parseInt(this.partMinField.getText()));
                tomato.setPartMax(Integer.parseInt(this.partMaxField.getText()));
                tomato.setPartPrice(Double.parseDouble(this.partPriceField.getText()));
                
                if(this.partIDField.getText().length() == 0) {
                    tomato.setPartID(Inventory.getPartIDCount());
                    Inventory.addPart(tomato);
                } else {
                    tomato.setPartID(Integer.parseInt(this.partIDField.getText()));
                    Inventory.updatePart(selectedIndex(), tomato);
                }
            } else {
                Outsourced cucumber = new Outsourced();
                cucumber.setCompanyName(this.partSourceField.getText());
                cucumber.setPartName(this.partNameField.getText());
                cucumber.setPartInStock(Integer.parseInt(this.partInStockField.getText()));
                cucumber.setPartMin(Integer.parseInt(this.partMinField.getText()));
                cucumber.setPartMax(Integer.parseInt(this.partMaxField.getText()));
                cucumber.setPartPrice(Double.parseDouble(this.partPriceField.getText()));
                
                if(this.partIDField.getText().length() == 0) {
                    cucumber.setPartID(Inventory.getPartIDCount());
                    Inventory.addPart(cucumber);
                } else {
                    cucumber.setPartID(Integer.parseInt(this.partIDField.getText()));
                    Inventory.updatePart(selectedIndex(), cucumber); 
                }
           }
        }
           
        
            System.out.println("Modification saved.");
        
        //Back to Main Screen
        Stage stage; 
        Parent root;       
        stage=(Stage) saveAdd.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader=new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        root =loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     *
     * @param partID
     * @param partName
     * @param partInStock
     * @param partPrice
     * @param partMax
     * @param partMin
     * @param partSource
     * @return
     */
    public boolean isPartValid(String partID, String partName, String partInStock, String partPrice, String partMax, String partMin, String partSource) {
        String errorMessage = "";
        Integer intMin = null, intMax = null;
        
        boolean valid;
        
        if(partName == null || partName.length() == 0) {
            errorMessage += ("Part Name is blank\n");
        }
        
        try {
            intMin = Integer.parseInt(partMin);
        } catch (NumberFormatException e) {
            errorMessage += ("Minimum must be a number\n");
        }
        
        try {
            intMax = Integer.parseInt(partMax);
        } catch (NumberFormatException e) {
            errorMessage += ("Maximum must be a number\n");
        }
        
        if(intMin != null && intMin < 0) {
            errorMessage += ("Minimum cannot be negative\n");
        }
        
        if(intMin != null && intMax != null && intMin > intMax) {
            errorMessage += ("Minimum must be less than maximum\n");
        }
        
        try {
            int intInv = Integer.parseInt(partInStock);
            
            if(intMin != null && intMax != null && intInv < intMin && intInv > intMax) {
               errorMessage += ("Inventory must be between minimum and maximum\n"); 
            }
        } catch (NumberFormatException e) {
            errorMessage += ("Inventory must be a number\n");
        }
        
        try {
            double dPrice = Double.parseDouble(partPrice);
            
            if(dPrice <= 0) {
               errorMessage += ("Price must be greater than 0\n"); 
            }
        } catch (NumberFormatException e) {
            errorMessage += ("Price must be a number\n");
        }
        
        try {
            if (this.partSource.getSelectedToggle().equals(this.inhouseAdd)){
                //intMachineId
                try {
                    Integer.parseInt(partSource);
                } catch (NumberFormatException e) {
                    errorMessage += ("Machine ID must be a number\n");
                }
            } else if (this.partSource.getSelectedToggle().equals(this.outsourcedAdd)) {
                if(partSource == null || partSource.length() == 0) {
                    errorMessage += ("Company Name is blank\n");
                }
            }    
        } catch(Exception e) {
           errorMessage += ("Part type of In-House or Outsourced must be selected\n"); 
        }
        
        if (errorMessage.length() > 0) {
            errorMessage += ("\nFix errors and save again");
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Part Validation Error");
            alert.setHeaderText("Error");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            valid = false;
        } else {
            valid = true;
        }
        
        return valid;
    }
    
    
}
