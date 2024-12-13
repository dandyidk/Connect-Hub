package NotificationSystem;
import javax.swing.*;
import java.awt.*;

public class NotificationGUI extends JDialog{

    public NotificationGUI(java.awt.Frame parent, boolean modal,String filePath, int userId) {

        StringBuilder friendRequestsDisplay = new StringBuilder();
        StringBuilder groupUsersDisplay = new StringBuilder();
        StringBuilder notificationsDisplay = new StringBuilder();
        StringBuilder statusNotificationDisplay = new StringBuilder();


        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 900);
        setLayout(new BorderLayout());

        JPanel notificationPanel = new JPanel();
        notificationPanel.setLayout(new BoxLayout(notificationPanel, BoxLayout.Y_AXIS));

        JPanel friendRequestPanel = new JPanel();
        friendRequestPanel.setLayout(new BoxLayout(friendRequestPanel, BoxLayout.Y_AXIS));

        JTextArea groupUsersArea = new JTextArea(10, 50);
        groupUsersArea.setEditable(false);
        groupUsersArea.setBorder(BorderFactory.createTitledBorder("Group Users"));

        JTextArea groupPostsArea = new JTextArea(10, 50);
        groupPostsArea.setEditable(false);
        groupPostsArea.setBorder(BorderFactory.createTitledBorder("Group Post Notifications"));

        JTextArea statusNotificationsArea = new JTextArea(10, 50);
        statusNotificationsArea.setEditable(false);
        statusNotificationsArea.setBorder(BorderFactory.createTitledBorder("Group Status Change Notifications"));

        notificationPanel.add(friendRequestPanel);
        notificationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        notificationPanel.add(groupUsersArea);
        notificationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        notificationPanel.add(groupPostsArea);
        notificationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        notificationPanel.add(statusNotificationsArea);

        JScrollPane groupUsersScrollPane = new JScrollPane(groupUsersArea);
        JScrollPane groupPostsScrollPane = new JScrollPane(groupPostsArea);
        JScrollPane statusNotificationsScrollPane = new JScrollPane(statusNotificationsArea);

        notificationPanel.add(groupUsersScrollPane);
        notificationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        notificationPanel.add(groupPostsScrollPane);
        notificationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        notificationPanel.add(statusNotificationsScrollPane);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            Notification.refresh(filePath, userId, friendRequestsDisplay, groupUsersDisplay, notificationsDisplay, statusNotificationDisplay);

            friendRequestPanel.removeAll();
            for (String friendRequest : friendRequestsDisplay.toString().split("\n")) {
                JPanel requestPanel = new JPanel();
                requestPanel.setLayout(new FlowLayout());

                JLabel requestLabel = new JLabel(friendRequest);
                JButton acceptButton = new JButton("Accept");
                JButton deleteButton = new JButton("Delete");

                // Accept button action listener
acceptButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Extract profile ID from the label
        String profileId =Notification.getUserIdFromUsername(filePath,requestLabel.getText().split(":")[1].trim());
        System.out.println(profileId); 

        // Call backend method to accept the friend request
        Notification.acceptFriendRequest(filePath, userId, profileId);
            // Refresh GUI after successful operation
            JOptionPane.showMessageDialog( parent,"Friend request accepted: " + profileId);
            refreshButton.doClick();
            JOptionPane.showMessageDialog(parent,"Failed to accept friend request: " + profileId);

    }
});

// Delete button action listener
deleteButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Extract profile ID from the label
        String profileId =Notification.getUserIdFromUsername(filePath,requestLabel.getText().split(":")[1].trim());

        // Call backend method to delete the friend request
        Notification.deleteFriendRequest(filePath, userId, profileId);
        JOptionPane.showMessageDialog(parent, "Friend request deleted: " + profileId);
        refreshButton.doClick();
    }
});


                requestPanel.add(requestLabel);
                requestPanel.add(acceptButton);
                requestPanel.add(deleteButton);
                friendRequestPanel.add(requestPanel);
            }
            friendRequestPanel.revalidate();
            friendRequestPanel.repaint();

            groupUsersArea.setText(groupUsersDisplay.toString());
            groupPostsArea.setText(notificationsDisplay.toString());
            statusNotificationsArea.setText(statusNotificationDisplay.toString());
        });

        add(notificationPanel, BorderLayout.CENTER);
        add(refreshButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\mohamed\\OneDrive\\Desktop\\final\\profiles.json"; 
        int userId = 2;

        new NotificationGUI(null,true,filePath, userId);
    }
}
