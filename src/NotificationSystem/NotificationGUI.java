import javax.swing.*;
import java.awt.*;

public class NotificationGUI {
    
    // Constructor to initialize the GUI
    public NotificationGUI(String filePath, int userId) {
        // Initialize buffers for notifications
        StringBuilder friendRequestsDisplay = new StringBuilder();
        StringBuilder groupUsersDisplay = new StringBuilder();
        StringBuilder notificationsDisplay = new StringBuilder();

        // Create the main frame
        JFrame frame = new JFrame("Notifications");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);
        frame.setLayout(new BorderLayout());

        // Main panel for notifications
        JPanel notificationPanel = new JPanel();
        notificationPanel.setLayout(new BoxLayout(notificationPanel, BoxLayout.Y_AXIS));

        // Text areas for different types of notifications
        JTextArea friendRequestsArea = new JTextArea(10, 50);
        friendRequestsArea.setEditable(false);
        friendRequestsArea.setBorder(BorderFactory.createTitledBorder("Friend Requests"));

        JTextArea groupUsersArea = new JTextArea(10, 50);
        groupUsersArea.setEditable(false);
        groupUsersArea.setBorder(BorderFactory.createTitledBorder("Group Users"));

        JTextArea groupPostsArea = new JTextArea(10, 50);
        groupPostsArea.setEditable(false);
        groupPostsArea.setBorder(BorderFactory.createTitledBorder("Group Post Notifications"));

        // Add text areas to the notification panel
        notificationPanel.add(friendRequestsArea);
        notificationPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing
        notificationPanel.add(groupUsersArea);
        notificationPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing
        notificationPanel.add(groupPostsArea);

        JScrollPane friendRequestsScrollPane = new JScrollPane(friendRequestsArea);
        JScrollPane groupUsersScrollPane = new JScrollPane(groupUsersArea);
        JScrollPane groupPostsScrollPane = new JScrollPane(groupPostsArea);

        // Add scroll panes to the panel instead of text areas
        notificationPanel.add(friendRequestsScrollPane);
        notificationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        notificationPanel.add(groupUsersScrollPane);
        notificationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        notificationPanel.add(groupPostsScrollPane);


        // Refresh button
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            // Refresh the notifications
            Notification.refresh(filePath, userId, friendRequestsDisplay, groupUsersDisplay, notificationsDisplay);

            // Update text areas with refreshed data
            friendRequestsArea.setText(friendRequestsDisplay.toString());
            groupUsersArea.setText(groupUsersDisplay.toString());
            groupPostsArea.setText(notificationsDisplay.toString());
        });

        // Add components to the frame
        frame.add(notificationPanel, BorderLayout.CENTER);
        frame.add(refreshButton, BorderLayout.SOUTH);

        // Initial refresh to populate data
        /*Notification.refresh(filePath, userId, friendRequestsDisplay, groupUsersDisplay, notificationsDisplay);
        friendRequestsArea.setText(friendRequestsDisplay.toString());
        groupUsersArea.setText(groupUsersDisplay.toString());
        groupPostsArea.setText(notificationsDisplay.toString());*/

        // Display the frame
        frame.setVisible(true);
    }
    
    // Main method
    public static void main(String[] args) {
        // File path and user ID for testing
        String filePath = "C:\\Users\\mohamed\\OneDrive\\Desktop\\final\\profiles.json"; 
        int userId = 2;

        // Create the GUI
        new NotificationGUI(filePath, userId);
    }
}
