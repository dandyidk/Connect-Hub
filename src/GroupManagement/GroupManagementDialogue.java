/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GroupManagement;

import java.util.ArrayList;

import ContentCreation.Profile;

/**
 *
 * @author dandy
 */
public class GroupManagementDialogue extends javax.swing.JFrame {

    /**
     * Creates new form GroupManagementDialogue
     */
    private Profile user;
    private ArrayList <Group> groups;
    private int iterator;
    
    public GroupManagementDialogue() {
        initComponents();
    }

    public GroupManagementDialogue(Profile user) {
        this.user = user;
        this.groups = user.getGroups();
        this.iterator =0;
        initComponents();
        showGroups();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Back = new javax.swing.JButton();
        Next = new javax.swing.JButton();
        Open = new javax.swing.JButton();
        GroupName = new javax.swing.JLabel();
        createGroup = new javax.swing.JButton();
        joinGroup = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Back.setText("Back");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        Next.setText("Next");
        Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }
        });

        Open.setText("Open");
        Open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenActionPerformed(evt);
            }
        });

        createGroup.setText("Create group");
        createGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createGroupActionPerformed(evt);
            }
        });

        joinGroup.setText("Join Group");
        joinGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinGroupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Back)
                        .addGap(79, 79, 79)
                        .addComponent(Open))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(createGroup)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(joinGroup)
                    .addComponent(Next))
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createGroup)
                    .addComponent(joinGroup))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Back)
                    .addComponent(Next)
                    .addComponent(Open))
                .addGap(16, 16, 16))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(GroupName)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void showGroups(){
        if(this.groups.size()!=0){
        GroupName.setText(groups.get(iterator).getName());
        }
    }

    private void OpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenActionPerformed
        Group group = groups.get(iterator);
        Admin [] adminss =  group.getAdmin();
        for(Admin admin : adminss){
            if(admin.getUserId().compareTo(user.getUserId())==0){
                PrimaryGroupAdmin creator = new PrimaryGroupAdmin(user, group);
                GroupCreatorDialogue gp = new GroupCreatorDialogue(this, true,group,creator,false);
                gp.setLocationRelativeTo(this);
                gp.setTitle("Admin UI");
                gp.setVisible(true);
            }
        }
        if((group.getPrimaryAdmin().getUserId().compareTo(user.getUserId())==0)){
            PrimaryGroupAdmin creator = new PrimaryGroupAdmin(user, group);
            GroupCreatorDialogue gp = new GroupCreatorDialogue(this, true,group,creator,true);
            gp.setLocationRelativeTo(this);
            gp.setTitle("Primary Admin UI");
            gp.setVisible(true);
        }
        
    }//GEN-LAST:event_OpenActionPerformed

    private void NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextActionPerformed
        if (groups.size() != 0){
        this.iterator = (this.iterator+1)%groups.size();
        showGroups();
        }
    }//GEN-LAST:event_NextActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        if (groups.size() != 0){
            this.iterator = (this.iterator-1)%groups.size();
            showGroups();
            }
    }//GEN-LAST:event_BackActionPerformed

    private void createGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createGroupActionPerformed
     CreateGroupDialogue cr = new CreateGroupDialogue(this.user);
     cr.setLocationRelativeTo(this);
     cr.setVisible(true);
    }//GEN-LAST:event_createGroupActionPerformed

    private void joinGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinGroupActionPerformed
        JoinGroupDialogue jp =  new JoinGroupDialogue(this, true,this.user);
        jp.setLocationRelativeTo(this);
        jp.setVisible(true);
    }//GEN-LAST:event_joinGroupActionPerformed



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
            java.util.logging.Logger.getLogger(GroupManagementDialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GroupManagementDialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GroupManagementDialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GroupManagementDialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GroupManagementDialogue().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JLabel GroupName;
    private javax.swing.JButton Next;
    private javax.swing.JButton Open;
    private javax.swing.JButton createGroup;
    private javax.swing.JButton joinGroup;
    // End of variables declaration//GEN-END:variables
}