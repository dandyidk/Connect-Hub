/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GroupManagement;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import ContentCreation.ContentMedia;
import ContentCreation.Profile;
import ContentCreation.json;
import UserManagementSystem.Message;

/**
 *
 * @author dandy
 */
public class GroupContentManagement extends javax.swing.JDialog {

    /**
     * Creates new form GroupContentManagement
     */
    private Group group;
    private Profile profile;
    private ContentMedia[] contents;
    private int iterator;
    private boolean isCreator;
    public GroupContentManagement(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public GroupContentManagement(java.awt.Frame parent, boolean modal, Group group, Profile profile,boolean isCreator) {
        super(parent, modal);
        this.group = group;
        this.profile = profile;
        ArrayList <ContentMedia> contents = new ArrayList<ContentMedia>();
        for(int i =0;i<group.getContents().length;i++)
        {
            if (group.getContents()[i].getContentId().compareTo("null")!=0){
                contents.add(group.getContents()[i]);
            }
        }
        this.contents = new ContentMedia[contents.size()];
        for(int i =0;i<contents.size();i++)
        {
            this.contents[i]=  contents.get(i);
        }
        this.iterator =0;
        initComponents();
        this.isCreator = isCreator;
        showPost();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AddPost = new javax.swing.JButton();
        Next = new javax.swing.JButton();
        Back = new javax.swing.JButton();
        Edit = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        Image = new javax.swing.JLabel();
        Text = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        AddPost.setText("Add Post");
        AddPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPostActionPerformed(evt);
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

        Edit.setText("Edit");
        Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditActionPerformed(evt);
            }
        });

        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        Text.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Back)
                        .addGap(166, 166, 166)
                        .addComponent(Edit)
                        .addGap(173, 173, 173)
                        .addComponent(Delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Next)
                        .addGap(47, 47, 47))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(Text, javax.swing.GroupLayout.PREFERRED_SIZE, 756, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 104, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AddPost)
                .addGap(77, 77, 77))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(AddPost)
                .addGap(18, 18, 18)
                .addComponent(Image, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(Text, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Edit)
                    .addComponent(Delete)
                    .addComponent(Next)
                    .addComponent(Back))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void showPost() {
        if(contents.length !=0){
            if(contents[iterator].getContentId().compareTo("null")!=0){
        ImageIcon img = new ImageIcon(contents[iterator].getContent().getImage());

        Image.setIcon(img);
        Text.setText("Made by: " + json.readProfiles().get(contents[iterator].getUserId()).getUsername() + " on"
                + contents[iterator].getTimeStamp() + "\n" + contents[iterator].getContent().getText());
        }

    }
    
        this.Delete.setVisible(isCreator);
        this.Edit.setVisible(isCreator);
        
    
    }

    private void AddPostActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_AddPostActionPerformed
        ContentCreationDialogue cd = new ContentCreationDialogue(null, true,profile,group);
        cd.setLocationRelativeTo(this);
        cd.setVisible(true);
        dispose();
    }// GEN-LAST:event_AddPostActionPerformed

    private void NextActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_NextActionPerformed
        if (contents.length != 0){
            this.iterator = (this.iterator+1)%contents.length;
            showPost();
            }
    }// GEN-LAST:event_NextActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BackActionPerformed
        if (contents.length != 0 && iterator!=0 ){
            this.iterator = (this.iterator-1)%contents.length;
            showPost();
            }
    }// GEN-LAST:event_BackActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_DeleteActionPerformed
        group.deleteContent(contents[iterator]);
        Message m=new Message(null, true,"Post deleted");
        m.setLocationRelativeTo(this);
        m.setVisible(true);
        dispose();
    }// GEN-LAST:event_DeleteActionPerformed

    private void EditActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_EditActionPerformed
        ContentEditDialogue ce = new ContentEditDialogue(null, true,this.profile,this.group,contents[iterator]);
        ce.setLocationRelativeTo(this);
        ce.setVisible(true);
        dispose();
    }// GEN-LAST:event_EditActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GroupContentManagement.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GroupContentManagement.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GroupContentManagement.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GroupContentManagement.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GroupContentManagement dialog = new GroupContentManagement(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton AddPost;
    private javax.swing.JButton Back;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Edit;
    private javax.swing.JLabel Image;
    private javax.swing.JButton Next;
    private javax.swing.JLabel Text;
    // End of variables declaration//GEN-END:variables
}
