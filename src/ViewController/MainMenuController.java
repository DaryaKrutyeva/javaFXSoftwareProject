/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;


import Model.Inventory;
import static Model.Inventory.getAllParts;
import static Model.Inventory.getAllProducts;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Darya
 */

public class MainMenuController implements Initializable {

    Stage stage;
    Parent scene;
                    
    @FXML
    private TextField partsSearchBarTxt;

    @FXML
    private TableView<Part> table;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInventoryCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private Button Delete;

    @FXML
    private TextField productSearchBarTxt;

    @FXML
    private TableView<Product> table1;

    @FXML
    private TableColumn<Product, Integer> productIDCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Integer> productInventoryCol;

    @FXML
    private TableColumn<Product, Double> productCostCol;
        
    @FXML
    private Button exitButton;

    @FXML
    void onActionCloseProgram(ActionEvent event) {
        Stage stage = (Stage)exitButton.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    void onActionDeletePart(ActionEvent event) {
    Part selectedItem = table.getSelectionModel().getSelectedItem();
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning");
    alert.setHeaderText("Delete");
    alert.setContentText("Are you sure you want to PERMANENTLY delete this part?");
    Optional<ButtonType> result = alert.showAndWait();
    if(result.get() == ButtonType.OK){
     table.getItems().remove(selectedItem);
     updateTable();
    }
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {
    Product selectedItem = table1.getSelectionModel().getSelectedItem();
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning");
    alert.setHeaderText("Delete");
    alert.setContentText("Are you sure you want to PERMANENTLY delete this product?");
    Optional<ButtonType> result = alert.showAndWait();
    if(result.get() == ButtonType.OK){
    table1.getItems().remove(selectedItem); 
    updateTable1();}
    
    }
  public static int modifyProductsIndex(){
   return modifyProductsIndex;
        }
 
  private static Product modifyProduct;
    private static int modifyProductsIndex;
    
    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {
modifyProduct = table1.getSelectionModel().getSelectedItem();
modifyProductsIndex = getAllProducts().indexOf(modifyProduct);
Parent modifyProduct = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
Scene scene = new Scene(modifyProduct);
Stage window = (Stage)((Button) event.getSource()).getScene().getWindow();
window.setScene(scene);
window.show();
    }
        
    @FXML
    void onActionOpenAddPart(ActionEvent event) throws IOException {
stage = (Stage)((Button)event.getSource()).getScene().getWindow();
scene = FXMLLoader.load(getClass().getResource("AddParts.fxml"));
 stage.setScene(new Scene(scene));
 stage.show();
    }

    @FXML
    void onActionOpenAddProduct(ActionEvent event) throws IOException {
stage = (Stage)((Button)event.getSource()).getScene().getWindow();
scene = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
 stage.setScene(new Scene(scene));
 stage.show();
    }
    
    private static Part modifyPart;
    private static int modifyPartsIndex;
    public static int modifyPartsIndex(){
        return modifyPartsIndex;
    }

    @FXML
    void onActionOpenModifyPart(ActionEvent event) throws IOException {
modifyPart = table.getSelectionModel().getSelectedItem();
modifyPartsIndex = getAllParts().indexOf(modifyPart);
Parent modifyPart = FXMLLoader.load(getClass().getResource("ModifyParts.fxml"));
Scene scene = new Scene(modifyPart);
Stage window = (Stage)((Button) event.getSource()).getScene().getWindow();
window.setScene(scene);
window.show();
}

    @FXML
    private void onActionSearchParts(ActionEvent event) {
        String searchParts = partsSearchBarTxt.getText();
        int partIndex = -1;
            partIndex = Inventory.lookupPart(searchParts);
            Part searchedPart = Inventory.getAllParts().get(partIndex);
            ObservableList<Part> searchedParts = FXCollections.observableArrayList();
            searchedParts.add(searchedPart);
            table.setItems(searchedParts);
            
        }
      
        
        

    @FXML
    void onActionSearchProducts(ActionEvent event) {
String searchProducts = productSearchBarTxt.getText();
int productIndex = -1;

    productIndex = Inventory.lookUpProduct(searchProducts);
    Product searchedProduct = Inventory.getAllProducts().get(productIndex);
    ObservableList<Product> searchedProducts = FXCollections.observableArrayList();
    searchedProducts.add(searchedProduct);
    table1.setItems(searchedProducts);
}
    
        
            
public Part selectPart(int id)
{
    for(Part part : Inventory.getAllParts())
    {
        if(part.getId()== id)
            return part;
    }
    return null;
}
        
public void updateTable()
{
    table.setItems(getAllParts());
}
public void updateTable1()
{
    table1.setItems(getAllProducts());
}
static boolean entered;    
@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        table.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        table1.setItems(Inventory.getAllProducts());
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
       
        updateTable();
        updateTable1();
        
       
        
        
    }    

   
    
    
}
