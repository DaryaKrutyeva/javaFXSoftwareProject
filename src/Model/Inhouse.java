/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Darya
 */
public class Inhouse extends Part {
    
    private IntegerProperty machineId;

    
    
    /**
     *
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param machineID
     */
    public Inhouse() {
        super();
        machineId = new SimpleIntegerProperty();
    }
    
    /**get**/
    public int getMachineId() {
        return this.machineId.get();
    }

    
    
    
    
    /**set**/
    public void setMachineId(Integer machineId) {
        this.machineId.set(machineId);
    }
    
}
