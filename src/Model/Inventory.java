/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Darya
 */
public class Inventory {
    
private static ObservableList<Part>allParts=FXCollections.observableArrayList();

public static void addPart(Part part)
{
    allParts.add(part);
}
public static ObservableList<Part> getAllParts()
{
   
  return allParts; 
}

public static void deletePart (Part part)
{
    allParts.remove(part);
}
private static int partIdNumb = 1;
public static int getPartIdNumb()
{
    partIdNumb++;
    return partIdNumb;
}



public static int lookupPart (String partSearch)
{ boolean isFound = false;
int index = 0;
    if(isInteger(partSearch))
    {
        for (int i = 0; i < allParts.size(); i++)
        {
            if(Integer.parseInt(partSearch) ==allParts.get(i).getId()) {
                index = i;
                isFound = true;
                }
        }
    } else {
        for (int i = 0; i<allParts.size(); i++){
            if(partSearch.equals(allParts.get(i).getName())) {
                index = i;
                isFound = true;
            }
        }
    }
    if (isFound = true) {
        return index;
    } else {
        return -1;
    }
}

public static void updatePart (int index, Part parts)
{
    
    allParts.set(index, parts);
}


private static ObservableList<Product>allProducts=FXCollections.observableArrayList();


public static void addProduct (Product product)
{allProducts.add(product);}


public static ObservableList<Product> getAllProducts() {
        return allProducts;
}

   public static void deleteProduct (Product product)
{
    allProducts.remove(product);
}
    
private static int productIdNumb = 1;
public static int getproductIdNumb()
{
    productIdNumb++;
    return productIdNumb;
}
public static boolean isInteger(String input){
    try {Integer.parseInt(input);
    return true;} catch (Exception e) {
    return false;}
    

}


public static void updateProduct (int index, Product product)
{
    allProducts.set(index, product);
     
}
public static int lookUpProduct(String productSearch)
{
    boolean isFound = false;
    int index = 0;
    if(isInteger(productSearch))
    {
        for (int i = 0; i < allProducts.size(); i++)
        {
            if(Integer.parseInt(productSearch) ==allProducts.get(i).getId()) {
                index = i;
                isFound = true;
                }
        }
    } else {
        for (int i = 0; i<allProducts.size(); i++){
            if(productSearch.equals(allProducts.get(i).getName())) {
                index = i;
                isFound = true;
            }
        }
    }
    if (isFound = true) {
        return index;
    } else {
        return -1;
    }

}

}





