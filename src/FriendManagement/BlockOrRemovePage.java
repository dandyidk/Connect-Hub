/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package FriendManagement;

import java.util.HashMap;

import ContentCreation.Profile;
import ContentCreation.json;
import UserManagementSystem.Message;

/**
 *
 * @author dandy
 */
public class BlockOrRemovePage extends javax.swing.JDialog {

    /**
     * Creates new form BlockOrRemovePage
     */
    private Profile user;
    public BlockOrRemovePage(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    public BlockOrRemovePage(java.awt.Frame parent, boolean modal,Profile user) {
        super(parent, modal);
        initComponents();
        this.user=user;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FriendRequests = new javax.swing.JButton();
        FriendRequests1 = new javax.swing.JButton();
        Username = new javax.swing.JTextField();
        UserID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        FriendRequests.setText("Block");
        FriendRequests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FriendRequestsActionPerformed(evt);
            }
        });

        FriendRequests1.setText("Remove");
        FriendRequests1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FriendRequests1ActionPerformed(evt);
            }
        });

        jLabel1.setText("UserName");

        jLabel2.setText("User ID (Optional)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(FriendRequests)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FriendRequests1)
                .addGap(25, 25, 25))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1)
                        .addGap(59, 59, 59))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(UserID)
                    .addComponent(Username, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(103, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FriendRequests)
                    .addComponent(FriendRequests1))
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FriendRequestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FriendRequestsActionPerformed   this blocks
        Profile profile = new Profile(getName(), getTitle(), getWarningString(), getName()); //just to initialize somthin lol
        
        String name = Username.getText();
        String ID = UserID.getText();
        if(json.readProfiles().containsKey(ID)){
            profile = json.readProfiles().get(ID);
        }
        else{
            HashMap <String,Profile>profiletemp = json.readProfiles();
            for(int i = 0;i<profiletemp.size();i++){
                if(profiletemp.get(Integer.toString(i)).getUsername().compareTo(name)==0){
                    profile = profiletemp.get(Integer.toString(i));
                }
            }
        }
        AddingFriendsService add = new AddingFriendsService(this.user);
        add.blockFriend(profile);
        Message m = new Message(null, true,"Friend blocked");
        m.setLocationRelativeTo(this);
        m.setVisible(true);
    }//GEN-LAST:event_FriendRequestsActionPerformed

    private void FriendRequests1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FriendRequests1ActionPerformed  this is remove
        Profile profile = new Profile(getName(), getTitle(), getWarningString(), getName()); //just to initialize somthin lol
        
        String name = Username.getText();
        String ID = UserID.getText();
        if(json.readProfiles().containsKey(ID)){
            profile = json.readProfiles().get(ID);
        }
        else{
            HashMap <String,Profile>profiletemp = json.readProfiles();
            for(int i = 0;i<profiletemp.size();i++){
                if(profiletemp.get(Integer.toString(i)).getUsername().compareTo(name)==0){
                    profile = profiletemp.get(Integer.toString(i));
                }
            }
        }
        AddingFriendsService add = new AddingFriendsService(this.user);
        add.removeFriend(profile);
        Message m = new Message(null, true,"Friend removed");
        m.setLocationRelativeTo(this);
        m.setVisible(true);
    }//GEN-LAST:event_FriendRequests1ActionPerformed

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
            java.util.logging.Logger.getLogger(BlockOrRemovePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BlockOrRemovePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BlockOrRemovePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BlockOrRemovePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BlockOrRemovePage dialog = new BlockOrRemovePage(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton FriendRequests;
    private javax.swing.JButton FriendRequests1;
    private javax.swing.JTextField UserID;
    private javax.swing.JTextField Username;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
