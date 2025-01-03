/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package SearchBar;

import java.util.ArrayList;

import ContentCreation.Profile;
import GroupManagement.Admin;
import GroupManagement.Group;
import GroupManagement.GroupCreatorDialogue;
import GroupManagement.GroupRequest;
import GroupManagement.GroupUser;
import GroupManagement.GroupUserDialogue;
import GroupManagement.PrimaryGroupAdmin;
import UserManagementSystem.Message;

/**
 *
 * @author dandy
 */
public class GroupResult extends javax.swing.JDialog {

    /**
     * Creates new form GroupResult
     */
    private ArrayList<Group> groups;
    private Profile user;
    private int iterator;
    public GroupResult(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public GroupResult(java.awt.Frame parent, boolean modal,ArrayList <Group>groups,Profile user) {
        super(parent, modal);
        this.user = user;
        this.groups = groups;
        this.iterator =0;
        initComponents();
        init();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Next = new javax.swing.JButton();
        Back = new javax.swing.JButton();
        GroupName = new javax.swing.JLabel();
        JoinGroup = new javax.swing.JButton();
        LeaveGroup = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        JoinGroup.setText("Join Group");
        JoinGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JoinGroupActionPerformed(evt);
            }
        });

        LeaveGroup.setText("Leave Group");
        LeaveGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeaveGroupActionPerformed(evt);
            }
        });

        jButton1.setText("View Group");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(Back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Next)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(LeaveGroup)
                .addGap(30, 30, 30)
                .addComponent(JoinGroup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(GroupName)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(216, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JoinGroup)
                    .addComponent(LeaveGroup)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Next)
                    .addComponent(Back))
                .addGap(18, 18, 18))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(GroupName)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void init(){
        boolean flag = true;
        ArrayList <Group>joinedGroups = user.getGroups();
        for(Group group:joinedGroups){
            if(group.getId()==this.groups.get(iterator).getId()){
                this.JoinGroup.setVisible(false);
                this.LeaveGroup.setVisible(true);
                this.jButton1.setVisible(true);
                flag = false;
                break;
            }
        }
        if(flag){
            this.LeaveGroup.setVisible(false);
            this.jButton1.setVisible(false);
            this.JoinGroup.setVisible(true);
        }
        this.GroupName.setText(this.groups.get(iterator).getName());
    }
    
    private void NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_NextActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BackActionPerformed

    private void LeaveGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeaveGroupActionPerformed
        this.groups.get(this.iterator).removeUser(new GroupUser(user, this.groups.get(this.iterator)));
        Message m = new Message(null, true,"You left this group");
        m.setLocationRelativeTo(this);
        m.setVisible(true);
        dispose();
    }//GEN-LAST:event_LeaveGroupActionPerformed

    private void JoinGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JoinGroupActionPerformed
        GroupRequest gr = new GroupRequest(user.getUserId());
        gr.sendRequest(this.groups.get(this.iterator));
        Message m = new Message(null, true,"Sent a join request");
        m.setLocationRelativeTo(this);
        m.setVisible(true);
        dispose();
    }//GEN-LAST:event_JoinGroupActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
        Group group = groups.get(iterator);
        Admin [] adminss =  group.getAdmin();
        for(Admin admin : adminss){
            if(admin.getUserId().compareTo(user.getUserId())==0){
                PrimaryGroupAdmin creator = new PrimaryGroupAdmin(user, group);
                GroupCreatorDialogue gp = new GroupCreatorDialogue(null, true,group,creator,false);
                gp.setLocationRelativeTo(this);
                gp.setTitle("Admin UI");
                gp.setVisible(true);
                dispose();
            }
        }
        if((group.getPrimaryAdmin().getUserId().compareTo(user.getUserId())==0)){
            PrimaryGroupAdmin creator = new PrimaryGroupAdmin(user, group);
            GroupCreatorDialogue gp = new GroupCreatorDialogue(null, true,group,creator,true);
            gp.setLocationRelativeTo(this);
            gp.setTitle("Primary Admin UI");
            gp.setVisible(true);
            dispose();
        }
        for(GroupUser usering: group.getUsers()){
            if(usering.getUserId().compareTo(this.user.getUserId())==0){
        
            GroupUserDialogue gp = new GroupUserDialogue(user,group);
            gp.setLocationRelativeTo(this);
            gp.setTitle("User UI");
            gp.setVisible(true);
            dispose();
            }
        }
    }catch(IndexOutOfBoundsException e){

    }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(GroupResult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GroupResult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GroupResult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GroupResult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GroupResult dialog = new GroupResult(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton Back;
    private javax.swing.JLabel GroupName;
    private javax.swing.JButton JoinGroup;
    private javax.swing.JButton LeaveGroup;
    private javax.swing.JButton Next;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
