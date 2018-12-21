/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubber;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author User
 */
public class Rubber extends Application {
    
     public static Parent root;
    public static Stage st;
    
    @Override
    public void start(Stage primaryStage) {
           try {
            st = primaryStage;
            root = FXMLLoader.load(getClass().getResource("Main1.fxml"));

//             URL reUrl=ui.sportfx.class.getResource("theme1.css");
//            String css=reUrl.toExternalForm();
//                Scene scene = new Scene(root,Color.TRANSPARENT);
            Scene scene = new Scene(root);

            //   scene.getStylesheets().add(css);
            st.initStyle(StageStyle.UNDECORATED);
            st.setFullScreen(true);
            st.setScene(scene);
   
            st.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
