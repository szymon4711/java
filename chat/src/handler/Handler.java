package handler;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Handler implements Runnable {
    public static ArrayList<Handler> handlers = new ArrayList<>();
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private Socket socket;
    private String username;


    public Handler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = bufferedReader.readLine();
            handlers.add(this);
            message(this.username + " - new user");
        } catch (IOException e) {
            removerUser(socket, bufferedWriter, bufferedReader);
        }
    }

    public void message(String message) {
        for (Handler handler : handlers) {
            try {
                if (!handler.username.equals(username)) {
                    handler.bufferedWriter.write(message);
                    handler.bufferedWriter.newLine();
                    handler.bufferedWriter.flush();
                }
            } catch (IOException e) {
                removerUser(socket, bufferedWriter, bufferedReader);
            }
        }
    }

    public void removerUser(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) {
        handlers.remove(this);
        message(username + "disconnected\n");
        try {
            if (socket != null)
                socket.close();
            if (bufferedWriter != null)
                bufferedWriter.close();
            if (bufferedReader != null)
                bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String message;
        while (!socket.isClosed()) {
            try {
                message = bufferedReader.readLine();
                message(message);
            } catch (IOException e) {
                removerUser(socket, bufferedWriter, bufferedReader);
                break;
            }
        }
    }
}

