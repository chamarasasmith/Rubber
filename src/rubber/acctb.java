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
public class acctb {
    
    
    private final StringProperty tnum;
    @FXML
    private final StringProperty tname;
    @FXML
    private final StringProperty trtgs;
    @FXML
    private final StringProperty tmicr;
    @FXML
    private final StringProperty tbank;

    public acctb(String tnum, String tname, String trtgs, String tmicr, String tbank) {
        this.tnum = new SimpleStringProperty(tnum);
        this.tname = new SimpleStringProperty(tname);
        this.trtgs = new SimpleStringProperty(trtgs);
        this.tmicr = new SimpleStringProperty(tmicr);
        this.tbank = new SimpleStringProperty(tbank);
    }

    public String getTnum() {
        return tnum.get();
    }

    public String getTname() {
        return tname.get();
    }

    public String getTrtgs() {
        return trtgs.get();
    }

    public String getTmicr() {
        return tmicr.get();
    }

    public String getTbank() {
        return tbank.get();
    }
    
    
    
}
