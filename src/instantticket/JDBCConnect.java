/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instantticket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class JDBCConnect {
    JDBCConnect(){
        try
        {

            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway_database", "root", "root");
            
        }
        catch (SQLException ex)
        {
            System.out.print("exception is" + ex);
        }
        catch (ClassNotFoundException ex)
        {
            System.out.print("exception is" + ex);
        }
    }
    
}