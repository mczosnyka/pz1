package pz1.michalczosnyka;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Joined {
    public static void start() throws IOException {
        ClientManager cm = new ClientManager();
        int player_number = 0;
        boolean starting = false;
        while(!starting){
            String message = cm.receiveMessage();
            System.out.println(message);
            if(player_number == 0){
                if(message.contains("numer:") || message.contains("graczy:")){
                    String substring = "numer: ";
                    int ind = message.indexOf(substring);
                    String extracted = message.substring(ind, ind + substring.length() + 1);
                    player_number = Character.getNumericValue(extracted.charAt(extracted.length()-1));
                }
            }
            if(message.contains("gotowo")){
                int a = cm.s.nextInt();
                while(a!=1){
                    System.out.println("Potwierdz gotowosc wpisujac '1'");
                    a = cm.s.nextInt();
                }
                    cm.sendMessageToServer(String.valueOf(a));
                    starting = true;
                    message = cm.receiveMessage();
                    System.out.println(message);
                    message = cm.receiveMessage();
                    System.out.println(message);
                    if(message.contains("liczba graczy")){
                        starting = false;
                }

            }
        }
        // System.out.println("koniec petli");
        inGame ig = new inGame(cm, player_number);
        ig.start();
        // PLACEHOLDER:
        // int n = s.nextInt();
    }


}
