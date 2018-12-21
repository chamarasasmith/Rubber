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

/**
 *
 * @author Chamara Sasmith
 */
public class suppliertb {
    
    private final StringProperty tsid;
    @FXML
    private final StringProperty tname;
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
    @FXML
    private final StringProperty tfax;
    @FXML
    private final StringProperty ttype;
    @FXML
    private final StringProperty tacc;

    public suppliertb(String tsid, String tname, String tstreet, String tcity, String ttel, String tmob, String temail, String tfax, String ttype, String tacc) {
        this.tsid = new SimpleStringProperty(tsid);
        this.tname = new SimpleStringProperty(tname);
        this.tstreet = new SimpleStringProperty(tstreet);
        this.tcity = new SimpleStringProperty(tcity);
        this.ttel = new SimpleStringProperty(ttel);
        this.tmob = new SimpleStringProperty(tmob);
        this.temail = new SimpleStringProperty(temail);
        this.tfax = new SimpleStringProperty(tfax);
        this.ttype = new SimpleStringProperty(ttype);
        this.tacc = new SimpleStringProperty(tacc);
    }

    public String getTsid() {
        return tsid.get();
    }

    public String getTname() {
        return tname.get();
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

    public String getTfax() {
        return tfax.get();
    }

    public String getTtype() {
        return ttype.get();
    }

    public String getTacc() {
        return tacc.get();
    }
    
    
    
}
