package by.andd3dfx.sockets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClient implements AutoCloseable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocketClient.class);

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public void start(int serverPort, String address) throws IOException {
        InetAddress ipAddress = InetAddress.getByName(address); // создаем объект, который отображает вышеописанный IP-адрес
        System.out.println("CLIENT: Start socket client with IP address " + address + " and port " + serverPort);
        socket = new Socket(ipAddress, serverPort);      // создаем сокет используя IP-адрес и порт сервера

        // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом.
        // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    public void transmit(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.lines().forEach(line -> {
                System.out.println("CLIENT: Sending this line to the server: " + line);

                try {
                    dataOutputStream.writeUTF(line.toUpperCase());  // отсылаем введенную строку текста серверу
                    dataOutputStream.flush();                       // заставляем поток закончить передачу данных
                    String lineFromServer = dataInputStream.readUTF();// ждем пока сервер отошлет строку текста
                    System.out.println("CLIENT: received response from server: " + lineFromServer);
                } catch (IOException e) {
                    LOGGER.error("Error during data transmission", e);
                }
            });
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
    }
}
