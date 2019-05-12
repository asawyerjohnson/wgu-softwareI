package inventorysystem_ashleyjohnson.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ashley Johnson C482 Software I
 */
public class Inventory {
    
     /**
     * The data for Superclass Part displayed as an observable list.
     */
    public static ObservableList<Part> allParts = FXCollections.observableArrayList();
    public static ObservableList<Product> products = FXCollections.observableArrayList();
    private static int partIDCount = 0;
    private static int productIDCount = 0;       
    /**
     * 
     * @param product
     */
    public static void addProducts(Product product){
        products.add(product);
    }
    
    /**
     * Returns the data as an observable list of products.
     * @return 
     */
    public static ObservableList<Product> getProducts(){
        return products;
    }
    
    /**
     * 
     * @param product
     * @return 
     */
    public static boolean removeProducts(Product product){
        products.remove(product);
        return true;
    }
            
    /**
     * 
     * @param searchProductName
     * @return 
     */
    public static ObservableList searchProducts(String searchProductName){
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();
        
        if(searchProductName.length() == 0) {
            foundProducts = products;//return all if blank as passed in as search term
        } else {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getProductName().toLowerCase().contains(searchProductName.toLowerCase())) {
                    foundProducts.add(products.get(i));
                }
            }    
        }
        
        return foundProducts;
    }
    
    /**
     * 
     * @param index
     * @param product
     */
    public static void updateProducts(int index, Product product){
        products.set(index, product);
    }
    
    public static int getProductIDCount() {
        productIDCount += 1;
        return productIDCount;
    }
   
    public static int cancelProductIDCount() {
        productIDCount -= 1;
        return productIDCount;
    }
    
    /**
     * 
     * @param part
     */
    public static void addPart(Part part){
        allParts.add(part);
    }
    
    /**
     * Returns the data as an observable list of parts.
     * @return
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    } 
    
    /**
     * 
     * @param part
     * @return 
     */
    public static boolean removePart(Part part){
        allParts.remove(part);
        return true;
    }
    
    /**
     * 
     * @param searchPartName
     * @return 
     */
    public static ObservableList searchPart(String searchPartName){
        ObservableList<Part> foundParts = FXCollections.observableArrayList();
        
        if(searchPartName.length() == 0) {
            foundParts = allParts;//return all if blank as passed in as search term
        } else {
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i).getPartName().toLowerCase().contains(searchPartName.toLowerCase())) {
                    foundParts.add(allParts.get(i));
                }
            }    
        }
        
        return foundParts;
    }
    
    /**
     * 
     * @param index
     * @param part
     */
    public static void updatePart(int index, Part part){
        allParts.set(index, part);
    }
    
    public static int getPartIDCount() {
        partIDCount += 1;
        return partIDCount;
    }
    
    public static int cancelPartIDCount() {
        partIDCount -= 1;
        return partIDCount;
    }
}
