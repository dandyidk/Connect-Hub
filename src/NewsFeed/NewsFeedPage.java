/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package NewsFeed;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


import Chatting.MessageManagerGUI;
import Commenting.CommentingPage;
import Commenting.LikeFactory;
import ContentCreation.Content;
import ContentCreation.ContentCreationDialogue;
import ContentCreation.ContentMedia;
import ContentCreation.FILELOCATION;
import ContentCreation.Message;
import ContentCreation.Profile;
import ContentCreation.json;
import FriendManagement.Friend;
import FriendManagement.FriendManagementPage;
import GroupManagement.Group;
import GroupManagement.GroupManagementDialogue;
import NotificationSystem.NotificationGUI;
import ProfileManagement.ProfileGUI;
import SearchBar.SearchBar;


/**
 *
 * @author dandy
 */
public class NewsFeedPage extends javax.swing.JFrame implements FILELOCATION{
    private ArrayList<ContentMedia> contents;
    private ArrayList<Friend> friends;
    private ArrayList<Profile> suggesstions;
    private ArrayList<Group> groupsuggesstions;
    private ArrayList<Group> groups;

    private String user;
    private int iterator;
    /**
     * Creates new form NewJFrame
     */
    public NewsFeedPage() {
        initComponents();
    }
    public NewsFeedPage(String user) {
        NewsFeed ns = new NewsFeed(user);
        this.contents = ns.fetchContent();
        this.friends = ns.fetchFriends();
        this.suggesstions = ns.fetchFriendsSuggesstion();
        this.groupsuggesstions=ns.fetchGroupSuggesstions();
        this.user = user;
        this.groups= json.readProfiles().get(user).getGroups();
        initComponents();
        this.iterator =0;
        try{
        showPost(this.contents.get(this.iterator));
        }catch(IndexOutOfBoundsException e){
        }
        showFriends();
        showSuggestedFriends();
        showSuggestedGroups();
        showGroups();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        Post = new javax.swing.JButton();
        Refresh = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        FriendSuggesstoins = new javax.swing.JTextArea();
        Image = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Next = new javax.swing.JButton();
        Back = new javax.swing.JButton();
        Logout = new javax.swing.JButton();
        ViewProfile = new javax.swing.JButton();
        Post1 = new javax.swing.JButton();
        Post2 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        GroupSuggesstions = new javax.swing.JTextArea();
        jTextField5 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        FriendLIst = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        Like = new javax.swing.JButton();
        LIkes = new javax.swing.JLabel();
        Chat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(51, 102, 255));
        jTextField3.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("Friends List");

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(222, 222, 222));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        Post.setBackground(new java.awt.Color(102, 51, 255));
        Post.setText("Post Content");
        Post.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PostActionPerformed(evt);
            }
        });

        Refresh.setBackground(new java.awt.Color(102, 51, 255));
        Refresh.setText("Refresh");
        Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(51, 102, 255));
        jTextField2.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("Friends Suggestions");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        FriendSuggesstoins.setEditable(false);
        FriendSuggesstoins.setBackground(new java.awt.Color(222, 222, 222));
        FriendSuggesstoins.setColumns(20);
        FriendSuggesstoins.setRows(5);
        jScrollPane3.setViewportView(FriendSuggesstoins);

        Image.setToolTipText("");
        Image.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        Next.setBackground(new java.awt.Color(0, 0, 0));
        Next.setFont(new java.awt.Font("Liberation Sans", 2, 15)); // NOI18N
        Next.setForeground(new java.awt.Color(255, 255, 255));
        Next.setText("Next");
        Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }
        });

        Back.setBackground(new java.awt.Color(0, 0, 0));
        Back.setFont(new java.awt.Font("Liberation Sans", 2, 15)); // NOI18N
        Back.setForeground(new java.awt.Color(255, 255, 255));
        Back.setText("Back");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        Logout.setBackground(new java.awt.Color(102, 51, 255));
        Logout.setText("Logout");
        Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutActionPerformed(evt);
            }
        });

        ViewProfile.setBackground(new java.awt.Color(102, 51, 255));
        ViewProfile.setText("ViewProfile");
        ViewProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewProfileActionPerformed(evt);
            }
        });

        Post1.setBackground(new java.awt.Color(102, 51, 255));
        Post1.setText("Friends");
        Post1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Post1ActionPerformed(evt);
            }
        });

        Post2.setBackground(new java.awt.Color(102, 51, 255));
        Post2.setText("Groups");
        Post2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Post2ActionPerformed(evt);
            }
        });

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(51, 102, 255));
        jTextField4.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setText("Group Suggestions");
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        GroupSuggesstions.setEditable(false);
        GroupSuggesstions.setBackground(new java.awt.Color(222, 222, 222));
        GroupSuggesstions.setColumns(20);
        GroupSuggesstions.setRows(5);
        jScrollPane4.setViewportView(GroupSuggesstions);

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(51, 102, 255));
        jTextField5.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setText("Group List");

        FriendLIst.setEditable(false);
        FriendLIst.setBackground(new java.awt.Color(222, 222, 222));
        FriendLIst.setColumns(20);
        FriendLIst.setRows(5);
        jScrollPane5.setViewportView(FriendLIst);

        jButton1.setBackground(new java.awt.Color(0, 153, 255));
        jButton1.setText("search");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 204), java.awt.Color.cyan, null, null));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(102, 51, 255));
        jButton2.setText("Notification");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Comment");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        Like.setBackground(new java.awt.Color(0, 0, 0));
        Like.setForeground(new java.awt.Color(255, 255, 255));
        Like.setText("Like");
        Like.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LikeActionPerformed(evt);
            }
        });

        Chat.setBackground(new java.awt.Color(102, 51, 255));
        Chat.setText("Chat");
        Chat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField3)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                                        .addComponent(jTextField5)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(Post1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(17, 17, 17)
                                            .addComponent(Post2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(10, 10, 10)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Chat)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Back, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(317, 317, 317)
                                .addComponent(jButton3)
                                .addGap(61, 61, 61)
                                .addComponent(Like)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LIkes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Next)
                                .addGap(37, 37, 37))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 954, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Image, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 867, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(Refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Logout, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 17, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Post)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ViewProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(1201, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(4, 4, 4)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Post1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addComponent(Post2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Image, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Back)
                                    .addComponent(Next, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3)
                                    .addComponent(Like)
                                    .addComponent(LIkes))
                                .addGap(52, 52, 52))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Chat))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Logout, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Post, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ViewProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(527, 527, 527)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(16, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PostActionPerformed
        ContentCreationDialogue d = new ContentCreationDialogue(this,true,json.readProfiles().get(user));
        d.setTitle("Create new content");
        d.setVisible(true);
    }//GEN-LAST:event_PostActionPerformed

    private void RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshActionPerformed
        NewsFeed ns = new NewsFeed(user);
        this.contents = ns.fetchContent();
        this.friends = ns.fetchFriends();
        this.suggesstions = ns.fetchFriendsSuggesstion();
        this.groupsuggesstions = ns.fetchGroupSuggesstions();
        this.groups = json.readProfiles().get(user).getGroups();
        this.iterator =0;
        try{
        showPost(contents.get(this.iterator));
        }catch(IndexOutOfBoundsException e){
            showPost(new ContentMedia(null,null,null,new Content(null, null)));
        }
        showFriends();
        showSuggestedFriends();
        showSuggestedGroups();
        showGroups();
    }//GEN-LAST:event_RefreshActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed

    }//GEN-LAST:event_jTextField2ActionPerformed

    private void NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextActionPerformed
        if(this.contents.size()!=0){
        try{
        this.iterator = (this.iterator+1)%this.contents.size();
        }catch(Exception e){}
        showPost(this.contents.get(this.iterator));
    }
    }//GEN-LAST:event_NextActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        if(this.contents.size()!=0){
        try{
        this.iterator = Math.abs((this.iterator-1)%this.contents.size());
        }catch(Exception e){}
        showPost(this.contents.get(this.iterator));
    }
    }//GEN-LAST:event_BackActionPerformed

    private void LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutActionPerformed
        Profile profile = json.readProfiles().get(user);
        profile.setStatus("Offline");
        json j= new json();
        j.put("User Id",profile.getUserId());
       j.put("Username",profile.getUsername());
       j.put("Email",profile.getEmail());
       j.put("HashedPassword", profile.getHashedpassword());
       j.put("Date of Birth", profile.getDateOfBirth());
       j.put("Status",profile.isStatus());
       j.submitProfile();
       dispose();   
       System.exit(ABORT);
    }//GEN-LAST:event_LogoutActionPerformed

    private void ViewProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewProfileActionPerformed
        ProfileGUI p = new ProfileGUI(Integer.parseInt(user));
        p.setLocationRelativeTo(this);
        p.setVisible(true);

    }//GEN-LAST:event_ViewProfileActionPerformed

    private void Post1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Post1ActionPerformed
        FriendManagementPage fr = new FriendManagementPage(this, true,json.readProfiles().get(user));
        fr.setTitle("Friend Management");
        fr.setLocationRelativeTo(this);
        fr.setVisible(true);
    }//GEN-LAST:event_Post1ActionPerformed

    private void Post2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Post2ActionPerformed
        GroupManagementDialogue gp = new GroupManagementDialogue(json.readProfiles().get(user));
        gp.setLocationRelativeTo(this);
        gp.setTitle("Group Management");
        gp.setVisible(true);
    }//GEN-LAST:event_Post2ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        SearchBar sb = new SearchBar(null, true,json.readProfiles().get(user));
        sb.setLocationRelativeTo(this);
        sb.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        NotificationGUI noti = new NotificationGUI(DATABASE,Integer.parseInt(this.user));
        noti.setLocationRelativeTo(this);
        noti.setVisible(true);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{
        CommentingPage pg = new CommentingPage(this,true,json.readProfiles().get(user),this.contents.get(iterator));
        pg.setLocationRelativeTo(this);
        pg.setTitle("All comments");
        pg.setVisible(true);
        }catch(IndexOutOfBoundsException e){
            
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void LikeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LikeActionPerformed
        
        LikeFactory liker  = new LikeFactory();
        if(liker.buildLike(user, this.contents.get(iterator))){
            Like.setBackground(new java.awt.Color(0, 0, 255));
            showLikes(this.contents.get(this.iterator));
        }
        else{
            Like.setBackground(new java.awt.Color(0, 0, 0));
        showLikes(this.contents.get(this.iterator));
        }
    }//GEN-LAST:event_LikeActionPerformed

    private void ChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChatActionPerformed
        String Reciever = JOptionPane.showInputDialog("enter the username");
        for(Profile profile:json.readProfiles().values()){
            if(profile.getUsername().equals(Reciever)){
                MessageManagerGUI msg = new MessageManagerGUI(user,profile.getUserId());
                msg.setLocationRelativeTo(this);
                msg.setVisible(true);
                break;
            }
        }
        Message m = new Message(null,true,"There is no user with that name");
       
    }//GEN-LAST:event_ChatActionPerformed


    private void showPost(ContentMedia content){
        Like.setBackground(new java.awt.Color(0, 0, 0));
        ImageIcon img = new ImageIcon(content.getContent().getImage());
                                                                                                                                                                                    
        Image.setIcon(img);
        try{
        jLabel1.setText("Made by: "+json.readProfiles().get(content.getUserId()).getUsername()+" on"+content.getTimeStamp()+"\n"+content.getContent().getText());
        }catch(Exception e){
            jLabel1.setText("");
        }
        if(content.getUserId()!=null){
        showLikes(content);
        }
    }
    private void showFriends(){
        String text = "";
        for(Friend friend: friends){
            if(friend.getStatus().compareTo("Friends")==0){
            text = text + friend.getUsername()+"  :  "+friend.isStatus()+"\n";
            }
        }
        FriendLIst.setText(text);
    }
    private void showGroups(){
        String text = "";
        for(Group group: this.groups){
           
            text = text + group.getName()+"\n\n";
            
        }
        jTextArea1.setText(text);
    }
    private void showSuggestedFriends(){
        String text = "";
        for(Profile friend: this.suggesstions){
            text = text + friend.getUsername()+"\n\n";
        }
        FriendSuggesstoins.setText(text);
    }
    private void showSuggestedGroups(){
        String text = "";
        for(Group group: this.groupsuggesstions){
            text = text + group.getName()+"\n\n";
        }
        GroupSuggesstions.setText(text);
    }
    private void showLikes(ContentMedia content){
        int text = 0;
        HashMap <String,Commenting.Like> likes = json.readLikess();
        for(Commenting.Like like :likes.values()){
            if((like.getContentId().compareTo(content.getContentId())==0)&&(like.getAuthorId().compareTo(content.getUserId())==0)){
            text++;
            if(like.getUserId().compareTo(user)==0){
                Like.setBackground(new java.awt.Color(0, 0, 255));
            }
            }
        }
        LIkes.setText(Integer.toString(text));
    }
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
            java.util.logging.Logger.getLogger(NewsFeedPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewsFeedPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewsFeedPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewsFeedPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewsFeedPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JButton Chat;
    private javax.swing.JTextArea FriendLIst;
    private javax.swing.JTextArea FriendSuggesstoins;
    private javax.swing.JTextArea GroupSuggesstions;
    private javax.swing.JLabel Image;
    private javax.swing.JLabel LIkes;
    private javax.swing.JButton Like;
    private javax.swing.JButton Logout;
    private javax.swing.JButton Next;
    private javax.swing.JButton Post;
    private javax.swing.JButton Post1;
    private javax.swing.JButton Post2;
    private javax.swing.JButton Refresh;
    private javax.swing.JButton ViewProfile;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
