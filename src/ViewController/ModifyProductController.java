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
import static ViewController.MainMenuController.modifyProductsIndex;
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
import javafx.scene.control.Label;
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
public class ModifyProductController implements Initializable {

    @FXML
    private TextField productNameTxt;

    @FXML
    private TextField inventoryAmountTxt;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private TextField priceCostTxt;

    @FXML
    private TextField productSearchTxt;

    @FXML
    private TableView<Part> partTable;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> inventoryCol;

    @FXML
    private TableColumn<Part, Double> unitPriceCol;

    @FXML
    private TableView<Part> associatedTable;

    @FXML
    private TableColumn<Part, Integer> associatedIdCol;

    @FXML
    private TableColumn<Part, String> associatedNameCol;

    @FXML
    private TableColumn<Part, Integer> associatedInventoryCol;

    @FXML
    private TableColumn<Part, Double> associatedUnitPriceCol;

    @FXML
    private TextField productIDTxt;

    @FXML
            
    Stage stage;
    Parent scene;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    int productIndex = modifyProductsIndex();
    private int productID;
    /** delete Part from List**/
    @FXML
    void onActionDeleteProductFromList(ActionEvent event) {
    Part selectedItem = associatedTable.getSelectionModel().getSelectedItem();
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirm Deletion");
        alert.setContentText("Are you sure you want to delete this part?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
    associatedTable.getItems().remove(selectedItem); }
        else {
            
        }
    updateassociatedTable();
    }
    /**cancel button**/
    @FXML
    void onActionGoToMainScreen(ActionEvent event) throws IOException {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Cancellation");
    alert.setContentText("Are you sure you want to cancel?");
    Optional<ButtonType> result = alert.showAndWait();
       
       if (result.get() == ButtonType.OK) { 
stage = (Stage)((Button)event.getSource()).getScene().getWindow();
scene = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
 stage.setScene(new Scene(scene));
 stage.show();
    }
    }

    @FXML
    void onActionSaveModifiedProduct(ActionEvent event) throws IOException {
int id = Integer.parseInt(productIDTxt.getText());
      String name = productNameTxt.getText();    
      int stock = Integer.parseInt(inventoryAmountTxt.getText());   
      int max = Integer.parseInt(maxTxt.getText()); 
      int min = Integer.parseInt(minTxt.getText()); 
      Double price = Double.parseDouble(priceCostTxt.getText()); 
      
      ObservableList<Part> searchedParts = associatedTable.getItems();
      if(associatedParts.isEmpty()){
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setContentText("Product must contain a part");
          alert.showAndWait();
      }
      else{
      Product addProduct = new Product(id, name, price, stock, max, min);
      addProduct.setId(productID);
      addProduct.setName(name);
      addProduct.setStock((stock));
      addProduct.setPrice((price));
      addProduct.setMax((max));
      addProduct.setMin((min));
      addProduct.setsavedParts(associatedParts);
      Inventory.updateProduct(productIndex, addProduct);
       stage = (Stage)((Button)event.getSource()).getScene().getWindow();
       scene = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
       stage.setScene(new Scene(scene));
        stage.show();
    }
    }
/** add Part**/
    @FXML
    void onActionSaveProductToList(ActionEvent event) throws IOException {
    Part part = partTable.getSelectionModel().getSelectedItem();
associatedParts.add(part);
updateassociatedTable();
    }

    @FXML
    void onActionSearchProduct(ActionEvent event) {
String searchParts = productSearchTxt.getText();
int partIndex = -1;
    partIndex = Inventory.lookupPart(searchParts);
    Part filteredPart = Inventory.getAllParts().get(partIndex);
    ObservableList<Part> filteredParts = FXCollections.observableArrayList();
    filteredParts.add(filteredPart);
    partTable.setItems(filteredParts);
}
    



    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

  Product product =getAllProducts().get(productIndex);
   productID = getAllProducts().get(productIndex).getId();
   productIDTxt.setText("" + productID);
   productNameTxt.setText(product.getName());
   inventoryAmountTxt.setText(Integer.toString(product.getStock()));
   priceCostTxt.setText(Double.toString(product.getPrice()));
        minTxt.setText(Integer.toString(product.getMin()));
        maxTxt.setText(Integer.toString(product.getMax()));    
   partTable.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        unitPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedParts = product.getsavedParts();
        
        associatedIdCol.setCellValueFactory(cellData ->cellData.getValue().idProperty().asObject());
        associatedNameCol.setCellValueFactory(cellData ->cellData.getValue().nameProperty());
        associatedInventoryCol.setCellValueFactory(cellData ->cellData.getValue().stockProperty().asObject());
        associatedUnitPriceCol.setCellValueFactory(cellData ->cellData.getValue().priceProperty().asObject());
        
         updatepartTable();
        updateassociatedTable();
      
    }    
    public void updatepartTable()
{
    partTable.setItems(getAllParts());
}
    public void updateassociatedTable()
    {
        associatedTable.setItems(associatedParts);
    }
    
}
