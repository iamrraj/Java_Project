/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import student.student;

/**
 *
 * @author RahulRaj
 */
public class edit extends javax.swing.JFrame {

    /**
     * Creates new form edit
     */
    public edit() {
        initComponents();
        getConnection();
        getEmpTable();
    }
    
    Connection conn = null;
    Statement st = null;
    ResultSet rs;
    public void getConnection(){
        try{
           conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/san","root","");
           //JOptionPane.showMessageDialog(null, "Connection Established.");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
     public void getEmpTable(){
        DefaultTableModel emp = new DefaultTableModel(new String[]{"ID","Name", "Department","Phone", "Email","Salary","Address"}, 0);
        try{
            st = conn.createStatement();
            String sql="SELECT * FROM teacher";
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                String col1 = rs.getString("ID");
                String col2 = rs.getString("Last_Name");
                String col3 = rs.getString("Departmenty");
                String col4 = rs.getString("Phone");                
                String col5 = rs.getString("Email");
                String col6 = rs.getString("salary");
                String col7 = rs.getString("Address");           
                emp.addRow(new Object[]{col1, col2, col3, col4, col5, col6,col7});
            }
            jTableEmp.setModel(emp);
            rs.close();
            st.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
     
     public void updateEmp(){
        int selectedRowIndex = jTableEmp.getSelectedRow();
        String id = jTableEmp.getModel().getValueAt(selectedRowIndex, 0).toString();
        new update().setEmp(id);
    }
     
     private void searchEmp(){
        DefaultTableModel emp = new DefaultTableModel(new String[]{"ID","Name", "Department","Phone", "Email","Salary","Address"}, 0);
        try{
            st = conn.createStatement();
            String sql= null;
            if(jComboBoxSearch.getSelectedItem().equals("Search By : Name")){
                sql = "SELECT * FROM teacher WHERE Last_Name like '"+jTextFieldSearch.getText()+"%' or Last_Name like '"+jTextFieldSearch.getText()+"%'";
            }
            else if(jComboBoxSearch.getSelectedItem().equals("ID")){
                sql = "SELECT * FROM teacher WHERE ID like '"+jTextFieldSearch.getText()+"%'";
            }
            else if(jComboBoxSearch.getSelectedItem().equals("Department")){
                sql = "SELECT * FROM teacher WHERE Departmenty like '"+jTextFieldSearch.getText()+"%'";
            }
            else{
                sql = "SELECT * FROM teacher WHERE Sex like '"+jTextFieldSearch.getText()+"%'";
            }
            rs = st.executeQuery(sql);
            while(rs.next())
            {
                 String col1 = rs.getString("ID");
                String col2 = rs.getString("Last_Name");
                String col3 = rs.getString("Departmenty");
                String col4 = rs.getString("DOB");                
                String col5 = rs.getString("Sex");
                String col6 = rs.getString("salary");
                String col7 = rs.getString("Email");          
                emp.addRow(new Object[]{col1, col2, col3, col4, col5, col6,col7});
            }
            jTableEmp.setModel(emp);
            rs.close();
            st.close();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
     
     private void refreshEmp(){
        jTextFieldSearch.setText("");
        jLabelMessage.setText("");
        getEmpTable();
    }
     
     public void showDetails(){
        int selectedRowIndex = jTableEmp.getSelectedRow();
        String id = jTableEmp.getModel().getValueAt(selectedRowIndex, 0).toString();
        new tchdetails().showDetails(id);
    }
     
     private void deleteEmp(){
        if(jTableEmp.getSelectedRow()==-1){
            if (jTableEmp.getSelectedRow()==0){
                jLabelMessage.setText("Table is empty!!");
            }
            else{
                jLabelMessage.setText("You need to select an Teacher");
            }
        }
        else{
            int del = JOptionPane.showConfirmDialog(null, "Do you really want to delete this Teacher?", "DELETE", JOptionPane.YES_NO_OPTION);
            if(del==0){
                int selectedRowIndex = jTableEmp.getSelectedRow();
               // theQuery();
                theQuery("delete from teacher where ID='"+jTableEmp.getModel().getValueAt(selectedRowIndex, 0)+"'");
                
              //  theQuery("delete from login where ID='"+jTableEmp.getModel().getValueAt(selectedRowIndex, 0)+"'");
                JOptionPane.showMessageDialog(null, "Deleted Successful !");
                refreshEmp();
            }
        }
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


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEmp = new javax.swing.JTable();
        jTextFieldSearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxSearch = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jButtonRefresh = new javax.swing.JButton();
        jButtonDetails = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        jLabelMessage = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));

        jTableEmp.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTableEmp.setFont(new java.awt.Font("Lucida Sans", 1, 15)); // NOI18N
        jTableEmp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableEmp);

        jTextFieldSearch.setFont(new java.awt.Font("Lucida Sans", 1, 15)); // NOI18N
        jTextFieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 1, 17)); // NOI18N
        jLabel1.setText("Search");

        jComboBoxSearch.setFont(new java.awt.Font("Lucida Sans", 1, 15)); // NOI18N
        jComboBoxSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Search By : Name", "ID", "Department", "Job" }));
        jComboBoxSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSearchActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Lucida Sans", 1, 16)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Search_27877 (1).png")); // NOI18N
        jButton7.setText("Search");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButtonRefresh.setFont(new java.awt.Font("Lucida Sans", 1, 16)); // NOI18N
        jButtonRefresh.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Refresh_27873.png")); // NOI18N
        jButtonRefresh.setText("Refresh");
        jButtonRefresh.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });

        jButtonDetails.setFont(new java.awt.Font("Lucida Sans", 1, 16)); // NOI18N
        jButtonDetails.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/Students-icon (1).png")); // NOI18N
        jButtonDetails.setText("Show Details");
        jButtonDetails.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDetailsActionPerformed(evt);
            }
        });

        jButtonDelete.setFont(new java.awt.Font("Lucida Sans", 1, 16)); // NOI18N
        jButtonDelete.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Remove_27874 (1).png")); // NOI18N
        jButtonDelete.setText("Delete Teacher");
        jButtonDelete.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonUpdate.setFont(new java.awt.Font("Lucida Sans", 1, 16)); // NOI18N
        jButtonUpdate.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Synchronize_27883.png")); // NOI18N
        jButtonUpdate.setText("Update Teacher");
        jButtonUpdate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonAdd.setFont(new java.awt.Font("Lucida Sans", 1, 16)); // NOI18N
        jButtonAdd.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Add_27831 (1).png")); // NOI18N
        jButtonAdd.setText("Add Teacher");
        jButtonAdd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jLabelMessage.setFont(new java.awt.Font("Lucida Sans", 1, 16)); // NOI18N
        jLabelMessage.setForeground(new java.awt.Color(255, 0, 0));

        jButton2.setBackground(new java.awt.Color(255, 102, 102));
        jButton2.setFont(new java.awt.Font("Lucida Sans", 1, 16)); // NOI18N
        jButton2.setText("<<BACK");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 375, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabelMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(172, 172, 172))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchActionPerformed
        searchEmp();
    }//GEN-LAST:event_jTextFieldSearchActionPerformed

    private void jComboBoxSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSearchActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        searchEmp();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        refreshEmp();
    }//GEN-LAST:event_jButtonRefreshActionPerformed

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

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        deleteEmp();
    }//GEN-LAST:event_jButtonDeleteActionPerformed

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

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        teacher slr = new teacher();
       slr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       // firstForm.setSize(800, 600);
       slr.setLocationRelativeTo(null);
       slr.setVisible(true);
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        teacher slrr = new teacher();
        slrr.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(edit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(edit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(edit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(edit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new edit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonDetails;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox<String> jComboBoxSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelMessage;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTableEmp;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
}
