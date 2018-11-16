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
public class Admin extends javax.swing.JFrame {

    /**
     * Creates new form Admin
     */
    public Admin() {
        initComponents();
        getConnection();
        getEmpTable();
        getProductTable();
        getCatTable();
        getBrandTable();
        getSaleTable();
        jTextFieldGTotal.setEditable(false);
        getCustTable();
        
    }
    
    Connection conn = null;
    Statement st = null;
    ResultSet rs;
    public void getConnection(){
        try{
           conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/inpharmacy","root","");
           //JOptionPane.showMessageDialog(null, "Connection Established.");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void showDetails(){
        int selectedRowIndex = jTableEmp.getSelectedRow();
        String id = jTableEmp.getModel().getValueAt(selectedRowIndex, 0).toString();
        new EmpDetails().showDetails(id);
    }
    
    public void updateEmp(){
        int selectedRowIndex = jTableEmp.getSelectedRow();
        String id = jTableEmp.getModel().getValueAt(selectedRowIndex, 0).toString();
        new UpdateEmployee().setEmp(id);
    }
    
    public void updateProduct(){
        int selectedRowIndex = jTableProduct.getSelectedRow();
        String id = jTableProduct.getModel().getValueAt(selectedRowIndex, 0).toString();
        new UpdateProduct().setProduct(id);
    }
    
    
    public void theQuery(String query){
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
            st.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void getEmpTable(){
        DefaultTableModel emp = new DefaultTableModel(new String[]{"ID","Last Name", "First Name", "Department", "Job", "Salary"}, 0);
        try{
            st = conn.createStatement();
            String sql="SELECT * FROM employees";
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                String col1 = rs.getString("ID");
                String col2 = rs.getString("Last_Name");
                String col3 = rs.getString("First_Name");
                String col4 = rs.getString("Department");                
                String col5 = rs.getString("Job");
                String col6 = rs.getString("Salary");
                emp.addRow(new Object[]{col1, col2, col3, col4, col5, col6});
            }
            jTableEmp.setModel(emp);
            rs.close();
            st.close();
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
            st.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void getCatTable(){
        DefaultTableModel cat = new DefaultTableModel(new String[]{"ID","Category"}, 0);
        try{
            st = conn.createStatement();
            String sql="SELECT * FROM category_table";
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                String col1 = rs.getString("Category_ID");
                String col2 = rs.getString("Category");
                cat.addRow(new Object[]{col1, col2});
            }
            jTable1.setModel(cat);
            rs.close();
            st.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void getBrandTable(){
        DefaultTableModel brand = new DefaultTableModel(new String[]{"ID","Brand"}, 0);
        try{
            st = conn.createStatement();
            String sql="SELECT * FROM brand_table";
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                String col1 = rs.getString("Brand_ID");
                String col2 = rs.getString("Brand");
                brand.addRow(new Object[]{col1, col2});
            }
            jTable2.setModel(brand);
            rs.close();
            st.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void getSaleTable(){
        DefaultTableModel sales = new DefaultTableModel(new String[]{"Sale_ID","Salesman_ID","Customer_Phone","Date"}, 0);
        try{
            st = conn.createStatement();
            String sql="SELECT * FROM sales";
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                String col1 = rs.getString("Sale_ID");
                String col2 = rs.getString("Salesman_ID");
                String col3 = rs.getString("Cust_Phone");
                String col4 = rs.getString("Date");
                sales.addRow(new Object[]{col1, col2, col3, col4});
            }
            jTableSaleID.setModel(sales);
            rs.close();
            st.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void getSaleInfoTable(){
        int selectedRow = jTableSaleID.getSelectedRow();
        String id = jTableSaleID.getModel().getValueAt(selectedRow, 0).toString();
        DefaultTableModel saleInfo = new DefaultTableModel(new String[]{"Product","Warranty","Price","Quantity","Total"}, 0);
        int total = 0;
        try{
            st = conn.createStatement();
            String sql="SELECT * FROM sale_info WHERE Sale_ID = "+id;
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                String col1 = rs.getString("Product");
                String col5 = rs.getString("Warranty");
                String col2 = rs.getString("Price");
                String col3 = rs.getString("Quantity");
                String col4 = rs.getString("Total");
                saleInfo.addRow(new Object[]{col1, col5, col2, col3, col4});
            }
            jTableSaleInfo.setModel(saleInfo);
            for(int i =0; i< jTableSaleInfo.getModel().getRowCount(); i++){
                total += Integer.parseInt(jTableSaleInfo.getModel().getValueAt(i, 4).toString());
            }
            jTextFieldGTotal.setText(total + "");
            rs.close();
            st.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void getCustTable(){
        DefaultTableModel cust = new DefaultTableModel(new String[]{"Customer_Phone","Name","Address"}, 0);
        try{
            st = conn.createStatement();
            String sql="SELECT * FROM customer";
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                String col1 = rs.getString("Cust_Phone");
                String col2 = rs.getString("Name");
                String col3 = rs.getString("Address");
                cust.addRow(new Object[]{col1, col2, col3});
            }
            jTableCust.setModel(cust);
            rs.close();
            st.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    private void searchCat(){
        DefaultTableModel cat = new DefaultTableModel(new String[]{"ID","Category"}, 0);
        try{
            st = conn.createStatement();
            String sql="SELECT * FROM category_table WHERE Category = '"+tsearch.getText()+"%'";
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                String col1 = rs.getString("Category_ID");
                String col2 = rs.getString("Category");
                cat.addRow(new Object[]{col1, col2});
            }
            jTable1.setModel(cat);
            rs.close();
            st.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    private void searchBrand(){
        DefaultTableModel brand = new DefaultTableModel(new String[]{"ID","Category"}, 0);
        try{
            st = conn.createStatement();
            String sql="SELECT * FROM brand_table WHERE Brand = '"+tsearchb.getText()+"%'";
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                String col1 = rs.getString("Brand_ID");
                String col2 = rs.getString("Brand");
                brand.addRow(new Object[]{col1, col2});
            }
            jTable1.setModel(brand);
            rs.close();
            st.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    private void addCat(){
        if(!addc.getText().equals("")){
            try{
                theQuery("INSERT INTO category_table(Category) VALUES ('"+addc.getText().toUpperCase()+"')");
                addc.setText("");
                jLabelCatMsg.setText("");
                getCatTable();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        else{
            jLabelCatMsg.setText("Enter Category Name");
        }
    }
    
    private void addBrand(){
        if(!addb1.getText().equals("")){
            try{
                theQuery("INSERT INTO brand_table(Brand) VALUES ('"+addb1.getText().toUpperCase()+"')");
                addb1.setText("");
                jLabelBrMsg.setText("");
                getBrandTable();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        else{
            jLabelCatMsg.setText("Enter Brand Name");
        }
    }
    
    private void updateCat(){
        if(jTable1.getSelectedRow()==-1){
            if (jTable1.getSelectedRow()==0){
                jLabelCatMsg.setText("Table is empty!!");
            }
            else{
                jLabelCatMsg.setText("You need to select a Category");
            }
        }
        else{
            if(!addc.getText().equals("")){
                int selectedRowIndex = jTable1.getSelectedRow();
                theQuery("UPDATE category_table SET Category='"+addc.getText().toUpperCase()+"' WHERE Category_ID = "+jTable1.getModel().getValueAt(selectedRowIndex, 0));
                JOptionPane.showMessageDialog(null, "Updated !");
                jLabelCatMsg.setText("");
                getCatTable();
            }
            else{
                jLabelCatMsg.setText("Enter Category Name");
            }
        }
    }
    
    private void updateBrand(){
        if(jTable2.getSelectedRow()==-1){
            if (jTable2.getSelectedRow()==0){
                jLabelBrMsg.setText("Table is empty!!");
            }
            else{
                jLabelBrMsg.setText("You need to select a Brand");
            }
        }
        else{
            if(!addb1.getText().equals("")){
                int selectedRowIndex = jTable2.getSelectedRow();
                theQuery("UPDATE category_table SET Brand='"+addb1.getText().toUpperCase()+"' WHERE Brand_ID = "+jTable2.getModel().getValueAt(selectedRowIndex, 0));
                JOptionPane.showMessageDialog(null, "Updated !");
                jLabelBrMsg.setText("");
                getBrandTable();
            }
            else{
                jLabelBrMsg.setText("Enter Brand Name");
            }
        }
    }
    
    private void searchEmp(){
        DefaultTableModel emp = new DefaultTableModel(new String[]{"ID","Employee_Name", "Department", "Job", "Salary"}, 0);
        try{
            st = conn.createStatement();
            String sql= null;
            if(jComboBoxSearch.getSelectedItem().equals("Search By : Name")){
                sql = "SELECT * FROM employees WHERE Last_Name like '"+jTextFieldSearch.getText()+"%' or First_Name like '"+jTextFieldSearch.getText()+"%'";
            }
            else if(jComboBoxSearch.getSelectedItem().equals("ID")){
                sql = "SELECT * FROM employees WHERE ID like '"+jTextFieldSearch.getText()+"%'";
            }
            else if(jComboBoxSearch.getSelectedItem().equals("Department")){
                sql = "SELECT * FROM employees WHERE Department like '"+jTextFieldSearch.getText()+"%'";
            }
            else{
                sql = "SELECT * FROM employees WHERE Job like '"+jTextFieldSearch.getText()+"%'";
            }
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                String col1 = rs.getString("ID");
                String col2 = rs.getString("Last_Name");
                String col3 = rs.getString("First_Name");
                String col4 = rs.getString("Department");                
                String col5 = rs.getString("Job");
                String col6 = rs.getString("Salary");
                emp.addRow(new Object[]{col1, col2, col3, col4, col5, col6});
            }
            jTableEmp.setModel(emp);
            rs.close();
            st.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    private void searchProduct(){
        DefaultTableModel product = new DefaultTableModel(new String[]{"ID","Category", "Brand", "Name", "Warranty", "Price", "Quantity"}, 0);
        try{
            st = conn.createStatement();
            String sql= null;
            if(jComboBoxPrSearch.getSelectedItem().equals("Search By : Name")){
                sql = "SELECT * FROM product WHERE Product_Name like '"+jTextFieldPrSearch.getText()+"%'";
            }
            else if(jComboBoxPrSearch.getSelectedItem().equals("Category")){
                sql = "SELECT * FROM product WHERE Category like '"+jTextFieldPrSearch.getText()+"%'";
            }
            else if(jComboBoxPrSearch.getSelectedItem().equals("Brand")){
                sql = "SELECT * FROM product WHERE Brand like '"+jTextFieldPrSearch.getText()+"%'";
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
            st.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    private void searchSale(){
        DefaultTableModel sales = new DefaultTableModel(new String[]{"Sale_ID","Salesman_ID","Customer_Phone","Date"}, 0);
            jTableSaleID.setModel(sales);
        try{
            st = conn.createStatement();
            String sql= null;
            if(jComboBoxSale.getSelectedItem().equals("Sale_ID")){
                sql = "SELECT * FROM sales WHERE Sale_ID like '"+jTextFieldSaleID.getText()+"%'";
            }
            else if(jComboBoxSale.getSelectedItem().equals("Phone Number")){
                sql = "SELECT * FROM sales WHERE Cust_Phone like '"+jTextFieldSaleID.getText()+"%'";
            }
            else if(jComboBoxSale.getSelectedItem().equals("Date")){
                sql = "SELECT * FROM sales WHERE Date = '"+jTextFieldSaleID.getText()+"'";
            }
            else{
                sql = "SELECT * FROM sales WHERE Salesman_ID like '"+jTextFieldSaleID.getText()+"%'";
            }
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                String col1 = rs.getString("Sale_ID");
                String col2 = rs.getString("Salesman_ID");
                String col3 = rs.getString("Cust_Phone");
                String col4 = rs.getString("Date");
                sales.addRow(new Object[]{col1, col2, col3, col4});
            }
            jTableSaleID.setModel(sales);
            rs.close();
            st.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    private void searchCust(){
        DefaultTableModel cust = new DefaultTableModel(new String[]{"Customer_Phone","Name","Address"}, 0);
        try{
            st = conn.createStatement();
            String sql= null;
            if(jComboBoxCust.getSelectedItem().equals("Name")){
                sql = "SELECT * FROM customer WHERE Name like '"+jTextFieldCustSearch.getText()+"%'";
            }
            else{
                sql = "SELECT * FROM customer WHERE Cust_Phone like '"+jTextFieldCustSearch.getText()+"%'";
            }
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                String col1 = rs.getString("Cust_Phone");
                String col2 = rs.getString("Name");
                String col3 = rs.getString("Address");
                cust.addRow(new Object[]{col1, col2, col3});
            }
            jTableCust.setModel(cust);
            rs.close();
            st.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    private void deleteEmp(){
        if(jTableEmp.getSelectedRow()==-1){
            if (jTableEmp.getSelectedRow()==0){
                jLabelMessage.setText("Table is empty!!");
            }
            else{
                jLabelMessage.setText("You need to select an employee");
            }
        }
        else{
            int del = JOptionPane.showConfirmDialog(null, "Do you really want to delete this employee?", "DELETE", JOptionPane.YES_NO_OPTION);
            if(del==0){
                int selectedRowIndex = jTableEmp.getSelectedRow();
                theQuery("delete from employees where ID='"+jTableEmp.getModel().getValueAt(selectedRowIndex, 0)+"'");
                theQuery("delete from login where ID='"+jTableEmp.getModel().getValueAt(selectedRowIndex, 0)+"'");
                JOptionPane.showMessageDialog(null, "Deleted !");
                refreshEmp();
            }
        }
    }
    
    private void deleteProduct(){
        if(jTableProduct.getSelectedRow()==-1){
            if (jTableProduct.getSelectedRow()==0){
                jLabelMessage.setText("Table is empty!!");
            }
            else{
                jLabelMessage.setText("You need to select a product");
            }
        }
        else{
            int del = JOptionPane.showConfirmDialog(null, "Do you really want to delete this product?", "DELETE", JOptionPane.YES_NO_OPTION);
            if(del==0){
                int selectedRowIndex = jTableProduct.getSelectedRow();
                theQuery("delete from Product where Product_ID='"+jTableProduct.getModel().getValueAt(selectedRowIndex, 0)+"'");
                JOptionPane.showMessageDialog(null, "Deleted !");
                refreshProduct();
            }
        }
    }
    
    private void deleteCat(){
        if(jTable1.getSelectedRow()==-1){
            if (jTable1.getSelectedRow()==0){
                jLabelCatMsg.setText("Table is empty!!");
            }
            else{
                jLabelCatMsg.setText("You need to select a Category");
            }
        }
        else{
            int del = JOptionPane.showConfirmDialog(null, "Do you really want to delete this category?", "DELETE", JOptionPane.YES_NO_OPTION);
            if(del==0){
                int selectedRowIndex = jTable1.getSelectedRow();
                theQuery("delete from category_table where Category_ID="+jTable1.getModel().getValueAt(selectedRowIndex, 0));
                JOptionPane.showMessageDialog(null, "Deleted !");
                jLabelCatMsg.setText("");
                getCatTable();
            }
        }
    }
    
    private void deleteBrand(){
        if(jTable2.getSelectedRow()==-1){
            if (jTable2.getSelectedRow()==0){
                jLabelBrMsg.setText("Table is empty!!");
            }
            else{
                jLabelBrMsg.setText("You need to select a Brand");
            }
        }
        else{
            int del = JOptionPane.showConfirmDialog(null, "Do you really want to delete this brand?", "DELETE", JOptionPane.YES_NO_OPTION);
            if(del==0){
                int selectedRowIndex = jTable2.getSelectedRow();
                theQuery("delete from brand_table where Brand_ID="+jTable2.getModel().getValueAt(selectedRowIndex, 0));
                JOptionPane.showMessageDialog(null, "Deleted !");
                jLabelBrMsg.setText("");
                getBrandTable();
            }
        }
    }
    
    private void deleteCust(){
        if(jTableCust.getSelectedRow()==-1){
            if (jTableCust.getSelectedRow()==0){
                jLabelCustMsg.setText("Table is empty!!");
            }
            else{
                jLabelCustMsg.setText("You need to select a customer.");
            }
        }
        else{
            int del = JOptionPane.showConfirmDialog(null, "Do you really want to delete this customer?", "DELETE", JOptionPane.YES_NO_OPTION);
            if(del==0){
                int selectedRowIndex = jTableCust.getSelectedRow();
                theQuery("delete from customer where Cust_Phone="+jTableCust.getModel().getValueAt(selectedRowIndex, 0));
                JOptionPane.showMessageDialog(null, "Deleted !");
                jLabelCustMsg.setText("");
                getCustTable();
            }
        }
    }
    
    private void deleteSaleInfo(){
        if(jTableSaleID.getSelectedRow()==-1){
            if (jTableSaleID.getSelectedRow()==0){
                jLabelSaleMsg.setText("Table is empty!!");
            }
            else{
                jLabelSaleMsg.setText("You need to select an item");
            }
        }
        else{
            int del = JOptionPane.showConfirmDialog(null, "Do you really want to delete this item?", "DELETE", JOptionPane.YES_NO_OPTION);
            if(del==0){
                int selectedRowIndex = jTableSaleID.getSelectedRow();
                theQuery("delete from sales where Sale_ID="+jTableSaleID.getModel().getValueAt(selectedRowIndex, 0));
                theQuery("delete from sale_info where Sale_ID="+jTableSaleID.getModel().getValueAt(selectedRowIndex, 0));
                JOptionPane.showMessageDialog(null, "Deleted !");
                jLabelSaleMsg.setText("");
                getSaleTable();
            }
        }
    }
    
    private void saleInfo(){
        if(jTableSaleID.getSelectedRow()==-1){
            if(jTableSaleID.getSelectedRow()==0){
                jLabelSaleMsg.setText("Table is empty!!");
            }
            else{
                jLabelSaleMsg.setText("You need to select an item.");
            }
        }
        else{
            getSaleInfoTable();
        }
    }
    
    private void refreshEmp(){
        jTextFieldSearch.setText("");
        jLabelMessage.setText("");
        getEmpTable();
    }
    
    private void refreshProduct(){
        jTextFieldPrSearch.setText("");
        jLabelPrMsg.setText("");
        jComboBoxPrSearch.setSelectedItem("Search BY : Name");
        getProductTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Employee = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldSaleID = new javax.swing.JTextField();
        jComboBoxSale = new javax.swing.JComboBox<>();
        jButtonSale = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableSaleID = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabelSaleMsg = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableSaleInfo = new javax.swing.JTable();
        jTextFieldGTotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldCustSearch = new javax.swing.JTextField();
        jComboBoxCust = new javax.swing.JComboBox<>();
        jButtonCustSearch = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableCust = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jLabelCustMsg = new javax.swing.JLabel();
        jLabelCustMsg1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        Product = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tsearchb = new javax.swing.JTextField();
        searchb = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        deleteb = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        addb1 = new javax.swing.JTextField();
        addb = new javax.swing.JButton();
        updateb = new javax.swing.JButton();
        jLabelBrMsg = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tsearch = new javax.swing.JTextField();
        search = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        delete = new javax.swing.JButton();
        addc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        update = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabelCatMsg = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldPrSearch = new javax.swing.JTextField();
        jComboBoxPrSearch = new javax.swing.JComboBox<>();
        jButtonPrSearch = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableProduct = new javax.swing.JTable();
        jButtonAddPr = new javax.swing.JButton();
        jButtonUpdatePr = new javax.swing.JButton();
        jButtonDeletePr = new javax.swing.JButton();
        jButtonRefreshPr = new javax.swing.JButton();
        jLabelPrMsg = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jComboBoxSearch = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableEmp = new javax.swing.JTable();
        jButtonAdd = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonDetails = new javax.swing.JButton();
        jButtonRefresh = new javax.swing.JButton();
        jLabelMessage = new javax.swing.JLabel();
        jLabelMessage1 = new javax.swing.JLabel();
        jLabelMessage2 = new javax.swing.JLabel();
        cpwd = new javax.swing.JButton();
        logout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Panel");

        Employee.setBackground(new java.awt.Color(204, 255, 255));
        Employee.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        Employee.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        Employee.setToolTipText("");
        Employee.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel8.setBackground(new java.awt.Color(204, 255, 255));

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 1, 17)); // NOI18N
        jLabel7.setText("Search");

        jTextFieldSaleID.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        jTextFieldSaleID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTextFieldSaleID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSaleIDActionPerformed(evt);
            }
        });

        jComboBoxSale.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        jComboBoxSale.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sale ID", "Phone Number", "Date", "Salesman" }));
        jComboBoxSale.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jButtonSale.setBackground(new java.awt.Color(204, 255, 204));
        jButtonSale.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        jButtonSale.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/Search.png")); // NOI18N
        jButtonSale.setText("Search");
        jButtonSale.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaleActionPerformed(evt);
            }
        });

        jTableSaleID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTableSaleID.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jTableSaleID.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(jTableSaleID);

        jButton2.setBackground(new java.awt.Color(153, 255, 102));
        jButton2.setFont(new java.awt.Font("Lucida Grande", 1, 17)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/attach.png")); // NOI18N
        jButton2.setText("Details");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 51, 51));
        jButton3.setFont(new java.awt.Font("Lucida Grande", 1, 17)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/delete_16x16.gif")); // NOI18N
        jButton3.setText("Delete");
        jButton3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabelSaleMsg.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        jLabelSaleMsg.setForeground(new java.awt.Color(255, 0, 0));

        jTableSaleInfo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTableSaleInfo.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jTableSaleInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(jTableSaleInfo);

        jTextFieldGTotal.setFont(new java.awt.Font("Lucida Grande", 1, 15)); // NOI18N
        jTextFieldGTotal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel9.setFont(new java.awt.Font("Telugu MN", 1, 17)); // NOI18N
        jLabel9.setText("Grand Total");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldSaleID, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxSale, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButtonSale, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jTextFieldGTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(62, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSaleMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSaleID, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxSale, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSale, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldGTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSaleMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Sale History", jPanel8);

        jPanel9.setBackground(new java.awt.Color(204, 255, 255));

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 1, 17)); // NOI18N
        jLabel8.setText("Search");

        jTextFieldCustSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTextFieldCustSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCustSearchActionPerformed(evt);
            }
        });

        jComboBoxCust.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jComboBoxCust.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Phone" }));
        jComboBoxCust.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jButtonCustSearch.setBackground(new java.awt.Color(204, 255, 204));
        jButtonCustSearch.setFont(new java.awt.Font("Telugu MN", 1, 17)); // NOI18N
        jButtonCustSearch.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/Search.png")); // NOI18N
        jButtonCustSearch.setText("Search");
        jButtonCustSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonCustSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCustSearchActionPerformed(evt);
            }
        });

        jTableCust.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTableCust.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jTableCust.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane7.setViewportView(jTableCust);

        jButton5.setBackground(new java.awt.Color(255, 51, 51));
        jButton5.setFont(new java.awt.Font("Lucida Grande", 1, 19)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/delete_16x16.gif")); // NOI18N
        jButton5.setText("Delete Customer");
        jButton5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCustSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxCust, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCustSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(319, 319, 319)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1023, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelCustMsg1, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(274, 274, 274)
                    .addComponent(jLabelCustMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(275, 275, 275)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonCustSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jComboBoxCust)
                    .addComponent(jTextFieldCustSearch)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCustMsg1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(267, 267, 267)
                    .addComponent(jLabelCustMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(267, Short.MAX_VALUE)))
        );

        jTabbedPane3.addTab("Customer", jPanel9);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1081, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Employee.addTab("Sales", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
        );

        Employee.addTab("", jPanel1);

        Product.setBackground(new java.awt.Color(204, 255, 255));

        jPanel5.setBackground(new java.awt.Color(204, 255, 204));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel4.setText("Search");

        tsearchb.setFont(new java.awt.Font("Lucida Grande", 1, 15)); // NOI18N
        tsearchb.setToolTipText("Type Name OF product");

        searchb.setBackground(new java.awt.Color(153, 204, 255));
        searchb.setFont(new java.awt.Font("Lucida Grande", 1, 17)); // NOI18N
        searchb.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/Search.png")); // NOI18N
        searchb.setText("Search");
        searchb.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jTable2.setBackground(new java.awt.Color(204, 204, 255));
        jTable2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTable2.setFont(new java.awt.Font("Lucida Grande", 1, 15)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.setToolTipText("");
        jScrollPane2.setViewportView(jTable2);

        deleteb.setBackground(new java.awt.Color(255, 51, 51));
        deleteb.setFont(new java.awt.Font("Lucida Grande", 1, 17)); // NOI18N
        deleteb.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/delete_16x16.gif")); // NOI18N
        deleteb.setText("Delete Item");
        deleteb.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel5.setText("Brand: ");

        addb1.setFont(new java.awt.Font("Lucida Grande", 1, 15)); // NOI18N
        addb1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        addb.setBackground(new java.awt.Color(153, 255, 153));
        addb.setFont(new java.awt.Font("Tamil MN", 1, 17)); // NOI18N
        addb.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/Save-icon.png")); // NOI18N
        addb.setText("ADD  ITEM");
        addb.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        updateb.setBackground(new java.awt.Color(255, 255, 153));
        updateb.setFont(new java.awt.Font("Tamil MN", 1, 17)); // NOI18N
        updateb.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/update icon.png")); // NOI18N
        updateb.setText("UPDATE  ITEM");
        updateb.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabelBrMsg.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tsearchb, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchb, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(deleteb, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(addb, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(updateb, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabelBrMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(addb1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(96, 96, 96))))))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addb1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addb, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateb, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addComponent(jLabelBrMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tsearchb, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchb, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addComponent(deleteb, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        Product.addTab("Brand", jPanel5);

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel1.setText("Search");

        tsearch.setFont(new java.awt.Font("Lucida Grande", 1, 15)); // NOI18N
        tsearch.setToolTipText("Type Name OF product");

        search.setBackground(new java.awt.Color(153, 204, 255));
        search.setFont(new java.awt.Font("Lucida Grande", 1, 17)); // NOI18N
        search.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/Search.png")); // NOI18N
        search.setText("Search");
        search.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jTable1.setBackground(new java.awt.Color(204, 204, 255));
        jTable1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTable1.setFont(new java.awt.Font("Lucida Grande", 1, 15)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setToolTipText("");
        jScrollPane1.setViewportView(jTable1);

        delete.setBackground(new java.awt.Color(255, 51, 51));
        delete.setFont(new java.awt.Font("Lucida Grande", 1, 17)); // NOI18N
        delete.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/delete_16x16.gif")); // NOI18N
        delete.setText("Delete Item");
        delete.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        addc.setFont(new java.awt.Font("Lucida Grande", 1, 15)); // NOI18N
        addc.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel2.setText("Category: ");

        add.setBackground(new java.awt.Color(153, 255, 153));
        add.setFont(new java.awt.Font("Tamil MN", 1, 17)); // NOI18N
        add.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/Save-icon.png")); // NOI18N
        add.setText("ADD  ITEM");
        add.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        update.setBackground(new java.awt.Color(255, 255, 153));
        update.setFont(new java.awt.Font("Tamil MN", 1, 17)); // NOI18N
        update.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/update icon.png")); // NOI18N
        update.setText("UPDATE  ITEM");
        update.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 16)); // NOI18N
        jLabel3.setText("About Us");

        jLabelCatMsg.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(addc, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 80, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelCatMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36))))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabelCatMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        Product.addTab("Categories", jPanel4);

        jPanel6.setBackground(new java.awt.Color(204, 255, 204));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel6.setFont(new java.awt.Font("Tamil MN", 1, 17)); // NOI18N
        jLabel6.setText("Search");

        jTextFieldPrSearch.setFont(new java.awt.Font("Lucida Grande", 1, 15)); // NOI18N
        jTextFieldPrSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTextFieldPrSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPrSearchActionPerformed(evt);
            }
        });

        jComboBoxPrSearch.setFont(new java.awt.Font("Tamil MN", 1, 15)); // NOI18N
        jComboBoxPrSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Search By : Name", "Category", "Brand" }));
        jComboBoxPrSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jComboBoxPrSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPrSearchActionPerformed(evt);
            }
        });

        jButtonPrSearch.setFont(new java.awt.Font("Telugu MN", 1, 17)); // NOI18N
        jButtonPrSearch.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/Search.png")); // NOI18N
        jButtonPrSearch.setText("Search");
        jButtonPrSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonPrSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrSearchActionPerformed(evt);
            }
        });

        jTableProduct.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTableProduct.setFont(new java.awt.Font("Telugu MN", 1, 14)); // NOI18N
        jTableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTableProduct);

        jButtonAddPr.setBackground(new java.awt.Color(102, 255, 102));
        jButtonAddPr.setFont(new java.awt.Font("Telugu MN", 1, 16)); // NOI18N
        jButtonAddPr.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/Save-icon.png")); // NOI18N
        jButtonAddPr.setText("Add New Product");
        jButtonAddPr.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonAddPr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddPrActionPerformed(evt);
            }
        });

        jButtonUpdatePr.setBackground(new java.awt.Color(255, 255, 153));
        jButtonUpdatePr.setFont(new java.awt.Font("Telugu MN", 1, 16)); // NOI18N
        jButtonUpdatePr.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/update icon.png")); // NOI18N
        jButtonUpdatePr.setText("Update Existing Product");
        jButtonUpdatePr.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonUpdatePr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdatePrActionPerformed(evt);
            }
        });

        jButtonDeletePr.setBackground(new java.awt.Color(255, 102, 102));
        jButtonDeletePr.setFont(new java.awt.Font("Telugu MN", 1, 16)); // NOI18N
        jButtonDeletePr.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/delete_16x16.gif")); // NOI18N
        jButtonDeletePr.setText("Delete Product");
        jButtonDeletePr.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonDeletePr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeletePrActionPerformed(evt);
            }
        });

        jButtonRefreshPr.setBackground(new java.awt.Color(102, 153, 255));
        jButtonRefreshPr.setFont(new java.awt.Font("Telugu MN", 1, 16)); // NOI18N
        jButtonRefreshPr.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/update icon.png")); // NOI18N
        jButtonRefreshPr.setText("Refresh");
        jButtonRefreshPr.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonRefreshPr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshPrActionPerformed(evt);
            }
        });

        jLabelPrMsg.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldPrSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxPrSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jButtonAddPr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonUpdatePr, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonDeletePr, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonPrSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonRefreshPr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 984, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jLabelPrMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPrSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxPrSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jButtonPrSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonAddPr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonUpdatePr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDeletePr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRefreshPr, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jLabelPrMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Product.addTab("Product", jPanel6);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Product, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Product)
                .addContainerGap())
        );

        Employee.addTab("Inventory", jPanel2);

        jPanel7.setBackground(new java.awt.Color(204, 204, 255));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel10.setText("Search");

        jTextFieldSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTextFieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchActionPerformed(evt);
            }
        });

        jComboBoxSearch.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jComboBoxSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Search By : Name", "ID", "Department", "Job" }));
        jComboBoxSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jComboBoxSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSearchActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/Search.png")); // NOI18N
        jButton7.setText("Search");
        jButton7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jTableEmp.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTableEmp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTableEmp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(jTableEmp);

        jButtonAdd.setBackground(new java.awt.Color(153, 255, 153));
        jButtonAdd.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButtonAdd.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/Save-icon.png")); // NOI18N
        jButtonAdd.setText("Add New Employee");
        jButtonAdd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonUpdate.setBackground(new java.awt.Color(255, 204, 153));
        jButtonUpdate.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButtonUpdate.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/update icon.png")); // NOI18N
        jButtonUpdate.setText("Update Existing Employee");
        jButtonUpdate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonDelete.setBackground(new java.awt.Color(255, 102, 102));
        jButtonDelete.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButtonDelete.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/delete_16x16.gif")); // NOI18N
        jButtonDelete.setText("Delete Employee");
        jButtonDelete.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonDetails.setBackground(new java.awt.Color(255, 153, 255));
        jButtonDetails.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButtonDetails.setText("Show Details");
        jButtonDetails.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDetailsActionPerformed(evt);
            }
        });

        jButtonRefresh.setBackground(new java.awt.Color(153, 153, 255));
        jButtonRefresh.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButtonRefresh.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/Update.png")); // NOI18N
        jButtonRefresh.setText("Refresh");
        jButtonRefresh.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });

        jLabelMessage.setForeground(new java.awt.Color(255, 0, 0));

        jLabelMessage1.setForeground(new java.awt.Color(255, 0, 0));

        jLabelMessage2.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jComboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(97, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabelMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelMessage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(33, 33, 33))))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(207, 207, 207)
                    .addComponent(jLabelMessage2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(168, 168, 168)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabelMessage1, javax.swing.GroupLayout.DEFAULT_SIZE, 12, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 8, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(307, 307, 307)
                    .addComponent(jLabelMessage2, javax.swing.GroupLayout.DEFAULT_SIZE, 12, Short.MAX_VALUE)
                    .addGap(268, 268, 268)))
        );

        Employee.addTab("Employee", jPanel7);

        cpwd.setBackground(new java.awt.Color(204, 0, 255));
        cpwd.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        cpwd.setText("Change Password");
        cpwd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        logout.setBackground(new java.awt.Color(255, 51, 51));
        logout.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        logout.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/NetBeansProjects/Student Information System/src/student/information/system/images/logout.png")); // NOI18N
        logout.setText("Logout");
        logout.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cpwd, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Employee, javax.swing.GroupLayout.PREFERRED_SIZE, 1119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cpwd, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Employee, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1131, 723));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldPrSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPrSearchActionPerformed
        searchProduct();
    }//GEN-LAST:event_jTextFieldPrSearchActionPerformed

    private void jComboBoxPrSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPrSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxPrSearchActionPerformed

    private void jButtonPrSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrSearchActionPerformed
        searchProduct();
    }//GEN-LAST:event_jButtonPrSearchActionPerformed

    private void jButtonAddPrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddPrActionPerformed
        new Product().setVisible(true);
    }//GEN-LAST:event_jButtonAddPrActionPerformed

    private void jButtonUpdatePrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdatePrActionPerformed
        if(jTableProduct.getSelectedRow()==-1){
            if(jTableEmp.getSelectedRow()==0){
                jLabelPrMsg.setText("Table is empty!!");
            }
            else{
                jLabelPrMsg.setText("You need to select a Product");
            }
        }
        else{
            updateProduct();
        }
    }//GEN-LAST:event_jButtonUpdatePrActionPerformed

    private void jButtonDeletePrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeletePrActionPerformed
        deleteProduct();
    }//GEN-LAST:event_jButtonDeletePrActionPerformed

    private void jButtonRefreshPrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshPrActionPerformed
        refreshProduct();
    }//GEN-LAST:event_jButtonRefreshPrActionPerformed

    private void jTextFieldSaleIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSaleIDActionPerformed
        searchSale();
    }//GEN-LAST:event_jTextFieldSaleIDActionPerformed

    private void jButtonSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaleActionPerformed
        searchSale();
    }//GEN-LAST:event_jButtonSaleActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        saleInfo();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        deleteSaleInfo();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextFieldCustSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCustSearchActionPerformed
        searchCust();
    }//GEN-LAST:event_jTextFieldCustSearchActionPerformed

    private void jButtonCustSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCustSearchActionPerformed
        searchCust();
    }//GEN-LAST:event_jButtonCustSearchActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        deleteCust();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchActionPerformed
        searchEmp();
    }//GEN-LAST:event_jTextFieldSearchActionPerformed

    private void jComboBoxSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSearchActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        searchEmp();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        new AddEmployee().setVisible(true);
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        if(jTableEmp.getSelectedRow()==-1){
            if(jTableEmp.getSelectedRow()==0){
                jLabelMessage.setText("Table is empty!!");
            }
            else{
                jLabelMessage.setText("You need to select an Employee");
            }
        }
        else{
            updateEmp();
        }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        deleteEmp();
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDetailsActionPerformed
        if(jTableEmp.getSelectedRow()==-1){
            if(jTableEmp.getSelectedRow()==0){
                jLabelMessage.setText("Table is empty!!");
            }
            else{
                jLabelMessage.setText("You need to select an Employee");
            }
        }
        else{
            showDetails();
        }
    }//GEN-LAST:event_jButtonDetailsActionPerformed

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        refreshEmp();
    }//GEN-LAST:event_jButtonRefreshActionPerformed

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
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Employee;
    private javax.swing.JTabbedPane Product;
    private javax.swing.JButton add;
    private javax.swing.JButton addb;
    private javax.swing.JTextField addb1;
    private javax.swing.JTextField addc;
    private javax.swing.JButton cpwd;
    private javax.swing.JButton delete;
    private javax.swing.JButton deleteb;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAddPr;
    private javax.swing.JButton jButtonCustSearch;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonDeletePr;
    private javax.swing.JButton jButtonDetails;
    private javax.swing.JButton jButtonPrSearch;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonRefreshPr;
    private javax.swing.JButton jButtonSale;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JButton jButtonUpdatePr;
    private javax.swing.JComboBox<String> jComboBoxCust;
    private javax.swing.JComboBox<String> jComboBoxPrSearch;
    private javax.swing.JComboBox<String> jComboBoxSale;
    private javax.swing.JComboBox<String> jComboBoxSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelBrMsg;
    private javax.swing.JLabel jLabelCatMsg;
    private javax.swing.JLabel jLabelCustMsg;
    private javax.swing.JLabel jLabelCustMsg1;
    private javax.swing.JLabel jLabelMessage;
    private javax.swing.JLabel jLabelMessage1;
    private javax.swing.JLabel jLabelMessage2;
    private javax.swing.JLabel jLabelPrMsg;
    private javax.swing.JLabel jLabelSaleMsg;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTableCust;
    public javax.swing.JTable jTableEmp;
    private javax.swing.JTable jTableProduct;
    private javax.swing.JTable jTableSaleID;
    private javax.swing.JTable jTableSaleInfo;
    private javax.swing.JTextField jTextFieldCustSearch;
    private javax.swing.JTextField jTextFieldGTotal;
    private javax.swing.JTextField jTextFieldPrSearch;
    private javax.swing.JTextField jTextFieldSaleID;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JButton logout;
    private javax.swing.JButton search;
    private javax.swing.JButton searchb;
    private javax.swing.JTextField tsearch;
    private javax.swing.JTextField tsearchb;
    private javax.swing.JButton update;
    private javax.swing.JButton updateb;
    // End of variables declaration//GEN-END:variables
}
