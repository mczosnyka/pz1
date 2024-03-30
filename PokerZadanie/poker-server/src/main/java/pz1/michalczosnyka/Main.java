package pz1.michalczosnyka;

import lombok.Getter;
import pz1.michalczosnyka.Game;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    @Getter
    private static final List<SocketChannel> connectedClients = new ArrayList<>();
    private static final List<Integer> readyChecks = new ArrayList<>();
    private static final Map<SocketChannel, Queue<ByteBuffer>> pendingData = new HashMap<>();
    @Getter
    private static int a = 0;

    private static Game game;
    public static void main(String[] args) throws IOException {
        Scanner s = PokerScanner.scanner;
        System.out.println("Wpisz ile graczy będzie uczestniczyło w grze (od 2 do 4)");
        a = s.nextInt();
        while(a<2 || a>4){
            System.out.println("Podaj odpowiednią liczbę graczy (od 2 do 4)");
            a = s.nextInt();
        }
        System.out.printf("Gra zostanie skonfigurowana na %d graczy, teraz klienci mogą się połączyć", a);

        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("localhost", 8080));
        ssc.configureBlocking(false);
        ssc.register(selector, SelectionKey.OP_ACCEPT);





        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();

            for (Iterator<SelectionKey> it = keys.iterator(); it.hasNext(); ) {

                SelectionKey sk = it.next();
                it.remove();

                if (sk.isValid() && sk.isAcceptable() && connectedClients.size()<(a*2)) {
                    accept(selector, ssc);
                    if(connectedClients.size() % 2 == 0){
                        sendToAll("Aktualna liczba graczy: " + (connectedClients.size() /2 ) + "\n");
                    }
                    if(connectedClients.size() == a*2){
                        sendToAll("Serwer osiągnął już podaną liczbę graczy, potwierdź gotowość wpisując '1': \n");
                    }
                }
                else if (sk.isValid() && sk.isReadable()) {
                    get(sk);
                }
            }
        }
    }

    public static void accept(Selector selector, ServerSocketChannel serverSocket) throws IOException{
        SocketChannel client = serverSocket.accept();
        if (client != null) {
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            connectedClients.add(client);
            String welcomeMessage = "Witaj! Jesteś użytkownikiem numer: " + (connectedClients.size() / 2) + "\n";
            ByteBuffer welcomeBuffer = ByteBuffer.wrap(welcomeMessage.getBytes(StandardCharsets.UTF_8));
            client.write(welcomeBuffer);
        }
    }

    private static void get(SelectionKey sk) throws IOException {
        SocketChannel sc = (SocketChannel) sk.channel();
        ByteBuffer bb = ByteBuffer.allocate(120);

        int read = sc.read(bb);
        if (read == -1) {
            pendingData.remove(sc);
            sc.close();
        } else if (read > 0) {
            bb.flip();
            String a = new String(bb.array()).trim();
            processClientMessage(sc, a);
        }
    }

    public static void sendToAll(String message) throws IOException {
        for (SocketChannel client : connectedClients) {
            ByteBuffer messageBuffer = ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8));
            client.write(messageBuffer);
            messageBuffer.clear();
        }
    }

    private static void processClientMessage(SocketChannel client, String message) throws IOException {
        if (message.equals("1")) {
            readyChecks.add(1);
            sendToClient(client, "Potwierdzam, jesteś gotowy do gry. \n");
            if(readyChecks.size() == connectedClients.size()/2){
                sendToAll("wszyscy gracze gotowi, gra rozpoczyna się \n");
                game = new Game();
                readyChecks.clear();
                game.GameLoop();
            }
        }
        if (message.equals("0")) {
            System.out.println("Koniec gry");
            sendToAll("Jeden z graczy nie chciał kontynuować rozgrywki, koniec zmagań.");
        }
        if (message.contains("[")){
            ArrayList<Integer> indices = new ArrayList<>();
            for (int i = 0; i < message.length(); i++) {
                if(Character.isDigit(message.charAt(i))){
                    indices.add(Character.getNumericValue(message.charAt(i)));
                }
            }
            game.exchange(client, indices);
        };

    }

    public static void sendToClient(SocketChannel client, String message) throws IOException {
        ByteBuffer messageBuffer = ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8));
        client.write(messageBuffer);
        messageBuffer.clear();
    }
}