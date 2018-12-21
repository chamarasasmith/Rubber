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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
public class CustomerController implements Initializable {

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
    private RadioButton rb5;
    @FXML
    private TextField text;
    @FXML
    private TableView<customertb> tb;
    @FXML
    private TableColumn<customertb, String> tcid;
    @FXML
    private TableColumn<customertb, String> tfn;
    @FXML
    private TableColumn<customertb, String> tln;
    @FXML
    private TableColumn<customertb, String> tstreet;
    @FXML
    private TableColumn<customertb, String> tcity;
    @FXML
    private TableColumn<customertb, String> ttel;
    @FXML
    private TableColumn<customertb, String> tmob;
    @FXML
    private TableColumn<customertb, String> temail;
    @FXML
    private TextField fn;
    @FXML
    private TextField ln;
    @FXML
    private TextField street;
    @FXML
    private TextField city;
    @FXML
    private TextField tel;
    @FXML
    private TextField mob;
    @FXML
    private TextField email;
    @FXML
    private TextField cid;

    ObservableList<customertb> customer1;

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

            tb.getItems().clear();

            ResultSet rs = null;
            String s = text.getText() + "%";

            if (rb1.isSelected()) {
                rs = DB.getdata("SELECT * FROM `rubber`.`customer`");
            }
            if (rb2.isSelected()) {
                rs = DB.getdata("SELECT * FROM `rubber`.`customer` WHERE fname LIKE '" + s + "' OR lname LIKE '" + s + "'");
            }
            if (rb3.isSelected()) {
                rs = DB.getdata("SELECT * FROM `rubber`.`customer` WHERE cid LIKE '" + s + "' ");
            }
            if (rb4.isSelected()) {
                rs = DB.getdata("SELECT * FROM `rubber`.`customer` WHERE citycounty LIKE '" + s + "' ");
            }
            if (rb5.isSelected()) {
                rs = DB.getdata("SELECT * FROM `rubber`.`customer` WHERE tel LIKE '" + s + "' OR mobile LIKE '" + s + "' ");
            }

            while (rs.next()) {

                String cid = rs.getString("cid");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String street = rs.getString("street");
                String citycounty = rs.getString("citycounty");
                String email = rs.getString("email");
                String tel = rs.getString("tel");
                String mobile = rs.getString("mobile");

                customer1 = FXCollections.observableArrayList(
                        new customertb(cid, fname, lname, street, citycounty, tel, mobile, email)
                );
                setTable1();
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

            String cid1 = cid.getText();
            String fn1 = fn.getText();
            String ln1 = ln.getText();
            String st = street.getText();
            String city1 = city.getText();
            String tel1 = tel.getText();
            String mob1 = mob.getText();
            String email1 = email.getText();

            DB.putdata("INSERT INTO `rubber`.`customer` ( `cid`, `fname`, `lname`, `street`, `citycounty`, `email`, `tel`, `mobile`, `st` ) VALUES ( '" + cid1 + "', '" + fn1 + "', '" + ln1 + "', '" + st + "', '" + city1 + "', '" + email1 + "', '" + tel1 + "', '" + mob1 + "', '1' )");

            CSAlert.successView(cid1 + " Save Success");

            customer1 = FXCollections.observableArrayList(
                    new customertb(cid1, fn1, ln1, st, city1, tel1, mob1, email1)
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

            String cid1 = cid.getText();
            String fn1 = fn.getText();
            String ln1 = ln.getText();
            String st = street.getText();
            String city1 = city.getText();
            String tel1 = tel.getText();
            String mob1 = mob.getText();
            String email1 = email.getText();

            DB.putdata("UPDATE `rubber`.`customer` SET `fname` ='" + fn1 + "' , `lname` = '" + ln1 + "', `street` = '" + st + "', `citycounty` = '" + city1 + "', `email` = '" + email1 + "', `tel` = '" + tel1 + "', `mobile` = '" + mob1 + "', `st` = '1' WHERE `cid` = '"+cid1+"'");

            CSAlert.successView(cid1 + " Update Success");

            customer1 = FXCollections.observableArrayList(
                    new customertb(cid1, fn1, ln1, st, city1, tel1, mob1, email1)
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

                DB.putdata("DELETE FROM `rubber`.`customer` WHERE `cid` = '" + cid.getText() + "'");
                CSAlert.successView(cid.getText() + " Delete Success");
                tb.getItems().clear();
                clear1();

            } else {

            }

        } catch (Exception e) {
            CSAlert.errorView(e);
        }

    }

    private void setTable1() {

        tcid.setCellValueFactory(new PropertyValueFactory<>("tcid"));
        tfn.setCellValueFactory(new PropertyValueFactory<>("tfn"));
        tln.setCellValueFactory(new PropertyValueFactory<>("tln"));
        tstreet.setCellValueFactory(new PropertyValueFactory<>("tstreet"));
        tcity.setCellValueFactory(new PropertyValueFactory<>("tcity"));
        ttel.setCellValueFactory(new PropertyValueFactory<>("ttel"));
        tmob.setCellValueFactory(new PropertyValueFactory<>("tmob"));
        temail.setCellValueFactory(new PropertyValueFactory<>("temail"));
        tb.getItems().addAll(customer1);
    }

    private void clear1() {

        cid.clear();
        fn.clear();
        ln.clear();
        street.clear();
        city.clear();
        tel.clear();
        mob.clear();
        email.clear();

    }

    public void setData1() {

        try {
            customertb c = tb.getSelectionModel().getSelectedItem();

            cid.setText(c.getTcid());
            fn.setText(c.getTfn());
            ln.setText(c.getTln());
            street.setText(c.getTstreet());
            city.setText(c.getTcity());
            tel.setText(c.getTtel());
            mob.setText(c.getTmob());
            email.setText(c.getTemail());

        } catch (Exception e) {
            CSAlert.errorView(e);
        }
    }

    @FXML
    private void setD2(MouseEvent event) {
        setData1();
    }

    @FXML
    private void setD1(KeyEvent event) {
        setData1();
    }

}
