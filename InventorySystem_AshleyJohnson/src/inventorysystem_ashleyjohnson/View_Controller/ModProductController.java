package inventorysystem_ashleyjohnson.View_Controller;

import inventorysystem_ashleyjohnson.Model.Inventory;
import inventorysystem_ashleyjohnson.Model.Part;
import inventorysystem_ashleyjohnson.Model.Product;
import static inventorysystem_ashleyjohnson.Model.Product.theseParts;
import static inventorysystem_ashleyjohnson.View_Controller.MainScreenController.selectedIndex;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ashley Johnson C482 Software I
 */
public class ModProductController implements Initializable {
    
    @FXML
    private TextField iDField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField inStockField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField maxField;

    @FXML
    private TextField minField;
    
    @FXML
    private TableView<Part> partTable;

    @FXML
    private TableColumn<Part, Integer> pIDColumn;

    @FXML
    private TableColumn<Part, String> pNameColumn;

    @FXML
    private TableColumn<Part, Integer> pInStockColumn;

    @FXML
    private TableColumn<Part, Double> pPriceColumn;
    
    @FXML
    private TableView<Part> assocPartTable;

    @FXML
    private TableColumn<Part, Integer> apIDColumn;

    @FXML
    private TableColumn<Part, String> apNameColumn;

    @FXML
    private TableColumn<Part, Integer> apInStockColumn;

    @FXML
    private TableColumn<Part, Double> apPriceColumn;

    @FXML
    private TextField search;
    
    @FXML
    private Button cancelBtn;
    
    @FXML
    private Button saveBtn;
    
    private Product selectedProduct;
    
    private Product product;
    
    private Product changedProduct;
    
    
    
    @FXML
    void handleAddBtn(ActionEvent event) {
        System.out.println("Add button clicked");
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();
        
        if(selectedPart != null) {
            theseParts.add(selectedPart);
            updateAssocPartTable();    
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Select a Part");
            alert.setHeaderText("Please select a part to add to the product"); 
            alert.showAndWait();
        }
    }

    @FXML
    void handleCancelBtn(ActionEvent event) throws IOException {
        Stage stage; 
        Parent root;       
        stage=(Stage) cancelBtn.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader=new FXMLLoader(getClass().getResource(
               "MainScreen.fxml"));
        root =loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void handleDelBtn(ActionEvent event) {
        Part selectedPart = assocPartTable.getSelectionModel().getSelectedItem();
        
        if(selectedPart != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initModality(Modality.NONE);
            alert.setTitle("Delete Part");
            alert.setHeaderText("Confirm removal of part");
            alert.setContentText("Are you sure you want to delete '" + selectedPart.getPartName()+ "' from the Product?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                theseParts.remove(selectedPart);
                updateAssocPartTable();
            }    
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No Part selected");
            alert.setHeaderText("Please select a part to delete from this product"); 
            alert.showAndWait();
        }
    }
        
    @FXML
    void handleSearch(ActionEvent event){
        String term = search.getText();
        ObservableList foundParts = Inventory.searchPart(term);
        if(foundParts.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No match found");
            alert.setHeaderText("No Parts found matching '"+term+"'"); 
            alert.showAndWait();
        } else {
            partTable.setItems(foundParts);
        }
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Columns and Table for un-associated parts.
        pIDColumn.setCellValueFactory(new PropertyValueFactory<> ("partID"));
        pNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        pInStockColumn.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        pPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        partTable.setItems(Inventory.allParts);
        //Columns and Table for associated parts
        apIDColumn.setCellValueFactory(new PropertyValueFactory<> ("partID"));
        apNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        apInStockColumn.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        apPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        assocPartTable.setItems(Product.theseParts);
        
        updatePartTable();
        updateAssocPartTable();
    }

    public Boolean isProductValid(String name, String min, String max, String inv, String price) {
        String errorMessage = "";
        Integer intMin = null, intMax = null;
        Double dPrice = null;
        Boolean valid;
        
        if(name == null || name.length() == 0) {
            errorMessage += ("Provide a Product Name\n");
        }
        
        try {
            intMin = Integer.parseInt(min);
        } catch (NumberFormatException e) {
            errorMessage += ("Minimum must be a number\n");
        }
        
        try {
            intMax = Integer.parseInt(max);
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
            int intInv = Integer.parseInt(inv);
            
            if(intMin != null && intMax != null && intInv < intMin && intInv > intMax) {
               errorMessage += ("Inventory must be between minimum and maximum\n"); 
            }
        } catch (NumberFormatException e) {
            errorMessage += ("Inventory must be a number\n");
        }
        
        try {
            dPrice = Double.parseDouble(price);
            
            if(dPrice <= 0) {
               errorMessage += ("Price must be greater than 0\n"); 
            }
        } catch (NumberFormatException e) {
            errorMessage += ("Price must be a number\n");
        }
        
        if(theseParts.isEmpty()) {
            errorMessage += ("Product must have at least one part\n");
        } else {
            //loop through all the products and add up the cost
            //price of product cannot be less than the cost of the parts
            Double partCost = 0.0;
            for (int i = 0; i < theseParts.size(); i++) {
                partCost += theseParts.get(i).getPartPrice();
            }
            if(dPrice != null && partCost > dPrice) {
                errorMessage += ("Product price ("+dPrice+") must be greater than the combined cost of the parts ("+partCost+")\n");
            }
        }
        
        if (errorMessage.length() > 0) {
            errorMessage += ("\nTry again");
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Product Validation Error");
            alert.setHeaderText("Error");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            valid = false;
        } else {
            valid = true;
        }
        
        return valid;
    }
    
    @FXML
    void handleSaveBtn(ActionEvent event) throws IOException {
        if(isProductValid(
                this.nameField.getText(),
                this.minField.getText(),
                this.maxField.getText(),
                this.inStockField.getText(),
                this.priceField.getText()
        )) {
            this.changedProduct = product;
            product.setProductName(nameField.getText());
            product.setProductInStock(Integer.parseInt(inStockField.getText()));
            product.setProductPrice(Double.parseDouble(priceField.getText()));
            product.setProductMax(Integer.parseInt(maxField.getText()));
            product.setProductMin(Integer.parseInt(minField.getText())) ;
            
            product.setTheseParts(theseParts);
            
            if(this.iDField.getText().length() == 0) {
                product.setProductID(Inventory.getProductIDCount());
                Inventory.addProducts(product);
            } else {
                product.setProductID(Integer.parseInt(this.iDField.getText()));
                Inventory.updateProducts(selectedIndex(), product);
            }
            
            //Back to Main Screen
            Stage stage;
            Parent root;
            stage=(Stage) saveBtn.getScene().getWindow();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("MainScreen.fxml"));
            root =loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
        }
    }
    
    void setProduct(Product product) {
        this.selectedProduct = product;
        
        iDField.setText(Integer.toString(product.getProductID()));
        nameField.setText(product.getProductName());
        inStockField.setText(Integer.toString(product.getProductInStock()));
        priceField.setText(Double.toString(product.getProductPrice()));
        minField.setText(Integer.toString(product.getProductMin()));
        maxField.setText(Integer.toString(product.getProductMax()));
        
        theseParts = product.getTheseParts();
        updateAssocPartTable();
    }
    
    public void updatePartTable() {
        partTable.setItems(Inventory.getAllParts());
    }

    private void updateAssocPartTable() {
        assocPartTable.setItems(theseParts);
    }
    
    
    
}
