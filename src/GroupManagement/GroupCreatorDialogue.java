/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GroupManagement;

import UserManagementSystem.Message;

/**
 *
 * @author dandy
 */
public class GroupCreatorDialogue extends javax.swing.JDialog {

    /**
     * Creates new form GroupCreatorDialogue
     */
    private Group group;
    private PrimaryGroupAdmin user;
    private int iterator;
    private boolean isCreator;
    public GroupCreatorDialogue(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    public GroupCreatorDialogue(java.awt.Frame parent, boolean modal,Group group,PrimaryGroupAdmin user,boolean isCreator) {
        super(parent, modal);
        this.group = group;
        this.user = user;
        this.iterator=0;
        this.isCreator = isCreator;

        
        initComponents();
        showMembers();
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
        Promote = new javax.swing.JButton();
        Demote = new javax.swing.JButton();
        Members = new javax.swing.JLabel();
        ManagePosts = new javax.swing.JButton();
        deleteGRoup = new javax.swing.JButton();
        JOinRequests = new javax.swing.JButton();

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

        Promote.setText("Promote");
        Promote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PromoteActionPerformed(evt);
            }
        });

        Demote.setText("Demote");
        Demote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DemoteActionPerformed(evt);
            }
        });

        ManagePosts.setText("Manage posts");
        ManagePosts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManagePostsActionPerformed(evt);
            }
        });

        deleteGRoup.setText("Delete group");
        deleteGRoup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteGRoupActionPerformed(evt);
            }
        });

        JOinRequests.setText("Join Requests");
        JOinRequests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOinRequestsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(Back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Next))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Demote)
                        .addGap(51, 51, 51)
                        .addComponent(Promote)
                        .addGap(81, 81, 81))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Members)
                        .addGap(174, 174, 174))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ManagePosts)
                .addGap(39, 39, 39)
                .addComponent(JOinRequests)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteGRoup))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ManagePosts)
                    .addComponent(deleteGRoup)
                    .addComponent(JOinRequests))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addComponent(Members)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Promote)
                    .addComponent(Demote))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Next)
                    .addComponent(Back))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void showMembers(){
            if((this.group.getUsers().length!=0)&&iterator<this.group.getUsers().length){
            Members.setText(group.getUsers()[this.iterator].getUsername()+" -User");
            }
            else if((this.group.getAdmin().length!=0 && isCreator)){
                Members.setText(group.getAdmin()[this.iterator-this.group.getUsers().length].getUsername()+" -Admin");
            }
            else{
                this.iterator =0;
            }
    }

    private void ManagePostsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManagePostsActionPerformed
        GroupContentManagement gp = new GroupContentManagement(null, true,group,user);
        gp.setLocationRelativeTo(this);
        gp.setVisible(true);
    }//GEN-LAST:event_ManagePostsActionPerformed

    private void deleteGRoupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteGRoupActionPerformed
        if(isCreator){
        user.deleteGroup(Integer.toString(group.getId()));
        Message m = new Message(null, true,"Group Deleted");
        m.setLocationRelativeTo(this);
        m.setVisible(true);
        dispose();
        }else{
            Message m = new Message(null, true,"Insufficient permissions to delete this group");
            m.setLocationRelativeTo(this);
            m.setVisible(true);
        }
    }//GEN-LAST:event_deleteGRoupActionPerformed

    private void DemoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DemoteActionPerformed
        if(this.iterator<this.group.getUsers().length){
            group.removeUser(group.getUsers()[iterator]);
            Message m = new Message(null, true,"User successfully removed");
            m.setLocationRelativeTo(this);
            m.setVisible(true);
            
            this.dispose();
        }
        else if((this.iterator-this.group.getUsers().length<this.group.getAdmin().length)&& isCreator){
            user.demote(group.getAdmin()[iterator-this.group.getUsers().length]);
            Message m = new Message(null, true,"Admin successfully demoted");
            m.setLocationRelativeTo(this);
            m.setVisible(true);
            
            this.dispose();
        }
        else{
            Message m = new Message(null, true,"Insufficient permissions to demote another admin");
            m.setLocationRelativeTo(this);
            m.setVisible(true);
        }
    }//GEN-LAST:event_DemoteActionPerformed

    private void PromoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PromoteActionPerformed
        if((this.iterator<this.group.getUsers().length)&&isCreator){
        user.promote(group.getUsers()[iterator]);
        Message m = new Message(null, true,"User successfully promoted to admin");
        m.setLocationRelativeTo(this);
        m.setVisible(true);
        
        this.dispose();
        }
        else{
            Message m = new Message(null, true,"Insufficient permissions to promote");
            m.setLocationRelativeTo(this);
            m.setVisible(true);
        }
    }//GEN-LAST:event_PromoteActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        if (this.group.getUsers().length != 0 &&this.iterator !=0){
            this.iterator = (this.iterator-1)%(this.group.getUsers().length+this.group.getAdmin().length);
            showMembers();
        }
        else if (this.group.getAdmin().length != 0 && this.iterator!=0){
            this.iterator = (this.iterator-1)%(this.group.getUsers().length+this.group.getAdmin().length);
            showMembers();
        }
    }//GEN-LAST:event_BackActionPerformed

    private void NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextActionPerformed
        if (this.group.getUsers().length != 0){
            this.iterator = (this.iterator+1)%(this.group.getUsers().length+this.group.getAdmin().length);
            showMembers();
        }
        else if (this.group.getAdmin().length != 0){
            this.iterator = (this.iterator+1)%(this.group.getUsers().length+this.group.getAdmin().length);
            showMembers();
        }
    }//GEN-LAST:event_NextActionPerformed

    private void JOinRequestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JOinRequestsActionPerformed
        JoinRequestsDialogue jr = new JoinRequestsDialogue(null, true,this.group);
        jr.setTitle("Requests");
        jr.setLocationRelativeTo(this);
        jr.setVisible(true);
    }//GEN-LAST:event_JOinRequestsActionPerformed

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
            java.util.logging.Logger.getLogger(GroupCreatorDialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GroupCreatorDialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GroupCreatorDialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GroupCreatorDialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GroupCreatorDialogue dialog = new GroupCreatorDialogue(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton Demote;
    private javax.swing.JButton JOinRequests;
    private javax.swing.JButton ManagePosts;
    private javax.swing.JLabel Members;
    private javax.swing.JButton Next;
    private javax.swing.JButton Promote;
    private javax.swing.JButton deleteGRoup;
    // End of variables declaration//GEN-END:variables
}