/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem_ashleyjohnson.View_Controller;



import inventorysystem_ashleyjohnson.Model.*;
import static inventorysystem_ashleyjohnson.Model.Inventory.getAllParts;
import static inventorysystem_ashleyjohnson.Model.Inventory.getPartIDCount;
import static inventorysystem_ashleyjohnson.Model.Inventory.getProductIDCount;
import static inventorysystem_ashleyjohnson.Model.Inventory.getProducts;
import java.io.IOException;
import java.net.URL;
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
public class MainScreenController implements Initializable {
        
    @FXML
    private Button addPart;
    
    @FXML
    private Button modPart;
    
    @FXML
    private Button addProduct;
    
    @FXML
    private Button modProduct;
    
    @FXML
    private TextField searchPart;
    
    @FXML
    private TextField searchProduct;
    
    @FXML
    private TableView<Part> mainPartTable;
    
    @FXML
    private TableColumn<Part, Integer> partIDColumn;
    
    @FXML
    private TableColumn<Part, String> partNameColumn;
    
    @FXML
    private TableColumn<Part, Integer> partInStockColumn;
    
    @FXML
    private TableColumn<Part, Double> partPriceColumn;
    
    @FXML
    private TableView<Product> productTable;
    
    @FXML
    private TableColumn<Product, Integer> productIDColumn;
    
    @FXML
    private TableColumn<Product, String> productNameColumn;
    
    @FXML
    private TableColumn<Product, Integer> productInStockColumn;
    
    @FXML
    private TableColumn<Product, Double> productPriceColumn;
    
    static boolean entered;
    Inventory allParts;
    
    
    public static int selectedIndex() {
        int index = -1;
        return index;
    }
    
    /**
     * The constructor.
     */
    public MainScreenController(){
    }
    
    public void updatePartTableView() {
        mainPartTable.setItems(getAllParts());
    }

    public void updateProductTableView() {
        ObservableList<Product> productItems = getProducts();
        productTable.setItems(productItems);
    }
    
    @FXML
    void handleExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleAddPart(ActionEvent event) throws IOException {
        //"Add" button clicked on part side.
        Stage stage; 
        Parent root;       
        stage=(Stage) addPart.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPart.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Add part screen opened.");
    }
    
    @FXML
    void handleModPart(ActionEvent event) throws IOException {
        //"Modify" button clicked on part side.
        Stage stage; 
        Parent root;       
        stage = (Stage) modPart.getScene().getWindow();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ModPart.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ModPartController controller = loader.getController();
        Part selectedPart = mainPartTable.getSelectionModel().getSelectedItem();
        controller.setPart(selectedPart);
        
        //throw exception if no item is selected             
   
    }
    
    @FXML
    void handleDelPart(ActionEvent event) {
        int selectedIndex = mainPartTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
            mainPartTable.getItems().remove(selectedIndex);
            } 
    }
    
    @FXML
    void handleSearchPart(ActionEvent event) {
        String term = searchPart.getText();
        ObservableList foundParts = Inventory.searchPart(term);
        if(foundParts.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No match found");
            alert.setHeaderText("No Part Names found matching " + term); 
            alert.showAndWait();
        } else {
            mainPartTable.setItems(foundParts);
        }
    }
    
    @FXML
    void handleAddProduct(ActionEvent event) throws IOException {
        System.out.println("Add product screen opened.");
        //"Add" button clicked on product side.
        Stage stage; 
        Parent root;       
        stage=(Stage) addProduct.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

   @FXML
    void handleModProduct(ActionEvent event) throws IOException {
        System.out.println("Mod Product Button Clicked");
        //"Modify" button clicked on product side.
        Stage stage; 
        Parent root;       
        stage=(Stage) modProduct.getScene().getWindow();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ModProduct.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        ModProductController controller = loader.getController();
        Product product = productTable.getSelectionModel().getSelectedItem();
        controller.setProduct(product);
    }

    @FXML
    void handleDelProduct(ActionEvent event) {
        int selectedIndex = productTable.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
            productTable.getItems().remove(selectedIndex);
            }
    }
    
    @FXML
    void handleSearchProduct(ActionEvent event) {
        String term = searchProduct.getText();
        ObservableList foundProducts = Inventory.searchProducts(term);
        if(foundProducts.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No match found");
            alert.setHeaderText("No Product Names found matching " + term); 
            alert.showAndWait();
        } else {
            productTable.setItems(foundProducts);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Initialize some sample parts and products
        if (!entered){
               
            Inventory.addPart(new Inhouse(getPartIDCount(), "Tooth Paste", 500, 0.50, 1, 1000, 1234));
            Inventory.addPart(new Inhouse(getPartIDCount(), "Mouth Wash", 400, 1.50, 1, 1000, 5678));
            Inventory.addPart(new Outsourced(getPartIDCount(), "Tooth Brush", 500, 0.25, 1, 1000, "Company A"));
            Inventory.addPart(new Outsourced(getPartIDCount(), "Dental Floss", 400, 0.25, 1, 1000, "Company B"));
            
            Inventory.addProducts(new Product(getProductIDCount(), "Basic Cleaning", 500, 1.00, 1, 1000));
            Inventory.addProducts(new Product(getProductIDCount(), "Advanced Cleaning", 400, 0.5, 1, 1000));
    
            entered = true;
        }
        //Table and columns for "Parts"
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        partInStockColumn.setCellValueFactory(new PropertyValueFactory<>("partInStock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        mainPartTable.setItems(Inventory.allParts);
        
        //Table and columns for "Products"
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productInStockColumn.setCellValueFactory(new PropertyValueFactory<>("productInStock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        productTable.setItems(Inventory.products);
        
    }
}
