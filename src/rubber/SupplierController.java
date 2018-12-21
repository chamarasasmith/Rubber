/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubber;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chamara Sasmith
 */
public class SupplierController implements Initializable {

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
    private TableView<suppliertb> tb;
    @FXML
    private TableColumn<suppliertb, String> tsid;
    @FXML
    private TableColumn<suppliertb, String> tname;
    @FXML
    private TableColumn<suppliertb, String> tstreet;
    @FXML
    private TableColumn<suppliertb, String> tcity;
    @FXML
    private TableColumn<suppliertb, String> ttel;
    @FXML
    private TableColumn<suppliertb, String> tmob;
    @FXML
    private TableColumn<suppliertb, String> temail;
    @FXML
    private TableColumn<suppliertb, String> tfax;
    @FXML
    private TableColumn<suppliertb, String> ttype;
    @FXML
    private TableColumn<suppliertb, String> tacc;
    @FXML
    private TextField sid;
    @FXML
    private TextField name;
    @FXML
    private TextField street;
    @FXML
    private TextField tel;
    @FXML
    private TextField mob;
    @FXML
    private TextField email;
    @FXML
    private TextField fax;
    @FXML
    private ComboBox<String> type1;
    @FXML
    private ComboBox<String> acc;
    @FXML
    private TextField citycountry;

    ObservableList<suppliertb> supplier1;
    ObservableList<String> list2 = FXCollections.observableArrayList("Local", "Intenational");
    ObservableList<String> list3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type1.setItems(list2);
        acc.setItems(list3);
    }

    @FXML
    private void search(KeyEvent event) {

        tb.getItems().clear();
        try {

            String s = text.getText() + "%";

            ResultSet rs = null;

            if (rb1.isSelected()) {
                rs = DB.getdata("SELECT * FROM `supplier`");
            }
            if (rb2.isSelected()) {
                rs = DB.getdata("SELECT * FROM `supplier` WHERE name LIKE '" + s + "'");
            }
            if (rb3.isSelected()) {
                rs = DB.getdata("SELECT * FROM `supplier` WHERE sid LIKE '" + s + "'");
            }
            if (rb4.isSelected()) {
                rs = DB.getdata("SELECT * FROM `supplier` WHERE citycountry LIKE '" + s + "'");
            }
            if (rb5.isSelected()) {
                rs = DB.getdata("SELECT * FROM `supplier` WHERE tel LIKE '" + s + "'");
            }

            while (rs.next()) {

                String sid = rs.getString("sid");
                String name = rs.getString("name");
                String street = rs.getString("street");
                String citycountry = rs.getString("citycountry");
                String tel = rs.getString("tel");
                String mobile = rs.getString("mobile");
                String email = rs.getString("email");
                String fax = rs.getString("fax");
                String type = rs.getString("type");
                String accounts_idaccounts = rs.getString("accounts_idaccounts");

                String num = "";

                ResultSet rs1 = DB.getdata("SELECT `no` FROM `accounts` WHERE idaccounts='" + accounts_idaccounts + "'");

                if (rs1.first()) {
                    num = rs1.getString("no");
                }

                supplier1 = FXCollections.observableArrayList(
                        new suppliertb(sid, name, street, citycountry, tel, mobile, email, fax, type, num)
                );

                tsid.setCellValueFactory(new PropertyValueFactory<>("tsid"));
                tname.setCellValueFactory(new PropertyValueFactory<>("tname"));
                tstreet.setCellValueFactory(new PropertyValueFactory<>("tstreet"));
                tcity.setCellValueFactory(new PropertyValueFactory<>("tcity"));
                ttel.setCellValueFactory(new PropertyValueFactory<>("ttel"));
                tmob.setCellValueFactory(new PropertyValueFactory<>("tmob"));
                temail.setCellValueFactory(new PropertyValueFactory<>("temail"));
                tfax.setCellValueFactory(new PropertyValueFactory<>("tfax"));
                ttype.setCellValueFactory(new PropertyValueFactory<>("ttype"));
                tacc.setCellValueFactory(new PropertyValueFactory<>("tacc"));
                tb.getItems().addAll(supplier1);

            }
            clear1();
        } catch (Exception e) {
            CSAlert.errorView(e);
        }

    }

    @FXML
    private void setSave(ActionEvent event) {

        try {

            ResultSet rs = DB.getdata("SELECT * FROM `rubber`.`accounts` WHERE no='" + acc.getSelectionModel().getSelectedItem() + "'");

            if (rs.first()) {

                DB.putdata("INSERT INTO supplier ( `sid`, `name`, `street`, `citycountry`, `fax`, `email`, `tel`, `mobile`, `type`, `accounts_idaccounts`, `st` ) VALUES ( '" + sid.getText() + "', '" + name.getText() + "', '" + street.getText() + "', '" + citycountry.getText() + "', '" + fax.getText() + "', '" + email.getText() + "', '" + tel.getText() + "', '" + mob.getText() + "', '" + type1.getSelectionModel().getSelectedItem() + "', '" + rs.getString("idaccounts") + "', '1' )");

                CSAlert.successView(sid.getText() + " Save Success");

                supplier1 = FXCollections.observableArrayList(
                        new suppliertb(sid.getText(), name.getText(), street.getText(), citycountry.getText(), tel.getText(), mob.getText(), email.getText(), fax.getText(), type1.getSelectionModel().getSelectedItem(), acc.getSelectionModel().getSelectedItem())
                );

                tsid.setCellValueFactory(new PropertyValueFactory<>("tsid"));
                tname.setCellValueFactory(new PropertyValueFactory<>("tname"));
                tstreet.setCellValueFactory(new PropertyValueFactory<>("tstreet"));
                tcity.setCellValueFactory(new PropertyValueFactory<>("tcity"));
                ttel.setCellValueFactory(new PropertyValueFactory<>("ttel"));
                tmob.setCellValueFactory(new PropertyValueFactory<>("tmob"));
                temail.setCellValueFactory(new PropertyValueFactory<>("temail"));
                tfax.setCellValueFactory(new PropertyValueFactory<>("tfax"));
                ttype.setCellValueFactory(new PropertyValueFactory<>("ttype"));
                tacc.setCellValueFactory(new PropertyValueFactory<>("tacc"));
                tb.setItems(supplier1);

            }
            clear1();
        } catch (Exception e) {

            CSAlert.errorView(e);
            System.out.println(e);

        }

    }

    @FXML
    private void setUpdate(ActionEvent event) {

        try {

            ResultSet rs = DB.getdata("SELECT * FROM `rubber`.`accounts` WHERE no='" + acc.getSelectionModel().getSelectedItem() + "'");

            if (rs.first()) {

                DB.putdata("UPDATE `rubber`.`supplier` SET `name` = '" + name.getText() + "', `street` = '" + street.getText() + "', `citycountry` = '" + citycountry.getText() + "', `fax` = '" + fax.getText() + "', `email` = '" + email.getText() + "', `tel` = '" + tel.getText() + "', `mobile` = '" + mob.getText() + "', `type` = '" + type1.getSelectionModel().getSelectedItem() + "', `accounts_idaccounts` = '" + rs.getString("idaccounts") + "', `st` = '1' WHERE `sid` = '" + sid.getText() + "'");

                CSAlert.successView(sid.getText() + " Update Success");

                supplier1 = FXCollections.observableArrayList(
                        new suppliertb(sid.getText(), name.getText(), street.getText(), citycountry.getText(), tel.getText(), mob.getText(), email.getText(), fax.getText(), type1.getSelectionModel().getSelectedItem(), acc.getSelectionModel().getSelectedItem())
                );

                tsid.setCellValueFactory(new PropertyValueFactory<>("tsid"));
                tname.setCellValueFactory(new PropertyValueFactory<>("tname"));
                tstreet.setCellValueFactory(new PropertyValueFactory<>("tstreet"));
                tcity.setCellValueFactory(new PropertyValueFactory<>("tcity"));
                ttel.setCellValueFactory(new PropertyValueFactory<>("ttel"));
                tmob.setCellValueFactory(new PropertyValueFactory<>("tmob"));
                temail.setCellValueFactory(new PropertyValueFactory<>("temail"));
                tfax.setCellValueFactory(new PropertyValueFactory<>("tfax"));
                ttype.setCellValueFactory(new PropertyValueFactory<>("ttype"));
                tacc.setCellValueFactory(new PropertyValueFactory<>("tacc"));
                tb.setItems(supplier1);

            }
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

                DB.putdata("DELETE FROM `rubber`.`supplier` WHERE `sid` = '" + sid.getText() + "'");
                CSAlert.successView(sid.getText() + " Delete Success");
                tb.getItems().clear();
                clear1();

            } else {

            }

        } catch (Exception e) {
            CSAlert.errorView(e);
        }
    }

    @FXML
    private void addAcc(ActionEvent event) {

        try {
            Stage s = new Stage();
            FXMLLoader l = new FXMLLoader(Rubber.class.getResource("account.fxml"));
            AnchorPane ap = l.load();
            Scene scene = new Scene(ap);
            s.setMaximized(true);
            s.setScene(scene);

            s.show();
        } catch (IOException ex) {

            CSAlert.errorView(ex);
        }

    }

    @FXML
    private void setShow(Event event) {

        try {

            list3 = FXCollections.observableArrayList();

            ResultSet rs = DB.getdata("SELECT DISTINCT * FROM `rubber`.`accounts` ");

            while (rs.next()) {
                String s = rs.getString("no");

                list3.add(s);
            }

            acc.setItems(list3);

        } catch (Exception e) {
            CSAlert.errorView(e);
        }

    }

    public void setData1() {

        int f = tb.getSelectionModel().getFocusedIndex();

        try {

            suppliertb stb = tb.getSelectionModel().getSelectedItem();

            sid.setText(stb.getTsid());
            name.setText(stb.getTname());
            street.setText(stb.getTstreet());
            citycountry.setText(stb.getTcity());
            tel.setText(stb.getTtel());
            mob.setText(stb.getTmob());
            email.setText(stb.getTemail());
            fax.setText(stb.getTfax());
            type1.getSelectionModel().select(stb.getTtype());

//            ResultSet rs = DB.getdata("SELECT `idaccounts` FROM `rubber`.`accounts` WHERE no='"+stb.getTacc()+"'");
            acc.getSelectionModel().select(stb.getTacc());

        } catch (Exception e) {
            CSAlert.errorView(e);
        }

    }

    @FXML
    private void setD1(MouseEvent event) {
        setData1();
    }

    @FXML
    private void setD2(KeyEvent event) {
        setData1();
    }

    public void clear1() {

        sid.clear();
        citycountry.clear();
        email.clear();
        fax.clear();
        mob.clear();
        name.clear();
        street.clear();
        tel.clear();
//        text.clear();

    }

    @FXML
    private void setAdd(ActionEvent event) {
        clear1();

    }

}
