/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubber;

import java.net.URL;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Chamara Sasmith
 */
public class ItemController implements Initializable {

    @FXML
    private RadioButton rb1;
    @FXML
    private ToggleGroup a;
    @FXML
    private RadioButton rb2;
    @FXML
    private RadioButton rb3;
    @FXML
    private TextField text;
    @FXML
    private TableView<itemtb> tb;
    @FXML
    private TableColumn<itemtb, String> tiid;
    @FXML
    private TableColumn<itemtb, String> tiname;
    @FXML
    private TableColumn<itemtb, String> ths;
    @FXML
    private TableColumn<itemtb, String> tbox;
    @FXML
    private TableColumn<itemtb, String> tqty;
    @FXML
    private TableColumn<itemtb, String> tcprice;
    @FXML
    private TableColumn<itemtb, String> tsprice;
    @FXML
    private TextField txiid;
    @FXML
    private TextField txname;
    @FXML
    private TextField txhs;
    @FXML
    private TextField txbox;
    @FXML
    private TextField txqty;
    @FXML
    private ComboBox<String> txcprice;
    @FXML
    private ComboBox<String> txsprice;

    ObservableList<itemtb> item1 = FXCollections.observableArrayList();

    ObservableList<String> cprice1;
    ObservableList<String> sprice1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCombo1();
        loadCombo2();
    }

    private void loadCombo1() {

        try {
            cprice1 = FXCollections.observableArrayList();
            ResultSet rs = DB.getdata("SELECT * FROM `rubber`.`price` WHERE item_itemid='" + txiid.getText() + "'");

            while (rs.next()) {
                cprice1.add(rs.getString("cprice"));
            }
            txcprice.setItems(cprice1);
        } catch (Exception e) {
            CSAlert.errorView(e);
        }

    }

    private void loadCombo2() {
        try {
            sprice1 = FXCollections.observableArrayList();
            ResultSet rs = DB.getdata("SELECT * FROM `rubber`.`price` WHERE item_itemid='" + txiid.getText() + "'");

            while (rs.next()) {
                sprice1.add(rs.getString("sprice"));
            }
            txsprice.setItems(sprice1);
        } catch (Exception e) {
            CSAlert.errorView(e);
        }
    }

    @FXML
    private void search1(KeyEvent event) {
        try {

            tb.getItems().clear();

            ResultSet rs1 = null;
          
            String s = text.getText() + "%";
//            String s = "1";

            if (rb1.isSelected()) {
//                rs1 = DB.getdata("SELECT * FROM `rubber`.`price`");
                rs1 = DB.getdata("SELECT * FROM `rubber`.`item`");
            }
            if (rb2.isSelected()) {
//                rs1 = DB.getdata("SELECT * FROM `rubber`.`price` WHERE item_itemid LIKE '" + s + "'");
                rs1 = DB.getdata("SELECT * FROM `rubber`.`item` WHERE  itemid LIKE '" + s + "'");
            }
            if (rb3.isSelected()) {
//                rs1 = DB.getdata("SELECT * FROM price WHERE item_itemid IN(SELECT itemid FROM `rubber`.`item` WHERE pname LIKE '" + s + "')");
                rs1 = DB.getdata("SELECT * FROM `rubber`.`item` WHERE pname LIKE '"+s+"'");
            }
            
            while (rs1.next()) {                
//                String s1 = rs1.getString("item_itemid");
                String s1 = rs1.getString("itemid");
//                System.out.println("AA "+s1);
//                ResultSet rs = DB.getdata("SELECT * FROM `rubber`.`item` WHERE itemid='"+s1+"'");
                ResultSet rs = DB.getdata("SELECT * FROM `rubber`.`price` WHERE item_itemid='"+s1+"'");
                
//                while (rs1.next()) {                    
                    
                    String iid = rs1.getString("itemid");
                    String hs=rs1.getString("hscode");
                    String pn=rs1.getString("pname");
                    String box=rs1.getString("box");
                    String qty=rs1.getString("qty");
                    
                    ResultSet rs2 = DB.getdata("SELECT * FROM price WHERE item_itemid='"+iid+"'");
                    
                    while (rs2.next()) {                        
                        
                        String cp1 = rs2.getString("cprice");
                        String sp1 =rs2.getString("sprice");
                    
                       item1=FXCollections.observableArrayList(
                       new itemtb(iid, pn, hs, box, qty, cp1, sp1)
                       );
                       setTable1();
                    }
                    
                    
                   
                    
//                }
                
            }

        } catch (Exception e) {
            CSAlert.errorView(e);
        }
    }

    @FXML
    private void setAdd(ActionEvent event) {
        clear1();
    }

    @FXML
    private void setSave(ActionEvent event) {

        tb.getItems().clear();
        try {

            String iid = txiid.getText();
            String name = txname.getText();
            String hs = txhs.getText();
            String box = txbox.getText();
            String qty = txqty.getText();
            String cp = txcprice.getSelectionModel().getSelectedItem();
            String sp = txsprice.getSelectionModel().getSelectedItem();

            DB.putdata("INSERT INTO `rubber`.`item` ( `itemid`, `hscode`, `pname`, `box`, `qty` ) VALUES ( '" + iid + "', '" + hs + "', '" + name + "', '" + box + "', '" + qty + "' ) ");

            ResultSet rs = DB.getdata("SELECT * FROM `rubber`.`price` WHERE item_itemid='" + iid + "' AND cprice='" + cp + "' AND sprice='" + sp + "'");

            if (rs.first()) {

            } else {
                DB.putdata("INSERT INTO `rubber`.`price` (`cprice`,`sprice`, `item_itemid` ) VALUES ('" + cp + "','" + sp + "' ,'" + new Integer(iid) + "' ) ");

            }

            CSAlert.successView(iid + " Save Success");

            item1 = FXCollections.observableArrayList(
                    new itemtb(iid, name, hs, box, qty, cp, sp)
            );
            setTable1();
            clear1();
        } catch (Exception e) {
            CSAlert.errorView(e);
        }

    }

    @FXML
    private void setUpdate(ActionEvent event) {

        tb.getItems().clear();
        try {

            String iid = txiid.getText();
            String name = txname.getText();
            String hs = txhs.getText();
            String box = txbox.getText();
            String qty = txqty.getText();
            String cp = txcprice.getSelectionModel().getSelectedItem();
            String sp = txsprice.getSelectionModel().getSelectedItem();

            DB.putdata("UPDATE `rubber`.`item` SET `hscode` = '" + hs + "', `pname` = '" + name + "', `box` = '" + box + "', `qty` = '" + qty + "' WHERE `itemid` = '" + iid + "'");

            ResultSet rs = DB.getdata("SELECT * FROM `rubber`.`price` WHERE item_itemid='" + iid + "' AND cprice='" + cp + "' AND sprice='" + sp + "'");

            if (rs.first()) {

            } else {

                DB.putdata("INSERT INTO `rubber`.`price` (`cprice`,`sprice`, `item_itemid` ) VALUES ('" + cp + "','" + sp + "' ,'" + new Integer(iid) + "' ) ");

            }

            CSAlert.successView(iid + " Update Success");

            item1 = FXCollections.observableArrayList(
                    new itemtb(iid, name, hs, box, qty, cp, sp)
            );
            setTable1();
            clear1();
        } catch (Exception e) {
            CSAlert.errorView(e);
        }

    }

    @FXML
    private void setDelete(ActionEvent event) {

        try {

            Optional<ButtonType> o = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure", ButtonType.NO, ButtonType.YES).showAndWait();

            if (o.get() == ButtonType.YES) {

                DB.putdata("DELETE FROM `rubber`.`price` WHERE `item_itemid` = '" + txiid.getText() + "'");
                DB.putdata("DELETE FROM `rubber`.`item` WHERE `itemid` = '" + txiid.getText() + "'");
                CSAlert.successView(txiid.getText() + " Delete Success");
                tb.getItems().clear();
                clear1();
            }
        } catch (Exception e) {
            CSAlert.errorView(e);
        }

    }

    private void setTable1() {

        tiid.setCellValueFactory(new PropertyValueFactory<>("tiid"));
        tiname.setCellValueFactory(new PropertyValueFactory<>("tiname"));
        ths.setCellValueFactory(new PropertyValueFactory<>("ths"));
        tbox.setCellValueFactory(new PropertyValueFactory<>("tbox"));
        tqty.setCellValueFactory(new PropertyValueFactory<>("tqty"));
        tcprice.setCellValueFactory(new PropertyValueFactory<>("tcprice"));
        tsprice.setCellValueFactory(new PropertyValueFactory<>("tsprice"));
        tb.getItems().addAll(item1);

    }

    @FXML
    private void addCp(ActionEvent event) {

        try {
            String s = CSAlert.inputView("Enter New Price");
//            String iid = txiid.getText();
//            
//            if (s.isEmpty() && iid.isEmpty()) {
//
//            } else {
//
//                ResultSet rs = DB.getdata("SELECT * FROM `price` WHERE item_itemid='" + iid + "' AND cprice='0'");
//
//                while (rs.next()) {
//                    DB.putdata(" UPDATE `rubber`.`price` SET `cprice` = '" + s + "' WHERE item_itemid='" + iid + "' AND cprice='0'");
//
//                }
//                if (rs.first()) {
//
//                } else {
//                    DB.putdata("INSERT INTO `rubber`.`price` (`cprice`,`sprice`, `item_itemid` ) VALUES ('" + s + "','0' ,'" + new Integer(iid) + "' ) ");
//                }
            cprice1.add(s);

            txcprice.setItems(cprice1);

            txcprice.getSelectionModel().select(s);

//            }
        } catch (Exception ex) {
            CSAlert.errorView(ex);
        }

    }

    @FXML
    private void removeCp(ActionEvent event) {

        String s = txcprice.getSelectionModel().getSelectedItem();
        try {
            DB.putdata("DELETE FROM `rubber`.`price` WHERE `item_itemid` = '" + txiid.getText() + "' AND cprice = '" + s + "'");
            txcprice.getSelectionModel().clearAndSelect(-1);
        } catch (Exception e) {
            CSAlert.errorView(e);
        }

    }

    @FXML
    private void addSp(ActionEvent event) {

        try {
            String s = CSAlert.inputView("Enter New Price");
//            String iid = txiid.getText();
//            if (s.isEmpty() && iid.isEmpty()) {
//
//            } else {
//
//                ResultSet rs = DB.getdata("SELECT * FROM `rubber`.`price` WHERE item_itemid='" + iid + "' AND sprice='0'");
//
//                while (rs.next()) {
//                    DB.putdata(" UPDATE `rubber`.`price` SET `sprice` = '" + s + "' WHERE item_itemid='" + iid + "' AND sprice='0'");
//
//                }
//                if (rs.first()) {
//
//                } else {
//                    DB.putdata("INSERT INTO `rubber`.`price` (`sprice`,`cprice`, `item_itemid` ) VALUES ('" + s + "','0','" + new Integer(iid) + "' ) ");
//                }

            sprice1.add(s);

            txsprice.setItems(sprice1);

            txsprice.getSelectionModel().select(s);
//            }
        } catch (Exception ex) {
            CSAlert.errorView(ex);
        }

    }

    @FXML
    private void removeSp(ActionEvent event) {

        String s = txsprice.getSelectionModel().getSelectedItem();
        try {
            DB.putdata("DELETE FROM `rubber`.`price` WHERE `item_itemid` = '" + txiid.getText() + "' AND sprice = '" + s + "'");
            txsprice.getSelectionModel().clearAndSelect(-1);
        } catch (Exception e) {
            CSAlert.errorView(e);
        }

    }

    @FXML
    private void setData1(Event event) {
        loadCombo1();
    }

    @FXML
    private void setData2(Event event) {
        loadCombo2();
    }

    private void setTData() {

        try {
            itemtb i = tb.getSelectionModel().getSelectedItem();

            txiid.setText(i.getTiid());
            txname.setText(i.getTiname());
            txhs.setText(i.getThs());
            txbox.setText(i.getTbox());
            txqty.setText(i.getTqty());
            loadCombo1();
            loadCombo2();
            txcprice.getSelectionModel().select(i.getTcprice());
            txsprice.getSelectionModel().select(i.getTsprice());

        } catch (Exception e) {
            CSAlert.errorView(e);
        }

    }

    private void clear1() {

        txiid.clear();
        txname.clear();
        txhs.clear();
        txbox.clear();
        txqty.clear();
        txcprice.getSelectionModel().select(-1);
        txsprice.getSelectionModel().select(-1);
    }

    @FXML
    private void setTD2(MouseEvent event) {
        setTData();
    }

    @FXML
    private void setTD1(KeyEvent event) {
        setTData();
    }

    @FXML
    private void setPDelete(ActionEvent event) {
        try {

            Optional<ButtonType> o = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure", ButtonType.NO, ButtonType.YES).showAndWait();

            if (o.get() == ButtonType.YES) {

                String cp = txcprice.getSelectionModel().getSelectedItem();
            String sp = txsprice.getSelectionModel().getSelectedItem();
                
                if (txcprice.getSelectionModel().isSelected(-1) || txsprice.getSelectionModel().isSelected(-1)) {
                   CSAlert.successView("Select Cost Price And Sale Price");
                }else{
                
                DB.putdata("DELETE FROM `rubber`.`price` WHERE `item_itemid` = '" + txiid.getText() + "' AND cprice='"+cp+"' AND sprice='"+sp+"'");
//                DB.putdata("DELETE FROM `rubber`.`item` WHERE `itemid` = '" + txiid.getText() + "'");
                CSAlert.successView(txiid.getText() + " Delete Success");
                tb.getItems().clear();
                clear1();
                
                }
            }
        } catch (Exception e) {
            CSAlert.errorView(e);
        }

    }

}
