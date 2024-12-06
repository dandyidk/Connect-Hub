package ProfileManagement;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ContentCreation.FILELOCATION;

public class ProfileGUI extends JFrame implements FILELOCATION {

    private JTextField bioTextField;
    private JPasswordField passwordField;
    private JButton viewProfileDetailsButton;
    private JButton updatePasswordButton;
    private JLabel profilePictureLabel, coverPictureLabel;
    private JButton updateProfileButton, updateCoverButton, updateBioButton;
    private JButton viewPostsButton, viewFriendsButton;
    private String filePath; // Path to the  file
    private int userId;

    public ProfileGUI(int userId) {
        this.userId = userId;
        this.filePath =DATABASE;

        setTitle("User Profile Management");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set layout for the frame
        setLayout(new BorderLayout());

        // Top panel for profile photo and cover photo
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        profilePictureLabel = new JLabel("Profile Picture");
        coverPictureLabel = new JLabel("Cover Picture");
        topPanel.add(profilePictureLabel);
        topPanel.add(coverPictureLabel);
        add(topPanel, BorderLayout.NORTH);

        // Center panel for profile details
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5, 2));

        ///Bio text field
        bioTextField = new JTextField();
        bioTextField.setEditable(false);
        centerPanel.add(new JLabel("Bio:"));
        centerPanel.add(bioTextField);

        // Password field
        passwordField = new JPasswordField();
        centerPanel.add(new JLabel("New Password:"));
        centerPanel.add(passwordField);

        // Buttons to update profile and bio
        updateProfileButton = new JButton("Change Profile Photo");
        updateCoverButton = new JButton("Change Cover Photo");
        updateBioButton = new JButton("Update Bio");
        updatePasswordButton = new JButton("Update Password");
        viewProfileDetailsButton = new JButton("View Profile Details");
        
        

        centerPanel.add(updateProfileButton);
        centerPanel.add(updateCoverButton);
        centerPanel.add(updateBioButton);
        centerPanel.add(updatePasswordButton);

        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel for displaying posts and friends
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        viewPostsButton = new JButton("View Posts");
        viewFriendsButton = new JButton("View Friends");

        bottomPanel.add(viewPostsButton);
        bottomPanel.add(viewFriendsButton);
        bottomPanel.add(viewProfileDetailsButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Action Listeners for buttons
        updateProfileButton.addActionListener(e -> updateProfilePhoto());
        updateCoverButton.addActionListener(e -> updateCoverPhoto());
        updateBioButton.addActionListener(e -> updateBio());
        updatePasswordButton.addActionListener(e -> updatePassword());
        viewPostsButton.addActionListener(e -> viewPosts());
        viewFriendsButton.addActionListener(e -> viewFriends());
        viewProfileDetailsButton.addActionListener(e -> viewProfileDetails());

        
    }

    private void updateProfilePhoto() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Profile Photo");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String profilePhotoPath = selectedFile.getAbsolutePath();
            ProfileManagement.updateProfileField(filePath, userId, "Profile Picture", profilePhotoPath);
            displayProfilePhoto(profilePhotoPath);
        }
    }

    private void updateCoverPhoto() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Cover Photo");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String coverPhotoPath = selectedFile.getAbsolutePath();
            ProfileManagement.updateProfileField(filePath, userId, "Cover Picture", coverPhotoPath);
            displayCoverPhoto(coverPhotoPath);
        }
    }

    private void updateBio() {
        String newBio = JOptionPane.showInputDialog("enter the new bio");
        ProfileManagement.updateProfileField(filePath, userId, "Bio", newBio);
    }

    private void updatePassword() {
        String newPassword = new String(passwordField.getPassword());
        if (newPassword.length() >= 8 ) {
            ProfileManagement.updatePassword(newPassword, filePath, userId);
        }else{
            JOptionPane.showMessageDialog(this, "Password must be at least 8 characters long");
        }
        
    }

    private void viewProfileDetails() {
        JSONObject userProfile = ProfileManagement.getUserProfile(filePath, userId);
        if (userProfile != null) {
    
            // Retrieve Profile information from the Profile array
            JSONArray profileArray = (JSONArray) userProfile.get("Profile");
            
            String bio = "No bio available.";
            String profilePicturePath = "No profile picture available.";
            String coverPicturePath = "No cover picture available.";
            
            // Loop through Profile array and extract bio, profile picture, and cover picture
            for (Object profileObject : profileArray) {
                JSONObject profile = (JSONObject) profileObject;
                if (profile.containsKey("Bio") && bio.equals("No bio available.")) {
                    bio = (String) profile.get("Bio");
                }
                if (profile.containsKey("Profile Picture") && profilePicturePath.equals("No profile picture available.")) {
                    profilePicturePath = (String) profile.get("Profile Picture");
                }
                if (profile.containsKey("Cover Picture") && coverPicturePath.equals("No cover picture available.")) {
                    coverPicturePath = (String) profile.get("Cover Picture");
                }
            }
    
            // Display Bio
            bioTextField.setText(bio);
            JOptionPane.showMessageDialog(this,"Your Bio is: " + bio);
    
            // Display Profile Picture
            if (!profilePicturePath.equals("No profile picture available.") && new File(profilePicturePath).exists()) {
                displayProfilePhoto(profilePicturePath);
            } else {
                profilePictureLabel.setText("Profile picture not found.");
                profilePictureLabel.setIcon(null);
            }
    
            // Display Cover Picture
            if (!coverPicturePath.equals("No cover picture available.") && new File(coverPicturePath).exists()) {
                displayCoverPhoto(coverPicturePath);
            } else {
                coverPictureLabel.setText("Cover picture not found.");
                coverPictureLabel.setIcon(null);
            }
    
        } else {
            JOptionPane.showMessageDialog(this, "User profile not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    

    private void viewPosts() {
    JSONObject jsonData = ProfileManagement.jsonObjReader(filePath);
    if (jsonData != null) {
        JSONArray usersArray = (JSONArray) jsonData.get("Users");

        // Find the user
        for (Object userObject : usersArray) {
            JSONObject user = (JSONObject) userObject;
            int id = Integer.parseInt((String) user.get("User Id"));

            if (id == userId) {
                JSONArray postsArray = (JSONArray) user.get("Content");

                if (postsArray != null && !postsArray.isEmpty()) {
                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                    for (Object postObject : postsArray) {
                        JSONObject post = (JSONObject) postObject;
                        String contentText = (String) post.get("Content Text");
                        String contentImage = (String) post.get("Content Image");

                        JPanel postPanel = new JPanel();
                        postPanel.setLayout(new BorderLayout());

                        // Add text content
                        JLabel textLabel = new JLabel("<html><b>Post:</b> " + contentText + "</html>");
                        postPanel.add(textLabel, BorderLayout.NORTH);

                        // Add image content if exists
                        if (contentImage != null && !contentImage.isEmpty()) {
                            File imageFile = new File(contentImage);
                            if (imageFile.exists()) {
                                ImageIcon imageIcon = new ImageIcon(contentImage);
                                Image scaledImage = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                                JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
                                postPanel.add(imageLabel, BorderLayout.CENTER);
                            } else {
                                postPanel.add(new JLabel("Image not found: " + contentImage), BorderLayout.CENTER);
                            }
                        }

                        postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                        panel.add(postPanel);
                    }

                    JScrollPane scrollPane = new JScrollPane(panel);
                    scrollPane.setPreferredSize(new Dimension(400, 400));
                    JOptionPane.showMessageDialog(this, scrollPane, "User Posts", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No posts found for this user.", "User Posts", JOptionPane.INFORMATION_MESSAGE);
                }
                return;
            }
        }
    }

    JOptionPane.showMessageDialog(this, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
}


    

    private void viewFriends() {
        StringBuilder friendsDisplay = new StringBuilder();
        ProfileManagement.getUserFriends(filePath, userId, friendsDisplay);

        JTextArea friendsArea = new JTextArea(friendsDisplay.toString());
        friendsArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(friendsArea);
        JOptionPane.showMessageDialog(this, scrollPane, "User Friends", JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayProfilePhoto(String photoPath) {
        ImageIcon profileIcon = new ImageIcon(photoPath);
        Image profileImage = profileIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        profilePictureLabel.setIcon(new ImageIcon(profileImage));
    }

    private void displayCoverPhoto(String photoPath) {
        ImageIcon coverIcon = new ImageIcon(photoPath);
        Image coverImage = coverIcon.getImage().getScaledInstance(600, 150, Image.SCALE_SMOOTH);
        coverPictureLabel.setIcon(new ImageIcon(coverImage));
    }
}
