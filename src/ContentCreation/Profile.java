/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ContentCreation;


import java.util.ArrayList;
import java.util.HashMap;



import org.json.simple.JSONArray;

import FriendManagement.FriendRequests;
import GroupManagement.Admin;
import GroupManagement.Group;
import GroupManagement.GroupBuilder;
import GroupManagement.GroupUser;

/**
 *
 * @author ahmed
 */
public class Profile {
    protected String userId;
    protected String email;
    protected String username;
    protected String hashedpassword;
    protected String dateOfBirth;
    protected String status;
    protected JSONArray contents;
    protected JSONArray friends;
    protected JSONArray profile;
    protected JSONArray friendRequests;
    protected ContentMedia[] contents2;
    protected FriendRequests[] friendRequests2; 
    protected JSONArray groups;

    private GroupBuilder gp;

    public Profile(String userId, String email, String username, String hashedpassword, String dateOfBirth, String status) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.hashedpassword = hashedpassword;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
    }
    public Profile( String email, String username, String hashedpassword, String dateOfBirth) {
        this.email = email;
        this.username = username;
        this.hashedpassword = hashedpassword;
        this.dateOfBirth = dateOfBirth;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHashedpassword(String hashedpassword) {
        this.hashedpassword = hashedpassword;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setContents(JSONArray contents) {
        this.contents = contents;
    }
    public void setContents(ContentMedia[] contents) {
        this.contents2 = contents;
    }
    public void setGroups(JSONArray groups) {
        this.groups = groups;
    }
    public void setFriends(JSONArray friends) {
        this.friends = friends;
    }
    public void setFriendRequests(JSONArray friends) {
        this.friendRequests = friends;
    }
    public void setFriendRequests(FriendRequests[] friends) {
        this.friendRequests2 = friends;
    }
    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedpassword() {
        return hashedpassword;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public JSONArray getContents() {
        return contents;
    }
    public ArrayList<Group> getGroups() {
        HashMap <String,Group>groups =json.readGroups();
        ArrayList <Group> groupss = new ArrayList<Group>();
        for(Group groupie:groups.values()){
            GroupUser[] users = groupie.getUsers();
            for (int i =0;i<users.length;i++){
                if(users[i].getUserId().compareTo(this.userId)==0){
                    groupss.add(groupie);
                }
            }
            Admin[] admins = groupie.getAdmin();
            for (int i =0;i<admins.length;i++){
                if(admins[i].getUserId().compareTo(this.userId)==0){
                    groupss.add(groupie);
                }
            }
                if(groupie.getPrimaryAdmin().getUserId().compareTo(this.userId)==0){
                    groupss.add(groupie);
                
            }
        }
        
        return groupss;
    }
    public JSONArray getFriends() {
        return friends;
    }
    public JSONArray getFriendsRequests() {
        return friendRequests;
    }
    public String isStatus() {
        return status;
    }
    public void setProfile(JSONArray profile) {
        this.profile = profile;
    }

    public void constructGroup(String name,String description,String groupPhoto,Profile[] users){
        this.gp = new GroupBuilder(this);
        this.gp.buildGroup(name,description, groupPhoto);
    }
}

