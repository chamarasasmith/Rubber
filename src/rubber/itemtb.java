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
public class itemtb {
    
    
    private StringProperty tiid;
    @FXML
    private StringProperty tiname;
    @FXML
    private StringProperty ths;
    @FXML
    private StringProperty tbox;
    @FXML
    private StringProperty tqty;
    @FXML
    private StringProperty tcprice;
    @FXML
    private StringProperty tsprice;

    public itemtb(String tiid, String tiname, String ths, String tbox, String tqty, String tcprice, String tsprice) {
        this.tiid = new SimpleStringProperty(tiid);
        this.tiname = new SimpleStringProperty(tiname);
        this.ths = new SimpleStringProperty(ths);
        this.tbox = new SimpleStringProperty(tbox);
        this.tqty = new SimpleStringProperty(tqty);
        this.tcprice = new SimpleStringProperty(tcprice);
        this.tsprice = new SimpleStringProperty(tsprice);
    }

    public String getTiid() {
        return tiid.get();
    }

    public String getTiname() {
        return tiname.get();
    }

    public String getThs() {
        return ths.get();
    }

    public String getTbox() {
        return tbox.get();
    }

    public String getTqty() {
        return tqty.get();
    }

    public String getTcprice() {
        return tcprice.get();
    }

    public String getTsprice() {
        return tsprice.get();
    }
    
    
    
}
