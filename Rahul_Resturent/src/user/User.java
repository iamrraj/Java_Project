/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import com.sun.glass.events.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import rahul_resturent.rahul;

/**
 *
 * @author RahulRaj
 */
public class User extends javax.swing.JFrame {

    /**
     * Creates new form User
     */
    public User() {
        initComponents();
        getConnection();
        getProductTable();
        getCat();
        getBrand();
        jTextFieldDis.setText("0");
        setVisible(true);
    }
    
    Connection conn = null;
    Statement st = null;
    ResultSet rs;
    public static int count = 0;
    public int ID = 0, saleID = 0;
    public void getConnection(){
        try{
           conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/raj","root","");
           //JOptionPane.showMessageDialog(null, "Connection Established.");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void theQuery(String query){
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void setUser(int id){
        ID = id;
        try{
            String sql = "SELECT User FROM login WHERE ID = "+id;
            rs = st.executeQuery(sql);
            while(rs.next()){
                jLabelUser.setText(rs.getString("User").toUpperCase());
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
   public void getProductTable(){
        DefaultTableModel product = new DefaultTableModel(new String[]{"ID","Category", "Brand", "Name", "Warranty", "Price", "Quantity"}, 0);
        try{
            st = conn.createStatement();
            String sql="SELECT * FROM product";
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                String col1 = rs.getString("Product_ID");
                String col2 = rs.getString("Category");
                String col3 = rs.getString("Brand");                
                String col4 = rs.getString("Product_Name");
                String col5 = rs.getString("Warranty");
                String col6 = rs.getString("Price");
                String col7 = rs.getString("Quantity");
                product.addRow(new Object[]{col1, col2, col3, col4, col5, col6, col7});
            }
            jTableProduct.setModel(product);
            rs.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
   
   public void getSaleInfoTable(){
        DefaultTableModel saleInfo = new DefaultTableModel(new String[]{"Product ID","Product","Warranty","Price","Quantity","Total"}, 0);
        int total = 0;
        try{
            st = conn.createStatement();
            String sql="SELECT * FROM sale_info WHERE Sale_ID = "+saleID;
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                String col6 = rs.getString("Product_ID");
                String col1 = rs.getString("Product");
                String col5 = rs.getString("Warranty");
                String col2 = rs.getString("Price");
                String col3 = rs.getString("Quantity");
                String col4 = rs.getString("Total");
                saleInfo.addRow(new Object[]{col6, col1, col5, col2, col3, col4});
            }
            jTableSaleInfo.setModel(saleInfo);
            for(int i =0; i< jTableSaleInfo.getModel().getRowCount(); i++){
                total += Integer.parseInt(jTableSaleInfo.getModel().getValueAt(i, 5).toString());
            }
            jTextFieldTotal.setText(total + "");
            rs.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
   
   private void addProduct1st(){
       if(!jTextFieldQttAdd.getText().equals("")){
           try{
               theQuery("INSERT INTO sales(Salesman_ID,Cust_Phone,Date) VALUES ("+ID+",'1111',CURRENT_DATE)");
               String sql = "SELECT Sale_ID FROM sales WHERE Cust_Phone = '1111'";
               rs = st.executeQuery(sql);
               while(rs.next()){
                   saleID = Integer.parseInt(rs.getString("Sale_ID"));
               }
               addProduct();
           }catch(Exception ex){
               JOptionPane.showMessageDialog(null, ex.getMessage());
           }
       }
       else{
           jLabelMsg.setText("Add Quantity !");
       }
   }
   
   private void addProduct(){
       if(jTableProduct.getSelectedRow()==-1){
            if (jTableProduct.getSelectedRow()==0){
                jLabelMsg.setText("Table is empty!!");
            }
            else{
                jLabelMsg.setText("You need to select a product");
            }
        }
       else{
           if(!jTextFieldQttAdd.getText().equals("")){
               try{
                   int selectedRowIndex = jTableProduct.getSelectedRow();
                   if(Integer.parseInt(jTableProduct.getModel().getValueAt(selectedRowIndex, 6).toString()) >= Integer.parseInt(jTextFieldQttAdd.getText())){
                       try{
                            theQuery("INSERT INTO sale_info(Sale_ID,Product_ID,Product,Warranty,Price,Quantity,Total) VALUES ("+saleID+",'"+jTableProduct.getModel().getValueAt(selectedRowIndex, 0)+"','"+jTableProduct.getModel().getValueAt(selectedRowIndex, 3)+"','"+jTableProduct.getModel().getValueAt(selectedRowIndex, 4)+"',"+jTableProduct.getModel().getValueAt(selectedRowIndex, 5)+","+jTextFieldQttAdd.getText()+","+(Integer.parseInt(jTableProduct.getModel().getValueAt(selectedRowIndex, 5).toString())*Integer.parseInt(jTextFieldQttAdd.getText()))+")");
                            jLabelMsg.setText("");
                            getSaleInfoTable();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                   }
                   else{
                       jLabelMsg.setText("Insufficient Product.");
                   }
               }catch(Exception ex){
                   JOptionPane.showMessageDialog(null, ex.getMessage());
               }
            }
            else{
                jLabelMsg.setText("Add Quantity !");
            }
       }
   }
   
   private void updateProduct(){
       if(jTableSaleInfo.getSelectedRow()==-1){
            if (jTableSaleInfo.getSelectedRow()==0){
                jLabelMsg.setText("Table is empty!!");
            }
            else{
                jLabelMsg.setText("You need to select a product");
            }
        }
       else{
          if(!jTextFieldQttUp.getText().equals("")){
               try{
                   int selectedRowIndex = jTableSaleInfo.getSelectedRow();
                   int qtt = 0;
                   try{
                       String sql = "select * from product where Product_ID = '"+jTableSaleInfo.getModel().getValueAt(selectedRowIndex, 0)+"'";
                       rs = st.executeQuery(sql);
                       while(rs.next()){
                           qtt = Integer.parseInt(rs.getString("Quantity"));
                       }
                   }catch(Exception ex){
                       
                   }
                   if(qtt >= Integer.parseInt(jTextFieldQttUp.getText())){
                       try{
                            theQuery("UPDATE sale_info SET Quantity ="+jTextFieldQttUp.getText()+",Total="+(Integer.parseInt(jTableSaleInfo.getModel().getValueAt(selectedRowIndex, 3).toString())*Integer.parseInt(jTextFieldQttUp.getText()))+" WHERE Product_ID='"+jTableSaleInfo.getModel().getValueAt(selectedRowIndex, 0)+"'");
                            jLabelMsg.setText("");
                            getSaleInfoTable();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                   }
                   else{
                       jLabelMsg.setText("Insufficient Product.");
                   }
               }catch(Exception ex){
                   JOptionPane.showMessageDialog(null, ex.getMessage());
               }
            }
            else{
                jLabelMsg.setText("Add Quantity !");
            } 
       }
   }
   
   private void deleteProduct(){
       if(jTableSaleInfo.getSelectedRow()==-1){
            if (jTableSaleInfo.getSelectedRow()==0){
                jLabelMsg.setText("Table is empty!!");
            }
            else{
                jLabelMsg.setText("You need to select an item");
            }
        }
        else{
            int del = JOptionPane.showConfirmDialog(null, "Do you really want to delete this item?", "DELETE", JOptionPane.YES_NO_OPTION);
            if(del==0){
                int selectedRowIndex = jTableSaleInfo.getSelectedRow();
                theQuery("delete from sale_info where Product_ID='"+jTableSaleInfo.getModel().getValueAt(selectedRowIndex, 0)+"'");
                JOptionPane.showMessageDialog(null, "Deleted !");
                jLabelMsg.setText("");
                getSaleInfoTable();
            }
        }
   }
   
   private void productCancel(){
       int cancel = JOptionPane.showConfirmDialog(null, "Are you sure?", "Cancel", JOptionPane.YES_NO_OPTION);
       if(cancel==0 && count!=0){
           theQuery("delete from sales where Sale_ID="+saleID);
           theQuery("delete from sale_info where Sale_ID="+saleID);
           dispose();
           new rahul().setVisible(true);
       }
   }
   
   private void searchProduct(){
        DefaultTableModel product = new DefaultTableModel(new String[]{"ID","Category", "Brand", "Name", "Warranty", "Price", "Quantity"}, 0);
        try{
            st = conn.createStatement();
            String sql= null;
            if(jComboBoxCat.getSelectedItem().equals("All") && jComboBoxBrand.getSelectedItem().equals("All")){
                sql = "SELECT * FROM product WHERE Product_Name like '"+jTextFieldSearch.getText()+"%'";
            }
            else if(!jComboBoxCat.getSelectedItem().equals("All") && jComboBoxBrand.getSelectedItem().equals("All")){
                sql = "SELECT * FROM product WHERE Product_Name like '"+jTextFieldSearch.getText()+"%' AND Category = '"+jComboBoxCat.getSelectedItem()+"'";
            }
            else if(jComboBoxCat.getSelectedItem().equals("All") && !jComboBoxBrand.getSelectedItem().equals("All")){
                sql = "SELECT * FROM product WHERE Product_Name like '"+jTextFieldSearch.getText()+"%' AND Brand = '"+jComboBoxBrand.getSelectedItem()+"'";
            }
            else{
                sql = "SELECT * FROM product WHERE Product_Name like '"+jTextFieldSearch.getText()+"%' AND Brand = '"+jComboBoxBrand.getSelectedItem()+"' AND Category = '"+jComboBoxCat.getSelectedItem()+"'";
            }
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                String col1 = rs.getString("Product_ID");
                String col2 = rs.getString("Category");
                String col3 = rs.getString("Brand");                
                String col4 = rs.getString("Product_Name");
                String col5 = rs.getString("Warranty");
                String col6 = rs.getString("Price");
                String col7 = rs.getString("Quantity");
                product.addRow(new Object[]{col1, col2, col3, col4, col5, col6, col7});
            }
            jTableProduct.setModel(product);
            rs.close();
            //st.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
   
   public void getCat(){
        try{
            st = conn.createStatement();
            String sql="SELECT * FROM category_table";
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                jComboBoxCat.addItem(rs.getString("Category"));
            }
            rs.close();
            //st.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
   
   public void getBrand(){
        try{
            st = conn.createStatement();
            String sql="SELECT * FROM brand_table";
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                jComboBoxBrand.addItem(rs.getString("Brand"));
            }
            rs.close();
            //st.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
   
   private void checkOut(){
       if(!jTextFieldCust.equals("") && !jTextFieldPhone.equals("") && !jTextFieldAddress.equals("")){
           int total = Integer.parseInt(jTextFieldTotal.getText()), qtt = 0, exist = 1;
           for(int i =0; i< jTableSaleInfo.getModel().getRowCount(); i++){
                qtt = Integer.parseInt(jTableSaleInfo.getModel().getValueAt(i, 4).toString());
                try{
                    theQuery("UPDATE product SET Quantity = ( Quantity - "+qtt+") WHERE Product_ID = '"+jTableSaleInfo.getModel().getValueAt(i, 0).toString()+"'");
                    theQuery("UPDATE sales SET Cust_Phone = '"+jTextFieldPhone.getText()+"' WHERE Sale_ID = "+saleID);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
           try{
                String sql = "SELECT * FROM customer";
                rs = st.executeQuery(sql);
                while(rs.next()){
                    if(rs.getString("Cust_Phone").equals(jTextFieldPhone.getText())){
                        exist = 0;
                    }
                }
                if(exist == 1){
                    theQuery("INSERT INTO customer(Cust_Phone,Name,Address) VALUES('"+jTextFieldPhone.getText()+"','"+jTextFieldCust.getText()+"','"+jTextFieldAddress.getText()+"')");
                }
           }catch(Exception ex){
               JOptionPane.showMessageDialog(null, ex.getMessage());
           }
           if(!jTextFieldDis.getText().equals("")){
               jTextFieldTotal.setText((total - Integer.parseInt(jTextFieldDis.getText())) + "");
           }
           new BillInfo().setBill(ID,saleID,Integer.parseInt(jTextFieldDis.getText()),Integer.parseInt(jTextFieldTotal.getText()),jTextFieldPhone.getText(),jTextFieldCust.getText());
           dispose();
           new User().setUser(ID);
       }
       else{
           jLabelMsg.setText("Please fill the Customer Name, Phone & Address Field");
       }
   }
   
   private void refresh(){
       getProductTable();
       getSaleInfoTable();
       jLabelMsg.setText("");
       jTextFieldQttAdd.setText("");
       jTextFieldQttUp.setText("");
       jTextFieldSearch.setText("");
       jTextFieldDis.setText("");
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldCust = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldAddress = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProduct = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldQttAdd = new javax.swing.JTextField();
        jButtonAdd = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSaleInfo = new javax.swing.JTable();
        jLabelMsg = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldQttUp = new javax.swing.JTextField();
        jButtonUpdate = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonCheck = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldTotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldDis = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jComboBoxCat = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        jComboBoxBrand = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldPhone = new javax.swing.JTextField();
        jLabelUser = new javax.swing.JLabel();
        jButtonProf = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButtonLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jLabel1.setText("Customer Name:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 160, 39));

        jTextFieldCust.setFont(new java.awt.Font("Century Schoolbook", 1, 16)); // NOI18N
        jTextFieldCust.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel1.add(jTextFieldCust, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 9, 206, 34));

        jLabel3.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jLabel3.setText("Email:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 55, 135, 36));

        jTextFieldAddress.setFont(new java.awt.Font("Century Schoolbook", 1, 16)); // NOI18N
        jTextFieldAddress.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel1.add(jTextFieldAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 55, 398, 36));

        jScrollPane1.setBackground(new java.awt.Color(204, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.black, java.awt.Color.darkGray, java.awt.Color.black, java.awt.Color.lightGray));

        jTableProduct.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jTableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableProduct);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 145, 950, 140));

        jLabel4.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Search");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 97, 77, 40));

        jTextFieldSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel1.add(jTextFieldSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 99, 257, 40));

        jButtonSearch.setBackground(new java.awt.Color(51, 51, 255));
        jButtonSearch.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonSearch.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/Search.png")); // NOI18N
        jButtonSearch.setText("Search");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 97, 146, 40));

        jLabel5.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Quantity");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 291, 91, 44));

        jTextFieldQttAdd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTextFieldQttAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldQttAddKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldQttAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 291, 106, 44));

        jButtonAdd.setBackground(new java.awt.Color(51, 255, 51));
        jButtonAdd.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonAdd.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Add_27831 (1).png")); // NOI18N
        jButtonAdd.setText("Add");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 291, 112, 44));

        jTableSaleInfo.setBackground(new java.awt.Color(204, 255, 255));
        jTableSaleInfo.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jTableSaleInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTableSaleInfo);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 341, 950, 174));

        jLabelMsg.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(jLabelMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 291, 595, 44));

        jLabel6.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jLabel6.setText("Quantity");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, -1, 48));

        jTextFieldQttUp.setFont(new java.awt.Font("Lucida Bright", 0, 15)); // NOI18N
        jTextFieldQttUp.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTextFieldQttUp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldQttUpKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldQttUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 520, 106, 48));

        jButtonUpdate.setBackground(new java.awt.Color(102, 255, 102));
        jButtonUpdate.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonUpdate.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/update icon.png")); // NOI18N
        jButtonUpdate.setText("Update");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 510, 112, 60));

        jButtonDelete.setBackground(new java.awt.Color(255, 0, 0));
        jButtonDelete.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonDelete.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/delete_16x16.gif")); // NOI18N
        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 570, 121, 55));

        jButtonCheck.setBackground(new java.awt.Color(0, 204, 51));
        jButtonCheck.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonCheck.setText("Checkout");
        jButtonCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 570, 122, 55));

        jButtonCancel.setBackground(new java.awt.Color(255, 51, 51));
        jButtonCancel.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonCancel.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/erase-128.png")); // NOI18N
        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 146, 57));

        jLabel7.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jLabel7.setText("Total($)");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 566, 106, 38));

        jTextFieldTotal.setFont(new java.awt.Font("Lucida Bright", 0, 18)); // NOI18N
        jTextFieldTotal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTextFieldTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTotalActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(707, 566, 213, 38));

        jLabel8.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jLabel8.setText("Discount");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 521, 106, 39));

        jTextFieldDis.setFont(new java.awt.Font("Lucida Bright", 0, 18)); // NOI18N
        jTextFieldDis.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTextFieldDis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldDisKeyTyped(evt);
            }
        });
        jPanel1.add(jTextFieldDis, new org.netbeans.lib.awtextra.AbsoluteConstraints(707, 521, 213, 39));

        jComboBoxCat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        jComboBoxCat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jComboBoxCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCatActionPerformed(evt);
            }
        });
        jScrollPane4.setViewportView(jComboBoxCat);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(364, 97, 147, 42));

        jComboBoxBrand.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        jComboBoxBrand.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jScrollPane5.setViewportView(jComboBoxBrand);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(517, 97, 155, 42));

        jButton1.setBackground(new java.awt.Color(153, 153, 255));
        jButton1.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/update icon.png")); // NOI18N
        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 570, 127, 57));

        jLabel2.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jLabel2.setText("Phone Number:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 6, 151, 39));

        jTextFieldPhone.setFont(new java.awt.Font("Century Schoolbook", 1, 16)); // NOI18N
        jTextFieldPhone.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel1.add(jTextFieldPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(571, 7, 229, 39));

        jLabelUser.setFont(new java.awt.Font("Lucida Bright", 1, 29)); // NOI18N
        jLabelUser.setForeground(new java.awt.Color(51, 0, 255));

        jButtonProf.setBackground(new java.awt.Color(255, 102, 255));
        jButtonProf.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonProf.setText("See Profile");
        jButtonProf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProfActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(51, 255, 204));
        jButton2.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Redo_27872 (1).png")); // NOI18N
        jButton2.setText("Change Password");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButtonLogout.setBackground(new java.awt.Color(255, 0, 0));
        jButtonLogout.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonLogout.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Log Out_27856.png")); // NOI18N
        jButtonLogout.setText("Logout");
        jButtonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jButtonProf, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonLogout, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonProf, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(jLabelUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        searchProduct();
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jTextFieldQttAddKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldQttAddKeyTyped
        char c =evt.getKeyChar();
        if(!(Character.isDigit(c) || c==KeyEvent.VK_BACKSPACE || c==KeyEvent.VK_DELETE)){
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldQttAddKeyTyped

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        if(count==0){
            count++;
            addProduct1st();
        }
        else{
            addProduct();
        }
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jTextFieldQttUpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldQttUpKeyTyped
        char c =evt.getKeyChar();
        if(!(Character.isDigit(c) || c==KeyEvent.VK_BACKSPACE || c==KeyEvent.VK_DELETE)){
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldQttUpKeyTyped

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        updateProduct();
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        deleteProduct();
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckActionPerformed
        checkOut();
    }//GEN-LAST:event_jButtonCheckActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        productCancel();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jTextFieldDisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDisKeyTyped
        char c =evt.getKeyChar();
        if(!(Character.isDigit(c) || c==KeyEvent.VK_BACKSPACE || c==KeyEvent.VK_DELETE)){
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldDisKeyTyped

    private void jComboBoxCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxCatActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        refresh();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonProfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProfActionPerformed
       // new UserProfile().setEmp(ID);
        
        UserProfile slr = new UserProfile();
        slr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       // firstForm.setSize(800, 600);
        slr.setLocationRelativeTo(null);
        slr.setEmp(ID);
    }//GEN-LAST:event_jButtonProfActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       // dispose();
      //  new ChangeUserPass().setID(ID);
        ChangeUserPass pwd = new ChangeUserPass();
        pwd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       // firstForm.setSize(800, 600);
        pwd.setLocationRelativeTo(null);
        pwd.setID(ID);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButtonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogoutActionPerformed
        dispose();
        new loginUser().setVisible(true);
    }//GEN-LAST:event_jButtonLogoutActionPerformed

    private void jTextFieldTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTotalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonCheck;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonLogout;
    private javax.swing.JButton jButtonProf;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox<String> jComboBoxBrand;
    private javax.swing.JComboBox<String> jComboBoxCat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelMsg;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTableProduct;
    private javax.swing.JTable jTableSaleInfo;
    private javax.swing.JTextField jTextFieldAddress;
    private javax.swing.JTextField jTextFieldCust;
    private javax.swing.JTextField jTextFieldDis;
    private javax.swing.JTextField jTextFieldPhone;
    private javax.swing.JTextField jTextFieldQttAdd;
    private javax.swing.JTextField jTextFieldQttUp;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JTextField jTextFieldTotal;
    // End of variables declaration//GEN-END:variables
}
