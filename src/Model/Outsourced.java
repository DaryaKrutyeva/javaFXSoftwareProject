/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Darya
 */
public class Outsourced extends Part {
    //Declare fields
    private StringProperty companyName;

    //Declare constructor
    public Outsourced() {
    super();
    
    companyName = new SimpleStringProperty();
    }
    

    //Declare methods

    public String getCompanyName() {
        return this.companyName.get();
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }

  
    
}
