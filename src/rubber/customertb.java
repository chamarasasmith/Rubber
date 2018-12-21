/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubber;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Chamara Sasmith
 */
public class customertb {
    
    private final StringProperty tcid;
    @FXML
    private final StringProperty tfn;
    @FXML
    private final StringProperty tln;
    @FXML
    private final StringProperty tstreet;
    @FXML
    private final StringProperty tcity;
    @FXML
    private final StringProperty ttel;
    @FXML
    private final StringProperty tmob;
    @FXML
    private final StringProperty temail;

    public customertb(String tcid, String tfn, String tln, String tstreet, String tcity, String ttel, String tmob, String temail) {
        this.tcid = new SimpleStringProperty(tcid);
        this.tfn = new SimpleStringProperty(tfn);
        this.tln = new SimpleStringProperty(tln);
        this.tstreet = new SimpleStringProperty(tstreet);
        this.tcity = new SimpleStringProperty(tcity);
        this.ttel = new SimpleStringProperty(ttel);
        this.tmob = new SimpleStringProperty(tmob);
        this.temail = new SimpleStringProperty(temail);
    }

    public String getTcid() {
        return tcid.get();
    }

    public String getTfn() {
        return tfn.get();
    }

    public String getTln() {
        return tln.get();
    }

    public String getTstreet() {
        return tstreet.get();
    }

    public String getTcity() {
        return tcity.get();
    }

    public String getTtel() {
        return ttel.get();
    }

    public String getTmob() {
        return tmob.get();
    }

    public String getTemail() {
        return temail.get();
    }
    
   
    
}
