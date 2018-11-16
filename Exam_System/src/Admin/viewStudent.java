/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author RahulRaj
 */
public class viewStudent extends javax.swing.JFrame {

    /**
     * Creates new form viewStudent
     */
    public viewStudent() {
        initComponents();
        getConnection();
        getEmpTable();
        
    }
    
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    
    public void getConnection()
    {
        try{
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sanexam","root","" );
            //JOptionPane.showMessageDialog(null, "Connection Establist");
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
                
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
    
    
    public void showDetails() {
        int selectedRowIndex = jTableEmp.getSelectedRow();
        String id = jTableEmp.getModel().getValueAt(selectedRowIndex, 0).toString();
        
        stddetails em= new stddetails();
        em.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // firstForm.setSize(800, 600);
        em.setLocationRelativeTo(null);
       // em.showDetails(id);
    }
    
    
    public void updateEmp() {
        int selectedRowIndex = jTableEmp.getSelectedRow();
        String id = jTableEmp.getModel().getValueAt(selectedRowIndex, 0).toString();
        // new UpdateEmployee().setEmp(id);
        updatestd slr11 = new updatestd();
        slr11.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // firstForm.setSize(800, 600);
        slr11.setLocationRelativeTo(null);
        slr11.setEmp(id);

    }

   public void getEmpTable() {
        DefaultTableModel emp = new DefaultTableModel(new String[]{"ID", "Name", "Email","Phone","Semester","Department"}, 0);
        try {
            st = conn.createStatement();
            String sql = "SELECT * FROM student";
            rs = st.executeQuery(sql);
            while (rs.next()) {
               String  col1 = rs.getString("id");
                String col2 = rs.getString("name");
                String col3 = rs.getString("email");
                String col4 = rs.getString("phone");
                String col5 = rs.getString("semester");
                String col6 = rs.getString("department");
                emp.addRow(new Object[]{col1,col2, col3,col4,col5, col6});
            }
            jTableEmp.setModel(emp);
            rs.close();
            st.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    private void searchEmp() {
        DefaultTableModel emp = new DefaultTableModel(new String[]{"ID", "Name", "Email","Phone","Semester","Department"}, 0);
        try {
            st = conn.createStatement();
            String sql = null;
            if (jComboBoxSearch.getSelectedItem().equals("Search By : Name")) {
                sql = "SELECT * FROM student WHERE  name like '" + jTextFieldSearch.getText() + "%'";
            } else if (jComboBoxSearch.getSelectedItem().equals("Semester")) {
                sql = "SELECT * FROM student WHERE semester like '" + jTextFieldSearch.getText() + "%'";
            }
            else if (jComboBoxSearch.getSelectedItem().equals("Department")) {
                sql = "SELECT * FROM student WHERE department like '" + jTextFieldSearch.getText() + "%'";
            }
            else {
                sql = "SELECT * FROM student WHERE sex like '" + jTextFieldSearch.getText() + "%'";
            }
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String  col1 = rs.getString("id");
                String col2 = rs.getString("anme");
                String col3 = rs.getString("email");
                String col4 = rs.getString("phone");
                String col5 = rs.getString("semester");
                String col6 = rs.getString("department");
                emp.addRow(new Object[]{col1,col2, col3,col4,col5, col6});
            }
            jTableEmp.setModel(emp);
            rs.close();
            st.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    private void deleteEmp() {
        if (jTableEmp.getSelectedRow() == -1) {
            if (jTableEmp.getSelectedRow() == 0) {
                Jmessage.setText("Table is empty!!");
            } else {
                Jmessage.setText("You need to select an employee");
            }
        } else {
            int del = JOptionPane.showConfirmDialog(null, "Do you really want to delete this employee?", "DELETE", JOptionPane.YES_NO_OPTION);
            if (del == 0) {
                int selectedRowIndex = jTableEmp.getSelectedRow();
                theQuery("delete from student where id='" + jTableEmp.getModel().getValueAt(selectedRowIndex, 0) + "'");
                //theQuery("delete from login where ID='" + jTableEmp.getModel().getValueAt(selectedRowIndex, 0) + "'");
                JOptionPane.showMessageDialog(null, "Deleted !");
                refreshEmp();
            }
        }
    }
    
    private void refreshEmp() {
        jTextFieldSearch.setText("");
        Jmessage.setText("");
        getEmpTable();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        user = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        prog = new javax.swing.JButton();
        vstudent = new javax.swing.JButton();
        viewresult = new javax.swing.JButton();
        question = new javax.swing.JButton();
        vsems = new javax.swing.JButton();
        dept = new javax.swing.JButton();
        sems = new javax.swing.JButton();
        student = new javax.swing.JButton();
        viewdept = new javax.swing.JButton();
        cours = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEmp = new javax.swing.JTable();
        jTextFieldSearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxSearch = new javax.swing.JComboBox<>();
        jButton8 = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonDetails = new javax.swing.JButton();
        jButtonRefresh = new javax.swing.JButton();
        message = new javax.swing.JLabel();
        message1 = new javax.swing.JLabel();
        Jmessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("View Student");

        jPanel6.setBackground(new java.awt.Color(255, 102, 102));

        jPanel7.setBackground(new java.awt.Color(102, 102, 255));

        jLabel9.setFont(new java.awt.Font("Lucida Bright", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("VIEW STUDENT");
        jLabel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 2, true));

        user.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        user.setForeground(new java.awt.Color(255, 255, 255));

        jButton12.setBackground(new java.awt.Color(255, 0, 0));
        jButton12.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Log Out_27856.png")); // NOI18N
        jButton12.setText("LOGOUT");
        jButton12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 51, 51));

        prog.setBackground(new java.awt.Color(255, 51, 51));
        prog.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        prog.setText("Add Progr. Lang");
        prog.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        prog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                progActionPerformed(evt);
            }
        });

        vstudent.setBackground(new java.awt.Color(255, 51, 51));
        vstudent.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        vstudent.setText("View Student");
        vstudent.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        vstudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vstudentActionPerformed(evt);
            }
        });

        viewresult.setBackground(new java.awt.Color(255, 51, 51));
        viewresult.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        viewresult.setText("View Result");
        viewresult.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        viewresult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewresultActionPerformed(evt);
            }
        });

        question.setBackground(new java.awt.Color(255, 51, 51));
        question.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        question.setText("Add Question");
        question.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        question.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                questionActionPerformed(evt);
            }
        });

        vsems.setBackground(new java.awt.Color(255, 51, 51));
        vsems.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        vsems.setText("View Semester");
        vsems.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        vsems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vsemsActionPerformed(evt);
            }
        });

        dept.setBackground(new java.awt.Color(255, 51, 51));
        dept.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        dept.setText("Add Department");
        dept.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        dept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deptActionPerformed(evt);
            }
        });

        sems.setBackground(new java.awt.Color(255, 51, 51));
        sems.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        sems.setText("Add Semester");
        sems.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        sems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                semsActionPerformed(evt);
            }
        });

        student.setBackground(new java.awt.Color(255, 51, 51));
        student.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        student.setText("Add Stdudent");
        student.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentActionPerformed(evt);
            }
        });

        viewdept.setBackground(new java.awt.Color(255, 51, 51));
        viewdept.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        viewdept.setText("View Depart.");
        viewdept.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        viewdept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewdeptActionPerformed(evt);
            }
        });

        cours.setBackground(new java.awt.Color(255, 51, 51));
        cours.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        cours.setText("Add Course");
        cours.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        cours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coursActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(prog, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(vstudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(viewresult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(question, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dept, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
            .addComponent(cours, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(vsems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(viewdept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(student, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(viewdept, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prog, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(vstudent, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewresult, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(question, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sems, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dept, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cours, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vsems, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGap(119, 119, 119)
                    .addComponent(student, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(414, Short.MAX_VALUE)))
        );

        jTableEmp.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTableEmp.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        jTableEmp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableEmp);

        jTextFieldSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTextFieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jLabel2.setText("Search");

        jComboBoxSearch.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jComboBoxSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Search By : Name", "ID", "Department", "Job" }));
        jComboBoxSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jComboBoxSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSearchActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Search_27877 (1).png")); // NOI18N
        jButton8.setText("Search");
        jButton8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButtonAdd.setBackground(new java.awt.Color(51, 255, 51));
        jButtonAdd.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonAdd.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Add_27831 (1).png")); // NOI18N
        jButtonAdd.setText("Add Student");
        jButtonAdd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonUpdate.setBackground(new java.awt.Color(102, 255, 204));
        jButtonUpdate.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonUpdate.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Redo_27872.png")); // NOI18N
        jButtonUpdate.setText("Update Student");
        jButtonUpdate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonDelete.setBackground(new java.awt.Color(255, 0, 51));
        jButtonDelete.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jButtonDelete.setIcon(new javax.swing.ImageIcon("/Users/vipuldani/Downloads/java logo/if_Remove_27874 (1).png")); // NOI18N
        jButtonDelete.setText("Delete Student");
        jButtonDelete.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

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

        message.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        message.setForeground(new java.awt.Color(255, 255, 255));

        message1.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        message1.setForeground(new java.awt.Color(255, 255, 255));

        Jmessage.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        Jmessage.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBoxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 6, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(Jmessage, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(389, 389, 389)
                    .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(389, Short.MAX_VALUE)))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addContainerGap(399, Short.MAX_VALUE)
                    .addComponent(message1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(379, 379, 379)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBoxSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextFieldSearch, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Jmessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(325, 325, 325)
                    .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(326, Short.MAX_VALUE)))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addContainerGap(335, Short.MAX_VALUE)
                    .addComponent(message1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(316, 316, 316)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        dispose();
        new admin().setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void progActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_progActionPerformed
        // TODO add your handling code here:
        dispose();
        new programlang().setVisible(true);
    }//GEN-LAST:event_progActionPerformed

    private void vstudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vstudentActionPerformed
        // TODO add your handling code here:
        dispose();
        new viewStudent().setVisible(true);
    }//GEN-LAST:event_vstudentActionPerformed

    private void viewresultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewresultActionPerformed
        // TODO add your handling code here:
        dispose();
        new result().setVisible(true);
    }//GEN-LAST:event_viewresultActionPerformed

    private void questionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_questionActionPerformed
        // TODO add your handling code here:
        dispose();
        new question().setVisible(true);
    }//GEN-LAST:event_questionActionPerformed

    private void vsemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vsemsActionPerformed
        // TODO add your handling code here:
        dispose();
        new viewsemes().setVisible(true);
    }//GEN-LAST:event_vsemsActionPerformed

    private void deptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deptActionPerformed
        // TODO add your handling code here:
        dispose();
        new department().setVisible(true);
    }//GEN-LAST:event_deptActionPerformed

    private void semsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semsActionPerformed
        // TODO add your handling code here:
        dispose();
        new semester().setVisible(true);
    }//GEN-LAST:event_semsActionPerformed

    private void studentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentActionPerformed
        // TODO add your handling code here:
        dispose();
        new student().setVisible(true);
    }//GEN-LAST:event_studentActionPerformed

    private void viewdeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewdeptActionPerformed
        // TODO add your handling code here:
        dispose();
        new viewDeprt().setVisible(true);
    }//GEN-LAST:event_viewdeptActionPerformed

    private void coursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coursActionPerformed
        // TODO add your handling code here:
        dispose();
        new course().setVisible(true);
    }//GEN-LAST:event_coursActionPerformed

    private void jTextFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchActionPerformed
        searchEmp();
    }//GEN-LAST:event_jTextFieldSearchActionPerformed

    private void jComboBoxSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSearchActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        searchEmp();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed

        student ad = new student();
        ad.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // firstForm.setSize(800, 600);
        ad.setLocationRelativeTo(null);
        ad.setVisible(true);
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        if (jTableEmp.getSelectedRow() == -1) {
            if (jTableEmp.getSelectedRow() == 0) {
                Jmessage.setText("Table is empty!!");
            } else {
                Jmessage.setText("You need to select an Employee");
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
                Jmessage.setText("Table is empty!!");
            } else {
                Jmessage.setText("You need to select an Employee");
            }
        } else {
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
            java.util.logging.Logger.getLogger(viewStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jmessage;
    private javax.swing.JButton cours;
    private javax.swing.JButton dept;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonDetails;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox<String> jComboBoxSearch;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTableEmp;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JLabel message;
    private javax.swing.JLabel message1;
    private javax.swing.JButton prog;
    private javax.swing.JButton question;
    private javax.swing.JButton sems;
    private javax.swing.JButton student;
    private javax.swing.JLabel user;
    private javax.swing.JButton viewdept;
    private javax.swing.JButton viewresult;
    private javax.swing.JButton vsems;
    private javax.swing.JButton vstudent;
    // End of variables declaration//GEN-END:variables
}
