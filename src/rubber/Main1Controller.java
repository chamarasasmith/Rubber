/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubber;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class Main1Controller implements Initializable {

    @FXML
    private ImageView close;
    @FXML
    private AnchorPane map;
    @FXML
    private RadioButton rup;
    @FXML
    private ToggleGroup a;
    @FXML
    private RadioButton other1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadFXML.view1=map;
        cconvert.o=other1;
        cconvert.r=rup;
    }    

    @FXML
    private void ex(MouseEvent event) {
        URL url = Rubber.class.getResource("/img/c1.png");
        Image image = new Image(url.toString());
        close.setImage(image);
    }

    @FXML
    private void en(MouseEvent event) {
        URL url = Rubber.class.getResource("/img/c2.png");
        Image image = new Image(url.toString());
        close.setImage(image);
    }

    @FXML
    private void close1(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void setSuplier(MouseEvent event) {
       LoadFXML.setFXML("supplier.fxml");
    }

    @FXML
    private void setCustomer(MouseEvent event) {
        LoadFXML.setFXML("customer.fxml");
    }

    @FXML
    private void setProduct(MouseEvent event) {
        LoadFXML.setFXML("item.fxml");
    }

    @FXML
    private void setAccounts(MouseEvent event) {
    LoadFXML.setFXML("account.fxml");
    }

    @FXML
    private void setInvoice(MouseEvent event) {
        LoadFXML.setFXML("invoice.fxml");
    }

    @FXML
    private void setGrn(MouseEvent event) {
        LoadFXML.setFXML("grn.fxml");
    }

    @FXML
    private void setCheque(MouseEvent event) {
        LoadFXML.setFXML("cheque.fxml");
    }

    @FXML
    private void setSettings(MouseEvent event) {
        LoadFXML.setFXML("settings1.fxml");
    }

    @FXML
    private void setOrders(MouseEvent event) {
           LoadFXML.setFXML("orders.fxml");
    }

    @FXML
    private void setOther(ActionEvent event) {
        
        
        TextInputDialog tid = new TextInputDialog();
        tid.setContentText("Ex 147.59");
        tid.setTitle("Set Currency");
        tid.setHeaderText("Insert Value");
        Optional<String> o = tid.showAndWait();
        
        if (o.isPresent()) {
            cconvert.d1=Double.parseDouble(o.get());
//            double d = cconvert.setConvert(10.0);
            
        }
        

     
    }
    
}
