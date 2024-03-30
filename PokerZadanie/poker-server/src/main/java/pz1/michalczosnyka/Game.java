package pz1.michalczosnyka;
import pz1.michalczosnyka.Deck;

import java.io.IOException;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static pz1.michalczosnyka.Main.*;

public class Game {
    // public void Betting(){}
    List<SocketChannel> playerClients;
    List<Player> players = new ArrayList<>();
    Deck deck;
    Player win;
    int q = 1;

    HashMap<Player, Checker> checkers = new HashMap<>();

    public Game(){
        deck = new Deck();
        playerClients = getConnectedClients();
        deck.shuffle();
    }
    public void GameLoop() throws IOException {
        for(int i = 0; i<playerClients.size(); i+=2){
            players.add(new Player());
        }
        distribute();
        // TEMP SENDER
        int j = 0;
        int k = 0;
        for(SocketChannel pc : playerClients){
            sendToClient(pc, players.get(j).handToString());
            checkers.put(players.get(j), new Checker(players.get(j)));
            checkers.get(players.get(j)).checkEngine();
            String s = "Twój aktualny układ to: " + players.get(j).comboToString() + ". wpisz numery kart, które chcesz wymieniać, '0', jeśli już nie chcesz wymieniać więcej kart: ";
            sendToClient(pc, s);
            k++;
            if(k%2==0){
                j++;
            }
        }
        sendToAll("\n" + "teraz kolej gracza: " + q + "\n");

    }

    void distribute(){
        for(int i=0; i<Deck.HAND_SIZE; i++){
            for (Player player : players) {
                player.addToHand(deck.getCard(0));
                deck.removetopCard();
            }
        }
    }

    void exchange(SocketChannel client, ArrayList<Integer> indices) throws IOException {
        int ind = playerClients.indexOf(client);
        if(ind%2 == 1) {
            Player player = players.get(ind/2);
            player.discard(indices);
            for (int i = 0; i < indices.size(); i++) {
                player.addToHand(deck.getCard(0));
                deck.removetopCard();
            }
            sendToClient(client, player.handToString());
            player.resetCombo();
            checkers.get(player).checkEngine();
            String s = "Twój aktualny układ to: " + player.comboToString();
            sendToClient(client, s);
        }
        q++;
        if(q==players.size()+1){
            results();
        }
        else{
            sendToAll("\n" + "Teraz kolej gracza: " + q + "\n");
        }


    }

    void results() throws IOException {
        Player winner = players.get(0);
        for(Player player : players){
            if(player.combo.getPower()>winner.combo.getPower()){
                winner = player;
            }
            else if(player.combo.getPower()==winner.combo.getPower()){
                if(player.score>winner.score){
                    winner = player;
                }
            }
        }
        win = winner;
        sendToAll("Wygrał gracz: " + (players.indexOf(winner)+1) + "\n" + "Czy chcesz rozpocząć kolejną rozgrywkę (wpisz '1')?\n");
    }

}

