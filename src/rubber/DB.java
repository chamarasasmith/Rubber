/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rubber;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 *
 * @author CS1st
 */
public class DB {
    
    static Connection c;
   

    public static Connection getcon() throws Exception {

       if (c == null) {
           
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubber","root", "zenonideas");
            return c;

        } else {
            return c;
        }
    }

    public static  void putdata(String sql) throws Exception {

        Connection c = DB.getcon();
        Statement st = c.createStatement();
        st.executeUpdate(sql);

    }

    public static ResultSet getdata(String sql) throws Exception {

        Connection c = DB.getcon();
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);

        return rs;


    }
    
}


