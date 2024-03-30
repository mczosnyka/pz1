package pz1.michalczosnyka;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientManager {
    public SocketChannel sc;
    public Scanner s;

    public static ClientManager i = new ClientManager();

    public ClientManager() {
        try {
            sc = SocketChannel.open();
            sc.connect(new InetSocketAddress("localhost", 8080));
            s = new Scanner(System.in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage() {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(2048);
            sc.read(buffer);  // Odczytaj wiadomość powitalną od serwera
            buffer.flip();
            return new String(buffer.array()).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendMessageToServer(String message) {
        try {
            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8));
            sc.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
