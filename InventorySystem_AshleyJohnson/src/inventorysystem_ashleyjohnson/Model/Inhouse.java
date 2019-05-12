package inventorysystem_ashleyjohnson.Model;

/**
 *
 * @author Ashley Johnson C482 Software I
 */
public class Inhouse extends Part {
    
    private int machineID;
    
    public Inhouse() {
        this(0, null, 0, 0.0, 0, 0, 0);
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
     * @param machineID
     */
    public Inhouse(int partID, String partName, int partInStock, double partPrice, int partMin, int partMax, int machineID){
        
        super(partID, partName,  partInStock, partPrice, partMin, partMax);
        this.machineID = machineID ;
    }

    /**
     * Mutators and accessors for:
     * @param machineID
     */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
    
    public int getMachineID(){
        return machineID;
    }
    
}
