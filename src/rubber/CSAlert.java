/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubber;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 *
 * @author Chamara Sasmith
 */
public class CSAlert {

    public static void errorView(Exception e) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Unsuccess !");
        alert.setContentText("Try Again");

//        StringWriter sw = new StringWriter();
//        PrintWriter pw = new PrintWriter(sw);
//        e.printStackTrace(pw);
//        String exceptionText = sw.toString();
        Label label = new Label("Exception is");

        TextArea textArea = new TextArea(e.toString());
//        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();

    }

    public static void successView(String e) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success Dialog");
        alert.setHeaderText("Success !");
        alert.setContentText(e);

        alert.showAndWait();

    }

    public static String inputView(String e) {

        TextInputDialog tid = new TextInputDialog();
        tid.setContentText(e);
        tid.setTitle("Input Dialog");
        tid.setHeaderText("Insert Value");
        Optional<String> o = tid.showAndWait();

        if (o.isPresent()) {
            return o.get();
        }
        return "";
    }

}
