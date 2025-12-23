package by.andd3dfx.sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements AutoCloseable {

    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
        System.out.println("SERVER: Waiting for a client...");

        socket = serverSocket.accept();              // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
        System.out.println("SERVER: Got a client :) ... Finally, someone saw me through all the cover!");
        System.out.println();

        // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());

        String line = null;
        while (true) {
            line = dataInputStream.readUTF();               // ожидаем пока клиент пришлет строку текста.
            System.out.println("SERVER: The client just sent me this line: " + line);
            System.out.println("SERVER: I'm sending it back in uppercase...");
            dataOutputStream.writeUTF(line.toUpperCase());  // отсылаем клиенту обратно ту самую строку текста в uppercase.
            dataOutputStream.flush();                       // заставляем поток закончить передачу данных.
        }
    }

    @Override
    public void close() throws IOException {
        if (dataInputStream != null) {
            dataInputStream.close();
        }
        if (dataOutputStream != null) {
            dataOutputStream.close();
        }
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
        if (serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
        }
    }
}
