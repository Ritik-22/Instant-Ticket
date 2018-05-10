
package instantticket;

import java.awt.event.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;




public class ManageAdmin extends javax.swing.JFrame {

    Connection con;
    PreparedStatement ps=null,ps1=null;
    Statement st=null;
    ResultSet rs;

   
    private void createConnection()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
           
            String url = "jdbc:mysql://localhost:3306/railway_database";
            con = DriverManager.getConnection(url,"root","root");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManageAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManageAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setClear()
    {
    adminId.setText("");
    name.setText("");
    address.setText("");
    phoneNo.setText("");
    gender.setSelectedIndex(0);
    password.setText("");
    password2.setText("");
    rs=null;
    ps=null;
    }
    
    public void insert()
    {
        try {
            if(rs.next())
            {
            adminId.setText(rs.getString(1));
            name.setText(rs.getString(2));
            address.setText(rs.getString(3));
            phoneNo.setText(rs.getString(5));
            if(rs.getString(4).equals("Male"))
            gender.setSelectedIndex(0);
            else
            gender.setSelectedIndex(1);
            password.setText(rs.getString(6));
            password2.setText(rs.getString(6));
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane,"Not Exist");
                setClear();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ManageAdmin() {
        initComponents();
        createConnection();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        adminId = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        password2 = new javax.swing.JPasswordField();
        name = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        phoneNo = new javax.swing.JTextField();
        clear = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        gender = new javax.swing.JComboBox<>();
        password = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADMIN LOGIN");
        setBackground(new java.awt.Color(255, 255, 51));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Re Enter Password");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 420, 140, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Phone No.");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 300, 80, -1));

        adminId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminIdActionPerformed(evt);
            }
        });
        jPanel4.add(adminId, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, 130, 30));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setText("CHECK ");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 180, 60, 30));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/High Speed Train_50px1.png"))); // NOI18N
        jLabel6.setText("INDIAN RAILWAYS");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, 410, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Admin Id");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 180, 70, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Name");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 220, 50, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Gender");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 340, 60, -1));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel10.setText("Address");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, 60, -1));

        password2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                password2ActionPerformed(evt);
            }
        });
        jPanel4.add(password2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 420, 210, 30));

        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        jPanel4.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 220, 210, 30));

        address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressActionPerformed(evt);
            }
        });
        jPanel4.add(address, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 260, 210, 30));

        phoneNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneNoActionPerformed(evt);
            }
        });
        jPanel4.add(phoneNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 300, 210, 30));

        clear.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        clear.setText("CLEAR");
        clear.setBorder(null);
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        jPanel4.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 500, 60, 40));

        update.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        update.setText("UPDATE");
        update.setBorder(null);
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        jPanel4.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 500, 70, 40));

        delete.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        delete.setText("DELETE");
        delete.setBorder(null);
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        jPanel4.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 500, 70, 40));

        gender.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MALE", "FEMALE" }));
        jPanel4.add(gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 340, 70, 30));

        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        jPanel4.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 380, 210, 30));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Password");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 380, 70, -1));

        add.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        add.setText("ADD NEW ADMIN");
        add.setBorder(null);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel4.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 500, 110, 40));

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton2.setText("SAVE");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 500, 50, 40));

        jButton4.setBackground(new java.awt.Color(230, 230, 163));
        jButton4.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jButton4.setText("Home");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 30, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/instantticket/pic.jpg"))); // NOI18N
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 720));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adminIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adminIdActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
           
            ps=con.prepareStatement("select * from admin where idadmin=?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ps.setInt(1,Integer.parseInt(adminId.getText()));
            rs=ps.executeQuery();
            insert();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane,"Some thing wrong");
            Logger.getLogger(ManageAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void password2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_password2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_password2ActionPerformed

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressActionPerformed

    private void phoneNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneNoActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        setClear();
        
    }//GEN-LAST:event_clearActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        try {
            if(rs==null)
            {
            JOptionPane.showMessageDialog(rootPane,"please search first");    
            }
            else
            {
            rs.updateString(2,name.getText());
            rs.updateString(3,address.getText());
            rs.updateString(4,String.valueOf(gender.getSelectedItem()));
            rs.updateInt(5,Integer.parseInt(phoneNo.getText()));
            rs.updateString(6,String.valueOf(password.getPassword()));
            if(String.valueOf(password.getPassword()).equals(String.valueOf(password2.getPassword())))
            {rs.updateRow();
            JOptionPane.showMessageDialog(rootPane,"Updated");
            setClear();
            }
            else
                JOptionPane.showMessageDialog(rootPane,"Passwors dont match");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane,"Some thing wrong");
            Logger.getLogger(ManageAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        try {
            if(rs==null)
            {
            JOptionPane.showMessageDialog(rootPane,"please search first");    
            }
            else
            { rs.deleteRow();
            JOptionPane.showMessageDialog(rootPane,"deleted");
            setClear();}
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane,"Some thing wrong");
            Logger.getLogger(ManageAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        setClear();
        ps=null;
    }//GEN-LAST:event_addActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

            if(String.valueOf(password.getPassword()).equals(String.valueOf(password2.getPassword())))
            {
                try {
                    ps=con.prepareStatement("insert into admin values(?,?,?,?,?,?)");
                     ps.setInt(1,Integer.parseInt(adminId.getText()));
                      ps.setString(2,name.getText());
                       ps.setString(3,address.getText());
                       ps.setString(4,String.valueOf(gender.getSelectedItem()));
                       ps.setInt(5,Integer.parseInt(phoneNo.getText()));
                       ps.setString(6,String.valueOf(password.getPassword()));
                        ps.executeUpdate();
                    JOptionPane.showMessageDialog(rootPane,"SuccessFully Registerd");
                    setClear();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane,"Some thing wrong");
                    Logger.getLogger(ManageAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
                JOptionPane.showMessageDialog(rootPane,"Passwors dont match");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        AdminHome hp = new AdminHome();
        hp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JTextField address;
    private javax.swing.JTextField adminId;
    private javax.swing.JButton clear;
    private javax.swing.JButton delete;
    private javax.swing.JComboBox<String> gender;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField name;
    private javax.swing.JPasswordField password;
    private javax.swing.JPasswordField password2;
    private javax.swing.JTextField phoneNo;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
