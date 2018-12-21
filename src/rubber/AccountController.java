/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubber;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chamara Sasmith
 */
public class AccountController implements Initializable {

    @FXML
    private ToggleGroup a;
    @FXML
    private RadioButton rb1;
    @FXML
    private RadioButton rb2;
    @FXML
    private RadioButton rb3;
    @FXML
    private TextField text;
    @FXML
    private TableView<acctb> tb;
    @FXML
    private TableColumn<acctb, String> tnum;
    @FXML
    private TableColumn<acctb, String> tname;
    @FXML
    private TableColumn<acctb, String> trtgs;
    @FXML
    private TableColumn<acctb, String> tmicr;
    @FXML
    private TableColumn<acctb, String> tbank;
    @FXML
    private TextField num;
    @FXML
    private TextField name;
    @FXML
    private TextField rtgs;
    @FXML
    private TextField micr;
    @FXML
    private ComboBox<String> bank;
    
    ObservableList<String> list3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void setBank(ActionEvent event) {
        
         try {
            Stage s = new Stage();
            FXMLLoader l = new FXMLLoader(Rubber.class.getResource("banks.fxml"));
            AnchorPane ap=l.load();
            Scene scene = new Scene(ap);
//            s.setMaximized(true);
            s.setScene(scene);
   
            s.show();
        } catch (IOException ex) {
           
        CSAlert.errorView(ex);
        }
        
    }

    @FXML
    private void setSearch(KeyEvent event) {
    }

    @FXML
    private void setSave(ActionEvent event) {
        
        try {
            ResultSet rs=DB.getdata("SELECT * FROM `rubber`.`banks` WHERE name='"+bank.getSelectionModel().getSelectedItem()+"'");
            if (rs.first()) {
  
            DB.putdata("INSERT INTO `rubber`.`accounts` ( `no`, `name`, `rtgs`, `micr`, `banks_idbanks` ) VALUES ( '"+num.getText()+"', '"+name.getText()+"', '"+rtgs.getText()+"', '"+micr.getText()+"', '"+rs.getString("idbanks")+"' )");
            }
            CSAlert.successView(num.getText()+" Account Save Success");
        } catch (Exception e) {
            CSAlert.errorView(e);
        }
        
    }

    @FXML
    private void setUpdate(ActionEvent event) {
        
        try {
            ResultSet rs=DB.getdata("SELECT * FROM `rubber`.`banks` WHERE name='"+bank.getSelectionModel().getSelectedItem()+"'");
            if (rs.first()) {
  
            DB.putdata("UPDATE `rubber`.`accounts` SET `name` = '"+name.getText()+"', `rtgs` = '"+rtgs.getText()+"', `micr` = '"+micr.getText()+"', `banks_idbanks` = '"+rs.getString("idbanks")+"' WHERE `no` = '"+num.getText()+"'");
                    
     
            }
            CSAlert.successView(num.getText()+" Account Update Success");
        } catch (Exception e) {
            CSAlert.errorView(e);
        }
        
    }

    @FXML
    private void setDelete(ActionEvent event) {
    
           try {
            
               DB.putdata("DELETE FROM `rubber`.`accounts` WHERE `no` = '"+num.getText()+"'");
               
            CSAlert.successView(num.getText()+" Account Delete Success");
        } catch (Exception e) {
            CSAlert.errorView(e);
        }
    
    }

    @FXML
    private void setShow1(Event event) {
        
        try {
            
            list3 = FXCollections.observableArrayList();
            
            ResultSet rs = DB.getdata("SELECT DISTINCT * FROM `rubber`.`banks` ");
        
            while (rs.next()) {
                String s = rs.getString("name");
               
                list3.add(s);
            }
            
            bank.setItems(list3);
            
        } catch (Exception e) {
        CSAlert.errorView(e);
        }
        
         
        
    }
    
}
