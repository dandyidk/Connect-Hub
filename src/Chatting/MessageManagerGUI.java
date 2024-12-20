package Chatting;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MessageManagerGUI extends JFrame {

    private JTextArea messagesArea;
    private JTextArea newMessageField;
    private JButton loadMessagesButton;
    private JButton sendMessageButton;

    private String senderId;
    private String receiverId;

    private static final String DEFAULT_FILE_PATH = "C:\\Users\\mohamed\\OneDrive\\Desktop\\test\\Connect-Hub\\Chats.json";

    public MessageManagerGUI(String senderId, String receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        initUI();
    }

    private void initUI() {
        // Frame properties
        setTitle("Message Manager");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
                            messagesBuilder.append(message.getString("timestamp"))
                                    .append(" - ")
                                    .append(message.getString("senderId"))
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

                JOptionPane.showMessageDialog(MessageManagerGUI.this,
                        "Message sent successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                newMessageField.setText("");
                messagesArea.append("You: " + messageText + "\n");

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
            // first number is the sender and the second is the receiver
            MessageManagerGUI gui = new MessageManagerGUI("2", "1");
            gui.setVisible(true);
        });
    }
}
