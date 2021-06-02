/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.HashSet;
import java.util.Set;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Darya
 */
//abstract class

public abstract class Part {
    //Declare fields
    private IntegerProperty id;
    private StringProperty name;
    private DoubleProperty price;
    private IntegerProperty stock;
    private IntegerProperty min;
    private IntegerProperty max;

   
    
    //
    //Declare constructor
public Part() {
        id = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        price = new SimpleDoubleProperty();
        stock = new SimpleIntegerProperty();
        min = new SimpleIntegerProperty();
        max = new SimpleIntegerProperty();
    }

     public IntegerProperty idProperty()
     {
         return id;
     }
     public int getId() {
        return this.id.get();
    }

    public void setId(int id) {
        this.id.set(id);
        
    }
    public StringProperty nameProperty ()
    {
        return name;
    }
    public String getName() {
        return this.name.get();
    }

    public void setName(String name) {
        this.name.set(name); 
    }
    public DoubleProperty priceProperty ()
    {return price;
        
    }
    public Double getPrice() {
        return this.price.get();
    }

    public void setPrice(Double price) {
        this.price.set(price);
    }
    public IntegerProperty stockProperty()
    {
        return stock;
    }
    public int getStock() {
        return this.stock.get();
    }

    public void setStock(Integer stock) {
        this.stock.set(stock);
    }
    
    public int getMin() {
        return this.min.get();
    }

    public void setMin(Integer min) {
        this.min.set(min);
    }
    public int getMax() {
        return this.max.get();
    }

    public void setMax(Integer max) {
        this.max.set(max);
    }
    
    
            
    
    

   
}
