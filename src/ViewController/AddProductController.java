/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import Model.Inventory;

import static Model.Inventory.getAllParts;
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
public class AddProductController implements Initializable {

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
    private TextField searchBarTxt;

    @FXML
    private TableView<Part> addPartTableView;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInventoryCol;

    @FXML
    private TableColumn<Part, Double> partUnitPriceCol;

    @FXML
    private TableView<Part> associatedPartTbv;

    @FXML
    private TableColumn<Part, Integer> associatedPartIdCol;

    @FXML
    private TableColumn<Part, String> associatedPartNameCol;

    @FXML
    private TableColumn<Part, Integer> associatedInventoryCol;

    @FXML
    private TableColumn<Part, Double> associatedUnitPriceCol;

    @FXML
    private TextField identityTxt;

    

    
    Stage stage;
    Parent scene;
    
    
   
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    @FXML
            /** actually add part **/
    void onActionAddNewProduct(ActionEvent event) throws IOException {
Part part = addPartTableView.getSelectionModel().getSelectedItem();
associatedParts.add(part);
updateassociatedPartTbv();
     }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        Part part = associatedPartTbv.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirm Deletion");
        alert.setContentText("Are you sure you want to delete this part?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            associatedParts.remove(part);
        } else {
            
        }
    updateassociatedPartTbv();
    }
/** cancel**/
    @FXML
    void onActionDisplayMainMenu(ActionEvent event) throws IOException {
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

    } }

    @FXML
    void onActionSaveNewProduct(ActionEvent event) throws IOException {
      int id = Integer.parseInt(identityTxt.getText());
      String name = productNameTxt.getText();    
      int stock = Integer.parseInt(inventoryAmountTxt.getText());   
      int max = Integer.parseInt(maxTxt.getText()); 
      int min = Integer.parseInt(minTxt.getText()); 
      Double price = Double.parseDouble(priceCostTxt.getText()); 
    
      
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
      Inventory.addProduct(addProduct);
       stage = (Stage)((Button)event.getSource()).getScene().getWindow();
       scene = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
       stage.setScene(new Scene(scene));
        stage.show();
      
      }
  
    }     
    

    @FXML
    void onActionSearchforProduct(ActionEvent event) {
String searchParts = searchBarTxt.getText();
int partIndex = -1;
    partIndex = Inventory.lookupPart(searchParts);
    Part filteredPart = Inventory.getAllParts().get(partIndex);
    ObservableList<Part> filteredParts = FXCollections.observableArrayList();
    filteredParts.add(filteredPart);
    addPartTableView.setItems(filteredParts);
}
    


    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    private int productID;
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
               
        addPartTableView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partUnitPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        associatedPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedUnitPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        updateaddPartTableView();
        updateassociatedPartTbv();
        productID = Inventory.getproductIdNumb();
        identityTxt.setText(""+productID);
        
        
      
      
    }    
    public void updateaddPartTableView()
{
    addPartTableView.setItems(getAllParts());
}
    public void updateassociatedPartTbv()
    {
        associatedPartTbv.setItems(associatedParts);
    }
}
