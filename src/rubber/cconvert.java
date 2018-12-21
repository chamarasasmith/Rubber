/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubber;

import java.text.DecimalFormat;
import javafx.scene.control.RadioButton;

/**
 *
 * @author Chamara Sasmith
 */
public class cconvert {
    
    public static RadioButton o;
    public static RadioButton r;
    public static double d1=0.0; 
    
   public static String setConvert(String d2){
  
        double d3 = Double.parseDouble(d2);
       
       if (o.isSelected()) {
           return new DecimalFormat("0.000").format(d1/d3)+"";
       }
         if (r.isSelected()) {
           return d2;
       }
       return d2;
   }
    
   
}
