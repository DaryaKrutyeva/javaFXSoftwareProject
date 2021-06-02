/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewController;

import Model.Inhouse;
import Model.Inventory;
import static Model.Inventory.getAllParts;
import Model.Outsourced;
import Model.Part;
import static ViewController.MainMenuController.modifyPartsIndex;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Darya
 */
public class ModifyParts implements Initializable {
    
    @FXML
    private RadioButton inhouseRbtn;

    @FXML
    private ToggleGroup modifyPartTypeTG;

    @FXML
    private RadioButton outsourcedRbtn;

    @FXML
    private TextField partNameTxt;

    @FXML
    private Label partNameLbl;

    @FXML
    private TextField inventoryTxt;

    @FXML
    private Label inventoryLbl;

    @FXML
    private TextField maxAmtTxt;

    @FXML
    private Label priceLbl;

    @FXML
    private Label maxAmtLbl;

    @FXML
    private TextField machineIdTxt;

    @FXML
    private Label machineIdLbl;

    @FXML
    private Label minAmtLbl;

    @FXML
    private TextField minAmtTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField modifyIDTxt;


    
     
    Stage stage;
    Parent scene;
   private boolean isInhouse;
   int partIndex = modifyPartsIndex();
   private int ID;
    
   /**cancel**/
   @FXML
    void onActionGoToMainMenu(ActionEvent event) throws IOException {
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
    void onActionSaveModifiedPart(ActionEvent event) throws IOException {
        String name = partNameTxt.getText();
     String price = priceTxt.getText();
     String stock = inventoryTxt.getText();
     String min = minAmtTxt.getText();
      String max = maxAmtTxt.getText();
      String companyName = machineIdTxt.getText();
      String machineId = machineIdTxt.getText();
     
     
     
     if(isInhouse ==true){
      Inhouse inPart = new Inhouse();
      inPart.setId(ID);
      inPart.setName(name);
      inPart.setStock(Integer.parseInt(stock));
      inPart.setPrice(Double.parseDouble(price));
      inPart.setMax(Integer.parseInt(max));
      inPart.setMin(Integer.parseInt(min));
      inPart.setMachineId(Integer.parseInt(machineId));
      
        Inventory.updatePart(partIndex, inPart);           } else {
        Outsourced outPart = new Outsourced();
      outPart.setId(ID);
      outPart.setName(name);
      outPart.setStock(Integer.parseInt(stock));
      outPart.setPrice(Double.parseDouble(price));
      outPart.setMax(Integer.parseInt(max));
      outPart.setMin(Integer.parseInt(min));
      outPart.setCompanyName(companyName);
      Inventory.updatePart(partIndex, outPart);
         }
      
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
       scene = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
       stage.setScene(new Scene(scene));
        stage.show();
            
    }
    
    
    
    @FXML
    void selectInhouseOptionHandler(ActionEvent event) {
        isInhouse = true;
        machineIdLbl.setText("Machine ID");
        machineIdTxt.setPromptText("Machine ID");
        outsourcedRbtn.setSelected(false);
    }

    @FXML
    void selectOutsourcedBtnHandler(ActionEvent event) {
        isInhouse = false;
        machineIdLbl.setText("Company Name");
        machineIdTxt.setPromptText("Company Name");
        outsourcedRbtn.setSelected(false);
    }
        
            
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Part part = getAllParts().get(partIndex);
        ID = getAllParts().get(partIndex).getId();
       
        modifyIDTxt.setText("" +ID);
        partNameTxt.setText(part.getName());
        inventoryTxt.setText(Integer.toString(part.getStock()));
        priceTxt.setText(Double.toString(part.getPrice()));
        minAmtTxt.setText(Integer.toString(part.getMin()));
        maxAmtTxt.setText(Integer.toString(part.getMax()));
        if (part instanceof Inhouse) {
            machineIdTxt.setText(Integer.toString(((Inhouse)getAllParts().get(partIndex)).getMachineId()));
            machineIdLbl.setText("Machine Id");
            inhouseRbtn.setSelected(true);
        } else {
            machineIdTxt.setText(((Outsourced)getAllParts().get(partIndex)).getCompanyName());
        machineIdLbl.setText("Company Name");
        outsourcedRbtn.setSelected(true);
        }
        
        
        
    }    
    
}
