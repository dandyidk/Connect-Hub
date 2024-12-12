package GroupManagement;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ContentCreation.ContentMedia;
import ContentCreation.Profile;
import ContentCreation.json;

public class Group {
    private String name;
    private String description;
    private String groupPhoto;
    private PrimaryGroupAdmin primaryAdmin;
    private Admin[] admins;
    private GroupUser[] users;
    private ContentMedia[] contents;
    private int id;
    private GroupRequest[] requests;

    public Group(String name, String description, String groupPhoto, PrimaryGroupAdmin primaryAdmin) {
        this.name = name;
        this.description = description;
        this.groupPhoto = groupPhoto;
        this.primaryAdmin = primaryAdmin;
        this.users = users;
        this.id = 1;
        this.primaryAdmin.setGroup(this);

    }

    public Group(String id, String name, String description, String groupPhoto, PrimaryGroupAdmin primaryAdmin,
            GroupUser[] users, Admin[] admins, ContentMedia[] contents) {
        this.name = name;
        this.description = description;
        this.groupPhoto = groupPhoto;
        this.primaryAdmin = primaryAdmin;
        this.admins = admins;
        this.users = users;
        this.id = 1;
        this.primaryAdmin.setGroup(this);
    }

    public Group(String id, String name, String description, String groupPhoto, PrimaryGroupAdmin primaryAdmin,
            ArrayList<GroupUser> users, ArrayList<Admin> admins, ContentMedia[] contents) {
        this.name = name;
        this.description = description;
        this.groupPhoto = groupPhoto;
        this.primaryAdmin = primaryAdmin;
        this.admins = new Admin[admins.size()];
        for (int i = 0; i < admins.size(); i++) {
            this.admins[i] = admins.get(i);
        }
        this.users = new GroupUser[users.size()];
        for (int i = 0; i < users.size(); i++) {
            this.users[i] = users.get(i);
        }
        this.id = Integer.parseInt(id);
        this.primaryAdmin.setGroup(this);
    }

    public String getName() {
        return this.name;
    }

    public void setContents(ContentMedia[] contents) {
        this.contents = contents;
    }

    public void setId(String Id) {
        this.id = Integer.parseInt(Id);
    }

    public String getDescription() {
        return this.description;
    }

    public String getGroupPhoto() {
        return this.groupPhoto;
    }

    public GroupRequest[] getRequests() {
        return this.requests;
    }

    public void setRequests(GroupRequest[] req){
        this.requests = req;
    }

    public ContentMedia[] getContents() {
        return this.contents;
    }

    public PrimaryGroupAdmin getPrimaryAdmin() {
        return this.primaryAdmin;
    }

    public Admin[] getAdmin() {
        return this.admins;
    }

    public int getId() {
        return this.id;

    }

    public GroupUser[] getUsers() {
        return this.users;
    }

    public void setPrimaryAdmin(PrimaryGroupAdmin profile) {
        this.primaryAdmin = profile;
    }

    public void addAdmin(Admin profile) {
        ArrayList<Admin> temp = new ArrayList<Admin>();
        for (int i = 0; i < admins.length; i++) {
            temp.add(this.admins[i]);
        }
        temp.add(profile);

        admins = new Admin[temp.size()];
        for (int i = 0; i < admins.length; i++) {
            admins[i] = temp.get(i);
        }
        ArrayList<GroupUser> temp1 = new ArrayList<GroupUser>();

        for (int i = 0; i < users.length; i++) {
            if (!users[i].getUserId().equals(profile.getUserId())) {
                temp1.add(this.users[i]);
            }
        }
        users = new GroupUser[temp1.size()];
        for (int i = 0; i < users.length; i++) {
            users[i] = temp1.get(i);
        }
        json js = new json();
        js.put("User Id", profile.getUserId());
        js.put("User Status", "Admin");
        js.submitGroupArray(Integer.toString(this.id), "Group Users", "User Id", profile.getUserId());

    }

    public void addUser(GroupUser profile) {
        ArrayList<GroupUser> temp = new ArrayList<GroupUser>();
        for (int i = 0; i < users.length; i++) {
            temp.add(this.users[i]);
        }
        temp.add(profile);
        users = new GroupUser[temp.size()];

        for (int i = 0; i < temp.size(); i++) {
            this.users[i] = temp.get(i);
        }

        json js = new json();
        js.put("User Id", profile.getUserId());
        js.put("User Status", "User");
        js.submitGroupArray(Integer.toString(this.id), "Group Users", "User Id", profile.getUserId());

    }

    public void removeAdmin(Admin profile) {
        ArrayList<Admin> temp = new ArrayList<Admin>();
        for (int i = 0; i < admins.length; i++) {
            if (profile.getUserId().compareTo(admins[i].getUserId()) != 0) {
                temp.add(this.admins[i]);
            }
        }

        admins = new Admin[temp.size()];
        for (int i = 0; i < admins.length; i++) {
            admins[i] = temp.get(i);
        }

        json js = new json();
        js.put("User Id", profile.getUserId());
        js.put("User Status", "User");
        js.submitGroupArray(Integer.toString(this.id), "Group Users", "User Id", profile.getUserId());
    }

    public void removeUser(GroupUser profile) {
        ArrayList<GroupUser> temp = new ArrayList<GroupUser>();
        for (int i = 0; i < users.length; i++) {
            if (profile.getUserId().compareTo(users[i].getUserId()) != 0) {
                temp.add(this.users[i]);
            }
        }

        users = new GroupUser[temp.size()];
        for (int i = 0; i < users.length; i++) {
            users[i] = temp.get(i);
        }

        json js = new json();
        js.put("User Id", profile.getUserId());
        js.put("User Status", "Removed");
        js.submitGroupArray(Integer.toString(this.id), "Group Users", "User Id", profile.getUserId());
    }

    public void deleteContent(ContentMedia content) {
        ArrayList<ContentMedia> temp = new ArrayList<ContentMedia>();
        for (int i = 0; i < contents.length; i++) {
            if (content.getUserId().compareTo(contents[i].getUserId()) != 0) {
                temp.add(this.contents[i]);
            }
        }
        contents = new ContentMedia[temp.size()];
        for (int i = 0; i < contents.length; i++) {
            contents[i] = temp.get(i);
        }
        json js = new json();
        js.put("Content ID", "null");
        js.put("Author ID",content.getUserId());
        js.put("timeStamp",content.getTimeStamp());
        js.put("Content Text",content.getContent().getText());
        js.put("Content Image",content.getContent().getImage());
        js.submitGroupArray(Integer.toString(this.id), "Contents", "Content ID", content.getUserId());

    }
}
