/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UserManagementSystem;

import java.util.HashMap;

import ContentCreation.Profile;
import ContentCreation.json;
import NewsFeed.NewsFeedPage;

/**
 *
 * @author ahmed
 */
public class Loginform extends javax.swing.JFrame {

    public Loginform() {
        initComponents();
    }


    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        Backbtn = new javax.swing.JButton();
        Loginbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setBackground(new java.awt.Color(51, 255, 51));
        jLabel2.setText("Password");

        jLabel3.setText("Email");

        Backbtn.setText("Back");
        Backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackbtnActionPerformed(evt);
            }
        });

        Loginbtn.setText("Login");
        Loginbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(Backbtn)
                                                .addGap(90, 90, 90)
                                                .addComponent(Loginbtn))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 65,
                                                                Short.MAX_VALUE)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(63, 63, 63)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(emailField)
                                                        .addComponent(jPasswordField1,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 99,
                                                                Short.MAX_VALUE))))
                                .addContainerGap(114, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(69, 69, 69)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Backbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Loginbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(80, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoginbtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_LoginbtnActionPerformed
        String email = emailField.getText();
        String password = new String(jPasswordField1.getPassword());
        if (password.isEmpty()) {
            Message m = new Message(this, true, "invalid ,cant be empty");
            m.setVisible(true);
        } else if (Validation.isvalidEmail(email) == false) {
            Message m = new Message(this, true, "invalid email");
            m.setVisible(true);
        } else if (Validation.isvalidEmail(email) == false) {
            Message m = new Message(this, true, "Invalid: Email Address Can't Be Empty");
            m.setVisible(true);
        } else {
            password = PasswordHasher.hashPassword(password);
            HashMap<String, Profile> profiles = json.readProfiles();
            boolean loginSuccessful = false;
            Profile user = new User(email, email, password, password);

            for (int i = 0; i < json.readProfiles().size(); i++) {
                if (email.compareTo(profiles.get(Integer.toString(i)).getEmail()) == 0
                        && password.compareTo(profiles.get(Integer.toString(i)).getHashedpassword()) == 0) {
                            user = profiles.get(Integer.toString(i));
                    loginSuccessful = true;
                    break;
                }
            }
            if (loginSuccessful) {
                User us=new User(user.getUserId(),user.getEmail(), user.getUsername(), user.getHashedpassword(), user.getDateOfBirth(),user.isStatus());
                us.login();
                Message m = new Message(this, true, "Login Successful!");
                m.setVisible(true);
                NewsFeedPage nf = new NewsFeedPage(user.getUserId());
                nf.setTitle("News Feed");
                nf.setVisible(true);
                dispose();
            } else {
                Message m = new Message(this, true, "Invalid email or password.");
                m.setVisible(true);
            }
        }

    }// GEN-LAST:event_LoginbtnActionPerformed

    private void BackbtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BackbtnActionPerformed
        Firstpage f = new Firstpage();
        f.setVisible(true);
        dispose();
    }// GEN-LAST:event_BackbtnActionPerformed

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
            java.util.logging.Logger.getLogger(Loginform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loginform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loginform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loginform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Loginform().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Backbtn;
    private javax.swing.JButton Loginbtn;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField1;
    // End of variables declaration//GEN-END:variables
}
