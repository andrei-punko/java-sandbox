package by.andd3dfx.sockets;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class SocketServer implements AutoCloseable {

    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
        log.info("SERVER: Waiting for a client...");

        socket = serverSocket.accept();              // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
        log.info("SERVER: Got a client :) ... Finally, someone saw me through all the cover!");

        // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());

        String line = null;
        while (true) {
            line = dataInputStream.readUTF();               // ожидаем пока клиент пришлет строку текста.
            log.debug("SERVER: The client just sent me this line: {}", line);
            log.debug("SERVER: I'm sending it back in uppercase...");
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
