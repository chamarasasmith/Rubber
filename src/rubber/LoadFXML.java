/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubber;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author Chamara Sasmith
 */
public class LoadFXML {

    static FXMLLoader loader;
    static AnchorPane ap;
    public static AnchorPane view1;
    static ScaleTransition st;

    public static void setFXML(String text) {

        try {

            loader = new FXMLLoader(Rubber.class.getResource(text));
            ap = loader.load();
            view1.setTopAnchor(ap, 0.0);
            view1.setBottomAnchor(ap, 0.0);
            view1.setLeftAnchor(ap, 0.0);
            view1.setRightAnchor(ap, 0.0);

            view1.getChildren().clear();
            view1.getChildren().add(ap);

            st = new ScaleTransition(Duration.millis(250));
            st.setNode(ap);

            st.setFromX(0);
            st.setFromY(0);
            st.setToX(1);
            st.setToY(1);

            st.play();

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}
