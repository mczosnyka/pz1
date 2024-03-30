package pz1.michalczosnyka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class inGame {
    ClientManager cm;
    Integer player_number;


    inGame(ClientManager manager, Integer pn){
        cm = manager;
        player_number = pn;
    }

    public void start() throws IOException{
        String cards = cm.receiveMessage();
        System.out.println(cards);
        /* String combo = cm.receiveMessage();
        System.out.println(combo); */
        boolean going = true;
        String message = cards;
        while(going){
            if(message.contains(player_number.toString())){
                going = false;
            }
            else{
                message = cm.receiveMessage();
                System.out.println(message);
            }
        }
        ArrayList<Integer> to_discard = new ArrayList<>();
        boolean ender = false;
        while(to_discard.size() != 5 && !ender){
            int ni = cm.s.nextInt();
            if(ni==0){
                ender=true;
            }
            else if(ni>0 && ni<6){
                to_discard.add(ni);
            }
            else{
                System.out.println("NIE MASZ TYLU KART, PODAJ WLASCIWA LICZBE");
                int nni = cm.s.nextInt();
                while(nni<0 || nni>=6){
                    nni = cm.s.nextInt();
                }
                if(nni==0){
                    ender=true;
                }
                else{
                    to_discard.add(nni);
                }
            }
        }
        cm.sendMessageToServer(to_discard.toString());
        cards = cm.receiveMessage();
        System.out.println(cards);
        String combo = cm.receiveMessage();
        System.out.println(combo);
        boolean stopper = true;
        while(stopper){
            String mes = cm.receiveMessage();
            System.out.println(mes);
            if(mes.contains("wygra") || mes.contains("chcesz rozpocz")){
                stopper = false;
                waitForNextGame();
            }
        }

    }
    public void waitForNextGame() throws IOException {
        int a = cm.s.nextInt();
        while(a!=1 && a!=0){
            System.out.println("\n Potwierdź chęć zagrania kolejnej gry wpisując '1' lub chęć wyjścia wpisując '0'");
            a = cm.s.nextInt();
        }
        cm.sendMessageToServer(String.valueOf(a));
        String m = cm.receiveMessage();
        System.out.println(m);
        if(m.contains("nie")){
            System.out.println("jeden z graczy nie chciał kontynuować rozgrywki, koniec zmagań.");
        }
        else{
            m = cm.receiveMessage();
            System.out.println(m);
            if(m.contains("wszyscy gracze gotowi")){
                inGame newGame = new inGame(cm, player_number);
                newGame.start();
            }
        }

    }
}
