/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instantticket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class TrainManager extends javax.swing.JFrame {

    /**
     * Creates new form TrainManager
     */
    public TrainManager() {
        initComponents();
        fillTrainJtable(jTable1,"");
    }
    
    final public void fillTrainJtable(JTable table,String value){
        
    try
        {

            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway_database", "root", "root");
            PreparedStatement ps;
            if(value=="")
            {
                 ps=con.prepareStatement("Select * from train ");
            }
            else
            {
                ps=con.prepareStatement("Select * from train WHERE CONCAT(idtrain,destination,baseFare,traintype,trainName) LIKE ?");
            ps.setString(1,"%"+value+"%");
            }
              
               
            ResultSet rs =ps.executeQuery();
            DefaultTableModel model =(DefaultTableModel) table.getModel();
            
            Object[] row;
            while(rs.next())
            {
                row = new Object[6];
                row[0]=rs.getInt(1);
                row[1]=rs.getString(2);
                row[2]="INDORE(INDB)";
                row[3]=rs.getString(4);
                row[4]=rs.getString(3);
                row[5]=rs.getFloat(5);
                
                model.addRow(row);
            }
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
    public void editTrain(char op,int id,String tname,String des,String type,float bf,String pp1,String pp2,String pp3,String pp4,int di1,int di2,int di3,int di4){
        
        if(op=='i')
        {
        try
        {

            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway_database", "root", "root");

            PreparedStatement st2=con.prepareStatement("Insert into train(idtrain,trainName,traintype,destination,baseFare) values(?,?,?,?,?)");
                st2.setInt(1,id);
                st2.setString(2,tname);
                st2.setString(3,type);
                st2.setString(4,des);
                st2.setFloat(5,bf);
                
                
                PreparedStatement st=con.prepareStatement("Insert into passes(stopNo,distance,idtrain,idstation) values(?,?,?,(select idstation from station where stationName like ?)),(?,?,?,(select idstation from station where stationName like ?)),(?,?,?,(select idstation from station where stationName like ?)),(?,?,?,(select idstation from station where stationName like ?))");
                st.setInt(1,1);
                st.setInt(2,di1);
                st.setInt(3,id);
                st.setString(4,pp1);
                st.setInt(5,2);
                st.setInt(6,di2);
                st.setInt(7,id);
                st.setString(8,pp2);
                st.setInt(9,3);
                st.setInt(10,di3);
                st.setInt(11,id);
                st.setString(12,pp3);
                st.setInt(13,4);
                st.setInt(14,di4);
                st.setInt(15,id);
                st.setString(16,pp4);
                  

                if(st2.executeUpdate()>0 && st.executeUpdate()>0){
                    JOptionPane.showMessageDialog(rootPane,"Added Successfully");
                }
            
            
            st2.close();
            con.close();
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
        else if(op=='u')
        {
        try
        {

            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway_database", "root", "root");

            PreparedStatement st2=con.prepareStatement("Update train set trainName=?,traintype=?,destination=?,baseFare=? WHERE idtrain=?");
                st2.setString(1,tname);
                st2.setString(2,type);
                st2.setString(3,des);
                st2.setFloat(4,bf);
                st2.setInt(5,id);
                
                if(st2.executeUpdate()>0)
                {
                   
                
                boolean a=false;
                int i;
            for(i=1;i<5;i++)
            {PreparedStatement st=con.prepareStatement("Update passes set distance=?,idstation=(select idstation from station where stationName like ?) WHERE idtrain=? and stopNo=?");
               if(i==1) 
               {st.setInt(1,di1);
                st.setInt(3,id);
                st.setString(2,pp1);
                st.setInt(4,1);}
                if(i==2){st.setInt(1,di2);
                st.setInt(3,id);
                st.setString(2,pp2);
                st.setInt(4,2);}
                if(i==3){st.setInt(1,di3);
                st.setInt(3,id);
                st.setString(2,pp1);
                st.setInt(4,3);}
                if(i==4){st.setInt(1,di4);
                st.setInt(3,id);
                st.setString(2,pp4);
                st.setInt(4,4);}
                if(st.executeUpdate()>0){
                    a=true;
                }
                else{a=false;}
                
                st.close();
                if(a){JOptionPane.showMessageDialog(null, "Station Updated");}
            }
           }
           else{
                   JOptionPane.showMessageDialog(null, "Station Doesn't Exist");
                }  
            
            
            st2.close();
            con.close();
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
        else if(op=='d')
        {
        try
        {

            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway_database", "root", "root");

            PreparedStatement st2=con.prepareStatement("Delete From train WHERE idtrain=?");
                
                st2.setInt(1,id);
                

                if(st2.executeUpdate()>0)
                {
                   JOptionPane.showMessageDialog(null, "Station Profile Deleted");
                }
                else{
                   JOptionPane.showMessageDialog(null, "Station doesn't exist");
                }
            
            
            st2.close();
            con.close();
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
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        name = new javax.swing.JTextPane();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        searchValue = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        dest = new javax.swing.JTextPane();
        jScrollPane10 = new javax.swing.JScrollPane();
        typ = new javax.swing.JTextPane();
        tid = new javax.swing.JTextField();
        p3 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        p4 = new javax.swing.JTextField();
        basefare2 = new javax.swing.JTextField();
        p1 = new javax.swing.JTextField();
        p2 = new javax.swing.JTextField();
        d3 = new javax.swing.JTextField();
        d4 = new javax.swing.JTextField();
        d2 = new javax.swing.JTextField();
        d1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TrainId", "Name", "Source", "Destination", "Type", "BaseFare"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 150, 660, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel3.setText("Train Id:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, -1, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel4.setText("Name :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel5.setText("Source:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel6.setText("Destination:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, -1, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel7.setText("Passes Through:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 440, -1, 30));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel8.setText("Type:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, -1, -1));

        jScrollPane8.setViewportView(name);

        jPanel1.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, 150, 30));

        jTextPane3.setEditable(false);
        jTextPane3.setText("INDORE(INDB)");
        jTextPane3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextPane3KeyTyped(evt);
            }
        });
        jScrollPane11.setViewportView(jTextPane3);

        jPanel1.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, 150, 30));

        jButton2.setBackground(new java.awt.Color(230, 230, 163));
        jButton2.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 610, -1, -1));

        jButton3.setBackground(new java.awt.Color(230, 230, 163));
        jButton3.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jButton3.setText("Edit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 610, -1, -1));

        jButton5.setBackground(new java.awt.Color(230, 230, 163));
        jButton5.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jButton5.setText("Add New");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 610, -1, -1));

        searchValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchValueActionPerformed(evt);
            }
        });
        searchValue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchValueKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchValueKeyTyped(evt);
            }
        });
        jPanel1.add(searchValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 110, 150, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel9.setText("Search: ");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, -1, -1));

        jScrollPane9.setViewportView(dest);

        jPanel1.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 310, 150, 30));

        jScrollPane10.setViewportView(typ);

        jPanel1.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 350, 150, 30));
        jPanel1.add(tid, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 150, 30));
        jPanel1.add(p3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 520, 150, 30));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/High Speed Train_50px1.png"))); // NOI18N
        jLabel2.setText("INDIAN RAILWAYS");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 500, 40));

        jButton4.setBackground(new java.awt.Color(230, 230, 163));
        jButton4.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jButton4.setText("Home");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 30, -1, -1));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jLabel10.setText("BaseFare:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 392, -1, 30));
        jPanel1.add(p4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 560, 150, 30));
        jPanel1.add(basefare2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, 150, 30));
        jPanel1.add(p1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 440, 150, 30));
        jPanel1.add(p2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 480, 150, 30));
        jPanel1.add(d3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 520, 50, 30));
        jPanel1.add(d4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 560, 50, 30));
        jPanel1.add(d2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, 50, 30));
        jPanel1.add(d1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 442, 50, 30));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextPane3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextPane3KeyTyped
        // TODO add your handling code here:
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_jTextPane3KeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int id = Integer.parseInt(tid.getText());

       editTrain('d',id, null, null,null,0.0f,null,null,null,null,0,0,0,0);
       jTable1.setModel(new DefaultTableModel(null, new Object[]{"TrainId","Name","Source","destination","Type","BaseFare"}));
       fillTrainJtable(jTable1, searchValue.getText());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
       String tname = name.getText();
       String ttype = typ.getText();
       int id = Integer.parseInt(tid.getText());
       String d = dest.getText();
       String pp1=p1.getText();
       String pp2=p2.getText();
       String pp3=p3.getText();
       String pp4=p4.getText();
       int di1=Integer.parseInt(d1.getText());
       int di2=Integer.parseInt(d2.getText());
       int di3=Integer.parseInt(d3.getText());
       int di4=Integer.parseInt(d4.getText());
       Float bf= Float.parseFloat(basefare2.getText());
     

        
        editTrain('u',id,tname,ttype,d,bf,pp1,pp2,pp3,pp4,di1,di2,di3,di4);
        jTable1.setModel(new DefaultTableModel(null, new Object[]{"TrainId","Name","Source","destination","Type","BaseFare"}));
        fillTrainJtable(jTable1, searchValue.getText());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String tname = name.getText();
       String ttype = typ.getText();
       int id = Integer.parseInt(tid.getText());
       String d = dest.getText();
       String pp1=p1.getText();
       String pp2=p2.getText();
       String pp3=p3.getText();
       String pp4=p4.getText();
       int di1=Integer.parseInt(d1.getText());
       int di2=Integer.parseInt(d2.getText());
       int di3=Integer.parseInt(d3.getText());
       int di4=Integer.parseInt(d4.getText());
       
       Float bf= Float.parseFloat(basefare2.getText());

        
        editTrain('i', id,tname, ttype,d,bf,pp1,pp2,pp3,pp4,di1,di2,di3,di4);
        jTable1.setModel(new DefaultTableModel(null, new Object[]{"TrainId","Name","Source","destination","Type","BaseFare"}));
        fillTrainJtable(jTable1, searchValue.getText());
    }//GEN-LAST:event_jButton5ActionPerformed

    private void searchValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchValueActionPerformed

    private void searchValueKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchValueKeyReleased
        // TODO add your handling code here:
        jTable1.setModel(new DefaultTableModel(null, new Object[]{"TrainId","Name","source","destination","Type","BaseFare"}));
        fillTrainJtable(jTable1, searchValue.getText());
    }//GEN-LAST:event_searchValueKeyReleased

    private void searchValueKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchValueKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_searchValueKeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        AdminHome hp = new AdminHome();
        hp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int rowIndex = jTable1.getSelectedRow();
        DefaultTableModel model= (DefaultTableModel) jTable1.getModel();
        tid.setText(model.getValueAt(rowIndex,0).toString());
        name.setText(model.getValueAt(rowIndex,1).toString());
        dest.setText(model.getValueAt(rowIndex,3).toString());
        typ.setText(model.getValueAt(rowIndex,4).toString());
        basefare2.setText(model.getValueAt(rowIndex,5).toString());
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(TrainManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrainManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrainManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrainManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrainManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField basefare2;
    private javax.swing.JTextField d1;
    private javax.swing.JTextField d2;
    private javax.swing.JTextField d3;
    private javax.swing.JTextField d4;
    private javax.swing.JTextPane dest;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane jTextPane3;
    private javax.swing.JTextPane name;
    private javax.swing.JTextField p1;
    private javax.swing.JTextField p2;
    private javax.swing.JTextField p3;
    private javax.swing.JTextField p4;
    private javax.swing.JTextField searchValue;
    private javax.swing.JTextField tid;
    private javax.swing.JTextPane typ;
    // End of variables declaration//GEN-END:variables
}
