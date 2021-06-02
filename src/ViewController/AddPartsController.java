/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import Model.Inhouse;
import Model.Inventory;
import Model.Outsourced;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Darya
 */
public class AddPartsController implements Initializable {

      @FXML
    private RadioButton InhouseRbtn;

      @FXML
    private TextField companyNameTxt;

    @FXML
    private RadioButton outsourcedRbtn;

    @FXML
    private TextField partNameTxt;

    @FXML
    private TextField inventoryTxt;

    @FXML
    private TextField maxAmtTxt;

    @FXML
    private TextField machineIdTxt;

    @FXML
    private Label minAmtLbl;

    @FXML
    private TextField minAmtTxt;

    @FXML
    private TextField priceTxt;
  
    @FXML
    private Label machineIdLbl;
    @FXML
    private TextField partIDTxt;

    Stage stage;
    Parent scene;
    private boolean isInhouse;
    
    /**Cancel Button**/
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

    }
    }
    /** inhouse button choice**/
    
    @FXML
    void onActionInhousePartChoice(ActionEvent event) {
          isInhouse = true;
        machineIdLbl.setText("Machine ID");
        machineIdTxt.setPromptText("Machine ID");
        outsourcedRbtn.setSelected(false);
       
    }
    /** outsourced button choice**/
    @FXML
    void onActionGoToOutsourcedMenu(ActionEvent event) throws IOException {
        isInhouse = false;
        machineIdLbl.setText("Company Name");
        machineIdTxt.setPromptText("Company Name");
        outsourcedRbtn.setSelected(true);
        
    }

    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {
       
    
   
     String name = partNameTxt.getText();
     String price = priceTxt.getText();
     String stock = inventoryTxt.getText();
     String min = minAmtTxt.getText();
      String max = maxAmtTxt.getText();
      String companyName = machineIdTxt.getText();
      String machineId = machineIdTxt.getText();
     
     
     if(isInhouse ==true){
      Inhouse inPart = new Inhouse();
      inPart.setId(id);
      inPart.setName(name);
      inPart.setStock(Integer.parseInt(stock));
      inPart.setPrice(Double.parseDouble(price));
      inPart.setMax(Integer.parseInt(max));
      inPart.setMin(Integer.parseInt(min));
      inPart.setMachineId(Integer.parseInt(machineId));
      Inventory.addPart(inPart);
           } else {
        Outsourced outPart = new Outsourced();
      outPart.setId(id);
      outPart.setName(name);
      outPart.setStock(Integer.parseInt(stock));
      outPart.setPrice(Double.parseDouble(price));
      outPart.setMax(Integer.parseInt(max));
      outPart.setMin(Integer.parseInt(min));
      outPart.setCompanyName(companyName);
      Inventory.addPart(outPart);
         }
       
                   
     
 
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
       scene = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
       stage.setScene(new Scene(scene));
        stage.show();
            
    }

     
    
    
    


    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    private int id;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id = Inventory.getPartIdNumb();
        partIDTxt.setText("" + id);
    }    
    
}
