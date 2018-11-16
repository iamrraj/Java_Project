/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADMIN;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import user.UserProfile;

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
        getUser();
        jTextFieldGTotal.setEditable(false);
        getCustTable();
    }

    Connection conn = null;
    Statement st = null;
    ResultSet rs;

    public void getConnection() {
        try {
           // conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/raj", "root", "");
           conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/raj","root","");
            //JOptionPane.showMessageDialog(null, "Connection Established.");
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void showDetails() {
        int selectedRowIndex = jTableEmp.getSelectedRow();
        String id = jTableEmp.getModel().getValueAt(selectedRowIndex, 0).toString();
        
        EmpDetails em= new EmpDetails();
        em.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // firstForm.setSize(800, 600);
        em.setLocationRelativeTo(null);
        em.showDetails(id);
    }

    public void updateEmp() {
        int selectedRowIndex = jTableEmp.getSelectedRow();
        String id = jTableEmp.getModel().getValueAt(selectedRowIndex, 0).toString();
        // new UpdateEmployee().setEmp(id);
        UpdateEmployee slr11 = new UpdateEmployee();
        slr11.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // firstForm.setSize(800, 600);
        slr11.setLocationRelativeTo(null);
        slr11.setEmp(id);

    }

    public void updateProduct() {
        int selectedRowIndex = jTableProduct.getSelectedRow();
        String id = jTableProduct.getModel().getValueAt(selectedRowIndex, 0).toString();
        //new UpdateProduct().setProduct(id);
        UpdateProduct slr1 = new UpdateProduct();
        slr1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // firstForm.setSize(800, 600);
        slr1.setLocationRelativeTo(null);
        slr1.setProduct(id);
    }

    public void theQuery(String query) {
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
            st.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void getEmpTable() {
        DefaultTableModel emp = new DefaultTableModel(new String[]{"ID", "First Name", "Salary"}, 0);
        try {
            st = conn.createStatement();
            String sql = "SELECT * FROM employees";
            rs = st.executeQuery(sql);
            while (rs.next()) {
               String col1 = rs.getString("ID");
                String col3 = rs.getString("First_Name");
                String col6 = rs.getString("Salary");
                emp.addRow(new Object[]{col1, col3, col6});
            }
            jTableEmp.setModel(emp);
            rs.close();
            st.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void getUser() {
        DefaultTableModel usr = new DefaultTableModel(new String[]{"ID", "Username", "Password"}, 0);
        try {
            st = conn.createStatement();
            String sql = "SELECT * FROM login";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String col1 = rs.getString("ID");
                String col2 = rs.getString("User");
                String col3 = rs.getString("Pass");

                usr.addRow(new Object[]{col1, col2, col3});
            }
            userlist.setModel(usr);
            rs.close();
            st.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void getProductTable() {
        DefaultTableModel product = new DefaultTableModel(new String[]{"ID", "Category", "Brand", "Name", "Warranty", "Price()", "Quantity"}, 0);
        try {
            st = conn.createStatement();
            String sql = "SELECT * FROM product";
            rs = st.executeQuery(sql);
            while (rs.next()) {
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
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void getCatTable() {
        DefaultTableModel cat = new DefaultTableModel(new String[]{"ID", "Category"}, 0);
        try {
            st = conn.createStatement();
            String sql = "SELECT * FROM category_table";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String col1 = rs.getString("Category_ID");
                String col2 = rs.getString("Category");
                cat.addRow(new Object[]{col1, col2});
            }
            jTableCat.setModel(cat);
            rs.close();
            st.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void getBrandTable() {
        DefaultTableModel brand = new DefaultTableModel(new String[]{"ID", "Brand"}, 0);
        try {
            st = conn.createStatement();
            String sql = "SELECT * FROM brand_table";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String col1 = rs.getString("Brand_ID");
                String col2 = rs.getString("Brand");
                brand.addRow(new Object[]{col1, col2});
            }
            jTableBrand.setModel(brand);
            rs.close();
            st.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void getSaleTable() {
        DefaultTableModel sales = new DefaultTableModel(new String[]{"Sale_ID", "Salesman_ID", "Customer_Phone", "Date"}, 0);
        try {
            st = conn.createStatement();
            String sql = "SELECT * FROM sales";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String col1 = rs.getString("Sale_ID");
                String col2 = rs.getString("Salesman_ID");
                String col3 = rs.getString("Cust_Phone");
                String col4 = rs.getString("Date");
                sales.addRow(new Object[]{col1, col2, col3, col4});
            }
            jTableSaleID.setModel(sales);
            rs.close();
            st.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void getSaleInfoTable() {
        int selectedRow = jTableSaleID.getSelectedRow();
        String id = jTableSaleID.getModel().getValueAt(selectedRow, 0).toString();
        DefaultTableModel saleInfo = new DefaultTableModel(new String[]{"Product", "Warranty", "Price", "Quantity", "Total"}, 0);
        int total = 0;
        try {
            st = conn.createStatement();
            String sql = "SELECT * FROM sale_info WHERE Sale_ID = " + id;
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String col1 = rs.getString("Product");
                String col5 = rs.getString("Warranty");
                String col2 = rs.getString("Price");
                String col3 = rs.getString("Quantity");
                String col4 = rs.getString("Total");
                saleInfo.addRow(new Object[]{col1, col5, col2, col3, col4});
            }
            jTableSaleInfo.setModel(saleInfo);
            for (int i = 0; i < jTableSaleInfo.getModel().getRowCount(); i++) {
                total += Integer.parseInt(jTableSaleInfo.getModel().getValueAt(i, 4).toString());
            }
            jTextFieldGTotal.setText(total + "");
            rs.close();
            st.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void getCustTable() {
        DefaultTableModel cust = new DefaultTableModel(new String[]{"Customer_Phone", "Name", "Address"}, 0);
        try {
            st = conn.createStatement();
            String sql = "SELECT * FROM customer";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String col1 = rs.getString("Cust_Phone");
                String col2 = rs.getString("Name");
                String col3 = rs.getString("Address");
                cust.addRow(new Object[]{col1, col2, col3});
            }
            jTableCust.setModel(cust);
            rs.close();
            st.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void searchCat() {
        DefaultTableModel cat = new DefaultTableModel(new String[]{"ID", "Category"}, 0);
        try {
            st = conn.createStatement();
            String sql = "SELECT * FROM category_table WHERE Category = '" + jTextFieldCatSearch.getText() + "%'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String col1 = rs.getString("Category_ID");
                String col2 = rs.getString("Category");
                cat.addRow(new Object[]{col1, col2});
            }
            jTableCat.setModel(cat);
            rs.close();
            st.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void searchBrand() {
        DefaultTableModel brand = new DefaultTableModel(new String[]{"ID", "Category"}, 0);
        try {
            st = conn.createStatement();
            String sql = "SELECT * FROM brand_table WHERE Brand = '" + jTextFieldBrSearch.getText() + "%'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String col1 = rs.getString("Brand_ID");
                String col2 = rs.getString("Brand");
                brand.addRow(new Object[]{col1, col2});
            }
            jTableBrand.setModel(brand);
            rs.close();
            st.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void addCat() {
        if (!jTextFieldAddCat.getText().equals("")) {
            try {
                theQuery("INSERT INTO category_table(Category) VALUES ('" + jTextFieldAddCat.getText().toUpperCase() + "')");
                jTextFieldAddCat.setText("");
                jLabelCatMsg.setText("");
                getCatTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            jLabelCatMsg.setText("Enter Category Name");
        }
    }

    private void addBrand() {
        if (!jTextFieldAddBr.getText().equals("")) {
            try {
                theQuery("INSERT INTO brand_table(Brand) VALUES ('" + jTextFieldAddBr.getText().toUpperCase() + "')");
                jTextFieldAddBr.setText("");
                jLabelBrMsg.setText("");
                getBrandTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            jLabelCatMsg.setText("Enter Brand Name");
        }
    }

    private void updateCat() {
        if (jTableCat.getSelectedRow() == -1) {
            if (jTableCat.getSelectedRow() == 0) {
                jLabelCatMsg.setText("Table is empty!!");
            } else {
                jLabelCatMsg.setText("You need to select a Category");
            }
        } else {
            if (!jTextFieldAddCat.getText().equals("")) {
                int selectedRowIndex = jTableCat.getSelectedRow();
                theQuery("UPDATE category_table SET Category='" + jTextFieldAddCat.getText().toUpperCase() + "' WHERE Category_ID = " + jTableCat.getModel().getValueAt(selectedRowIndex, 0));
                JOptionPane.showMessageDialog(null, "Updated !");
                jLabelCatMsg.setText("");
                getCatTable();
            } else {
                jLabelCatMsg.setText("Enter Category Name");
            }
        }
    }

    private void updateBrand() {
        if (jTableBrand.getSelectedRow() == -1) {
            if (jTableBrand.getSelectedRow() == 0) {
                jLabelBrMsg.setText("Table is empty!!");
            } else {
                jLabelBrMsg.setText("You need to select a Brand");
            }
        } else {
            if (!jTextFieldAddBr.getText().equals("")) {
                int selectedRowIndex = jTableBrand.getSelectedRow();
                theQuery("UPDATE brand_table SET Brand='" + jTextFieldAddBr.getText().toUpperCase() + "' WHERE Brand_ID = " + jTableBrand.getModel().getValueAt(selectedRowIndex, 0));
                JOptionPane.showMessageDialog(null, "Updated !");
                jLabelBrMsg.setText("");
                getBrandTable();
            } else {
                jLabelBrMsg.setText("Enter Brand Name");
            }
        }
    }

    private void searchEmp() {
        DefaultTableModel emp = new DefaultTableModel(new String[]{"ID", "First Name", "Salary"}, 0);
        try {
            st = conn.createStatement();
            String sql = null;
            if (jComboBoxSearch.getSelectedItem().equals("Search By : Name")) {
                sql = "SELECT * FROM employees WHERE  First_Name like '" + jTextFieldSearch.getText() + "%'";
            } else if (jComboBoxSearch.getSelectedItem().equals("ID")) {
                sql = "SELECT * FROM employees WHERE ID like '" + jTextFieldSearch.getText() + "%'";
            }
            else {
                sql = "SELECT * FROM employees WHERE Salary like '" + jTextFieldSearch.getText() + "%'";
            }
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String col1 = rs.getString("ID");
                String col3 = rs.getString("First_Name");
                String col6 = rs.getString("Salary");
                emp.addRow(new Object[]{col1, col3, col6});
            }
            jTableEmp.setModel(emp);
            rs.close();
            st.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void searchProduct() {
        DefaultTableModel product = new DefaultTableModel(new String[]{"ID", "Category", "Brand", "Name", "Warranty", "Price", "Quantity"}, 0);
        try {
            st = conn.createStatement();
            String sql = null;
            if (jComboBoxPrSearch.getSelectedItem().equals("Search By : Name")) {
                sql = "SELECT * FROM product WHERE Product_Name like '" + jTextFieldPrSearch.getText() + "%'";
            } else if (jComboBoxPrSearch.getSelectedItem().equals("Category")) {
                sql = "SELECT * FROM product WHERE Category like '" + jTextFieldPrSearch.getText() + "%'";
            } else if (jComboBoxPrSearch.getSelectedItem().equals("Brand")) {
                sql = "SELECT * FROM product WHERE Brand like '" + jTextFieldPrSearch.getText() + "%'";
            }
            rs = st.executeQuery(sql);
            while (rs.next()) {
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
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void searchSale() {
        DefaultTableModel sales = new DefaultTableModel(new String[]{"Sale_ID", "Salesman_ID", "Customer_Phone", "Date"}, 0);
        jTableSaleID.setModel(sales);
        try {
            st = conn.createStatement();
            String sql = null;
            if (jComboBoxSale.getSelectedItem().equals("Sale_ID")) {
                sql = "SELECT * FROM sales WHERE Sale_ID like '" + jTextFieldSaleID.getText() + "%'";
            } else if (jComboBoxSale.getSelectedItem().equals("Phone Number")) {
                sql = "SELECT * FROM sales WHERE Cust_Phone like '" + jTextFieldSaleID.getText() + "%'";
            } else if (jComboBoxSale.getSelectedItem().equals("Date")) {
                sql = "SELECT * FROM sales WHERE Date = '" + jTextFieldSaleID.getText() + "'";
            } else {
                sql = "SELECT * FROM sales WHERE Salesman_ID like '" + jTextFieldSaleID.getText() + "%'";
            }
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String col1 = rs.getString("Sale_ID");
                String col2 = rs.getString("Salesman_ID");
                String col3 = rs.getString("Cust_Phone");
                String col4 = rs.getString("Date");
                sales.addRow(new Object[]{col1, col2, col3, col4});
            }
            jTableSaleID.setModel(sales);
            rs.close();
            st.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void searchCust() {
        DefaultTableModel cust = new DefaultTableModel(new String[]{"Customer_Phone", "Name", "Address"}, 0);
        try {
            st = conn.createStatement();
            String sql = null;
            if (jComboBoxCust.getSelectedItem().equals("Name")) {
                sql = "SELECT * FROM customer WHERE Name like '" + jTextFieldCustSearch.getText() + "%'";
            } else {
                sql = "SELECT * FROM customer WHERE Cust_Phone like '" + jTextFieldCustSearch.getText() + "%'";
            }
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String col1 = rs.getString("Cust_Phone");
                String col2 = rs.getString("Name");
                String col3 = rs.getString("Address");
                cust.addRow(new Object[]{col1, col2, col3});
            }
            jTableCust.setModel(cust);
            rs.close();
            st.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void deleteEmp() {
        if (jTableEmp.getSelectedRow() == -1) {
            if (jTableEmp.getSelectedRow() == 0) {
                jLabelMessage.setText("Table is empty!!");
            } else {
                jLabelMessage.setText("You need to select an employee");
            }
        } else {
            int del = JOptionPane.showConfirmDialog(null, "Do you really want to delete this employee?", "DELETE", JOptionPane.YES_NO_OPTION);
            if (del == 0) {
                int selectedRowIndex = jTableEmp.getSelectedRow();
                theQuery("delete from employees where ID='" + jTableEmp.getModel().getValueAt(selectedRowIndex, 0) + "'");
                theQuery("delete from login where ID='" + jTableEmp.getModel().getValueAt(selectedRowIndex, 0) + "'");
                JOptionPane.showMessageDialog(null, "Deleted !");
                refreshEmp();
            }
        }
    }

    private void deleteProduct() {
        if (jTableProduct.getSelectedRow() == -1) {
            if (jTableProduct.getSelectedRow() == 0) {
                jLabelMessage.setText("Table is empty!!");
            } else {
                jLabelMessage.setText("You need to select a product");
            }
        } else {
            int del = JOptionPane.showConfirmDialog(null, "Do you really want to delete this product?", "DELETE", JOptionPane.YES_NO_OPTION);
            if (del == 0) {
                int selectedRowIndex = jTableProduct.getSelectedRow();
                theQuery("delete from Product where Product_ID='" + jTableProduct.getModel().getValueAt(selectedRowIndex, 0) + "'");
                JOptionPane.showMessageDialog(null, "Deleted !");
                refreshProduct();
            }
        }
    }

    private void deleteCat() {
        if (jTableCat.getSelectedRow() == -1) {
            if (jTableCat.getSelectedRow() == 0) {
                jLabelCatMsg.setText("Table is empty!!");
            } else {
                jLabelCatMsg.setText("You need to select a Category");
            }
        } else {
            int del = JOptionPane.showConfirmDialog(null, "Do you really want to delete this category?", "DELETE", JOptionPane.YES_NO_OPTION);
            if (del == 0) {
                int selectedRowIndex = jTableCat.getSelectedRow();
                theQuery("delete from category_table where Category_ID=" + jTableCat.getModel().getValueAt(selectedRowIndex, 0));
                JOptionPane.showMessageDialog(null, "Deleted !");
                jLabelCatMsg.setText("");
                getCatTable();
            }
        }
    }

    private void deleteBrand() {
        if (jTableBrand.getSelectedRow() == -1) {
            if (jTableBrand.getSelectedRow() == 0) {
                jLabelBrMsg.setText("Table is empty!!");
            } else {
                jLabelBrMsg.setText("You need to select a Brand");
            }
        } else {
            int del = JOptionPane.showConfirmDialog(null, "Do you really want to delete this brand?", "DELETE", JOptionPane.YES_NO_OPTION);
            if (del == 0) {
                int selectedRowIndex = jTableBrand.getSelectedRow();
                theQuery("delete from brand_table where Brand_ID=" + jTableBrand.getModel().getValueAt(selectedRowIndex, 0));
                JOptionPane.showMessageDialog(null, "Deleted !");
                jLabelBrMsg.setText("");
                getBrandTable();
            }
        }
    }

    private void deleteCust() {
        if (jTableCust.getSelectedRow() == -1) {
            if (jTableCust.getSelectedRow() == 0) {
                jLabelCustMsg.setText("Table is empty!!");
            } else {
                jLabelCustMsg.setText("You need to select a customer.");
            }
        } else {
            int del = JOptionPane.showConfirmDialog(null, "Do you really want to delete this customer?", "DELETE", JOptionPane.YES_NO_OPTION);
            if (del == 0) {
                int selectedRowIndex = jTableCust.getSelectedRow();
                theQuery("delete from customer where Cust_Phone=" + jTableCust.getModel().getValueAt(selectedRowIndex, 0));
                JOptionPane.showMessageDialog(null, "Deleted !");
                jLabelCustMsg.setText("");
                getCustTable();
            }
        }
    }

    private void deleteSaleInfo() {
        if (jTableSaleID.getSelectedRow() == -1) {
            if (jTableSaleID.getSelectedRow() == 0) {
                jLabelSaleMsg.setText("Table is empty!!");
            } else {
                jLabelSaleMsg.setText("You need to select an item");
            }
        } else {
            int del = JOptionPane.showConfirmDialog(null, "Do you really want to delete this item?", "DELETE", JOptionPane.YES_NO_OPTION);
            if (del == 0) {
                int selectedRowIndex = jTableSaleID.getSelectedRow();
                theQuery("delete from sales where Sale_ID=" + jTableSaleID.getModel().getValueAt(selectedRowIndex, 0));
                theQuery("delete from sale_info where Sale_ID=" + jTableSaleID.getModel().getValueAt(selectedRowIndex, 0));
                JOptionPane.showMessageDialog(null, "Deleted !");
                jLabelSaleMsg.setText("");
                getSaleTable();
            }
        }
    }

    private void saleInfo() {
        if (jTableSaleID.getSelectedRow() == -1) {
            if (jTableSaleID.getSelectedRow() == 0) {
                jLabelSaleMsg.setText("Table is empty!!");
            } else {
                jLabelSaleMsg.setText("You need to select an item.");
            }
        } else {
            getSaleInfoTable();
        }
    }

    private void refreshEmp() {
        jTextFieldSearch.setText("");
        jLabelMessage.setText("");
        getEmpTable();
    }

    private void refreshProduct() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableCat = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldCatSearch = new javax.swing.JTextField();
        jButtonCatSearch = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldAddCat = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButtonUpCat = new javax.swing.JButton();
        jButtonDelCat = new javax.swing.JButton();
        jLabelCatMsg = new javax.swing.JLabel();
        refreshcat = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableBrand = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldBrSearch = new javax.swing.JTextField();
        jButtonBrSearch = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldAddBr = new javax.swing.JTextField();
        jButtonAddBr = new javax.swing.JButton();
        jButtonUpBr = new javax.swing.JButton();
        jButtonDelBr = new javax.swing.JButton();
        jLabelBrMsg = new javax.swing.JLabel();
        refreshbr = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldPrSearch = new javax.swing.JTextField();
        jComboBoxPrSearch = new javax.swing.JComboBox<>();
        jButtonPrSearch = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProduct = new javax.swing.JTable();
        jLabelPrMsg = new javax.swing.JLabel();
        jButtonAddPr = new javax.swing.JButton();
        jButtonUpdatePr = new javax.swing.JButton();
        jButtonDeletePr = new javax.swing.JButton();
        jButtonRefreshPr = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableSaleID = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldSaleID = new javax.swing.JTextField();
        jComboBoxSale = new javax.swing.JComboBox<>();
        jButtonSale = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableSaleInfo = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabelSaleMsg = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldGTotal = new javax.swing.JTextField();
        refreshcus = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldCustSearch = new javax.swing.JTextField();
        jComboBoxCust = new javax.swing.JComboBox<>();
        jButtonCustSearch = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableCust = new javax.swing.JTable();
        jLabelCustMsg = new javax.swing.JLabel();
        refreshcust = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEmp = new javax.swing.JTable();
        jButtonAdd = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jLabelMessage = new javax.swing.JLabel();
        jButtonDetails = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jTextFieldSearch = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxSearch = new javax.swing.JComboBox<>();
        jButtonRefresh = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        userlist = new javax.swing.JTable();
        refreshuser = new javax.swing.JButton();
        refreshcus1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));

        jLabel6.setFont(new java.awt.Font("Lucida Bright", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ADMIN AREA");

        jButton6.setBackground(new java.awt.Color(255, 0, 0));
        jButton6.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Log Out_27856.png")); // NOI18N
        jButton6.setText("LogOut");
        jButton6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(273, 273, 273)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jTabbedPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTabbedPane2.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        jTabbedPane1.setBackground(new java.awt.Color(204, 255, 255));

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));

        jTableCat.setAutoCreateRowSorter(true);
        jTableCat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTableCat.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jTableCat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTableCat);

        jLabel3.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jLabel3.setText("Search");

        jTextFieldCatSearch.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jTextFieldCatSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jButtonCatSearch.setBackground(new java.awt.Color(51, 102, 255));
        jButtonCatSearch.setFont(new java.awt.Font("Lucida Bright", 1, 24)); // NOI18N
        jButtonCatSearch.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Search_27877 (1).png")); // NOI18N
        jButtonCatSearch.setText("Search");
        jButtonCatSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonCatSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCatSearchActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jLabel4.setText("Category");

        jTextFieldAddCat.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jTextFieldAddCat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jButton1.setBackground(new java.awt.Color(51, 255, 51));
        jButton1.setFont(new java.awt.Font("Lucida Bright", 1, 24)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Add_27831.png")); // NOI18N
        jButton1.setText("Add Category");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonUpCat.setBackground(new java.awt.Color(102, 255, 204));
        jButtonUpCat.setFont(new java.awt.Font("Lucida Bright", 1, 24)); // NOI18N
        jButtonUpCat.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Refresh_27873.png")); // NOI18N
        jButtonUpCat.setText("Update Category");
        jButtonUpCat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonUpCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpCatActionPerformed(evt);
            }
        });

        jButtonDelCat.setBackground(new java.awt.Color(255, 0, 0));
        jButtonDelCat.setFont(new java.awt.Font("Lucida Bright", 1, 24)); // NOI18N
        jButtonDelCat.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Remove_27874 (1).png")); // NOI18N
        jButtonDelCat.setText("Delete");
        jButtonDelCat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonDelCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelCatActionPerformed(evt);
            }
        });

        jLabelCatMsg.setForeground(new java.awt.Color(255, 0, 51));

        refreshcat.setBackground(new java.awt.Color(255, 51, 102));
        refreshcat.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        refreshcat.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Refresh_27873.png")); // NOI18N
        refreshcat.setText("Refresh");
        refreshcat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        refreshcat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshcatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCatSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCatSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jButtonDelCat, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(refreshcat, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabelCatMsg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextFieldAddCat, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonUpCat, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldCatSearch)
                    .addComponent(jButtonCatSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldAddCat, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonUpCat, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabelCatMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDelCat, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshcat, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        jTabbedPane1.addTab("Categories", jPanel3);

        jPanel9.setBackground(new java.awt.Color(204, 255, 255));

        jTableBrand.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTableBrand.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jTableBrand.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(jTableBrand);

        jLabel5.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jLabel5.setText("Search");

        jTextFieldBrSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jButtonBrSearch.setBackground(new java.awt.Color(51, 102, 255));
        jButtonBrSearch.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonBrSearch.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Search_27877 (1).png")); // NOI18N
        jButtonBrSearch.setText("Search");
        jButtonBrSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonBrSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBrSearchActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jLabel7.setText("Brand");

        jTextFieldAddBr.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jTextFieldAddBr.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jButtonAddBr.setBackground(new java.awt.Color(102, 255, 0));
        jButtonAddBr.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonAddBr.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Add_27831 (1).png")); // NOI18N
        jButtonAddBr.setText("Add New Brand");
        jButtonAddBr.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonAddBr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddBrActionPerformed(evt);
            }
        });

        jButtonUpBr.setBackground(new java.awt.Color(102, 255, 153));
        jButtonUpBr.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonUpBr.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Redo_27872.png")); // NOI18N
        jButtonUpBr.setText("Update Brand");
        jButtonUpBr.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonUpBr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpBrActionPerformed(evt);
            }
        });

        jButtonDelBr.setBackground(new java.awt.Color(255, 51, 102));
        jButtonDelBr.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonDelBr.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Remove_27874 (1).png")); // NOI18N
        jButtonDelBr.setText("Delete");
        jButtonDelBr.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonDelBr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelBrActionPerformed(evt);
            }
        });

        jLabelBrMsg.setForeground(new java.awt.Color(255, 0, 51));

        refreshbr.setBackground(new java.awt.Color(255, 0, 0));
        refreshbr.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        refreshbr.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Refresh_27873.png")); // NOI18N
        refreshbr.setText("Refresh");
        refreshbr.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        refreshbr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshbrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldBrSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonBrSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                            .addGap(24, 24, 24)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jTextFieldAddBr, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabelBrMsg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(94, 94, 94)
                                        .addComponent(jButtonAddBr, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonUpBr, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addComponent(jButtonDelBr, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(refreshbr, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(761, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldBrSearch)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBrSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldAddBr, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonAddBr, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonUpBr, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(jLabelBrMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonDelBr, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                    .addContainerGap(458, Short.MAX_VALUE)
                    .addComponent(refreshbr, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(39, 39, 39)))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1095, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 55, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 56, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 562, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Brands", jPanel4);

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        jLabel2.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jLabel2.setText("Search");

        jTextFieldPrSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jTextFieldPrSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPrSearchActionPerformed(evt);
            }
        });

        jComboBoxPrSearch.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jComboBoxPrSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Search By : Name", "Category", "Brand" }));
        jComboBoxPrSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jComboBoxPrSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPrSearchActionPerformed(evt);
            }
        });

        jButtonPrSearch.setBackground(new java.awt.Color(51, 102, 255));
        jButtonPrSearch.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonPrSearch.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Search_27877 (1).png")); // NOI18N
        jButtonPrSearch.setText("Search");
        jButtonPrSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonPrSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldPrSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxPrSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonPrSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 222, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextFieldPrSearch, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jComboBoxPrSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jButtonPrSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTableProduct.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTableProduct.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jTableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTableProduct);

        jLabelPrMsg.setForeground(new java.awt.Color(255, 0, 0));

        jButtonAddPr.setBackground(new java.awt.Color(51, 255, 51));
        jButtonAddPr.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonAddPr.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Add_27831 (1).png")); // NOI18N
        jButtonAddPr.setText("Add New Product");
        jButtonAddPr.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonAddPr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAddPrMouseClicked(evt);
            }
        });
        jButtonAddPr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddPrActionPerformed(evt);
            }
        });

        jButtonUpdatePr.setBackground(new java.awt.Color(51, 255, 153));
        jButtonUpdatePr.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonUpdatePr.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Redo_27872 (2).png")); // NOI18N
        jButtonUpdatePr.setText("Update  Product");
        jButtonUpdatePr.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonUpdatePr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdatePrActionPerformed(evt);
            }
        });

        jButtonDeletePr.setBackground(new java.awt.Color(255, 0, 0));
        jButtonDeletePr.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonDeletePr.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Remove_27874.png")); // NOI18N
        jButtonDeletePr.setText("Delete Product");
        jButtonDeletePr.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonDeletePr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeletePrActionPerformed(evt);
            }
        });

        jButtonRefreshPr.setBackground(new java.awt.Color(255, 51, 255));
        jButtonRefreshPr.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonRefreshPr.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Refresh_27873.png")); // NOI18N
        jButtonRefreshPr.setText("Refresh");
        jButtonRefreshPr.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonRefreshPr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshPrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabelPrMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButtonAddPr, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonUpdatePr, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDeletePr, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRefreshPr, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonRefreshPr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonAddPr, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonUpdatePr, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonDeletePr, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 24, Short.MAX_VALUE)
                .addComponent(jLabelPrMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Products", jPanel2);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTabbedPane2.addTab("Inventory", jPanel6);

        jPanel10.setBackground(new java.awt.Color(204, 204, 255));

        jTableSaleID.setFont(new java.awt.Font("Lucida Bright", 1, 15)); // NOI18N
        jTableSaleID.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(jTableSaleID);

        jLabel8.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jLabel8.setText("Search");

        jTextFieldSaleID.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jTextFieldSaleID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTextFieldSaleID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSaleIDActionPerformed(evt);
            }
        });

        jComboBoxSale.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jComboBoxSale.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sale ID", "Phone Number", "Date", "Salesman" }));
        jComboBoxSale.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jButtonSale.setBackground(new java.awt.Color(102, 102, 255));
        jButtonSale.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonSale.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Search_27877 (1).png")); // NOI18N
        jButtonSale.setText("Search");
        jButtonSale.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldSaleID, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxSale, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSale, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTextFieldSaleID, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jComboBoxSale, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButtonSale, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTableSaleInfo.setFont(new java.awt.Font("Lucida Bright", 1, 15)); // NOI18N
        jTableSaleInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(jTableSaleInfo);

        jButton2.setBackground(new java.awt.Color(102, 153, 255));
        jButton2.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButton2.setText("Details");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(204, 0, 0));
        jButton3.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Remove_27874 (1).png")); // NOI18N
        jButton3.setText("Delete");
        jButton3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabelSaleMsg.setForeground(new java.awt.Color(255, 0, 0));

        jLabel9.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jLabel9.setText("Grand Total");

        jTextFieldGTotal.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jTextFieldGTotal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        refreshcus.setBackground(new java.awt.Color(255, 51, 102));
        refreshcus.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        refreshcus.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Refresh_27873.png")); // NOI18N
        refreshcus.setText("Refresh");
        refreshcus.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        refreshcus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshcusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshcus, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldGTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelSaleMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldGTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelSaleMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(refreshcus, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jTabbedPane3.addTab("Sale History", jPanel10);

        jPanel11.setBackground(new java.awt.Color(204, 204, 255));

        jLabel10.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jLabel10.setText("Search");

        jTextFieldCustSearch.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jTextFieldCustSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTextFieldCustSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCustSearchActionPerformed(evt);
            }
        });

        jComboBoxCust.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jComboBoxCust.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Phone" }));
        jComboBoxCust.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jButtonCustSearch.setBackground(new java.awt.Color(102, 102, 255));
        jButtonCustSearch.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonCustSearch.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Search_27877 (1).png")); // NOI18N
        jButtonCustSearch.setText("Search");
        jButtonCustSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonCustSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCustSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldCustSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxCust, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jButtonCustSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(332, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTextFieldCustSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jComboBoxCust, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButtonCustSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTableCust.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane7.setViewportView(jTableCust);

        refreshcust.setBackground(new java.awt.Color(255, 51, 51));
        refreshcust.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        refreshcust.setText("Refresh");
        refreshcust.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        refreshcust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshcustActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 51, 51));
        jButton5.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Remove_27874 (1).png")); // NOI18N
        jButton5.setText("Delete Customer");
        jButton5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(219, 219, 219)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(refreshcust, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelCustMsg, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(26, 26, 26))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCustMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(refreshcust, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Customer", jPanel11);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTabbedPane2.addTab("Sales", jPanel7);

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));

        jTableEmp.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTableEmp.setFont(new java.awt.Font("Lucida Bright", 1, 15)); // NOI18N
        jTableEmp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableEmp);

        jButtonAdd.setBackground(new java.awt.Color(51, 255, 51));
        jButtonAdd.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonAdd.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Add_27831 (1).png")); // NOI18N
        jButtonAdd.setText("Add  Employee");
        jButtonAdd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonUpdate.setBackground(new java.awt.Color(102, 255, 204));
        jButtonUpdate.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonUpdate.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Redo_27872.png")); // NOI18N
        jButtonUpdate.setText("Update  Employee");
        jButtonUpdate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonDelete.setBackground(new java.awt.Color(255, 0, 51));
        jButtonDelete.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonDelete.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Remove_27874 (1).png")); // NOI18N
        jButtonDelete.setText("Delete Employee");
        jButtonDelete.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jLabelMessage.setForeground(new java.awt.Color(255, 0, 0));

        jButtonDetails.setBackground(new java.awt.Color(153, 153, 153));
        jButtonDetails.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonDetails.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_investment_3338991.png")); // NOI18N
        jButtonDetails.setText("Show Details");
        jButtonDetails.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDetailsActionPerformed(evt);
            }
        });

        jTextFieldSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTextFieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButton7.setText("Search");
        jButton7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jLabel1.setText("Search");

        jComboBoxSearch.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jComboBoxSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Search By : Name", "ID", "Department", "Job" }));
        jComboBoxSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jComboBoxSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jComboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(238, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextFieldSearch, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxSearch)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jButtonRefresh.setBackground(new java.awt.Color(255, 153, 153));
        jButtonRefresh.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonRefresh.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Refresh_27873.png")); // NOI18N
        jButtonRefresh.setText("Refresh");
        jButtonRefresh.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 255, 153));
        jButton4.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButton4.setText("Creat User Account");
        jButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabelMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(722, 722, 722))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 47, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(401, 401, 401)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(62, 62, 62))
        );

        jTabbedPane2.addTab("Employees", jPanel5);

        userlist.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        userlist.setFont(new java.awt.Font("Lucida Bright", 1, 15)); // NOI18N
        userlist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane8.setViewportView(userlist);

        refreshuser.setBackground(new java.awt.Color(255, 51, 102));
        refreshuser.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        refreshuser.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Refresh_27873.png")); // NOI18N
        refreshuser.setText("Refresh");
        refreshuser.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        refreshuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshuserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(refreshuser, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(219, 219, 219))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refreshuser, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        refreshcus1.setBackground(new java.awt.Color(255, 51, 102));
        refreshcus1.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        refreshcus1.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Refresh_27873.png")); // NOI18N
        refreshcus1.setText("Refresh");
        refreshcus1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        refreshcus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshcus1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(221, Short.MAX_VALUE))
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addGap(456, 456, 456)
                    .addComponent(refreshcus1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(457, Short.MAX_VALUE)))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 67, Short.MAX_VALUE))
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addGap(244, 244, 244)
                    .addComponent(refreshcus1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(244, Short.MAX_VALUE)))
        );

        jTabbedPane4.addTab("List", jPanel15);

        jTabbedPane2.addTab("User List", jTabbedPane4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 631, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCatSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCatSearchActionPerformed
        searchCat();
    }//GEN-LAST:event_jButtonCatSearchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        addCat();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonUpCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpCatActionPerformed
        updateCat();
    }//GEN-LAST:event_jButtonUpCatActionPerformed

    private void jButtonDelCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelCatActionPerformed
        deleteCat();
    }//GEN-LAST:event_jButtonDelCatActionPerformed

    private void jButtonBrSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBrSearchActionPerformed
        searchBrand();
    }//GEN-LAST:event_jButtonBrSearchActionPerformed

    private void jButtonAddBrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddBrActionPerformed
        addBrand();
    }//GEN-LAST:event_jButtonAddBrActionPerformed

    private void jButtonUpBrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpBrActionPerformed
        updateBrand();
    }//GEN-LAST:event_jButtonUpBrActionPerformed

    private void jButtonDelBrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelBrActionPerformed
        deleteBrand();
    }//GEN-LAST:event_jButtonDelBrActionPerformed

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

        Product slr = new Product();
        slr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // firstForm.setSize(800, 600);
        slr.setLocationRelativeTo(null);
        slr.setVisible(true);
    }//GEN-LAST:event_jButtonAddPrActionPerformed

    private void jButtonUpdatePrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdatePrActionPerformed
        if (jTableProduct.getSelectedRow() == -1) {
            if (jTableEmp.getSelectedRow() == 0) {
                jLabelPrMsg.setText("Table is empty!!");
            } else {
                jLabelPrMsg.setText("You need to select a Product");
            }
        } else {
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

    private void refreshcustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshcustActionPerformed
        getCustTable();
    }//GEN-LAST:event_refreshcustActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed

        AddEmployee ad = new AddEmployee();
        ad.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // firstForm.setSize(800, 600);
        ad.setLocationRelativeTo(null);
        ad.setVisible(true);

    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        if (jTableEmp.getSelectedRow() == -1) {
            if (jTableEmp.getSelectedRow() == 0) {
                jLabelMessage.setText("Table is empty!!");
            } else {
                jLabelMessage.setText("You need to select an Employee");
            }
        } else {
            updateEmp();
        }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        deleteEmp();
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDetailsActionPerformed
        if (jTableEmp.getSelectedRow() == -1) {
            if (jTableEmp.getSelectedRow() == 0) {
                jLabelMessage.setText("Table is empty!!");
            } else {
                jLabelMessage.setText("You need to select an Employee");
            }
        } else {
            showDetails();
        }
    }//GEN-LAST:event_jButtonDetailsActionPerformed

    private void jTextFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchActionPerformed
        searchEmp();
    }//GEN-LAST:event_jTextFieldSearchActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        searchEmp();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jComboBoxSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSearchActionPerformed

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        refreshEmp();
    }//GEN-LAST:event_jButtonRefreshActionPerformed

    private void jButtonAddPrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAddPrMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAddPrMouseClicked

    private void refreshbrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshbrActionPerformed
        // TODO add your handling code here:
        getBrandTable();
    }//GEN-LAST:event_refreshbrActionPerformed

    private void refreshcatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshcatActionPerformed
        // TODO add your handling code here:
        getCatTable();
    }//GEN-LAST:event_refreshcatActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        NewAccount nw = new NewAccount();
        nw.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // firstForm.setSize(800, 600);
        nw.setLocationRelativeTo(null);
        nw.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        dispose();
        new login().setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void refreshcusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshcusActionPerformed
        // TODO add your handling code here:
        getSaleTable();
    }//GEN-LAST:event_refreshcusActionPerformed

    private void refreshcus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshcus1ActionPerformed
        // TODO add your handling code here:
        getCustTable();
    }//GEN-LAST:event_refreshcus1ActionPerformed

    private void refreshuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshuserActionPerformed
        // TODO add your handling code here:
        getUser();
    }//GEN-LAST:event_refreshuserActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         deleteCust();
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAddBr;
    private javax.swing.JButton jButtonAddPr;
    private javax.swing.JButton jButtonBrSearch;
    private javax.swing.JButton jButtonCatSearch;
    private javax.swing.JButton jButtonCustSearch;
    private javax.swing.JButton jButtonDelBr;
    private javax.swing.JButton jButtonDelCat;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonDeletePr;
    private javax.swing.JButton jButtonDetails;
    private javax.swing.JButton jButtonPrSearch;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonRefreshPr;
    private javax.swing.JButton jButtonSale;
    private javax.swing.JButton jButtonUpBr;
    private javax.swing.JButton jButtonUpCat;
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
    private javax.swing.JLabel jLabelMessage;
    private javax.swing.JLabel jLabelPrMsg;
    private javax.swing.JLabel jLabelSaleMsg;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
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
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTableBrand;
    private javax.swing.JTable jTableCat;
    private javax.swing.JTable jTableCust;
    public javax.swing.JTable jTableEmp;
    private javax.swing.JTable jTableProduct;
    private javax.swing.JTable jTableSaleID;
    private javax.swing.JTable jTableSaleInfo;
    private javax.swing.JTextField jTextFieldAddBr;
    private javax.swing.JTextField jTextFieldAddCat;
    private javax.swing.JTextField jTextFieldBrSearch;
    private javax.swing.JTextField jTextFieldCatSearch;
    private javax.swing.JTextField jTextFieldCustSearch;
    private javax.swing.JTextField jTextFieldGTotal;
    private javax.swing.JTextField jTextFieldPrSearch;
    private javax.swing.JTextField jTextFieldSaleID;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JButton refreshbr;
    private javax.swing.JButton refreshcat;
    private javax.swing.JButton refreshcus;
    private javax.swing.JButton refreshcus1;
    private javax.swing.JButton refreshcust;
    private javax.swing.JButton refreshuser;
    public javax.swing.JTable userlist;
    // End of variables declaration//GEN-END:variables
}
