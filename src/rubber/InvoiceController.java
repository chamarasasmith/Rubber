/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubber;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Chamara Sasmith
 */
public class InvoiceController implements Initializable {

    @FXML
    private TextField txinid;
    @FXML
    private TextField txqty;
    @FXML
    private TextField txnprice;
    @FXML
    private TextField txdis;
    @FXML
    private TextField txnet;
    @FXML
    private ComboBox<String> txiid;
    @FXML
    private DatePicker txdate1;
    @FXML
    private TextField txpay;
    @FXML
    private TextField txbal;
    @FXML
    private ComboBox<String> txcus;
    @FXML
    private TextField txtprice;
    @FXML
    private RadioButton rb1;
    @FXML
    private ToggleGroup a;
    @FXML
    private RadioButton rb2;
    @FXML
    private RadioButton rb3;
    @FXML
    private RadioButton rb4;
    @FXML
    private TextField text;
    @FXML
    private TableView<?> tb;
    @FXML
    private TableColumn<?, ?> tinid;
    @FXML
    private TableColumn<?, ?> tiid;
    @FXML
    private TableColumn<?, ?> tiname;
    @FXML
    private TableColumn<?, ?> tdate;
    @FXML
    private TableColumn<?, ?> tqty;
    @FXML
    private TableColumn<?, ?> tnprice;
    @FXML
    private TableColumn<?, ?> tdis;
    @FXML
    private TableColumn<?, ?> tnet;

    ObservableList<String> item1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void setAdd(ActionEvent event) {
    }

    @FXML
    private void setSave(ActionEvent event) {
    }

    @FXML
    private void setUpdate(ActionEvent event) {
    }

    @FXML
    private void setDelete(ActionEvent event) {
    }

    private void setCombo1() {

        try {
            String s = txiid.getEditor().getText() + "%";
            System.out.println(s);
            ResultSet rs = DB.getdata("SELECT * FROM `rubber`.`item` WHERE itemid LIKE '" + s + "' ");
            item1 = FXCollections.observableArrayList();
            while (rs.next()) {

                String id1 = rs.getString("itemid");
                String iname = rs.getString("pname");

                ResultSet rs1 = DB.getdata("SELECT * FROM `rubber`.`price` WHERE item_itemid LIKE '" + id1 + "' ");

                while (rs1.next()) {
                    String cp = rs1.getString("cprice");
                    String sp = rs1.getString("sprice");
                    
                    String s1 = cconvert.setConvert(cp);
                    String s2 = cconvert.setConvert(sp);
                    
                    item1.add(id1 + " : " + iname+" : "+s2);
                }
                
             

            }
            txiid.setItems(item1);
        } catch (Exception e) {
            CSAlert.errorView(e);
        }

    }

    @FXML
    private void setD1(Event event) {
        setCombo1();
    }

    @FXML
    private void setNPrice(KeyEvent event) {
        
       
        try {
            
            if (txqty.getText().matches("[0-9]*")) {
                    
                
            
            if (txiid.getSelectionModel().isSelected(-1) | txiid.getEditor().getText().isEmpty() | txqty.getText().isEmpty()) {
                txnprice.clear();
            }else{
            
                String[] split = txiid.getEditor().getText().split(" : ");
                double d1 = Double.parseDouble(txqty.getText());
                double d2 = Double.parseDouble(split[2]);
                
                txnprice.setText(d1*d2+"");
                
            }
            
            }
        } catch (Exception e) {
        CSAlert.errorView(e);
        }
        
    }

    @FXML
    private void setDiscount(KeyEvent event) {
        
        try {
            
            if (txdis.getText().matches("[0-9]*")) {
                    
                
            
            if (txnprice.getText().isEmpty() | txdis.getText().isEmpty()) {
                txnet.clear();
            }else{
            
               
                double d1 = Double.parseDouble(txnprice.getText());
//                double d1 = new Double(txnprice.getText());
                double d2 = Double.parseDouble(txdis.getText());
                
                txnet.setText(d1-(d1/100*d2)+"");
                
            }
            
            }
        } catch (Exception e) {
        CSAlert.errorView(e);
        }
        
        
    }

}
