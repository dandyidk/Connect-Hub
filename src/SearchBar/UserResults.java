/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package SearchBar;

import java.util.ArrayList;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ContentCreation.Profile;
import ContentCreation.json;
import FriendManagement.AddingFriendsService;
import FriendManagement.FriendRequests;

import UserManagementSystem.Message;

/**
 *
 * @author dandy
 */
public class UserResults extends javax.swing.JDialog {

    /**
     * Creates new form UserResults
     */
    private ArrayList<Profile> users;
    private Profile user;
    private int iterator;
    public UserResults(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public UserResults(java.awt.Frame parent, boolean modal,ArrayList <Profile>users,Profile user) {
        super(parent, modal);
        this.user = user;
        this.users = users;
        this.iterator =0;
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BlockUser = new javax.swing.JButton();
        Next = new javax.swing.JButton();
        Back = new javax.swing.JButton();
        RemoveFriend = new javax.swing.JButton();
        AddFriend = new javax.swing.JButton();
        ViewUser = new javax.swing.JButton();
        GroupName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        BlockUser.setText("Block User");
        BlockUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlockUserActionPerformed(evt);
            }
        });

        Next.setText("Next");
        Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }
        });

        Back.setText("Back");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        RemoveFriend.setText("Remove Friend");
        RemoveFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveFriendActionPerformed(evt);
            }
        });

        AddFriend.setText("Add Friend");
        AddFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddFriendActionPerformed(evt);
            }
        });

        ViewUser.setText("View User");
        ViewUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(Back)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Next)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(AddFriend)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RemoveFriend)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BlockUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ViewUser)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(228, 228, 228)
                    .addComponent(GroupName)
                    .addContainerGap(229, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 234, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RemoveFriend)
                    .addComponent(AddFriend)
                    .addComponent(BlockUser)
                    .addComponent(ViewUser))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Next)
                    .addComponent(Back)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(150, 150, 150)
                    .addComponent(GroupName)
                    .addContainerGap(150, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        private void init(){
        boolean flag = true;
        JSONArray temp = user.getFriends();
        ArrayList <Profile> friends = new ArrayList<>();
        for(int i = 0;i<temp.size();i++){
            friends.add(json.readProfiles().get(((JSONObject)temp.get(i)).get("Friend ID")));
        }
        
        if(this.users.get(iterator).getUserId().compareTo(this.user.getUserId())==0){
                this.AddFriend.setVisible(false);
                this.RemoveFriend.setVisible(false);
                this.BlockUser.setVisible(false);
                flag = false;
        }
        for(Profile friend:friends){
            if(friend.getUserId()==this.users.get(iterator).getUserId()){
                this.AddFriend.setVisible(false);
                this.RemoveFriend.setVisible(true);
                this.BlockUser.setVisible(true);
                flag = false;
                break;
            }

        }

        if(flag){
            this.AddFriend.setVisible(true);
            this.RemoveFriend.setVisible(false);
            this.BlockUser.setVisible(true);
        }
        this.GroupName.setText(this.users.get(iterator).getUsername());
    }

    private void BlockUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BlockUserActionPerformed
        Profile friend = this.users.get(iterator);

        AddingFriendsService add = new AddingFriendsService(this.user);
        add.blockFriend(friend);
        Message m = new Message(null, true,"Friend blocked");
        m.setLocationRelativeTo(this);
        m.setVisible(true);
    }//GEN-LAST:event_BlockUserActionPerformed

    private void NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NextActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BackActionPerformed

    private void RemoveFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveFriendActionPerformed
        Profile friend = this.users.get(iterator);
        

        AddingFriendsService add = new AddingFriendsService(this.user);
        add.removeFriend(friend);
        Message m = new Message(null, true,"Friend removed");
        m.setLocationRelativeTo(this);
        m.setVisible(true);
        dispose();
    }//GEN-LAST:event_RemoveFriendActionPerformed

    private void AddFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddFriendActionPerformed
        Profile friend = this.users.get(iterator);
        
        FriendRequests Fr = new FriendRequests("Pending", friend, this.user);
        Fr.sendFriendRequest();
        Message m = new Message(null, true,"Friend request sent");
        m.setLocationRelativeTo(this);
        m.setVisible(true);
        dispose();
    }//GEN-LAST:event_AddFriendActionPerformed

    private void ViewUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewUserActionPerformed
        ViewProfileDialogue vs = new ViewProfileDialogue(null, true,this.users.get(iterator));
        vs.setLocationRelativeTo(this);
        vs.setVisible(true);
        dispose();
    }//GEN-LAST:event_ViewUserActionPerformed

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
            java.util.logging.Logger.getLogger(UserResults.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserResults.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserResults.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserResults.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UserResults dialog = new UserResults(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton AddFriend;
    private javax.swing.JButton Back;
    private javax.swing.JButton BlockUser;
    private javax.swing.JLabel GroupName;
    private javax.swing.JButton Next;
    private javax.swing.JButton RemoveFriend;
    private javax.swing.JButton ViewUser;
    // End of variables declaration//GEN-END:variables
}
