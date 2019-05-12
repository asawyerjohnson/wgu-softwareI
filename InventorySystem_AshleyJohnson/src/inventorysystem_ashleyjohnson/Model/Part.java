package inventorysystem_ashleyjohnson.Model;

/**
 * Model Class for a Part
 * 
 * @author Ashley Johnson C482 Software I
 */
public abstract class Part {
        
    int partID;
    String partName;
    double partPrice;
    int partInStock;
    int partMin;
    int partMax;
    
    public Part(){
        this(0, null, 0, 0.0, 0, 0);
    }   
    /**
     * Constructor with some initial data.
     * 
     * @param partID
     * @param partName
     * @param partInStock
     * @param partPrice
     * @param partMin
     * @param partMax
     */
    public Part(int partID, String partName, int partInStock, double partPrice, int partMin, int partMax){
        this.partID = partID;
        this.partName = partName;
        this.partInStock = partInStock;
        this.partPrice = partPrice;
        
        //Initial product min and max for testing purposes.
        this.partMin = partMin;
        this.partMax = partMax;
    }
    
    /**
     * @param partID the partID to set
     */
    public void setPartID(int partID) {
        this.partID = partID;
    }
    
    public int getPartID(){
        return partID;
    }
    
    /**
     * Mutators and accessors for:
     * @param partName 
     */
    public void setPartName(String partName) {
        this.partName = partName;
    }
    
    public String getPartName() {
        return partName;
    }
    
    /**
     * Mutators and accessors for:
     * @param partPrice 
     */
    public void setPartPrice(double partPrice) {
        this.partPrice = partPrice;
    }
    
    public double getPartPrice() {
        return partPrice;
    }
    
    /**
     * Mutators and accessors for:
     * @param partInStock 
     */
    public void setPartInStock(int partInStock) {
        this.partInStock = partInStock;
    }
    
    public int getPartInStock() {
        return partInStock;
    }
    
    /**
     * Mutators and accessors for:
     * @param partMin 
     */
    public void setPartMin(int partMin) {
        this.partMin = partMin;
    }
    
    public int getPartMin() {
        return partMin;
    }
    
    /**
     * Mutators and accessors for:
     * @param partMax 
     */
    public void setPartMax(int partMax) {
        this.partMax = partMax;
    }
    
    public int getPartMax() {
        return partMax;
    }
       
}
