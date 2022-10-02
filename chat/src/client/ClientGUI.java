package client;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class ClientGUI {
    private JTextArea textArea1;
    private JPanel panel1;
    private JTextField textField1;
    private JButton button1;
    private JLabel label;

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    public ClientGUI(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            removeUser(socket, bufferedWriter, bufferedReader);
        }
        button1.addActionListener(e -> {
            try {
                if (username == null || username.equals("")) {
                    username = textField1.getText();
                    bufferedWriter.write(username);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    label.setText("Enter your message:");
                } else if (socket.isConnected()) {
                    String message = textField1.getText();
                    textArea1.append(message + "\n");
                    bufferedWriter.write("@" + username + " -> " + message);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
            } catch (IOException er) {
                removeUser(socket, bufferedWriter, bufferedReader);
            }
        });
    }

    public void removeUser(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) {

        try {
            if (socket != null)
                socket.close();
            if (bufferedReader != null)
                bufferedReader.close();
            if (bufferedWriter != null)
                bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void awaitMessage() {
        new Thread(() -> {
            String message;
            while (socket.isConnected()) {
                try {
                    message = bufferedReader.readLine();
                    textArea1.append(message + "\n");
                } catch (IOException e) {
                    removeUser(socket, bufferedWriter, bufferedReader);
                }
            }
        }).start();
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9999);
        JFrame frame = new JFrame("chat");
        ClientGUI clientGUI = new ClientGUI(socket);
        frame.setContentPane(clientGUI.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        clientGUI.awaitMessage();
    }
}
