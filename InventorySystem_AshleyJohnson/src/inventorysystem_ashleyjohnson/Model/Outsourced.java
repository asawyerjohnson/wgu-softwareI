package inventorysystem_ashleyjohnson.Model;

/**
 *
 * @author Ashley Johnson C482 Software I
 */
public class Outsourced extends Part {
    
    private String companyName;
        
    public Outsourced() {
        this(0, null, 0, 0.0, 0, 0, null);
    }
    
    /**
     * Constructor with some initial data.
     * 
     * @param partID
     * @param partName
     * @param partInStock
     * @param partPrice
     * @param partMax
     * @param partMin
     * @param companyName
     */
    public Outsourced(int partID, String partName, int partInStock, double partPrice, int partMax, int partMin, String companyName){
        super(partID, partName,  partInStock, partPrice, partMin, partMax);
        this.companyName = companyName;
    }

    /**
     * Mutators and accessors for:
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public String getCompanyName() {
        return companyName;
    }
    
}
