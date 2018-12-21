/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubber;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Chamara Sasmith
 */
public class BanksController implements Initializable {

    @FXML
    private TextField bname;
    @FXML
    private TextField street;
    @FXML
    private TextField city;
    @FXML
    private TextField country;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void search1(KeyEvent event) {
          try {
            
            ResultSet rs = DB.getdata("SELECT * FROM `rubber`.`banks` WHERE name='"+bname.getText()+"'");
        
            if (rs.next()) {
                street.setText(rs.getString("street"));
                city.setText(rs.getString("city"));
                country.setText(rs.getString("country"));
            }else{
            
            street.clear();
            city.clear();
            country.clear();
            }
            
        } catch (Exception e) {
        CSAlert.errorView(e);
        }
    }

    @FXML
    private void setSave(ActionEvent event) {
        
        try {
            
            DB.putdata("INSERT INTO `rubber`.`banks` (`name`, `street`, `city`, `country` ) VALUES ( '"+bname.getText()+"', '"+street.getText()+"', '"+city.getText()+"', '"+country.getText()+"' ) ");
            
            CSAlert.successView(bname.getText()+" Save Success");
            clear1();
        } catch (Exception e) {
        CSAlert.errorView(e);
        }
        
    }

    @FXML
    private void setDelete(ActionEvent event) {
        
        try {
            DB.putdata("DELETE FROM `rubber`.`banks` WHERE name='"+bname.getText()+"'");
            CSAlert.successView(bname.getText()+" Delete Success");
            clear1();
        } catch (Exception ex) {
        
            CSAlert.errorView(ex);
        
        }
        
    }
    
    private void clear1() {
    
        
        bname.clear();
        street.clear();
        city.clear();
        country.clear();
    
    }
    
}
