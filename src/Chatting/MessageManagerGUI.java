package Chatting;

import org.json.JSONArray;
import org.json.JSONObject;

import ContentCreation.FILELOCATION;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class MessageManagerGUI extends JFrame implements FILELOCATION {

    private JTextArea messagesArea;
    private JTextArea newMessageField;
    private JButton loadMessagesButton;
    private JButton sendMessageButton;

    private String senderId;
    private String receiverId;

    private static final String DEFAULT_FILE_PATH = CHATS;
    private static final String USERS_FILE_PATH = DATABASE;

    private Map<String, String> userMap;

    public MessageManagerGUI(String senderId, String receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        loadUserData();
        initUI();
    }

    // Load user data from Users.json
    private void loadUserData() {
        userMap = new HashMap<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(USERS_FILE_PATH)));
            JSONObject userData = new JSONObject(content);
            JSONArray users = userData.getJSONArray("Users");

            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);
                String userId = user.getString("User Id");
                String username = user.getString("Username");
                userMap.put(userId, username);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error loading user data: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initUI() {
        // Frame properties
        setTitle("Message Manager");
        setSize(600, 400);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Center panel for displaying messages
        JPanel centerPanel = new JPanel(new BorderLayout());
        messagesArea = new JTextArea();
        messagesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messagesArea);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel for composing and sending messages
        JPanel bottomPanel = new JPanel(new BorderLayout());
        newMessageField = new JTextArea(3, 20);
        bottomPanel.add(new JScrollPane(newMessageField), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        loadMessagesButton = new JButton("Load Messages");
        loadMessagesButton.addActionListener(new LoadMessagesAction());
        buttonPanel.add(loadMessagesButton);

        sendMessageButton = new JButton("Send Message");
        sendMessageButton.addActionListener(new SendMessageAction());
        buttonPanel.add(sendMessageButton);

        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private class LoadMessagesAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String content = new String(Files.readAllBytes(Paths.get(DEFAULT_FILE_PATH)));
                JSONObject chatData = new JSONObject(content);
                JSONArray chats = chatData.getJSONArray("chats");
                StringBuilder messagesBuilder = new StringBuilder();

                for (int i = 0; i < chats.length(); i++) {
                    JSONObject chat = chats.getJSONObject(i);
                    JSONArray users = chat.getJSONArray("users");

                    if ((users.getString(0).equals(senderId) && users.getString(1).equals(receiverId)) ||
                        (users.getString(0).equals(receiverId) && users.getString(1).equals(senderId))) {
                        JSONArray messages = chat.getJSONArray("messages");
                        for (int j = 0; j < messages.length(); j++) {
                            JSONObject message = messages.getJSONObject(j);
                            String sender = users.getString(0).equals(message.getString("senderId")) ? users.getString(0) : users.getString(1);
                            String senderName = userMap.get(sender);
                            messagesBuilder.append(message.getString("timestamp"))
                                    .append(" - ")
                                    .append(senderName)
                                    .append(": ")
                                    .append(message.getString("messageText"))
                                    .append("\n");
                        }
                        break;
                    }
                }

                messagesArea.setText(messagesBuilder.toString());

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(MessageManagerGUI.this,
                        "Error loading messages: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class SendMessageAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String messageText = newMessageField.getText().trim();

            if (messageText.isEmpty()) {
                JOptionPane.showMessageDialog(MessageManagerGUI.this,
                        "Please enter a message!",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                MessageSender.FILE_PATH = DEFAULT_FILE_PATH;
                MessageSender messageSender = new MessageSender();
                Message message = new Message(senderId, receiverId, messageText);
                messageSender.sendMessage(message);

                // Append message in chat view with sender's name
                String senderName = userMap.get(senderId);
                JOptionPane.showMessageDialog(MessageManagerGUI.this,
                        "Message sent successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                newMessageField.setText("");
                messagesArea.append(senderName + ": " + messageText + "\n");

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(MessageManagerGUI.this,
                        "Error sending message: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Example: Sender ID = "2", Receiver ID = "1"
            MessageManagerGUI gui = new MessageManagerGUI("2", "1");
            gui.setVisible(true);
        });
    }
}
