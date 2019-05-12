package inventorysystem_ashleyjohnson.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ashley Johnson C482 Software I
 */
public class Product {

    public static ObservableList<Part> theseParts = FXCollections.observableArrayList();
    private int productID;
    private String productName;
    private double productPrice;
    private int productInStock;
    private int productMin;
    private int productMax;
    
    /**
     * Default constructor.
     */
    public Product(){
       this(0, null, 0, 0.00, 0, 0 ); 
    }
    
    
    /**
     * Constructor with some initial data.
     * 
     * @param productID
     * @param productName
     * @param productInStock
     * @param productPrice
     * @param productMin
     * @param productMax
     */
    public Product(int productID, String productName, int productInStock, 
            double productPrice, int  productMin, int productMax ) {
        this.productID = productID;
        this.productName = productName;
        this.productInStock = productInStock;
        this.productPrice = productPrice;
        
        //Initial product min and max for testing purposes.
        this.productMin = productMin;
        this.productMax = productMax;
    }

    /**
     * @return the associatedParts
     */
    public ObservableList<Part> getTheseParts() {
        return theseParts;
    }

    /**
     * @param assocParts
     */
    public void setTheseParts(ObservableList<Part> assocParts) {
        theseParts = assocParts;
    }

    /**
     * @return the productID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * @param productID the productID to set
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the productPrice
     */
    public double getProductPrice() {
        return productPrice;
    }

    /**
     * @param productPrice the productPrice to set
     */
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * @return the productInStock
     */
    public int getProductInStock() {
        return productInStock;
    }

    /**
     * @param productInStock the productInStock to set
     */
    public void setProductInStock(int productInStock) {
        this.productInStock = productInStock;
    }

    /**
     * @return the productMin
     */
    public int getProductMin() {
        return productMin;
    }

    /**
     * @param productMin the productMin to set
     */
    public void setProductMin(int productMin) {
        this.productMin = productMin;
    }

    /**
     * @return the productMax
     */
    public int getProductMax() {
        return productMax;
    }

    /**
     * @param productMax the productMax to set
     */
    public void setProductMax(int productMax) {
        this.productMax = productMax;
    }
}
