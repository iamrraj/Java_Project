/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inpharmacy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author RahulRaj
 */
public class InPharmacy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
     //   new LoginUser().setVisible(true);
        
    }
    
    
    Connection conn = null;
    Statement st = null;
    ResultSet rs;
    public void getConnection(){
        try{
           conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/inpharmacy","root","");
           JOptionPane.showMessageDialog(null, "Connection Established.");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
}
